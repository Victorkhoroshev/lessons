package net.thumbtack.school.figures.v1;

import java.util.Objects;

public class ColoredRectangle extends Rectangle {
    private int color;

    public ColoredRectangle(Point leftTop, Point rightBottom, int color) {
        super(leftTop, rightBottom);
        this.color = color;
    }

    public ColoredRectangle(int xLeft, int yTop, int xRight, int yBottom, int color) {
        this(new Point(xLeft, yTop), new Point(xRight, yBottom), color);
    }

    public ColoredRectangle(int length, int width, int color) {
        this(0, -width, length, 0, color);
    }

    public ColoredRectangle(int color) {
        this(0, -1, 1, 0, color);
    }

    public ColoredRectangle() {
        this(0, -1, 1, 0, 1);
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ColoredRectangle that = (ColoredRectangle) o;
        return color == that.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), color);
    }
}
