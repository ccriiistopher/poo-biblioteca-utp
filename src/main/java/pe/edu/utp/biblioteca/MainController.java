package pe.edu.utp.biblioteca;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;

public class MainController {

    @FXML
    private void exitApp() throws IOException {
        Platform.exit();
    }

    @FXML
    private void login() throws IOException {
        App.setRoot("login");
    }

    @FXML
    private void register() throws IOException {
        App.setRoot("register");
    }
}
