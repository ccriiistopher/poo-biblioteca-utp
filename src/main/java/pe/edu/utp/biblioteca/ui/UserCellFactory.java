package pe.edu.utp.biblioteca.ui;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import pe.edu.utp.biblioteca.model.Usuario;

public class UserCellFactory implements Callback<ListView<Usuario>, ListCell<Usuario>> {
    @Override
    public ListCell<Usuario> call(ListView<Usuario> param) {
        return new UserCell();
    }
}
