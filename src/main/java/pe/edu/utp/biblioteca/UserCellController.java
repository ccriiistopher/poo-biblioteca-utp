package pe.edu.utp.biblioteca;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import pe.edu.utp.biblioteca.model.Usuario;

public class UserCellController {
    @FXML
    public Label label_name, label_lastname, label_dni, label_role;

    public void setUsuario(Usuario usuario) {
        if( usuario != null) {
            label_name.textProperty().bind(usuario.nombresProperty());
            label_lastname.textProperty().bind(usuario.apellidosProperty());
            label_dni.textProperty().bind(usuario.dniProperty());
            label_role.setText(usuario.getTipo().toString());
            label_role.getStyleClass().clear();
            label_role.getStyleClass().add(usuario.getTipo() == Usuario.TipoUsuario.Admin ? "admin" : "user");
        }
    }
}
