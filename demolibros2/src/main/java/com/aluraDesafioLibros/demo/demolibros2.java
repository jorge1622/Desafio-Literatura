package com.aluraDesafioLibros.demo;

import com.aluraDesafioLibros.demo.model.DatosLibros;
import com.aluraDesafioLibros.demo.pricipal.Principal;
import com.aluraDesafioLibros.demo.service.DatosLibrosService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.naming.Context;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@SpringBootApplication
public class demolibros2  implements CommandLineRunner {
    private final DatosLibrosService datosLibrosService;

    public demolibros2(DatosLibrosService datosLibrosService) {
        this.datosLibrosService = datosLibrosService;
    }

    public static void main(String[] args) {SpringApplication.run(demolibros2.class, args);

       }
       @Override
    public void run(String... args) throws Exception {

           Principal principal = new Principal(datosLibrosService);
           principal.muestraMenu();

    }
}











