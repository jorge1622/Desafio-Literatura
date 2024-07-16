package com.aluraDesafioLibros.demo.service;

import com.aluraDesafioLibros.demo.model.Autor;
import com.aluraDesafioLibros.demo.model.DatosLibros;
import com.aluraDesafioLibros.demo.model.Idiomas;
import com.aluraDesafioLibros.demo.repository.AutorRepository;
import com.aluraDesafioLibros.demo.repository.DatosLibrosRepository;

//import com.aluraDesafioLibros.demo.repository.IdiomasRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DatosLibrosService {
    private final  AutorRepository autorRepository;
    private DatosLibrosRepository datosLibrosRepository;
   // private final IdiomasRepository idiomasRepository;

    @Autowired
    public DatosLibrosService(AutorRepository autorRepository, DatosLibrosRepository datosLibrosRepository
    ) {
        this.autorRepository = autorRepository;
        this.datosLibrosRepository = datosLibrosRepository;
      //  this.idiomasRepository = idiomasRepository;
    }

    @Transactional
    public void guardarLibroConAutor(DatosLibros datosLibros) {
        Optional<DatosLibros> libroExistenete = datosLibrosRepository.findByTitulo(datosLibros.getTitulo());
        if (libroExistenete.isPresent()) {
            System.out.println("El libno ya exixste en la base de Datos");
            return;
            
        }



        Autor autor = autorRepository.findByNombreAutor(datosLibros.getAutor().get(0).getNombreAutor())
                        .orElseGet(() ->{
                            Autor autores = new Autor();
                            autores.setNombreAutor(datosLibros.getAutor().get(0).getNombreAutor());
                            autores.setAñoNacimiento(datosLibros.getAutor().get(0).getAñoNacimiento());
                            autores.setAñoFallecimiento(datosLibros.getAutor().get(0).getAñoFallecimiento());
                             return autorRepository.save(autores);
                        });




        datosLibros.setAutor(List.of(autor));
        datosLibrosRepository.save(datosLibros);
  }

    public List<DatosLibros> obtenerAutorVvioEnAñoDterminado(int ano) {
        List<DatosLibros> libros = datosLibrosRepository.findAll();
        return libros.stream()
                .filter(libro -> libro.getAutor().stream()
                        .anyMatch(autor -> (autor.getAñoNacimiento() <= ano && (autor.getAñoFallecimiento() == null || autor.getAñoFallecimiento() >= ano))))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<DatosLibros> obtenerLibrosRegistrados(){
        return datosLibrosRepository.findAll();
    }
    public List<DatosLibros> getLibrosByIdioma(String idioma) {
        return datosLibrosRepository.findLibrosByIdioma(Collections.singletonList(idioma));


        }





        }





