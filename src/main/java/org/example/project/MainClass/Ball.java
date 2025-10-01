package org.example.project.MainClass;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ball extends Moveable {
    private double radius;;
    private double speed=2.0;
    private boolean reset=true;
    public Ball(double x, double y, double radius) {
        super(x, y, radius*2, radius*2, 0,0);
        this.radius = radius;
    }

    public boolean isReset() {
        return reset;
    }

    public void shoot() {
        dx=(Math.random() * 2 * speed) - speed;
        dy=-speed;
        reset=false;
    }
    public void reset(Paddle paddle) {
        reset=true;
        setX(paddle.getX()+paddle.getWidth()/2);
        setY(paddle.getY()-radius);
        dx=0;
        dy=0;
    }
    public boolean checkCollision(GameObject other) {
        return x + radius > other.x && x - radius < other.x + other.width &&
                y + radius > other.y && y - radius < other.y + other.height;
    }
    @Override
    public void update() {
        move();
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.fillOval(x - radius, y - radius, radius*2, radius*2);
    }
}
