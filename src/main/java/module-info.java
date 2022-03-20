module com.example.juegoapuntarjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.juegoapuntarjavafx to javafx.fxml;
    exports com.example.juegoapuntarjavafx;
}