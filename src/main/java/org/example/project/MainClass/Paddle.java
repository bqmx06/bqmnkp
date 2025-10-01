package org.example.project.MainClass;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Paddle extends Moveable {

    private double speed;
    private String currentPowerUp;

    public Paddle(double x, double y, double width, double height, double speed) {
        super(x, y, width, height, 0, 0);
        this.speed = speed;
    }

    public void moveLeft() {
        dx = -speed;
        move();
        dx = 0;
    }

    public void moveRight() {
        dx = speed;
        move();
        dx = 0;
    }

    public void applyPowerUp(String powerUp) {
        this.currentPowerUp = powerUp;
    }

    @Override
    public void update() {
        // paddle chỉ di chuyển theo input
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(Color.BLUE);
        gc.fillRect(x, y, width, height);
    }
}
