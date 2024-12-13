package pe.edu.utp.biblioteca;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import pe.edu.utp.biblioteca.domain.Libros;
import pe.edu.utp.biblioteca.domain.UserSession;
import pe.edu.utp.biblioteca.model.Libro;
import pe.edu.utp.biblioteca.model.Prestamo;
import pe.edu.utp.biblioteca.model.Usuario;
import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

public class BookPrestamoController implements Initializable {
    @FXML
    public DatePicker picker_retorno;

    @FXML
    public MenuButton button_menu_user;

    @FXML
    public Label label_error_user;

    @FXML
    public ButtonType button_ok;

    private Dialog<Prestamo> dialog;

    private Node button_ok_node;

    private LocalDate date_retorno;

    private Usuario usuario;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserSession.getUsuarios().forEach(
                it -> {
                    MenuItem userItem = new MenuItem();
                    userItem.setId(it.dniProperty().get());
                    userItem.setText(it.nombresProperty().get() + " " + it.apellidosProperty().get());
                    button_menu_user.getItems().add(userItem);
                    userItem.addEventHandler(ActionEvent.ACTION, event -> {
                        usuario = UserSession.encontrarUsuario(it.dniProperty().get());
                        button_menu_user.setText(userItem.getText());
                        validate();
                    });
                }
        );
    }


    public void setDialog(Dialog<Prestamo> dialog, DialogPane dialogPane) {
        this.dialog = dialog;
        this.button_ok_node = dialogPane.lookupButton(button_ok);
        this.button_ok_node.addEventFilter(ActionEvent.ANY, event -> {

        });
        this.dialog.setResultConverter(buttonType -> {
            if(!Objects.equals(ButtonBar.ButtonData.OK_DONE, buttonType.getButtonData())) {
                return null;
            }
            Libro libro = Libros.getLibro().get();
            return new Prestamo(usuario, libro, LocalDate.now(), date_retorno);
        });
        this.button_ok_node.setDisable(true);
        picker_retorno.valueProperty().addListener((observable, oldValue, newValue) -> {
            date_retorno = newValue;
            validate();
        });
    }

    private void validate() {
        boolean valid = usuario != null && date_retorno != null && date_retorno.isAfter(LocalDate.now());
        this.button_ok_node.setDisable(!valid);
        if(usuario == null) {
            label_error_user.setText("El usuario no puede estar vacio");
        }
        if(date_retorno == null) {
            label_error_user.setText("La fecha de retorno no puede estar vacia");
        }
        if (date_retorno.isBefore(LocalDate.now())) {
            label_error_user.setText("La fecha de retorno debe ser una fecha futura");
        }
        label_error_user.setVisible(!valid);
        label_error_user.setManaged(!valid);
    }
}
