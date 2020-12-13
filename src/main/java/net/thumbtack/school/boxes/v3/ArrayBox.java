package net.thumbtack.school.boxes.v3;

//REVU: не хватает наследования
public class ArrayBox <T> {

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
