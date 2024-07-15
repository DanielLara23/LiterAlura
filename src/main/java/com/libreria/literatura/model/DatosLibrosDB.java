package com.libreria.literatura.model;


import jakarta.persistence.*;

@Entity
@Table(name="libros")
public class DatosLibrosDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "autor_id")
    private DatosAutorDB autor;
    @Enumerated(EnumType.STRING)
    private Idioma idioma;
    private Integer numeroDeDescargas;

    public DatosLibrosDB(DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();
        this.idioma = Idioma.valueOf(datosLibro.idiomas().get(0));
        this.numeroDeDescargas = datosLibro.descargas();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("*********** Libro ***********\n");
        sb.append("Título: ").append(titulo).append("\n");
        sb.append("Autor: ").append(autor != null ? autor.getNombre() : "No hay datos del autor").append("\n");
        sb.append("Idioma: ").append(idioma).append("\n");
        sb.append("Número de descargas: ").append(numeroDeDescargas).append("\n");
        sb.append("****************************\n");
        return sb.toString();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public Integer getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Integer numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public DatosAutorDB getAutor() {
        return getAutor();
    }

    public void setAutor(DatosAutorDB autor) {
        this.autor = autor;
    }
}
