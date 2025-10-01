module org.example.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens org.example.project to javafx.fxml;
    exports org.example.project;
    exports org.example.project.Controller;
    opens org.example.project.Controller to javafx.fxml;
    exports org.example.project.MainClass;
    opens org.example.project.MainClass to javafx.fxml;
}