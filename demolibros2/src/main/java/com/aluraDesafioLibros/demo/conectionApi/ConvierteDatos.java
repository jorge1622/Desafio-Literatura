package com.aluraDesafioLibros.demo.conectionApi;

import com.aluraDesafioLibros.demo.pricipal.Principal;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class ConvierteDatos implements IconvierteDatos{
    private ObjectMapper mapper = new ObjectMapper();
    @Override
    public <T> T obtenerDatos(String json , Class<T>close){
        try {
            return mapper.readValue(json.toString(), close);
        }catch (JsonProcessingException e ){
            throw new RuntimeException(e);
        }

    }

}


