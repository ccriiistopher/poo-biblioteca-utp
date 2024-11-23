package pe.edu.utp.biblioteca.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import pe.edu.utp.biblioteca.App;
import pe.edu.utp.biblioteca.BookCellController;
import pe.edu.utp.biblioteca.model.Libro;

import java.io.IOException;

public class BookCell extends ListCell<Libro> {
    private BookCellController _controller;
    private FXMLLoader _loader;
    private Node _root;

    public BookCell() {
    }
    private void loadFXML() {
        _loader = new FXMLLoader(App.class.getResource("cell_book.fxml"));
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
    public void updateItem(Libro item, boolean empty) {
        super.updateItem(item, empty);

        if(empty || item == null) {
            setGraphic(null);
        } else {
            if (_loader == null) {
                loadFXML();
            }
            setGraphic(_root);
            _controller.setLibro(item);
        }

    }
}
