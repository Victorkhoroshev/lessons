package net.thumbtack.school.figures.v1;

import java.util.Objects;

public class Square {
    private Point leftTop;
    private Point rightBottom;
    private int size;

    public Square(Point leftTop, int size) {
        this.leftTop = leftTop;
        rightBottom = new Point(leftTop.getX() + size, leftTop.getY() + size);
        this.size = size;
    }

    public Square(int xLeft, int yTop, int size) {
        this(new Point(xLeft, yTop), size);
    }

    public Square(int size) {
        this(0, -size, size);
    }

    public Square() {
        this(0, -1, 1);
    }

    public Point getTopLeft() {
        return leftTop;
    }

    public Point getBottomRight() {
        return new Point(leftTop.getX() + size, leftTop.getY() + size);
    }

    public void setTopLeft(Point topLeft) {
        leftTop = new Point(topLeft.getX(), topLeft.getY());
    }

    public int getLength() {
        return size;
    }

    public void moveTo(int x, int y) {
        leftTop.moveTo(x, y);
    }

    public void moveTo(Point point) {
        moveTo(point.getX(), point.getY());
    }

    public void moveRel(int dx, int dy) {
        leftTop.moveRel(dx, dy);
    }

    public void resize(double ration) {
        size *= ration;
    }

    public double getArea() {
        return Math.pow(size, 2d);
    }

    public double getPerimeter() {
        return size * 4d;
    }

    public boolean isInside(int x, int y) {
        return x >= leftTop.getX() &&
                x <= rightBottom.getX() &&
                y >= leftTop.getY() &&
                y <= rightBottom.getY();
    }

    public boolean isInside(Point point) {
        return isInside(point.getX(), point.getY());
    }

    public boolean isIntersects(Square square) {
        //REVU: не нужно здесь заводить переменные.
        int xL = square.getTopLeft().getX();
        int xR = square.getBottomRight().getX();
        int yT = square.getTopLeft().getY();
        int yB = square.getBottomRight().getY();

        return yB >= leftTop.getY() &&
                yT <= rightBottom.getY() &&
                xR >= leftTop.getX() &&
                xL <= rightBottom.getX();
    }

    public boolean isInside(Square square) {
        return isInside(square.getBottomRight()) && isInside(square.getTopLeft());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return size == square.size &&
                leftTop.equals(square.leftTop) &&
                rightBottom.equals(square.rightBottom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftTop, rightBottom, size);
    }
}
