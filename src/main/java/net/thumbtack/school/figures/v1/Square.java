package net.thumbtack.school.figures.v1;

import java.util.Objects;

public class Square {
    //REVU: создайте лучше 2 точки leftTop и rightBottom вместо 4 переменных будет 2.
    private int xLeft;
    private int xRight;
    private int yBottom;
    private int yTop;
    private int size;

    public Square(Point leftTop, int size) {
        this.size = size;
        xLeft = leftTop.getX();
        xRight = xLeft + size;
        yTop = leftTop.getY();
        yBottom = yTop + size;
    }

    public Square(int xLeft, int yTop, int size) {
        //REVU: не дублируйте код. Переиспользуйте конструкторы - this(...)
        this.xLeft = xLeft;
        this.yTop = yTop;
        this.size = size;
        xRight = xLeft + size;
        yBottom = yTop + size;
    }

    public Square(int size) {
        //REVU: не дублируйте код. Переиспользуйте конструкторы - this(...)
        this.size = size;
        xLeft = 0;
        xRight = size;
        yTop = -size;
        yBottom = 0;
    }

    public Square() {
        //REVU: не дублируйте код. Переиспользуйте конструкторы - this(...)
        xLeft = 0;
        xRight = 1;
        yTop = - 1;
        yBottom = 0;
        size = 1;
    }

    public Point getTopLeft() {
        return new Point(xLeft, yTop);
    }

    public Point getBottomRight() {
        return new Point(xRight, yBottom);
    }

    public void setTopLeft(Point topLeft) {
        xLeft = topLeft.getX();
        yTop = topLeft.getY();
        xRight = xLeft + size;
        yBottom = yTop + size;
    }

    public int getLength() {
        return xRight - xLeft;
    }

    public void moveTo(int x, int y) {
        //REVU: используйте метод moveTo из класса Point
        xLeft = x;
        yTop = y;
        xRight = xLeft + size;
        yBottom = yTop + size;
    }

    public void moveTo(Point point) {
        //REVU: переиспользуйте метод выше
        xLeft = point.getX();
        yTop = point.getY();
        xRight = xLeft + size;
        yBottom = yTop + size;
    }

    public void moveRel(int dx, int dy) {
        //REVU: используйте метод moveRel из класса Point
        xLeft += dx;
        xRight += dx;
        yTop += dy;
        yBottom += dy;
    }

    public void resize(double ration) {
        xRight = (int) (size * ration) + xLeft;
        yBottom = (int) (size * ration) + yTop;
        size *= ration;
    }

    public double getArea() {
        return Math.pow(size, 2d);
    }

    public double getPerimeter() {
        return size * 4d;
    }

    public boolean isInside(int x, int y) {
        return x >= xLeft &&
                x <= xRight &&
                y >= yTop &&
                y <= yBottom;
    }

    public boolean isInside(Point point) {
        int x = point.getX();
        int y = point.getY();

        return x >= xLeft &&
                x <= xRight &&
                y >= yTop &&
                y <= yBottom;
    }

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

    public boolean isInside(Square square) {
        int xL = square.getTopLeft().getX();
        int xR = square.getBottomRight().getX();
        int yT = square.getTopLeft().getY();
        int yB = square.getBottomRight().getY();

        return yB <= yBottom &&
                yT >= yTop &&
                xR <= xRight &&
                xL >= xLeft;
    }

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
