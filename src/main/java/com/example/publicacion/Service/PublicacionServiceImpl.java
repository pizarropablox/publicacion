package com.example.publicacion.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.publicacion.Model.PublicacionModel;
import com.example.publicacion.Service.PublicacionService;
import com.example.publicacion.Repository.PublicacionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PublicacionServiceImpl implements PublicacionService {
   
    //hace las conexiones entre la interfase y la implementacion(internamente hace un constructor)
    @Autowired
    private PublicacionRepository publicacionRepository;

    // Es para asegurarnos de que estamos reemplazando correctamente los métodos heredados en nuestras clases de Spring Boot
    @Override
    //Define un método público llamado getAllPublicaciones()
    public List<PublicacionModel> getAllPublicaciones() {
        return publicacionRepository.findAll();
    }

    @Override
    //codigo para listar estudiantes por ID
    public Optional<PublicacionModel> getPublicacionById(Long id) {
        //Aquí, estamos devolviendo el resultado de la llamada al método findAll()
        return publicacionRepository.findById(id);
    }

    //Metodo para crear un estudiante
    @Override
    public PublicacionModel createPublicacion(PublicacionModel publicacionModel){
        return publicacionRepository.save(publicacionModel);
    }

    //Metodo para Actualizar estudiante
    @Override
    public PublicacionModel updatePublicacion(Long id, PublicacionModel publicacionModel){
        if(publicacionRepository.existsById(id)){
            publicacionModel.setId(id);
            return publicacionRepository.save(publicacionModel);
        }else{
            return null;
        }
    }

    //Metodo para eliminar estudiantes
    @Override
    public void deletePublicacion(Long id){
        publicacionRepository.deleteById(id);
    }
    
}
