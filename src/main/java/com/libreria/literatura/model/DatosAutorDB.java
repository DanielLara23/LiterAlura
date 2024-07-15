package com.libreria.literatura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name = "autor")
public class DatosAutorDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;
    private Integer fechaDeNacimiento;
    private Integer fechaDeMuerte;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch =FetchType.EAGER)
    private List<DatosLibrosDB> libros;

    public DatosAutorDB(){
    }

    public DatosAutorDB(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        this.fechaDeNacimiento = datosAutor.fechaDeNacimiento();
        this.fechaDeMuerte = datosAutor.fechaDeMuerte();
    }

    @Override
    public String toString() {

        StringBuilder librosStr = new StringBuilder();
        librosStr.append("Libros: ");

        for(int i = 0; i < libros.size() ; i++) {
            librosStr.append(libros.get(i).getTitulo());
            if (i < libros.size() - 1 ){
                librosStr.append(", ");
            }
        }
        return String.format("********** Autor **********%nNombre:" +
                " %s%n%s%nFecha de Nacimiento: %s%nFecha de Fallecimiento:" +
                " %s%n***************************%n",nombre,librosStr.toString(),fechaDeNacimiento,fechaDeMuerte);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Integer fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public Integer getFechaDeMuerte() {
        return fechaDeMuerte;
    }

    public void setFechaDeMuerte(Integer fechaDeMuerte) {
        this.fechaDeMuerte = fechaDeMuerte;
    }

    public List<DatosLibrosDB> getLibros() {
        return libros;
    }

    public void setLibros(List<DatosLibrosDB> libros) {
        this.libros = libros;
    }
}