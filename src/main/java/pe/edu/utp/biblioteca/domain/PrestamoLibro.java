package pe.edu.utp.biblioteca.domain;

import javafx.collections.ObservableList;
import pe.edu.utp.biblioteca.model.Libro;
import pe.edu.utp.biblioteca.model.Prestamo;
import pe.edu.utp.biblioteca.model.Usuario;

public class PrestamoLibro {
    public static String prestarLibro(Libro libro, Usuario usuario) {
        if(!libro.disponibilidadProperty().get()) return "El libro no está disponible.";
        libro.setDisponibilidad(false);
        Biblioteca.removerPrestamo(usuario, libro);
        return null;
    }

    public static String devolverLibro(Libro libro, Usuario usuario) {
        Biblioteca.removerPrestamo(usuario, libro);
        libro.setDisponibilidad(true);
        return null;
    }

    public static ObservableList<Prestamo> getPrestamos(Usuario usuario) {
        return Biblioteca.getPrestamos().get(usuario.dniProperty().get());
    }
}
