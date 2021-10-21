module com.example.programme {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.programme to javafx.fxml;
    exports com.example.programme;
}