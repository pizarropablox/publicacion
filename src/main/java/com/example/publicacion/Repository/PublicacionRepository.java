package com.example.publicacion.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.publicacion.Model.PublicacionModel;

@Repository
public interface PublicacionRepository extends JpaRepository<PublicacionModel, Long>{
    //
}
