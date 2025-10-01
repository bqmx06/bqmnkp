package org.example.project.Controller;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import org.example.project.MainClass.Ball;
import org.example.project.MainClass.Brick;
import org.example.project.MainClass.GameObject;
import org.example.project.MainClass.Paddle;

import java.util.ArrayList;
import java.util.List;

/**
 *Game controller co
 * init cac container,canvas,gc,...
 * init các object paddle ball brick (khai bao tham chieu)
 * init cac flag
 * ham xu li moi thu cua cac controller là initialize()
 *          co cac ham init object (o day la khoi tao vi tri)
 *                      keypressed
 *                      keyrelease
 *                      update logic cac object
 *                      render
 */
public class GameController {

    @FXML
    private StackPane root;
    @FXML
    private Canvas canvas;

    private GraphicsContext gc;

    private Paddle paddle;
    private Ball ball;
    private List<GameObject> bricks = new ArrayList<>();
    private boolean spacePressed = false;
    private boolean leftPressed = false;
    private boolean rightPressed = false;

    @FXML
    public void initialize() {

        gc = canvas.getGraphicsContext2D();

        // Key events
        root.setOnKeyPressed(this::keyPressed);
        root.setOnKeyReleased(this::keyReleased);

        // Cho phép root nhận phím

        Platform.runLater(() -> root.requestFocus());


        initObject();
        // Game loop
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                render();
            }
        };
        gameLoop.start();
    }

    private void keyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.J) leftPressed = true;
        if (event.getCode() == KeyCode.L) rightPressed = true;
        if (event.getCode() == KeyCode.SPACE&&ball.isReset()) {
            if (!spacePressed) {       // Chỉ chạy lần đầu
                spacePressed = true;
                ball.shoot();          // hành động 1 lần
            }
        }
    }
    private void initObject() {
        paddle = new Paddle(canvas.getWidth() / 2 - 50, canvas.getHeight() - 50, 100, 20, 5);
        ball = new Ball(canvas.getWidth() / 2, paddle.getY()-10, 10);

        // Tạo hàng Brick trên đầu
        for (int i = 0; i < 10; i++) {
            bricks.add(new Brick(50 + i * 60, 50, 50, 20, 1, "normal"));
        }
    }
    private void keyReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.J) leftPressed = false;
        if (event.getCode() == KeyCode.L) rightPressed = false;
        if (event.getCode() == KeyCode.SPACE) {
            spacePressed = false;      // Reset khi thả phím
        }
    }

    private void update() {
        if (paddle != null) {
            // Di chuyển Paddle
            if (leftPressed) paddle.moveLeft();
            if (rightPressed) paddle.moveRight();

            // Giới hạn biên Paddle
            if (paddle.getX() < 0) paddle.setX(0);
            if (paddle.getX() + paddle.getWidth() > canvas.getWidth())
                paddle.setX(canvas.getWidth() - paddle.getWidth());
        }

        if (ball != null) {
            // Cập nhật Ball
            ball.update();
            if(ball.isReset()) {
                ball.setX(paddle.getX()+paddle.getWidth()/2);
            }
            // Va chạm với Paddle
            if (paddle != null && ball.checkCollision(paddle)) {
                ball.setDy(-Math.abs(ball.getDy()));

            }

            // Va chạm với Brick
            for (GameObject obj : bricks) {
                if (obj instanceof Brick) {
                    Brick brick = (Brick) obj;
                    if (!brick.isDestroyed() && ball.checkCollision(brick)) {
                        brick.takeHit();
                        ball.setDy(-ball.getDy());
                    }
                }
            }

            // Va chạm biên Canvas
            double radius = ball.getWidth() / 2.0;

            if (ball.getX() - radius <= 0) {
                ball.setX(radius);
                ball.setDx(Math.abs(ball.getDx()));
            }

            if (ball.getX() + radius >= canvas.getWidth()) {
                ball.setX(canvas.getWidth() - radius);
                ball.setDx(-Math.abs(ball.getDx()));
            }

            if (ball.getY() - radius <= 0) {
                ball.setY(radius);
                ball.setDy(Math.abs(ball.getDy()));
            }

            if (ball.getY() + radius >= canvas.getHeight()) {
                // Reset Ball nếu rơi
                ball.reset(paddle);
            }
        }

        // Cập nhật các Brick (nếu có MovingBrick)
        for (GameObject obj : bricks) {
            obj.update();
        }
    }

    private void render() {
        if (gc == null) return;

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        if (paddle != null) paddle.render(gc);
        if (ball != null) ball.render(gc);

        for (GameObject obj : bricks) {
            obj.render(gc);
        }
    }
}
