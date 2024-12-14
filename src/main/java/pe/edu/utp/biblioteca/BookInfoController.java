package pe.edu.utp.biblioteca;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import org.kordamp.ikonli.javafx.FontIcon;
import pe.edu.utp.biblioteca.domain.Libros;
import pe.edu.utp.biblioteca.domain.PrestamoLibro;
import pe.edu.utp.biblioteca.domain.UserSession;
import pe.edu.utp.biblioteca.model.Libro;
import pe.edu.utp.biblioteca.model.Prestamo;
import pe.edu.utp.biblioteca.model.Usuario;
import pe.edu.utp.biblioteca.ui.BookPrestamoDialog;
import pe.edu.utp.biblioteca.util.ImageCache;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class BookInfoController implements ChangeListener<Libro>, Initializable {
    private Libro book;

    @FXML
    public Label label_title, label_author, label_genre, label_isbn, label_available, label_user;

    @FXML
    public ImageView image_book;

    @FXML
    public Button button_prestar;

    @FXML
    public HBox box_prestamo;

    public void initialize() {
        Libros.getLibro().addListener(this);
        System.out.println("BookInfoController initialize 2");

    }

    @Override
    public void changed(ObservableValue<? extends Libro> observable, Libro oldValue, Libro newValue) {
        if(newValue != null) System.out.println("Book has changed " + newValue.getTitulo());
        setLibro(newValue);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("BookInfoController initialize");
        Libros.getLibro().addListener(this);
        setLibro(book);
        System.out.println("Book info started");
        Usuario currentUser = UserSession.getUsuario().get();
        System.out.println(currentUser.dniProperty().get());
        button_prestar.setVisible(currentUser.getTipo() == Usuario.TipoUsuario.Admin);
        button_prestar.setManaged(currentUser.getTipo() == Usuario.TipoUsuario.Admin);
    }


    private void setPrestamo(Prestamo p) {
        box_prestamo.setVisible(p != null);
        box_prestamo.setManaged(p != null);
        if (p != null) {
            label_user.textProperty().bind(p.getUsuario().nombresProperty());
        }
    }

    public void setLibro(Libro book) {
        this.book = book;
        if (book != null) {
            setPrestamo(PrestamoLibro.getPrestamo(book));
            image_book.setImage(null);
            label_title.setText(book.getTitulo());
            label_author.setText(book.getAutor());
            label_genre.setText(book.getGenero());
            label_isbn.setText(book.getIsbn());
            label_available.getStyleClass().clear();
            boolean disponible = book.isDisponibilidad();
            label_available.getStyleClass().add(disponible ? "label_available" : "label_unavailable");
            label_available.setText(disponible ? "Disponible" : "No disponible");
            ((FontIcon) label_available.getGraphic()).setIconLiteral(disponible ? "fa-check" : "fa-close");
            ((FontIcon) label_available.getGraphic()).setIconColor(Paint.valueOf(disponible ? "green" : "#FFC107"));
            if (book.getPicture() != null && !book.getPicture().isBlank()) {
                Task<Image> loadImageTask = getImageTask(book);
                new Thread(loadImageTask).start();
            }
            if (book.isDisponibilidad()) {
                button_prestar.setText("Prestar libro");
            } else {
                button_prestar.setText("Confirmar devolución");
            }
        }
    }

    private Task<Image> getImageTask(Libro book) {
        return ImageCache.getImageTask(book, image -> {
            image_book.setImage(image);
        });
    }

    public void prestar() {
        if (book.isDisponibilidad()) {
            BookPrestamoDialog bookPrestamoDialog = new BookPrestamoDialog(App.globalStage);
            Optional<Prestamo> result = bookPrestamoDialog.showAndWait();
            result.ifPresent(PrestamoLibro::prestarLibro);
        } else {
            PrestamoLibro.devolverLibro(book);
        }
    }

    @FXML
    void close() {
        App.closeBookInfoScreen();
    }
}
