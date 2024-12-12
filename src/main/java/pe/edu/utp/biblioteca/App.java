package pe.edu.utp.biblioteca;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pe.edu.utp.biblioteca.domain.Libros;
import pe.edu.utp.biblioteca.domain.UserSession;
import pe.edu.utp.biblioteca.model.Libro;
import pe.edu.utp.biblioteca.model.Usuario;

import java.io.IOException;
import java.util.Objects;

public class App extends Application implements ChangeListener<Usuario> {

    private static Scene scene;

    private static  Parent currentScreen;
    private static String currentScreenName;
    private static Parent previousScreen;

    public static Stage globalStage;
    public static Stage bookInfoStage;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("main"), 640, 480);
        globalStage = stage;
        UserSession.registerUser("Cristopher Edú", "Salazar Parinango","75719117","123456", String.valueOf(Usuario.TipoUsuario.Alumno));
        UserSession.registerUser("Administrador", "Administrador", "87654321", "123456", String.valueOf(Usuario.TipoUsuario.Admin));
        stage.setScene(scene);
        stage.show();
        UserSession.getUsuario().addListener(this);
    }

    @Override
    public void changed(ObservableValue<? extends Usuario> observable, Usuario oldValue, Usuario newValue) {
        if(newValue == null && !Objects.equals(currentScreenName, "main")) {
            try {
                setRoot("main");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    static void goBack() throws IOException {
        currentScreen = previousScreen;
        scene.setRoot(previousScreen);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        previousScreen = currentScreen;
        currentScreen = fxmlLoader.load();
        currentScreenName = fxml;
        return currentScreen;
    }

    public static void main(String[] args) {
        launch();
    }

    public static void startBookInfoScreen(Libro book) throws IOException {
        if(bookInfoStage == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource( "book_info.fxml"));
            Scene bookInfoScene = new Scene(fxmlLoader.load(), 640, 480);
            bookInfoStage = new Stage();
            bookInfoStage.setScene(bookInfoScene);
        }
        if(!bookInfoStage.isShowing()) {
            bookInfoStage.show();
            bookInfoStage.toFront();
        }
        Libros.seleccionarLibro(book);
    }
}