package com.example.publicacion.Repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.publicacion.Model.PublicacionModel;


@DataJpaTest
//Esto me sirve para la configuracion de la base de datos
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PublicacionRepositoryTest {
    @Autowired
    //private PublicacionRepository publicacionRepository;
    private PublicacionRepository publicacionRepository;

    @Test
    public void guardarPublicacionTest() {
        PublicacionModel publicacionModel = new PublicacionModel();
        publicacionModel.setTitulo("John Doe");
        publicacionModel.setComentario("Buen nombre");
        publicacionModel.setCalificacion("6");

        PublicacionModel resultado = publicacionRepository.save(publicacionModel);

        assertNotNull(resultado.getId());
        assertEquals("John Doe", resultado.getTitulo());
        assertEquals("Buen nombre", resultado.getComentario());
        assertEquals("6", resultado.getCalificacion());
        
    }
}
