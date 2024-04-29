package com.example.publicacion.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.publicacion.Model.PublicacionModel;
import com.example.publicacion.Repository.PublicacionRepository;
import com.example.publicacion.Service.PublicacionServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PublicacionServiceTest {
    @InjectMocks
    private PublicacionServiceImpl publicacionServicio;

    @Mock
    private PublicacionRepository publicacionRepositoryMock;

    @Test
    public void guardarPublicacionTest() {

        PublicacionModel publicacionModel = new PublicacionModel();
        publicacionModel.setTitulo("Jose Rondon");
        publicacionModel.setComentario("Excelente nombre");
        publicacionModel.setCalificacion("7");

        when(publicacionRepositoryMock.save(any())).thenReturn(publicacionModel);

        PublicacionModel resultado = publicacionServicio.createPublicacion(publicacionModel);

        assertEquals("Jose Rondon", resultado.getTitulo());
    }
}
