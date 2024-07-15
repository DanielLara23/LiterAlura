package com.libreria.literatura.principal;
import com.libreria.literatura.model.*;
import com.libreria.literatura.repository.AutorRepository;
import com.libreria.literatura.repository.LibroRepository;
import com.libreria.literatura.service.ConsumoAPI;
import com.libreria.literatura.service.DataConvert;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;


public class Menu {
    private Scanner entradaDeUsuario = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private String URL_BASE = "https://gutendex.com/books/";
    private String COMPLEMENTOS_URL_BUSQUEDA_LIBROS = "?search=";
    private DataConvert conversor = new DataConvert();
    private List<DatosLibro> almacenDeLibros = new ArrayList<>();
    String titulo;
    private List<DatosAutorDB> autores;
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;

    @Autowired
    public Menu(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    private int opcion = -1;

    public void muestraMenu() {
        while (opcion != 9) {
            System.out.println("""
                Elija la opción a través de su número:
                1- Buscar libro por título
                2- Listar libros registrados
                3- Listar autores registrados
                4- Listar autores vivos en un determinado año
                5- Listar libros por idioma
                6- Buscar autor por nombre
                7- Salir
                """);

            try {
                opcion = Integer.parseInt(entradaDeUsuario.nextLine());
                switch (opcion) {
                    case 1:
                        buscarLibroPorTitulo();
                        break;
                    case 2:
                        mostrarLibrosGuardados();
                        break;
                    case 3:
                        listarAutores();
                        break;
                    case 4:
                        listarAutoresVivos();
                        break;
                    case 5:
                        listarLibrosPorIdioma();
                        break;
                    case 6:
                        buscarAutorPorNombre();
                        break;
                    case 7:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción no válida!");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
            }
        }
    }



    private void buscarLibroPorTitulo() {
        System.out.println("Introduzca el título del libro que desea buscar:");
        titulo = entradaDeUsuario.nextLine();
        DatosLibro libro = obtenerDatos();
        if (libro != null) {
            System.out.println(libro);
            almacenarLibros(libro);
        } else {
            System.out.println("El libro no ha podido ser encontrado.");
        }
    }

    private DatosLibro obtenerDatos() {
        String json = consumoAPI.obtenerDatos(URL_BASE + COMPLEMENTOS_URL_BUSQUEDA_LIBROS + titulo.toLowerCase().replace(" ", "+"));
        List<DatosLibro> datos = conversor.obtenerDatos(json, DatosLibreria.class).libros();
        if (datos.isEmpty()) {
            return null; // Si la lista está vacía, devuelve null
        } else {
            return datos.get(0); // Si no está vacía, devuelve el primer elemento
        }
    }

    private void almacenarLibros(DatosLibro libro) {
        almacenDeLibros.add(libro);
    }

    private void mostrarLibrosGuardados() {
        almacenDeLibros.forEach(System.out::println);
    }

    private void listarAutores() {
        autores = autorRepository.findAll();
        autores.forEach(System.out::println);
    }

    private void listarAutoresVivos() {
        System.out.println("Ingrese el año para buscar autores vivos:");
        int anio = Integer.parseInt(entradaDeUsuario.nextLine());
        List<DatosAutorDB> autoresVivos = autorRepository.listaAutoresVivos(anio);
        autoresVivos.forEach(System.out::println);
    }

    private void listarLibrosPorIdioma() {
        System.out.println("Ingrese el idioma para buscar los libros (ES, EN, FR):");
        String idiomaInput = entradaDeUsuario.nextLine();
        Idioma idioma = Idioma.fromString(idiomaInput);
        List<DatosLibrosDB> librosPorIdioma = libroRepository.findByIdioma(idioma);
        librosPorIdioma.forEach(System.out::println);
    }

    private void buscarAutorPorNombre() {
        System.out.println("Ingrese el nombre del autor que desea buscar:");
        String nombre = entradaDeUsuario.nextLine();
        Optional<DatosAutorDB> autor = autorRepository.findByNombre(nombre);
        autor.ifPresentOrElse(System.out::println, () -> System.out.println("Autor no encontrado."));
    }
}