package pe.edu.utp.biblioteca;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import pe.edu.utp.biblioteca.domain.UserSession;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField field_dni, field_password;

    @FXML
    private void goBack() throws IOException {
       App.goBack();
    }

    @FXML
    private void login() throws IOException {
        String dni = field_dni.getText();
        String password = field_password.getText();
        String result = UserSession.login(dni, password);
        if(result != null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login");
            alert.setHeaderText(null);
            alert.setContentText(result);
            alert.showAndWait();
            return;
        }
        App.setRoot("home");
    }
}
