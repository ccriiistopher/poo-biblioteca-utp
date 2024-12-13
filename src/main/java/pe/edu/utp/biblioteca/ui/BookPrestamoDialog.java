package pe.edu.utp.biblioteca.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.stage.Window;
import pe.edu.utp.biblioteca.App;
import pe.edu.utp.biblioteca.BookPrestamoController;
import pe.edu.utp.biblioteca.model.Prestamo;

import java.io.IOException;

public class BookPrestamoDialog extends Dialog<Prestamo> {

    public BookPrestamoDialog(Window owner) {
        FXMLLoader _loader = new FXMLLoader(App.class.getResource("dialog_prestar_libro.fxml"));
        DialogPane _dialogPane;
        try {
            _dialogPane = _loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
       ((BookPrestamoController) _loader.getController()).setDialog(this, _dialogPane);
        initOwner(owner);
        setDialogPane(_dialogPane);
        setTitle("Prestar Libro");
    }
}
