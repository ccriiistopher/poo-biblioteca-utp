package pe.edu.utp.biblioteca;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import pe.edu.utp.biblioteca.domain.UserSession;
import pe.edu.utp.biblioteca.model.Usuario;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class RegisterController {
    @FXML
    private TextField field_name, field_lastname, field_dni;

    @FXML
    private PasswordField field_password;

    @FXML
    private ComboBox<String> field_type;

    public void initialize() {

        field_type.setItems(FXCollections.observableList(Arrays.stream(Usuario.TipoUsuario.values()).map(Enum::toString).collect(Collectors.toList())));
    }

    @FXML
    private void goBack() throws IOException {
        App.goBack();
    }

    @FXML
    private void register() throws IOException {
        String name = field_name.getText();
        String lastname = field_lastname.getText();
        String dni = field_dni.getText();
        String password = field_password.getText();
        String tipo = field_type.getValue();
        String result = UserSession.registerUser(name, lastname, dni, password, tipo);
        if(result != null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(result);
            alert.showAndWait();
            return;
        }
        App.setRoot("home");
    }
}
