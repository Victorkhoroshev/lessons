package net.thumbtack.school.figures.v2;

import net.thumbtack.school.iface.v2.Resizable;
import net.thumbtack.school.iface.v2.Stretchable;
import java.util.Objects;

public class Rectangle extends Figure implements Resizable, Stretchable {

    private Point leftTop;
    private Point rightBottom;
    private int length;
    private int width;

    public Rectangle(Point leftTop, Point rightBottom) {
        this.leftTop = leftTop;
        this.rightBottom = rightBottom;
        length = rightBottom.getX() - leftTop.getX();
        width = rightBottom.getY() - leftTop.getY();
    }

    public Rectangle(int xLeft, int yTop, int xRight, int yBottom) {
        this(new Point(xLeft, yTop), new Point(xRight, yBottom));
    }

    public Rectangle(int length, int width) {
        this(0, -width, length, 0);
    }

    public Rectangle() {
        this(0, -1, 1, 0);
        length = 1;
        width = 1;
    }

    public Point getTopLeft() {
        return leftTop;
    }

    public Point getBottomRight() {
        return rightBottom;
    }

    public void setTopLeft(Point topLeft) {
        leftTop = new Point(topLeft.getX(), topLeft.getY());
    }

    public void setBottomRight(Point bottomRight) {
        rightBottom = new Point(bottomRight.getX(), bottomRight.getY());
    }

    public int getLength() {
        return rightBottom.getX() - leftTop.getX();
    }

    public int getWidth() {
        return rightBottom.getY() - leftTop.getY();
    }

    @Override
    public void moveTo(int x, int y) {
        rightBottom.moveTo(x + length, y + width);
        leftTop.moveTo(x, y);
    }

    @Override
    public void moveRel(int dx, int dy) {
        leftTop.moveRel(dx, dy);
        rightBottom.moveRel(dx, dy);
    }

    @Override
    public void resize(double ratio) {
        length = (int) (length * ratio);
        width = (int) (width * ratio);
        setBottomRight(new Point(leftTop.getX() + length, leftTop.getY() + width));
    }

    public void stretch(double xRatio, double yRatio) {
        length = (int) (length * xRatio);
        width = (int) (width * yRatio);
        setBottomRight(new Point(leftTop.getX() + length, leftTop.getY() + width));
    }

    @Override
    public double getArea() {
        return (double) (rightBottom.getX() - leftTop.getX()) * (rightBottom.getY() - leftTop.getY());
    }

    @Override
    public double getPerimeter() {
        return (rightBottom.getX() - leftTop.getX() + rightBottom.getY() - leftTop.getY()) *2d;
    }

    @Override
    public boolean isInside(int x, int y) {
        return x >= leftTop.getX() && x <= rightBottom.getX() && y >= leftTop.getY() && y <= rightBottom.getY();
    }

    public boolean isIntersects(Rectangle rectangle) {
        return rectangle.getTopLeft().getY() <= rightBottom.getY() &&
                rectangle.getBottomRight().getY() >= leftTop.getY() &&
                rectangle.getBottomRight().getX() >= leftTop.getX() &&
                rectangle.getTopLeft().getX() <= rightBottom.getX();
    }

    public boolean isInside(Rectangle rectangle) {
        return rectangle.getTopLeft().getX() >= leftTop.getX() &&
                rectangle.getBottomRight().getX() <= rightBottom.getX() &&
                rectangle.getTopLeft().getY() >= leftTop.getY() &&
                rectangle.getBottomRight().getY() <= rightBottom.getY();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return length == rectangle.length &&
                width == rectangle.width &&
                leftTop.equals(rectangle.leftTop) &&
                rightBottom.equals(rectangle.rightBottom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftTop, rightBottom, length, width);
    }
}
