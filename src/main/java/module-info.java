module com.example.programme {
    requires javafx.controls;
    requires javafx.fxml;


    opens windowManager to javafx.fxml;
    exports windowManager;
}