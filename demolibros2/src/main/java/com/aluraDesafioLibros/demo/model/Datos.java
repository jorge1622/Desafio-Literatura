package com.aluraDesafioLibros.demo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Datos {
        @JsonAlias("results") List<DatosLibros> datosLibros;

        public List<DatosLibros> getDatosLibros() {
                return datosLibros;
        }

        public void setDatosLibros(List<DatosLibros> datosLibros) {
                this.datosLibros =datosLibros;
        }


}
