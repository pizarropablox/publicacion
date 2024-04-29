package com.example.publicacion.Controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.test.web.servlet.MockMvc;

import com.example.publicacion.Controller.PublicacionController;
import com.example.publicacion.Model.PublicacionModel;
import com.example.publicacion.Service.PublicacionService;
import com.example.publicacion.Service.PublicacionServiceImpl;

@WebMvcTest(PublicacionController.class)
public class PublicacionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PublicacionService publicacionServiceMock;

    @Test
    public void obtenerTodosTest() throws Exception {
        PublicacionModel publicacion1 = new PublicacionModel();
        publicacion1.setTitulo("John");
        publicacion1.setComentario("que pasa loco jhon");
        publicacion1.setCalificacion("3");
        publicacion1.setId(1L);

        PublicacionModel publicacion2 = new PublicacionModel();
        publicacion2.setTitulo("Doe");
        publicacion1.setComentario("que pasa loco Doe");
        publicacion1.setCalificacion("4");
        publicacion2.setId(2L);
        List<PublicacionModel> publicaciones = List.of(publicacion1, publicacion2);

        List<EntityModel<PublicacionModel>> publicacionResources = publicaciones.stream()
            .map(publicacionModel -> EntityModel.of(publicacionModel))
            .collect(Collectors.toList());

        when(publicacionServiceMock.getAllPublicaciones()).thenReturn(publicaciones);

        mockMvc.perform(get("/publicaciones"))
                .andExpect(status().isOk())
                //En este caso, utilice la correspondencia directa de rutas JSON sin Matchers
                .andExpect(jsonPath("$._embedded.publicaciones.length()").value(2))
                .andExpect(jsonPath("$._embedded.publicaciones[0].titulo").value("John"))
                .andExpect(jsonPath("$._embedded.publicaciones[1].titulo").value("Doe"))
                .andExpect(jsonPath("$._embedded.publicaciones[0].Comentario").value("que pasa loco jhon")) 
                .andExpect(jsonPath("$._embedded.publicaciones[1].Comentario").value("que pasa loco Doe")) 
                .andExpect(jsonPath("$._embedded.publicaciones[0].Calificacion").value("3")) 
                .andExpect(jsonPath("$._embedded.publicaciones[1].Calificacion").value("3")) 
                .andExpect(jsonPath("$._embedded.publicaciones[0]._links.self.href").value("http://localhost:8080/publicaciones/1"))
                .andExpect(jsonPath("$._embedded.publicaciones[1]._links.self.href").value("http://localhost:8080/publicaciones/2"));
    }
}

