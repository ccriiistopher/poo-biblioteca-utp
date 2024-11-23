package pe.edu.utp.biblioteca;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import org.kordamp.ikonli.javafx.FontIcon;
import pe.edu.utp.biblioteca.domain.Libros;
import pe.edu.utp.biblioteca.model.Libro;
import pe.edu.utp.biblioteca.util.ImageCache;

public class BookInfoController implements ChangeListener<Libro> {
    private Libro book;

    @FXML
    public Label label_title, label_author, label_genre, label_isbn, label_available;

    @FXML
    public ImageView image_book;

    @FXML
    public ProgressIndicator progress;

    public void initialize(){
        Libros.getLibro().addListener(this);
    }

    @Override
    public void changed(ObservableValue<? extends Libro> observable, Libro oldValue, Libro newValue) {
        setLibro(newValue);
    }

    public void setLibro(Libro book) {
        if(this.book != null) {
            //this.book.disponibilidadProperty().removeListener(this);
        }
        this.book = book;
        if( book != null) {
            //progress.setVisible(true);
            image_book.setImage(null);
            label_title.setText(book.getTitulo());
            label_author.setText(book.getAutor());
            label_genre.setText(book.getGenero());
            label_isbn.setText(book.getIsbn());
            label_available.getStyleClass().clear();
            //this.book.disponibilidadProperty().addListener(this);
            if(book.getPicture() != null && !book.getPicture().isBlank()) {
                Task<Image> loadImageTask = getImageTask(book);

                new Thread(loadImageTask).start();
            }
        }
    }

    private Task<Image> getImageTask(Libro book) {
        return ImageCache.getImageTask(book, image -> {
            image_book.setImage(image);
            //progress.setVisible(false);
        });
    }

    public void prestar(){
        book.setDisponibilidad(false);
    }


   /* @Override
    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        System.out.println("changed");
        label_available.getStyleClass().add(newValue ? "label_available": "label_unavailable");
        label_available.setText(newValue ? "Disponible" : "No disponible");
        ((FontIcon)label_available.getGraphic()).setIconLiteral(newValue ? "fa-check" : "fa-close");
        ((FontIcon)label_available.getGraphic()).setIconColor(Paint.valueOf(newValue ? "green" : "#FFC107"));
    }*/
}
