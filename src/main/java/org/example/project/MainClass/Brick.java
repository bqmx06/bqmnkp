package org.example.project.MainClass;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Brick extends GameObject {

    private int hitPoints;
    private String type;

    public Brick(double x, double y, double width, double height, int hitPoints, String type) {
        super(x, y, width, height);
        this.hitPoints = hitPoints;
        this.type = type;
    }

    public void takeHit() {
        hitPoints--;
    }

    public boolean isDestroyed() {
        return hitPoints <= 0;
    }

    @Override
    public void update() {
        // brick tĩnh
    }

    @Override
    public void render(GraphicsContext gc) {
        if (!isDestroyed()) {
            gc.setFill(Color.ORANGE);
            gc.fillRect(x, y, width, height);
        }
    }
}
