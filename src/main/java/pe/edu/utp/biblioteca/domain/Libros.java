package pe.edu.utp.biblioteca.domain;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import pe.edu.utp.biblioteca.model.Libro;

public class Libros {
    private static ObjectProperty<Libro> libro = new SimpleObjectProperty<>();

    public static ObservableList<Libro> getLibros() {
        return Biblioteca.getLibros();
    }

    public static void registrarLibro(Libro libro) {
        Biblioteca.registrarLibro(libro);
    }

    public static ObjectProperty<Libro> getLibro() {
        return libro;
    }

    public static void seleccionarLibro(Libro libro) {
        Libros.libro.set(libro);
    }
}
