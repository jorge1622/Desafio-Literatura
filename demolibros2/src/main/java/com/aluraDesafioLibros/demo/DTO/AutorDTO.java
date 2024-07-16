package com.aluraDesafioLibros.demo.DTO;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AutorDTO {
@JsonAlias("name")
private String nombreAutor;
public AutorDTO(String nombreAutor) {
    this.nombreAutor = nombreAutor;
}

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    @Override
    public String toString() {
        return "AutorDTO" +
                "nombre Autor='" + nombreAutor ;
    }
}
