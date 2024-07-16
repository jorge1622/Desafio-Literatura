package com.aluraDesafioLibros.demo.controler;

import com.aluraDesafioLibros.demo.model.DatosLibros;
import com.aluraDesafioLibros.demo.service.DatosLibrosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class DatosLIbroControler {
    @Autowired
    private DatosLibrosService datosLibrosService;

    @GetMapping("/libros/idioma")
    public List<DatosLibros> getLibrosByIdioma(@RequestParam List<String> idioma) {
        return datosLibrosService.getLibrosByIdioma(String.valueOf(idioma));
    }
}
