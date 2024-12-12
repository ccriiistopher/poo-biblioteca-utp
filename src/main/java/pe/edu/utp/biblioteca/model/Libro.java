package pe.edu.utp.biblioteca.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Libro {
    private String titulo, autor, genero, isbn, picture;
    private final BooleanProperty disponibilidad = new SimpleBooleanProperty();

    public Libro(String titulo, String autor, String genero, String isbn, String picture) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.isbn = isbn;
        this.picture = picture;
        setDisponibilidad(true);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public BooleanProperty disponibilidadProperty() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        System.out.println("cambiando disponibilidad");
        this.disponibilidad.setValue(disponibilidad);
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

}
