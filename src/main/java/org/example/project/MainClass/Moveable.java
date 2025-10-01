package org.example.project.MainClass;

import javafx.scene.canvas.GraphicsContext;

public abstract class Moveable extends GameObject {
    protected double dx, dy;

    public Moveable(double x, double y, double width, double height, double dx, double dy) {
        super(x,y,width,height);
        this.dx = dx;
        this.dy = dy;
    }
    public void move() {
        x += dx;
        y += dy;
    }
    @Override
    public abstract void update();
    @Override
    public abstract void render(GraphicsContext gc);

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }
}
