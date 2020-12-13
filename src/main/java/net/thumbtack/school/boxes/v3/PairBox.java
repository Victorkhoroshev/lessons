package net.thumbtack.school.boxes.v3;

import net.thumbtack.school.iface.v3.HasArea;
//REVU: наследование нужно сделать от класса, а не от интерфейса
public class PairBox <T extends HasArea, V extends HasArea> implements HasArea {

    private T obj1;
    private V obj2;

    public PairBox(T obj1, V obj2) {
        this.obj1 = obj1;
        this.obj2 = obj2;
    }

    //REVU: геттер не соответствует имени объекта
    public T getContentFirst() {
        return obj1;
    }
    //REVU: геттер не соответствует имени объекта
    public V getContentSecond () {
        return obj2;
    }
    //REVU: сеттер не соответствует имени объекта
    public void setContentFirst(T obj) {
        obj1 = obj;
    }
    //REVU: сеттер не соответствует имени объекта
    public void setContentSecond(V obj) {
        obj2 = obj;
    }

    @Override
    public double getArea() {
        return obj1.getArea() + obj2.getArea();
    }

    public boolean isAreaEqual (PairBox<? extends HasArea, ? extends HasArea> pairBox) {
        return this.getArea() == pairBox.getArea();
    }

    public static boolean isAreaEqual(PairBox<? extends HasArea, ? extends HasArea> pairBox1,
                                      PairBox<? extends HasArea, ? extends HasArea> pairBox2) {
        return pairBox1.getArea() == pairBox2.getArea();
    }
}
