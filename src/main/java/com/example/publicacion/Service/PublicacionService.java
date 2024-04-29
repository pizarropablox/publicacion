package com.example.publicacion.Service;

import com.example.publicacion.Model.PublicacionModel;
import java.util.List;
import java.util.Optional;

public interface PublicacionService {
    //esto es lo que vamos a implementar en los endpotin traemos una lista de estudiantes
    List<PublicacionModel> getAllPublicaciones();
    //traemos un estudiante por ID
    Optional<PublicacionModel> getPublicacionById(Long id);
    
    // Metodos para CREAR 
    PublicacionModel createPublicacion(PublicacionModel publicacionModel);
    
    //Metodo para ACTUALIZAR 
    PublicacionModel updatePublicacion(Long id, PublicacionModel publicacionModel);

    //Metodo para ELIMINAR
    void deletePublicacion(Long id);
    
}