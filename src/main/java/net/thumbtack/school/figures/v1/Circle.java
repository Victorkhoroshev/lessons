package net.thumbtack.school.figures.v1;

import java.util.Objects;

public class Circle {
    private Point center = new Point();
    private int radius;

    //3.1
    public Circle(Point center, int radius) {
        this.center = center;
        this.radius = radius;
    }
    //3.2
    public Circle(int xCenter, int yCenter, int radius) {
        this.radius = radius;
        center.setX(xCenter);
        center.setY(yCenter);
    }
    //3.3
    public Circle(int radius) {
        this.radius = radius;
        center.setX(0);
        center.setY(0);
    }
    //3.4
    public Circle() {
        radius = 1;
        center.setX(0);
        center.setY(0);
    }
    //3.5
    public Point getCenter() {
        return center;
    }
    //3.6
    public int getRadius() {
        return radius;
    }
    //3.7
    public void setCenter(Point center) {
        this.center = center;
    }
    //3.8
    public void setRadius(int radius) {
        this.radius = radius;
    }
    //3.9
    public void moveTo(int x, int y) {
        center.setX(x);
        center.setY(y);
    }
    //3.10
    public void moveTo(Point point) {
        center.setX(point.getX());
        center.setY(point.getY());
    }
    //3.11
    public void moveRel(int dx, int dy) {
        center.setX(center.getX() + dx);
        center.setY(center.getY() + dy);
    }
    //3.12
    public void resize(double ratio) {
        this.radius *= ratio;
    }
    //3.13
    public double getArea() {
        return Math.PI * Math.pow(radius, 2d);
    }
    //3.14
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }
    //3.15
    public boolean isInside(int x, int y) {
        double d = Math.sqrt(Math.pow((double) center.getX() - x, 2d) +
                + Math.pow((double) center.getY() - y, 2d));
        return d <= radius;
    }
    //3.16
    public boolean isInside(Point point) {
        int x = point.getX();
        int y = point.getY();
        double d = Math.sqrt(Math.pow((double) center.getX() - x, 2d) +
                + Math.pow((double) center.getY() - y, 2d));
        return d <= radius;
    }
    //3.17
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Circle circle = (Circle) o;
        return radius == circle.radius &&
                center.equals(circle.center);
    }

    @Override
    public int hashCode() {
        return Objects.hash(center, radius);
    }
}
