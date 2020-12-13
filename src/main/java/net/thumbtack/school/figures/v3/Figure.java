package net.thumbtack.school.figures.v3;

import net.thumbtack.school.iface.v3.HasArea;
import net.thumbtack.school.iface.v3.Movable;
import net.thumbtack.school.iface.v3.Resizable;

public abstract class Figure implements Movable, HasArea, Resizable {

    public abstract double getPerimeter();

    public abstract boolean isInside(int x, int y);

    public boolean isInside(Point point) {
        return isInside(point.getX(), point.getY());
    }
    //REVU: этот метод здесь лишний
    public abstract boolean equals(Object o);
    //REVU: этот метод здесь лишний
    public abstract int hashCode();
}
