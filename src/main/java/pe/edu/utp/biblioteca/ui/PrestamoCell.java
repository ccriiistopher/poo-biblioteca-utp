package pe.edu.utp.biblioteca.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import pe.edu.utp.biblioteca.App;
import pe.edu.utp.biblioteca.PrestamoCellController;
import pe.edu.utp.biblioteca.model.Prestamo;

import java.io.IOException;

public class PrestamoCell extends ListCell<Prestamo> {
    private PrestamoCellController _controller;
    private FXMLLoader _loader;
    private Node _root;

    public PrestamoCell() {
    }
    private void loadFXML() {
        _loader = new FXMLLoader(App.class.getResource("cell_book_prestamo.fxml"));
        _loader.setRoot(this);
        try {
            _loader.load();
            _controller = _loader.getController();
            _root = getGraphic();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateItem(Prestamo item, boolean empty) {
        super.updateItem(item, empty);

        if(empty || item == null) {
            setGraphic(null);
        } else {
            if (_loader == null) {
                loadFXML();
            }
            setGraphic(_root);
            _controller.setPrestamo(item);
        }

    }
}
