package net.thumbtack.school.figures.v3;

import net.thumbtack.school.colors.v3.Color;
import net.thumbtack.school.colors.v3.ColorErrorCode;
import net.thumbtack.school.colors.v3.ColorException;
import net.thumbtack.school.iface.v3.Colored;
import java.util.Objects;

public class ColoredRectangle extends Rectangle implements Colored {

    private Color color;

    public ColoredRectangle(Point leftTop, Point rightBottom, Color color) throws ColorException {
        super(leftTop, rightBottom);
        this.setColor(color);
    }

    public ColoredRectangle(Point leftTop, Point rightBottom, String color) throws ColorException {
        this(leftTop, rightBottom, Color.colorFromString(color));
    }

    public ColoredRectangle(int xLeft, int yTop, int xRight, int yBottom, Color color) throws ColorException {
        this(new Point(xLeft, yTop), new Point(xRight, yBottom), color);
    }

    public ColoredRectangle(int xLeft, int yTop, int xRight, int yBottom, String color) throws ColorException {
        this(new Point(xLeft, yTop), new Point(xRight, yBottom), Color.colorFromString(color));
    }

    public ColoredRectangle(int length, int width, Color color) throws ColorException {
        this(0, -width, length, 0, color);
    }

    public ColoredRectangle(int length, int width, String color) throws ColorException {
        this(0, -width, length, 0, Color.colorFromString(color));
    }

    public ColoredRectangle(Color color) throws ColorException {
        this(0, -1, 1, 0, color);
    }

    public ColoredRectangle(String color) throws ColorException {
        this(0, -1, 1, 0, Color.colorFromString(color));
    }

    public ColoredRectangle() throws ColorException {
        this(0, -1, 1, 0, Color.RED);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) throws ColorException {
        if (color == null) {
            throw new ColorException(ColorErrorCode.NULL_COLOR);
        }
        this.color = Color.colorFromString(color.toString());
    }

    public void setColor(String color) throws ColorException {
        setColor(Color.colorFromString(color));
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
