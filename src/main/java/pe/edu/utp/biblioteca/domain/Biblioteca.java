package pe.edu.utp.biblioteca.domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import pe.edu.utp.biblioteca.model.Libro;
import pe.edu.utp.biblioteca.model.Prestamo;
import pe.edu.utp.biblioteca.model.Usuario;

public class Biblioteca {
    private static ObservableList<Usuario> usuarios = FXCollections.observableArrayList();
    private static ObservableList<Libro> libros = FXCollections.observableArrayList();
    private static ObservableMap<String, ObservableList<Prestamo>> prestamos = FXCollections.observableHashMap();

    private Biblioteca(){}

    static ObservableList<Usuario> getUsuarios() {
        return usuarios;
    }

    static ObservableList<Libro> getLibros() {
        return libros;
    }

    static ObservableMap<String, ObservableList<Prestamo>> getPrestamos() {
        return prestamos;
    }

    static void registrarPrestamo(Prestamo prestamo) {
        if(prestamos.containsKey(prestamo.getUsuario().dniProperty().get())) {
            prestamos.get(prestamo.getUsuario().dniProperty().get()).add(prestamo);
        } else {
            prestamos.put(prestamo.getUsuario().dniProperty().get(), FXCollections.observableArrayList(prestamo));
        }
    }

    static void removerPrestamo(Usuario usuario, Libro libro) {
        if(prestamos.containsKey(usuario.dniProperty().get())) {
            Prestamo prestamo = prestamos.get(usuario.dniProperty().get()).stream().filter(
                    it -> it.getLibro().equals(libro)
            ).findFirst().orElse(null);
            prestamos.get(usuario.dniProperty().get()).remove(prestamo);
        }
    }

    static void registrarLibro(Libro libro){
        libros.add(libro);
    }

    static void removerLibro(Libro libro) {
        libros.remove(libro);
    }

    static void registrarUsuario(Usuario usuario){
        usuarios.add(usuario);
    }

    static void removerUsuario(Usuario usuario) {
        usuarios.remove(usuario);
    }

}
