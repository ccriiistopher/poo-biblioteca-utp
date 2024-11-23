package pe.edu.utp.biblioteca.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.stage.Window;
import pe.edu.utp.biblioteca.App;
import pe.edu.utp.biblioteca.BookCreateController;
import pe.edu.utp.biblioteca.model.Libro;
import java.io.IOException;

public class BookCreateDialog extends Dialog<Libro> {
    public BookCreateDialog(Window owner) {
        FXMLLoader _loader = new FXMLLoader(App.class.getResource("dialog_pane_book_create.fxml"));
        DialogPane _dialogPane = null;
        try {
            _dialogPane = _loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
       ((BookCreateController) _loader.getController()).setDialog(this, _dialogPane);
        initOwner(owner);
        setDialogPane(_dialogPane);
        setTitle("Nuevo Libro");
    }
}
