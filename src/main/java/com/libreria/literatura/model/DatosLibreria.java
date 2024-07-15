package com.libreria.literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibreria(@JsonAlias("count") String cantidadDeLibros,
                            @JsonAlias("results") List<DatosLibro> libros) {
}
