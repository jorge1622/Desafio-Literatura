package com.aluraDesafioLibros.demo.repository;

import com.aluraDesafioLibros.demo.model.Autor;
import com.aluraDesafioLibros.demo.model.DatosLibros;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    Optional<Autor> findByNombreAutor(String nombreAutor);


}
