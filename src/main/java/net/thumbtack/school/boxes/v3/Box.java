package net.thumbtack.school.boxes.v3;

import net.thumbtack.school.figures.v3.Figure;
import net.thumbtack.school.iface.v3.HasArea;

public class Box <T extends Figure> implements HasArea {

    private T obj;

    public Box(T obj) {
        this.obj = obj;
    }

    public T getContent() {
        return obj;
    }

    public void setContent(T obj) {
        this.obj = obj;
    }

    @Override
    public double getArea() {
        return obj.getArea();
    }

    public boolean isAreaEqual (Box<? extends Figure> obj) {
        return this.obj.getArea() == obj.getArea();
    }

    public static boolean isAreaEqual(Box<? extends Figure> obj1, Box<? extends Figure> obj2) {
        return obj1.getArea() == obj2.getArea();
    }
}
