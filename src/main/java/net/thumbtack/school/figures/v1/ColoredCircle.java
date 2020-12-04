package net.thumbtack.school.figures.v1;

import java.util.Objects;

public class ColoredCircle extends Circle {
    private int color;

    public ColoredCircle(Point center, int radius, int color) {
        super(center, radius);
        this.color = color;
    }

    public ColoredCircle(int xCenter, int yCenter, int radius, int color) {
        this(new Point(xCenter, yCenter), radius, color);
    }

    public ColoredCircle(int radius, int color) {
        this(0, 0, radius, color);
    }

    public ColoredCircle(int color) {
        this(0, 0, 1, color);
    }

    public ColoredCircle() {
        this(0, 0, 1, 1);
    }


    public Point getCenter() {
        return super.getCenter();
    }

    public int getRadius() {
        return super.getRadius();
    }

    public void setCenter(Point center) {
        super.setCenter(center);
    }

    public void setRadius(int radius) {
        super.setRadius(radius);
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void moveRel(int dx, int dy) {
        super.moveRel(dx, dy);
    }

    public void resize(double ratio) {
        super.resize(ratio);
    }

    public double getArea() {
        return super.getArea();
    }

    public double getPerimeter() {
        return super.getPerimeter();
    }

    public boolean isInside(int x, int y) {
        return super.isInside(x, y);
    }

    public boolean isInside(Point point) {
        return super.isInside(point);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ColoredCircle that = (ColoredCircle) o;
        return color == that.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), color);
    }
}
