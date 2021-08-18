module org.javaweather {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.javaweather to javafx.fxml;
    exports org.javaweather;
    opens org.javaweather.view to javafx.fxml;
    exports org.javaweather.controller;
    opens org.javaweather.controller to javafx.fxml;
}