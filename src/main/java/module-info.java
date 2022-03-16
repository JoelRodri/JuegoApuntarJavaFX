module com.example.juegoapuntarjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.juegoapuntarjavafx to javafx.fxml;
    exports com.example.juegoapuntarjavafx;
}