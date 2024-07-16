package com.aluraDesafioLibros.demo.conectionApi;

public interface IconvierteDatos {
    <T> T obtenerDatos(String JSON,  Class<T> classe);

}
