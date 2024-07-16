package com.aluraDesafioLibros.demo.DTO;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DatosLibrosDTO {
    @JsonAlias("title")
    private String titulo;
    @JsonAlias("authors")
    private List<AutorDTO>autor;
    @JsonAlias("languages")
    private List<IdiomaDTO>idiomas;
    @JsonAlias("download_count")
    private Double numeroDeDescargas;

    public DatosLibrosDTO(String titulo, List<AutorDTO> autor, List<IdiomaDTO> idiomas,
                          Double numeroDeDescargas) {

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<AutorDTO> getAutor() {
        return autor;
    }

    public void setAutor(List<AutorDTO> autor) {
        this.autor = autor;
    }

    public List<IdiomaDTO> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<IdiomaDTO> idiomas) {
        this.idiomas = idiomas;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    @Override
    public String toString() {
        return
                "titulo='" + titulo  +
                ", autor=" + autor +
                ", idiomas=" + idiomas +
                ", numero De Descargas=" + numeroDeDescargas;
    }


    
}


