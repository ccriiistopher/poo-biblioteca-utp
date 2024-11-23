package pe.edu.utp.biblioteca.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Usuario {
    private final SimpleStringProperty nombres = new SimpleStringProperty();
    private final SimpleStringProperty apellidos = new SimpleStringProperty();
    private final SimpleStringProperty dni = new SimpleStringProperty();
    private final String contrasena;
    private final TipoUsuario tipoUsuario;

    public Usuario(String nombres, String apellidos, String dni, String contrasena, TipoUsuario tipo) {
        this.nombres.set(nombres);
        this.apellidos.set(apellidos);
        this.dni.set(dni);
        this.tipoUsuario = tipo;
        this.contrasena = contrasena;
    }

    public StringProperty nombresProperty() {
        return nombres;
    }

    public StringProperty apellidosProperty() {
        return apellidos;
    }

    public StringProperty dniProperty() {
        return dni;
    }

    public TipoUsuario getTipo() {
        return tipoUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }
    
    public enum TipoUsuario {
        Admin, Alumno, Profesor
    }
}
