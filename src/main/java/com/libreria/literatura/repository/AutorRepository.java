package com.libreria.literatura.repository;

import com.libreria.literatura.model.DatosAutorDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<DatosAutorDB,Long>{
    Optional<DatosAutorDB> findByNombre(String nombre);

    @Query("SELECT a FROM DatosAutorDB a WHERE a.fechaDeNacimiento <= :anio AND a.fechaDeMuerte >= :anio")
    List<DatosAutorDB> listaAutoresVivos(Integer anio);

}
