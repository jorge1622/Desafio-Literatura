package com.aluraDesafioLibros.demo.model;

import com.aluraDesafioLibros.demo.DTO.IdiomaDTO;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)

public class Idiomas{
    @JsonAlias("languages")
 String idiomas;



    public String getIdiomas() {
                return idiomas;
        }



    public void setIdiomas(String idiomas) {
                this.idiomas = idiomas;
        }

        public IdiomaDTO toDTO() {
                return new IdiomaDTO(idiomas);
        }


    @Override
        public String toString() {
                return "Idiomas{" +
                        "Idioma='" + idiomas + '\'' +
                        '}';
        }

}
