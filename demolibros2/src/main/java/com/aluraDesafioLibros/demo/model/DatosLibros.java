package com.aluraDesafioLibros.demo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name= "libros")
public class   DatosLibros{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
        @Column(unique=true)
        @JsonAlias("title") String titulo;


    @ManyToMany
    @JsonAlias("authors")
    private List<Autor> autor;


    //@ElementCollection
 @CollectionTable
@JsonAlias("languages")
    private List<String> idiomas;



    @JsonAlias("download_count") Double numeroDeDescargas;

    public DatosLibros() {

    }

    public DatosLibros(Long id, String titulo, List<Autor> autor, List<String> idiomas, Double numeroDeDescargas) {
       this. id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.idiomas = idiomas;
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public String getTitulo() {
        return titulo;
    }


    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

  public List<Autor> getAutor() {
      return autor;
  }
  public void setAutor(List<Autor> autor) {
       this.autor = autor;
   }


    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
    }


    @Override
    public String toString() {
        return "Libro{" +
                "titulo " + titulo  +
                ", Autor=" + autor +
                ", idiomas=" + idiomas +
                ", Numero De Descargas=" + numeroDeDescargas +
                '}';
    }





}
