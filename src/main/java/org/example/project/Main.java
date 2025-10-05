package org.example.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * 2 loai file java controller va mainclass
 * moi controller tuong ung voi 1 fxml va 1 giao dien hien thi tren man hinh
 * mainclass thi nhu binh thuong ko co ti javafx nao
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(root);
        Font.loadFont(getClass().getResourceAsStream("/org/example/project/Font/PressStart2P.ttf"), 16);

        // ✅ Add CSS
        scene.getStylesheets().add(getClass().getResource("/org/example/project/Css/pixel-style.css").toExternalForm());
        stage.setTitle("Game Menu");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        Application.launch(Main.class);
    }



}