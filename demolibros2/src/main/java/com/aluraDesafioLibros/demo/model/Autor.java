package com.aluraDesafioLibros.demo.model;

import com.aluraDesafioLibros.demo.DTO.AutorDTO;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "autores")
   public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
       @Column(unique=true)
        @JsonAlias("name")
        private String nombreAutor;

        @JsonAlias("birth_year")
        private Integer añoNacimiento;

        @JsonAlias("death_year")
        private Integer añoFallecimiento;
        @ManyToMany
        private List<DatosLibros> libros;


    public Autor() {

    }

    public Autor(Long id, String nombreAutor, Integer añoNacimiento, Integer añoFallecimiento, List<DatosLibros> libros) {
        this.id = id;
        this.nombreAutor = nombreAutor;
        this.añoNacimiento = añoNacimiento;
        this.añoFallecimiento = añoFallecimiento;
        this.libros = libros;
    }



    public List<DatosLibros> getLibros() {
        return libros;
    }

    public void setDatosLibros(List<DatosLibros> datosLibros) {
        this.libros = libros;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
        public String getNombreAutor() {
            return nombreAutor;
        }

        public void setNombreAutor(String nombreAutor) {
            this.nombreAutor = nombreAutor;
        }

        public Integer getAñoNacimiento() {
            return añoNacimiento;
        }

        public void setAñoNacimiento(Integer añoNacimiento) {
            this.añoNacimiento = añoNacimiento;
        }

        public Integer getAñoFallecimiento() {
            return añoFallecimiento;
        }

        public void setAñoFallecimiento(Integer añoFallecimiento) {
            this.añoFallecimiento = añoFallecimiento;
        }

public AutorDTO toDTO() {
            return new AutorDTO(nombreAutor);
}

        @Override
        public String toString() {
            return
                    "Autor='" + nombreAutor +
                    ", añoNacimiento='" + añoNacimiento +
                    ", añoFallecimiento='" + añoFallecimiento  +
                    '}';
        }


}


