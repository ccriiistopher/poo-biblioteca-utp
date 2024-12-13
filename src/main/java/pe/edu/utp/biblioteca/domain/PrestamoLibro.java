package pe.edu.utp.biblioteca.domain;

import javafx.collections.ObservableList;
import pe.edu.utp.biblioteca.model.Libro;
import pe.edu.utp.biblioteca.model.Prestamo;
import pe.edu.utp.biblioteca.model.Usuario;

public class PrestamoLibro {
    public static String prestarLibro(Prestamo prestamo) {
        if(!prestamo.getLibro().isDisponibilidad()) return "El libro no está disponible.";
        prestamo.getLibro().setDisponibilidad(false);
        Biblioteca.registrarPrestamo(prestamo);
        return null;
    }

    public static String devolverLibro(Libro libro) {
        Biblioteca.removerPrestamo(libro);
        libro.setDisponibilidad(true);
        return null;
    }

    public static ObservableList<Prestamo> getUsuariosPrestamos(Usuario usuario) {
        return Biblioteca.getPrestamos().filtered(prestamo -> prestamo.getUsuario().equals(usuario));
    }

    public static ObservableList<Prestamo> getPrestamos() {
        return Biblioteca.getPrestamos();
    }
}
