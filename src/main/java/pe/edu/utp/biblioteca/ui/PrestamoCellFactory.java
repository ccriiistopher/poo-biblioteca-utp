package pe.edu.utp.biblioteca.ui;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import pe.edu.utp.biblioteca.model.Prestamo;

public class PrestamoCellFactory implements Callback<ListView<Prestamo>, ListCell<Prestamo>> {
    @Override
    public ListCell<Prestamo> call(ListView<Prestamo> param) {
        return new PrestamoCell();
    }
}
