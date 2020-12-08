package net.thumbtack.school.figures.v2;

import net.thumbtack.school.iface.v2.HasArea;
import net.thumbtack.school.iface.v2.Movable;
import net.thumbtack.school.iface.v2.Resizable;

abstract class Figure implements Movable, HasArea, Resizable {

    public abstract double getPerimeter();

    public abstract boolean isInside(int x, int y);

    public boolean isInside(Point point) {
        return isInside(point.getX(), point.getY());
    }

    public abstract boolean equals(Object o);

    public abstract int hashCode();
}
