package net.thumbtack.school.boxes.v3;

import net.thumbtack.school.iface.v3.HasArea;
//REVU: наследование нужно сделать от класса, а не от интерфейса
public class NamedBox<T extends HasArea> extends Box<T> {

    private String name;

    public NamedBox(T obj, String name) {
        super(obj);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
