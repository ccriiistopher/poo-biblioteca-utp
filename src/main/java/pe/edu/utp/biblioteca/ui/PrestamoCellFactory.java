package pe.edu.utp.biblioteca.ui;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import pe.edu.utp.biblioteca.model.Libro;

public class PrestamoCellFactory implements Callback<ListView<Libro>, ListCell<Libro>> {
    @Override
    public ListCell<Libro> call(ListView<Libro> param) {
        return new BookCell();
    }
}
