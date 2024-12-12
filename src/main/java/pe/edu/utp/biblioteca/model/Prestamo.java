package pe.edu.utp.biblioteca.model;

import java.util.Date;

public class Prestamo {
    private Usuario usuario;
    private Libro libro;
    private Date fecha;

    public Prestamo(Usuario usuario, Libro libro, Date fecha) {
        this.usuario = usuario;
        this.libro = libro;
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Libro getLibro() {
        return libro;
    }

    public Date getFecha() {
        return fecha;
    }
}
