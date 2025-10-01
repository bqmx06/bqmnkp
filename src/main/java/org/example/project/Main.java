package org.example.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        stage.setTitle("Game Menu");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        Application.launch(Main.class);
    }



}