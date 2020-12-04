package net.thumbtack.school.figures.v1;

import java.util.Objects;

public class Circle {
    //REVU: инициализация полей происходит в конструкторе
    private Point center = new Point();
    private int radius;

    public Circle(Point center, int radius) {
        this.center = center;
        this.radius = radius;
    }

    public Circle(int xCenter, int yCenter, int radius) {
        //REVU: не дублируйте код. Переиспользуйте конструкторы - this(...)
        this.radius = radius;
        center.setX(xCenter);
        center.setY(yCenter);
    }

    public Circle(int radius) {
        //REVU: не дублируйте код. Переиспользуйте конструкторы - this(...)
        this.radius = radius;
        center.setX(0);
        center.setY(0);
    }

    public Circle() {
        //REVU: не дублируйте код. Переиспользуйте конструкторы - this(...)
        radius = 1;
        center.setX(0);
        center.setY(0);
    }

    public Point getCenter() {
        return center;
    }

    public int getRadius() {
        return radius;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void moveTo(int x, int y) {
        //REVU: используйте метод moveTo из класса Point
        center.setX(x);
        center.setY(y);
    }

    public void moveTo(Point point) {
        //REVU: вызовите метод выше. Не дублируйте код
        center.setX(point.getX());
        center.setY(point.getY());
    }

    public void moveRel(int dx, int dy) {
        //REVU: используйте метод moveRel из класса Point
        center.setX(center.getX() + dx);
        center.setY(center.getY() + dy);
    }

    public void resize(double ratio) {
        this.radius *= ratio;
    }

    public double getArea() {
        return Math.PI * Math.pow(radius, 2d);
    }

    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    public boolean isInside(int x, int y) {
        double d = Math.sqrt(Math.pow((double) center.getX() - x, 2d) +
                + Math.pow((double) center.getY() - y, 2d));
        return d <= radius;
    }

    public boolean isInside(Point point) {
        int x = point.getX();
        int y = point.getY();
        double d = Math.sqrt(Math.pow((double) center.getX() - x, 2d) +
                + Math.pow((double) center.getY() - y, 2d));
        return d <= radius;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Circle circle = (Circle) o;
        return radius == circle.radius &&
                center.equals(circle.center);
    }

    public int hashCode() {
        return Objects.hash(center, radius);
    }
}
