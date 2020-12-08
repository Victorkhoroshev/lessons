package net.thumbtack.school.misc.v2;

import net.thumbtack.school.figures.v2.Point;
import net.thumbtack.school.figures.v2.Square;
import net.thumbtack.school.iface.v2.Colored;

import java.util.Objects;

public class ColoredCube extends Square implements Colored {

    private int color;
    private Point3D leftTopFrond;
    private Point3D rightBottomBehind;

    public ColoredCube(Point leftTop, int size, int z, int color) {
        super(leftTop, size);
        this.leftTopFrond = new Point3D(leftTop, z);
        this.rightBottomBehind = new Point3D(leftTop.getX() + size, leftTop.getY() + size, z - size);
        this.color = color;
    }

    public ColoredCube(int xLeft, int yTop, int size, int z, int color) {
        this(new Point(xLeft, yTop), size, z, color);
    }

    public ColoredCube(int size) {
        this(0, -size, size, 0, 1);
    }

    public ColoredCube() {
        this(0, -1, 1, 0, 1);
    }

    @Override
    public void setColor(int color) {
        this.color = color;
    }

    public void setLeftTopFrond(Point3D leftTopFrond) {
        this.leftTopFrond = leftTopFrond;
    }

    public Point3D getTopLeftFront() {
        return leftTopFrond;
    }

    public Point3D getBottomRightBehind() {
        return new Point3D(leftTopFrond.getX() + getLength(), leftTopFrond.getY() + getLength(),
                leftTopFrond.getZ() - getLength());
    }

    @Override
    public int getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ColoredCube that = (ColoredCube) o;
        return color == that.color &&
                leftTopFrond.equals(that.leftTopFrond) &&
                rightBottomBehind.equals(that.rightBottomBehind);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), color, leftTopFrond, rightBottomBehind);
    }
}
