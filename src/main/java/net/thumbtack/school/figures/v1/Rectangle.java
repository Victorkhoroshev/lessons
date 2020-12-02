package net.thumbtack.school.figures.v1;

import java.util.Objects;

public class Rectangle {
    private int xLeft;
    private int xRight;
    private int yBottom;
    private int yTop;
    private int length;
    private int width;

    public Rectangle(Point leftTop, Point rightBottom) {
        xLeft = leftTop.getX();
        yTop = leftTop.getY();
        xRight = rightBottom.getX();
        yBottom = rightBottom.getY();
        length = xRight - xLeft;
        width = yBottom - yTop;
    }

    public Rectangle(int xLeft, int yTop, int xRight, int yBottom) {
        this.xLeft = xLeft;
        this.yTop = yTop;
        this.xRight = xRight;
        this.yBottom = yBottom;
        length = xRight - xLeft;
        width = yBottom - yTop;
    }

    public Rectangle(int length, int width) {
        this.length = length;
        this.width = width;
        xLeft = 0;
        yBottom = 0;
        xRight = length;
        yTop = -width;
    }

    public Rectangle() {
        xLeft = 0;
        yBottom = 0;
        xRight = 1;
        yTop = -1;
        length = 1;
        width = 1;
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
    }

    public void setBottomRight(Point bottomRight) {
        xRight = bottomRight.getX();
        yBottom = bottomRight.getY();
    }

    public int getLength() {
        return xRight - xLeft;
    }

    public int getWidth() {
        return yBottom - yTop;
    }

    public void moveTo(int x, int y) {
        xRight = x + xRight - xLeft;
        yBottom = y + yBottom - yTop;
        xLeft = x;
        yTop = y;
    }

    public void moveTo(Point point) {
        xRight = point.getX() + xRight - xLeft;
        yBottom = point.getY() + yBottom - yTop;
        xLeft = point.getX();
        yTop = point.getY();
    }

    public void moveRel(int dx, int dy) {
        xLeft += dx;
        xRight += dx;
        yTop += dy;
        yBottom += dy;
    }

    public void resize(double ratio) {
        length = (int) (length * ratio);
        width = (int) (width * ratio);
        xRight = xLeft + length;
        yBottom = yTop + width;
    }

    public void stretch(double xRatio, double yRatio) {
        length = (int) (length * xRatio);
        width = (int) (width * yRatio);
        xRight = xLeft + length;
        yBottom = yTop + width;
    }

    public double getArea() {
        return (double) (xRight - xLeft) * (yBottom - yTop);
    }

    public double getPerimeter() {
        return (xRight - xLeft + yBottom - yTop) * 2d;
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
                x <=xRight &&
                y >= yTop &&
                y <= yBottom;
    }

    public boolean isIntersects(Rectangle rectangle) {
        int xL = rectangle.getTopLeft().getX();
        int xR = rectangle.getBottomRight().getX();
        int yT = rectangle.getTopLeft().getY();
        int yB = rectangle.getBottomRight().getY();

        return yT <= this.yBottom &&
                yB >= this.yTop &&
                xR >= this.xLeft &&
                xL <= this.xRight;
    }

    public boolean isInside(Rectangle rectangle) {
        int xL = rectangle.getTopLeft().getX();
        int xR = rectangle.getBottomRight().getX();
        int yT = rectangle.getTopLeft().getY();
        int yB = rectangle.getBottomRight().getY();

        return xL >= this.xLeft &&
                xR <= this.xRight &&
                yT >= this.yTop &&
                yB <= this.yBottom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return xLeft == rectangle.xLeft &&
                xRight == rectangle.xRight &&
                yBottom == rectangle.yBottom &&
                yTop == rectangle.yTop &&
                length == rectangle.length &&
                width == rectangle.width;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xLeft, xRight, yBottom, yTop, length, width);
    }
}
