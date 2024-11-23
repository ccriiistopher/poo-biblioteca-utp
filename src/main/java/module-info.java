module pe.edu.utp.biblioteca {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    // add icon pack modules
    requires org.kordamp.ikonli.fontawesome;

    opens pe.edu.utp.biblioteca to javafx.fxml;
    exports pe.edu.utp.biblioteca;
    exports pe.edu.utp.biblioteca.ui;
    exports pe.edu.utp.biblioteca.model;
    opens pe.edu.utp.biblioteca.ui to javafx.fxml;
    exports pe.edu.utp.biblioteca.domain;
}
