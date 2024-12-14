package pe.edu.utp.biblioteca.domain;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import pe.edu.utp.biblioteca.model.Usuario;

public class UserSession {
    private static final SimpleObjectProperty<Usuario> usuario = new SimpleObjectProperty<>();

    public static ObservableList<Usuario> getUsuarios() {
        return Biblioteca.getUsuarios();
    }

    public static ObjectProperty<Usuario> getUsuario() {
        return usuario;
    }

    public static void signOut() {
        usuario.set(null);
    }

    public static String login(String dni, String password) {
        if(dni == null || dni.isBlank()) return "El número de DNI no es válido.";
        Usuario result = encontrarUsuario(dni);
        if(result == null) return "El usuario no existe.";
        if(result.getContrasena().equals(password)) {
            usuario.set(result);
            return null;
        }

        return "DNI o contraseña inválidos.";
    }

    public static String registerUser(String nombres, String apellidos, String dni, String contrasena, String tipoUsuario){
        if(encontrarUsuario(dni) != null) return "Usuario ya existe";
        if(nombres==null || nombres.isBlank()){
            return "Ingrese el nombre del usuario";
        }
        if(apellidos==null || apellidos.isBlank()){
            return "Ingrese el apellido del usuario";
        }
        if(dni==null || dni.isBlank()){
            return "Ingrese el dni del usuario";
        }
        if(tipoUsuario==null || tipoUsuario.isBlank()){
            return "Ingrese el tipo de usuario";
        }
        Usuario.TipoUsuario tipo;
        try {
            tipo = (Usuario.TipoUsuario.valueOf(tipoUsuario));
        } catch (IllegalArgumentException e) {
            return ("Tipo de Usuario no valido");
        }
        Usuario newUsuario = new Usuario(nombres, apellidos, dni, contrasena, tipo);
        Biblioteca.registrarUsuario(newUsuario);
        usuario.set(newUsuario);
        return null;
    }

    public static Usuario encontrarUsuario(String dni){
        Usuario resultado = null;
        for(Usuario usuario : Biblioteca.getUsuarios()) {
            if(usuario.dniProperty().get().equals(dni)) {
                resultado = usuario;
            }
        }
        return resultado;
    }
}
