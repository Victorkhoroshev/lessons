package net.thumbtack.school.misc.v2;

import net.thumbtack.school.figures.v2.Circle;
import net.thumbtack.school.figures.v2.Point;
import java.util.Objects;

public class Boll extends Circle {

    private Point3D center;

    public Boll(Point center, int radius, int z) {
        super(center, radius);
        this.center = new Point3D(center, z);
    }

    public Boll(int xCenter, int yCenter, int radius, int z) {
        this(new Point(xCenter, yCenter), radius, z);
    }

    public Boll(Point center, int radius) {
        this(center, radius, 0);
    }

    public Boll(int radius) {
        this(0, 0, radius, 0);
    }

    public Boll() {
        this(0, 0, 1, 0);
    }

    @Override
    public Point3D getCenter() {
        return center;
    }

    public void setCenter(Point3D center) {
        this.center = center;
    }

    public void moveRel(int dx, int dy, int dz) {
        center.moveRel(dx, dy, dz);
    }

    public void moveTo(int x, int y, int z) {
        center.moveTo(x, y, z);
    }

    public void moveTo(Point center, int z) {
        moveTo(center.getX(), center.getY(), z);
    }

    @Override
    public double getArea() {
        return 4 * Math.PI * Math.pow(getRadius(), 2d);
    }

    public double getVolume() {
        return 4 / 3d * Math.PI * Math.pow(getRadius(), 3);
    }

    public boolean isInside(int x, int y, int z) {
        return Math.pow((double) center.getX() - x, 2) + Math.pow((double) center.getY() - y, 2) +
                + Math.pow((double) center.getZ() - z, 2) <= Math.pow(getRadius(), 2);
    }

    public boolean isInside(Point3D point) {
        return isInside(point.getX(), point.getY(), point.getZ());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Boll boll = (Boll) o;
        return center.equals(boll.center);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), center);
    }
}
