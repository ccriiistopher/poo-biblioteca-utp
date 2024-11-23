package pe.edu.utp.biblioteca;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import pe.edu.utp.biblioteca.model.Libro;
import java.io.File;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class BookCreateController implements Initializable {
    @FXML
    public Label label_error_title;

    @FXML
    public Label label_error_author;

    @FXML
    public Label label_error_isbn;

    @FXML
    public Label label_error_genre;

    @FXML
    public ImageView img_picture;

    @FXML
    public TextField field_title;

    @FXML
    public TextField field_author;

    @FXML
    public TextField field_isbn;

    @FXML
    public TextField field_genre;

    @FXML
    public MenuButton button_menu_genre;

    @FXML
    public ButtonType button_add;

    @FXML
    public CheckBox checkbox_custom;

    private Dialog<Libro> dialog;

    private Node button_add_node;

    public String picture_path = "";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        checkbox_custom.selectedProperty().addListener((observable, oldValue, newValue) -> {
            field_genre.setDisable(!newValue);
            button_menu_genre.setDisable(newValue);
        });
        String[] generos = {"Ficción", "Fantasía", "Ciencia Ficción", "Romance", "Suspenso",
                "Misterio", "Terror", "Biografía", "No Ficción", "Histórico"};
        for (String genero : generos) {
            MenuItem generoItem = new MenuItem(genero);
            generoItem.addEventHandler(ActionEvent.ACTION, event -> {
                field_genre.setText(genero);
                button_menu_genre.setText(genero);
            });
            button_menu_genre.getItems().add(generoItem);
        }
        field_title.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.isBlank()) {
                label_error_title.setText("Ingrese un titulo válido");
            }
            label_error_title.setVisible(newValue.isBlank());
            label_error_title.setManaged(newValue.isBlank());
            validate();
        });

        field_author.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.isBlank()) {
                label_error_author.setText("Ingrese un autor válido");
            }
            label_error_author.setVisible(newValue.isBlank());
            label_error_author.setManaged(newValue.isBlank());
            validate();
        });
        field_isbn.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.isBlank()) {
                label_error_isbn.setText("Ingrese un código ISBN válido.");
            }
            label_error_isbn.setVisible(newValue.isBlank());
            label_error_isbn.setManaged(newValue.isBlank());
            validate();
        });
        field_genre.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.isBlank()) {
                label_error_genre.setText("Ingrese un género válido");
            }
            label_error_genre.setVisible(newValue.isBlank());
            label_error_genre.setManaged(newValue.isBlank());
            validate();
        });
    }

    @FXML
    public void pickPicture() {
        FileChooser chooser = new FileChooser();
        chooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("PNG", "*.png"));
        File file = chooser.showOpenDialog(null);
        picture_path = file.toURI().toString();
        img_picture.setImage(new Image(picture_path));
    }

    public void setDialog(Dialog<Libro> dialog, DialogPane dialogPane) {
        this.dialog = dialog;
        this.button_add_node = dialogPane.lookupButton(button_add);
        this.button_add_node.addEventFilter(ActionEvent.ANY, event -> {

        });
        this.dialog.setResultConverter(buttonType -> {
            if(!Objects.equals(ButtonBar.ButtonData.OK_DONE, buttonType.getButtonData())) {
                return null;
            }
            return new Libro(field_title.getText(), field_author.getText(), field_genre.getText(), field_isbn.getText(), picture_path);
        });
        this.button_add_node.setDisable(true);

    }

    private void validate() {
        this.button_add_node.setDisable(field_title.getText().isBlank() || field_author.getText().isBlank() || field_genre.getText().isBlank()
        || field_isbn.getText().isBlank());
    }
}
