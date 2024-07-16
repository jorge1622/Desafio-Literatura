package com.aluraDesafioLibros.demo.repository;

import com.aluraDesafioLibros.demo.model.DatosLibros;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DatosLibrosRepository extends JpaRepository<DatosLibros, Long >{


@EntityGraph(attributePaths = { "autor" })
 List<DatosLibros> findAll();

    Optional<DatosLibros> findByTitulo(String titulo);


    @Query("SELECT dl FROM DatosLibros dl JOIN dl.idiomas i WHERE i = :idioma")
    List<DatosLibros> findLibrosByIdioma(@Param("idioma") List<String> idioma);


}

