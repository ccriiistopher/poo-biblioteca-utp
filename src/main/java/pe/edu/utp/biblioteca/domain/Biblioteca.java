package pe.edu.utp.biblioteca.domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pe.edu.utp.biblioteca.model.Libro;
import pe.edu.utp.biblioteca.model.Prestamo;
import pe.edu.utp.biblioteca.model.Usuario;

public class Biblioteca {
    private static final ObservableList<Usuario> usuarios = FXCollections.observableArrayList();
    private static final ObservableList<Libro> libros = FXCollections.observableArrayList();
    private static final ObservableList<Prestamo> prestamos = FXCollections.observableArrayList();

    private Biblioteca() {
    }

    static ObservableList<Usuario> getUsuarios() {
        return usuarios;
    }

    static ObservableList<Libro> getLibros() {
        return libros;
    }

    static ObservableList<Prestamo> getPrestamos() {
        return prestamos;
    }

    static void registrarPrestamo(Prestamo prestamo) {
        prestamos.add(prestamo);
        int index = libros.indexOf(prestamo.getLibro());
        libros.remove(prestamo.getLibro());
        libros.add(index, prestamo.getLibro());
        Libros.seleccionarLibro(null);
        Libros.seleccionarLibro(prestamo.getLibro());
    }

    static void removerPrestamo(Libro libro) {
        prestamos.stream().filter(it -> it.getLibro().equals(libro)).findFirst().ifPresent(prestamos::remove);
        libro.setDisponibilidad(true);
        int index = libros.indexOf(libro);
        libros.remove(index);
        libros.add(index, libro);
        Libros.seleccionarLibro(null);
        Libros.seleccionarLibro(libro);

    }

    static void registrarLibro(Libro libro) {
        libros.add(libro);
    }

    static void removerLibro(Libro libro) {
        libros.remove(libro);
    }

    static void registrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    static void removerUsuario(Usuario usuario) {
        usuarios.remove(usuario);
    }

}
