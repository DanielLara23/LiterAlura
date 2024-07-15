package com.libreria.literatura.repository;

import com.libreria.literatura.model.DatosLibrosDB;
import com.libreria.literatura.model.Idioma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<DatosLibrosDB, Long> {

    List<DatosLibrosDB> findByIdioma(Idioma idioma);

    Optional<DatosLibrosDB> findByTitulo(String titulo);

}
