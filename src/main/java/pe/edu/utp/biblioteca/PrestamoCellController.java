package pe.edu.utp.biblioteca;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pe.edu.utp.biblioteca.model.Libro;
import pe.edu.utp.biblioteca.model.Prestamo;
import pe.edu.utp.biblioteca.util.ImageCache;

public class PrestamoCellController {
    private Prestamo prestamo;

    @FXML
    public Label label_title, label_author, label_genre, label_isbn, label_user;

    @FXML
    public ImageView image_book;

    @FXML
    public ProgressIndicator progress;

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
        if( prestamo != null) {
            progress.setVisible(true);
            image_book.setImage(null);
            label_title.setText(prestamo.getLibro().getTitulo());
            label_author.setText(prestamo.getLibro().getAutor());
            label_genre.setText(prestamo.getLibro().getGenero());
            label_isbn.setText(prestamo.getLibro().getIsbn());
            label_user.textProperty().bind(prestamo.getUsuario().nombresProperty());
            if(prestamo.getLibro().getPicture() != null && !prestamo.getLibro().getPicture().isBlank()) {
                Task<Image> loadImageTask = getImageTask(prestamo.getLibro());

                new Thread(loadImageTask).start();
            }
        }
    }

    private Task<Image> getImageTask(Libro book) {
        return ImageCache.getImageTask(book, image -> {
            image_book.setImage(image);
            progress.setVisible(false);
        });
    }
}
