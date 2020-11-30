package net.thumbtack.school.figures.v1;

import java.util.Objects;

public class Square {
    private int xLeft;
    private int xRight;
    private int yBottom;
    private int yTop;
    private int size;

    //2.1
    public Square(Point leftTop, int size) {
        this.size = size;
        xLeft = leftTop.getX();
        xRight = xLeft + size;
        yTop = leftTop.getY();
        yBottom = yTop + size;
    }
    //2.2
    public Square(int xLeft, int yTop, int size) {
        this.xLeft = xLeft;
        this.yTop = yTop;
        this.size = size;
        xRight = xLeft + size;
        yBottom = yTop + size;
    }
    //2.3
    public Square(int size) {
        this.size = size;
        xLeft = 0;
        xRight = size;
        yTop = -size;
        yBottom = 0;
    }
    //2.4
    public Square() {
        xLeft = 0;
        xRight = 1;
        yTop = - 1;
        yBottom = 0;
        size = 1;
    }
    //2.5
    public Point getTopLeft() {
        return new Point(xLeft, yTop);
    }
    //2.6
    public Point getBottomRight() {
        return new Point(xRight, yBottom);
    }
    //2.7
    public void setTopLeft(Point topLeft) {
        xLeft = topLeft.getX();
        yTop = topLeft.getY();
        xRight = xLeft + size;
        yBottom = yTop + size;
    }
    //2.8
    public int getLength() {
        return xRight - xLeft;
    }
    //2.9
    public void moveTo(int x, int y) {
        xLeft = x;
        yTop = y;
        xRight = xLeft + size;
        yBottom = yTop + size;
    }
    //2.10
    public void moveTo(Point point) {
        xLeft = point.getX();
        yTop = point.getY();
        xRight = xLeft + size;
        yBottom = yTop + size;
    }
    //2.11
    public void moveRel(int dx, int dy) {
        xLeft += dx;
        xRight += dx;
        yTop += dy;
        yBottom += dy;
    }
    //2.12
    public void resize(double ration) {
        xRight = (int) (size * ration) + xLeft;
        yBottom = (int) (size * ration) + yTop;
        size *= ration;
    }
    //2.13
    public double getArea() {
        return Math.pow(size, 2d);
    }
    //2.14
    public double getPerimeter() {
        return size * 4d;
    }
    //2.15
    public boolean isInside(int x, int y) {
        return x >= xLeft &&
                x <= xRight &&
                y >= yTop &&
                y <= yBottom;
    }
    //2.16
    public boolean isInside(Point point) {
        int x = point.getX();
        int y = point.getY();

        return x >= xLeft &&
                x <= xRight &&
                y >= yTop &&
                y <= yBottom;
    }
    //2.17
    public boolean isIntersects(Square square) {
        int xL = square.getTopLeft().getX();
        int xR = square.getBottomRight().getX();
        int yT = square.getTopLeft().getY();
        int yB = square.getBottomRight().getY();

        return yB >= yTop &&
                yT <= yBottom &&
                xR >= xLeft &&
                xL <= xRight;
    }
    //2.18
    public boolean isInside(Square square) {
        int xL = square.getTopLeft().getX();
        int xR = square.getBottomRight().getX();
        int yT = square.getTopLeft().getY();
        int yB = square.getBottomRight().getY();

        return xL >= xLeft &&
                xR <= xRight &&
                yT >= yTop &&
                yB <= yBottom;
    }
    //2.19
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return xLeft == square.xLeft &&
                xRight == square.xRight &&
                yBottom == square.yBottom &&
                yTop == square.yTop &&
                size == square.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xLeft, xRight, yBottom, yTop, size);
    }
}
