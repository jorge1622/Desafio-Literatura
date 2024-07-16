package com.aluraDesafioLibros.demo.pricipal;

import com.aluraDesafioLibros.demo.DTO.DatosLibrosDTO;
import com.aluraDesafioLibros.demo.conectionApi.ConnectionAPI;
import com.aluraDesafioLibros.demo.conectionApi.ConvierteDatos;
import com.aluraDesafioLibros.demo.model.Autor;
import com.aluraDesafioLibros.demo.model.Datos;
import com.aluraDesafioLibros.demo.model.DatosLibros;
import com.aluraDesafioLibros.demo.model.Idiomas;
import com.aluraDesafioLibros.demo.service.DatosLibrosService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private  static final String URL_BASE = "https://gutendex.com/books/";
    private final ConnectionAPI consumoAPI = new ConnectionAPI();
    private final ConvierteDatos conversor = new ConvierteDatos();



private DatosLibrosService datosLibrosService;
    private List<DatosLibros> datosLibros;

    public Principal(DatosLibrosService datosLibrosService) {
        this.datosLibrosService = datosLibrosService;
    }


    public void muestraMenu() throws IOException, InterruptedException {

        var json = consumoAPI.obtenerDatos(URL_BASE);
       // System.out.println(json);
      var datos = conversor.obtenerDatos(json, Datos.class);
       // System.out.println(datos);
       // this.datosLibros = datos.getDatosLibros();
        if (datos != null && datos.getDatosLibros() != null) {
            this.datosLibros = datos.getDatosLibros();
        } else {
            System.out.println("No se pudieron obtener los datos de libros.");
            return;
        }



        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libro por titulo  
                    2 - Listar de libros registrados 
                    3 - Lista autores registrados
                    4 - lista de autores vivos en determinado año
                    5 - Lista de libro por idiomas
                    0 - Salir
                   """;


            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                   listaDeLibrosRegristrados();
                    break;
                case 3:
                    listaDeAutoresRegistrados();
                    break;
                case 4:
                   listaDeAutoresVivosEnDeterminadosAño();
                    break;
                case 5:
                    buscarPorIdioma();

                    break;
                case 0:
                    System.out.println("Saliste de la App");

                default:
                    System.out.println("Opción inválida");
            }
        }

    }




    private void buscarLibroPorTitulo() throws IOException, InterruptedException {
        System.out.println("Ingresa el nombre del Libro que desea buscar ");
        var tituloLibro = teclado.nextLine();
        String json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
        Optional<DatosLibros> libroBuscado = datosBusqueda.getDatosLibros().stream()
                .filter(l -> l.getTitulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();
        if(libroBuscado.isPresent()){
DatosLibros libro = libroBuscado.get();
            System.out.println("--------Libro---------- ");
            System.out.println("Titulo: " + libro.getTitulo());
           libro.getAutor().forEach(autor -> System.out.println("Autor: " + autor.getNombreAutor()));
            System.out.println("Idioma: " + libro.getIdiomas());
            System.out.println( "Numero de descargas: " + libro.getNumeroDeDescargas());

            datosLibrosService.guardarLibroConAutor(libro);

            System.out.println("datos guardados en la base de datos");
        }else {
            System.out.println("Libro no encontrado");
        }
    }

private void listaDeLibrosRegristrados() {
    List<DatosLibros> libros = datosLibrosService.obtenerLibrosRegistrados();
    if (libros.isEmpty()) {
        System.out.println("No hay libros registrados");
    } else {
        System.out.println("-------Libros registrados-------");
        for (DatosLibros libro : libros) {
            System.out.println("Título: " + libro.getTitulo());

            System.out.print("Autor: ");
            List<Autor> autores = libro.getAutor();
            for (int i = 0; i < autores.size(); i++) {
                if (i > 0) {
                    System.out.print(", ");
                }
                System.out.print(autores.get(i).getNombreAutor());
            }
            System.out.println(); // Salto de línea después de los autores

//
            System.out.println("idioma: " + libro.getIdiomas());
            System.out.println("Número de descargas: " + libro.getNumeroDeDescargas());
            System.out.println("-------Libro--------");
        }
    }
}
    private void listaDeAutoresRegistrados() {
        List<DatosLibros> libros = datosLibrosService.obtenerLibrosRegistrados();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados");
        }else {
            System.out.println("-------Autores registrados-------");
            for (DatosLibros libro : libros) {
                List<Autor> autores = libro.getAutor();
                for (int i = 0; i < autores.size(); i++) {
                    if (i > 0) {
                        System.out.print(", ");
                    }
                    System.out.print("Autor: " + autores.get(i).getNombreAutor());
                    System.out.println(" .");
                    System.out.println("Año de nacicmiento: " +autores.get(i).getAñoNacimiento());
                    System.out.println("Año de Fallecimiento: " + autores.get(i).getAñoFallecimiento());
                }
                System.out.println("Título: " + libro.getTitulo());
                System.out.println("____________");
        }


        }


        }


    private void listaDeAutoresVivosEnDeterminadosAño() {
        System.out.println("Ingrese el año en el que desea buscar autores vivos:");
        int ano = teclado.nextInt();
        teclado.nextLine();  // Limpiar el buffer

        List<DatosLibros> libros = datosLibrosService.obtenerAutorVvioEnAñoDterminado(ano);
        if (libros.isEmpty()) {
            System.out.println("No hay autores vivos en el año " + ano);
        } else {
            System.out.println("-------Autores vivos en el año " + ano + " -------");
            for (DatosLibros libro : libros) {
                List<Autor> autores = libro.getAutor();
                for (Autor autor : autores) {
                    if (autor.getAñoNacimiento() <= ano && (autor.getAñoFallecimiento() == null || autor.getAñoFallecimiento() >= ano)) {
                        System.out.println("Autor: " + autor.getNombreAutor());
                        System.out.println("Año de nacimiento: " + autor.getAñoNacimiento());
                        System.out.println("Año de fallecimiento: " + (autor.getAñoFallecimiento() != null ? autor.getAñoFallecimiento() : "N/A"));
                        System.out.println("Título: " + libro.getTitulo());
                        System.out.println("____________");
                    }
                }
            }
        }
    }



    private void buscarPorIdioma() {
//        System.out.println("Ingrese el idioma para buscar los libros ");
//        System.out.println("es-español ");
//        System.out.println("en-ingles ");
//        System.out.println("ft-frances ");
//        System.out.println("pt-portugues ");
//        String idioma = teclado.nextLine().trim();
//
//        List<DatosLibros> libros = datosLibrosService.getLibrosByIdioma(idioma);
//        if (libros.isEmpty()) {
//            System.out.println("No se encontraron libros para el idioma especificado.");
//        } else {
//            System.out.println("-------Libros en " + idioma + "-------");
//            for (DatosLibros libro : libros) {
//                System.out.println("Título: " + libro.getTitulo());
//
//                System.out.print("Autor: ");
//                List<Autor> autores = libro.getAutor();
//                for (int i = 0; i < autores.size(); i++) {
//                    if (i > 0) {
//                        System.out.print(", ");
//                    }
//                    System.out.print(autores.get(i).getNombreAutor());
//                }
//                System.out.println(); // Salto de línea después de los autores
//
//                System.out.println("Idioma: " + libro.getIdiomas());
//                System.out.println("Número de descargas: " + libro.getNumeroDeDescargas());
//                System.out.println("-------Libro--------");
//            }
       // }
        System.out.println("Ingrese el idioma para buscar los libros ");
        System.out.println("es-español ");
        System.out.println("en-ingles ");
        System.out.println("ft-frances ");
        System.out.println("pt-portugues ");
        String idioma = teclado.nextLine();

        List<DatosLibros> libros = datosLibrosService.getLibrosByIdioma(idioma);
        if (libros.isEmpty()) {
            System.out.println("No hay libros disponibles en " + idioma);
        } else {
            System.out.println("-------Libros en " + idioma + "-------");
            for (DatosLibros libro : libros) {
                System.out.println("Título: " + libro.getTitulo());
                System.out.print("Autor: ");
                libro.getAutor().forEach(autor -> System.out.print(autor.getNombreAutor() + ", "));
                System.out.println();
                System.out.println("Número de descargas: " + libro.getNumeroDeDescargas());
                System.out.println("-------");
            }
        }

    }


}






