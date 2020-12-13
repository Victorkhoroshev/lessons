package net.thumbtack.school.misc.v3;

import net.thumbtack.school.colors.v3.Color;
import net.thumbtack.school.colors.v3.ColorErrorCode;
import net.thumbtack.school.colors.v3.ColorException;
import net.thumbtack.school.figures.v3.Point;
import net.thumbtack.school.figures.v3.Square;
import net.thumbtack.school.iface.v3.Colored;
import java.util.Objects;

public class ColoredCube extends Square implements Colored {

    private Color color;
    private Point3D leftTopFrond;
    private Point3D rightBottomBehind;

    public ColoredCube(Point leftTop, int size, int z, Color color) throws ColorException{
        super(leftTop, size);
        this.leftTopFrond = new Point3D(leftTop, z);
        this.rightBottomBehind = new Point3D(leftTop.getX() + size, leftTop.getY() + size, z - size);
        this.setColor(color);
    }

    public ColoredCube(Point leftTop, int size, int z, String color) throws ColorException {
        this(leftTop, size, z, Color.colorFromString(color));
    }

    public ColoredCube(int xLeft, int yTop, int size, int z, Color color) throws ColorException {
        this(new Point(xLeft, yTop), size, z, color);
    }

    public ColoredCube(int xLeft, int yTop, int size, int z, String color) throws ColorException {
        this(new Point(xLeft, yTop), size, z, Color.colorFromString(color));
    }

    public ColoredCube(int size) throws ColorException {
        this(0, -size, size, 0, Color.RED);
    }

    public ColoredCube() throws ColorException {
        this(0, -1, 1, 0, Color.RED);
    }

    @Override
    public void setColor(Color color) throws ColorException {
        if (color == null) {
            throw new ColorException(ColorErrorCode.NULL_COLOR);
        }
        this.color = Color.colorFromString(color.toString());
    }

    public void setColor(String color) throws ColorException {
        setColor(Color.colorFromString(color));
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
    public Color getColor() {
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
