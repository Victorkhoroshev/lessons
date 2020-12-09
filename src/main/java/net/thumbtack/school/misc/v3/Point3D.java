package net.thumbtack.school.misc.v3;

import net.thumbtack.school.figures.v3.Point;
import java.util.Objects;

public class Point3D extends Point {
    private int z;

    public Point3D(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }

    public Point3D(Point point, int z) {
        this(point.getX(), point.getY(), z);
    }

    public Point3D() {
        this(0, 0, 0);
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public void moveTo(int newX, int newY, int newZ) {
        moveTo(newX, newY);
        z = newZ;
    }

    public void moveRel(int dx, int dy, int dz) {
        moveRel(dx, dy);
        z += dz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Point3D point3D = (Point3D) o;
        return z == point3D.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), z);
    }
}
