package net.thumbtack.school.boxes.v3;

import net.thumbtack.school.figures.v3.Figure;

public class ArrayBox <T extends Figure> {

    private T[] array;

    public ArrayBox(T[] array) {
        this.array = array;
    }

    public T[] getContent() {
        return array;
    }

    public void setContent(T[] array) {
        this.array = array;
    }

    public T getElement(int i) {
        return array[i];
    }

    public void setElement(int i, T value) {
        array[i] = value;
    }

    public boolean isSameSize(ArrayBox<?> arrayBox) {
        return this.array.length == arrayBox.array.length;
    }

}
