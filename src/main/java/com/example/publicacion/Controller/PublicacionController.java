package com.example.publicacion.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.publicacion.Model.PublicacionModel;
import com.example.publicacion.Service.PublicacionService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//tt

@RestController
@RequestMapping("/publicaciones")
public class PublicacionController {


    private static final Logger log = LoggerFactory.getLogger(PublicacionController.class);

    @Autowired
    private PublicacionService publicacionService;


    //LOGGIN PARA LAS PUBLICACIONES
    @GetMapping
    public List<PublicacionModel> getAllPublicaciones(){
        log.info("Get /publicacion");
        log.info("Retornado todas las publicaciones");
        return publicacionService.getAllPublicaciones();
    }

    //BUSCAR USUARIO POR ID (VALIDADO) - SI NO LO ENCUENTRA ARROJA UN MENSAJE EN EL BODY CON YUN JSON
    @GetMapping("/{id}")
    public ResponseEntity<Object> getPublicacionById(@PathVariable Long id) {  
        Optional<PublicacionModel> publicacionModel = publicacionService.getPublicacionById(id);
        if (publicacionModel.isEmpty()) {
            log.error("No se encontro la publicacion", id);    
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontro publicacion con ID "+ id));
        }
        log.info("Estudiante encontrado con exito");
        return ResponseEntity.ok(publicacionService.getPublicacionById(id));
        //return ResponseEntity.ok(publicacionModel);
    } 


    //CREAR PUBLICACIONES (VALIDADO)
    @PostMapping
    public ResponseEntity<Object> createPublicacion(@RequestBody PublicacionModel publicacionModel) {
        PublicacionModel createPublicacion = publicacionService.createPublicacion(publicacionModel);
        //si el estudiante esta creado entonces es nulo
        if (createPublicacion == null){
            //logeo para ingresar a ver los datos
            log.error("Error al crear la publicacion", publicacionModel);
            //retorno un error Rad_Request
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("Error al crear la publicacion"));
        }
        return ResponseEntity.ok(createPublicacion);
                
    } 
    
    //CLASE ESTATICA PARA QUE FUNCIONES EL ERROR RESPONSE
    static class ErrorResponse{
        private final String message;

        public ErrorResponse(String message){
            this.message = message;
        }

        public String getMessage(){
            return message;
        }
    }

    //METODO ACTUALIZAR
    @PutMapping("/{id}")
    public PublicacionModel updatePublicacion(@PathVariable Long id, @RequestBody PublicacionModel publicacionModel){
        return publicacionService.updatePublicacion(id, publicacionModel);
    }

    //METODO ELIMINAR POR ID (VALIDADO)
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePublicacion(@PathVariable Long id) {
    // Busca la publicación por su ID utilizando el servicio publicacionService.
    Optional<PublicacionModel> publicacionModel = publicacionService.getPublicacionById(id);
    if (publicacionModel.isEmpty()) {
        // Si no se encuentra la publicación, devuelve una respuesta de "no encontrado".
        log.error("No se encontró la publicación con ID {}", id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ErrorResponse("No se encontró publicación con ID " + id));
    }

        // Elimina la publicación
        publicacionService.deletePublicacion(id);

        // Devuelve una respuesta exitosa
        return ResponseEntity.ok("Publicación eliminada con éxito");
    }

    
    
}

