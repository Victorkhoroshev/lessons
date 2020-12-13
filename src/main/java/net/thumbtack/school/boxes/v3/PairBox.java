package net.thumbtack.school.boxes.v3;

import net.thumbtack.school.figures.v3.Figure;
import net.thumbtack.school.iface.v3.HasArea;

public class PairBox <T extends Figure, V extends Figure> implements HasArea {

    private T first;
    private V second;

    public PairBox(T obj1, V obj2) {
        this.first = obj1;
        this.second = obj2;
    }


    public T getContentFirst() {
        return first;
    }

    public V getContentSecond () {
        return second;
    }

    public void setContentFirst(T obj) {
        first = obj;
    }

    public void setContentSecond(V obj) {
        second = obj;
    }

    @Override
    public double getArea() {
        return first.getArea() + second.getArea();
    }

    public boolean isAreaEqual (PairBox<? extends Figure, ? extends Figure> pairBox) {
        return this.getArea() == pairBox.getArea();
    }

    public static boolean isAreaEqual(PairBox<? extends Figure, ? extends Figure> pairBox1,
                                      PairBox<? extends Figure, ? extends Figure> pairBox2) {
        return pairBox1.getArea() == pairBox2.getArea();
    }
}
