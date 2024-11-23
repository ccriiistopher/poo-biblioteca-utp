package pe.edu.utp.biblioteca;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pe.edu.utp.biblioteca.domain.Libros;
import pe.edu.utp.biblioteca.domain.UserSession;
import pe.edu.utp.biblioteca.model.Libro;
import pe.edu.utp.biblioteca.model.Usuario;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;
    private static Scene bookInfoScene;

    private static  Parent currentScreen;
    private static Parent previousScreen;

    public static Stage globalStage;
    public static Stage bookInfoStage;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("main"), 640, 480);
        globalStage = stage;
        UserSession.registerUser("Cristopher Edú", "Salazar Parinango","75719117","cesp0612", String.valueOf(Usuario.TipoUsuario.Alumno));
        //scene = new Scene(loadFXML("home"), 640, 480);
        stage.setScene(scene);
        stage.show();
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
        return currentScreen;
    }

    public static void main(String[] args) {
        launch();
    }

    public static void startBookInfoScreen(Libro book) throws IOException {
        if(bookInfoStage == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource( "book_info.fxml"));
            bookInfoScene = new Scene(fxmlLoader.load(), 640, 480);
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