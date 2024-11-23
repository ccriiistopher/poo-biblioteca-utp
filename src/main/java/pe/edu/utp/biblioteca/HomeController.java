package pe.edu.utp.biblioteca;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import pe.edu.utp.biblioteca.domain.Biblioteca;
import pe.edu.utp.biblioteca.domain.Libros;
import pe.edu.utp.biblioteca.domain.UserSession;
import pe.edu.utp.biblioteca.model.Libro;
import pe.edu.utp.biblioteca.model.Usuario;
import pe.edu.utp.biblioteca.ui.BookCellFactory;
import pe.edu.utp.biblioteca.ui.BookCreateDialog;
import pe.edu.utp.biblioteca.ui.UserCellFactory;
import java.util.Optional;

public class HomeController {

    @FXML
    public ListView<Libro> list_books;

    @FXML
    public ListView<Usuario> list_usuarios;

    @FXML
    public Button button_add_book;

    public void initialize() {
        Libros.registrarLibro(new Libro(
                "Cien Años de Soledad",
                "Gabriel García Márquez",
                "Novela",
                "978-0060883287",
                "https://books.google.com/books/content?id=IZszwAEACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api"
        ));

        Libros.registrarLibro(new Libro(
                "El Quijote",
                "Miguel de Cervantes",
                "Novela",
                "978-0142437230",
                "https://books.google.com/books/content?id=vHklAQAAMAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api"
        ));

        Libros.registrarLibro(new Libro(
                "1984",
                "George Orwell",
                "Ciencia Ficción",
                "978-0451524935",
                "https://books.google.com/books/content?id=kotPYEqx7kMC&printsec=frontcover&img=1&zoom=1&source=gbs_api"
        ));

        Libros.registrarLibro(new Libro(
                "Don Juan Tenorio",
                "José Zorrilla",
                "Drama",
                "978-8437621953",
                "https://books.google.com/books/content?id=Qe8HDgAAQBAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api"
        ));

        Libros.registrarLibro(new Libro(
                "Crimen y Castigo",
                "Fiódor Dostoyevski",
                "Novela",
                "978-0140449136",
                "https://books.google.com/books/content?id=QClikC5uYY8C&printsec=frontcover&img=1&zoom=1&source=gbs_api"
        ));

        Libros.registrarLibro(new Libro(
                "Orgullo y Prejuicio",
                "Jane Austen",
                "Romance",
                "978-0141439518",
                "https://books.google.com/books/content?id=PrHQDwAAQBAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api"
        ));

        Libros.registrarLibro(new Libro(
                "El Principito",
                "Antoine de Saint-Exupéry",
                "Fantasía",
                "978-0156012195",
                "https://books.google.com/books/content?id=0ltuDwAAQBAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api"
        ));

        Libros.registrarLibro(new Libro(
                "Los Miserables",
                "Victor Hugo",
                "Novela",
                "978-0140444308",
                "https://books.google.com/books/content?id=7ec7AQAAMAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api"
        ));

        Libros.registrarLibro(new Libro(
                "La Divina Comedia",
                "Dante Alighieri",
                "Épica",
                "978-0195004120",
                "https://books.google.com/books/content?id=9EIJXHHgPZ8C&printsec=frontcover&img=1&zoom=1&source=gbs_api"
        ));

        Libros.registrarLibro(new Libro(
                "El Hobbit",
                "J.R.R. Tolkien",
                "Fantasía",
                "978-0547928227",
                "https://books.google.com/books/content?id=NEJ3nQtw1tAC&printsec=frontcover&img=1&zoom=1&source=gbs_api"
        ));

        list_books.setCellFactory(new BookCellFactory());
        list_books.setItems(
                Libros.getLibros()
        );
        list_books.setFixedCellSize(150);
        list_usuarios.setCellFactory(new UserCellFactory());
        list_usuarios.setItems(
                UserSession.getUsuarios()
        );
        list_usuarios.setFixedCellSize(100);
    }


    @FXML
    public void onAddNewBook() {
        System.out.println("Adding new book");
        BookCreateDialog bookCreateDialog = null;
        bookCreateDialog = new BookCreateDialog(App.globalStage);
        Optional<Libro> result = bookCreateDialog.showAndWait();
        result.ifPresent(Libros::registrarLibro);

    }
}
