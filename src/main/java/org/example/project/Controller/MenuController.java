package org.example.project.Controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Menu controller chi co 2 button va xu li khi click vao 2 button.
 */
public class MenuController {

    @FXML
    private VBox root;
    @FXML
    private Button startButton;

    @FXML
    private Button exitButton;

    @FXML
    public void initialize() {

        startButton.getStyleClass().add("pixel-button");
        exitButton.getStyleClass().add("pixel-button");

        startButton.setOnAction(e -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/project/Game.fxml"));
                Parent gameRoot = loader.load();
                Scene gameScene = new Scene(gameRoot);


                Stage stage = (Stage) startButton.getScene().getWindow();
                stage.setScene(gameScene);
                stage.setTitle("Arkanoid Game");
                stage.centerOnScreen();
                stage.show();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        exitButton.setOnAction(e -> {
            Stage stage = (Stage) exitButton.getScene().getWindow();
            stage.close();
        });
    }

}
