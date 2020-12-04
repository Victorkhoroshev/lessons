package net.thumbtack.school.figures.v1;

import java.util.Objects;

public class ColoredRectangle extends Rectangle {
    private int color;

    public ColoredRectangle(Point leftTop, Point rightBottom, int color) {
        super(leftTop, rightBottom);
        this.color = color;
    }

    public ColoredRectangle(int xLeft, int yTop, int xRight, int yBottom, int color) {
        //REVU: не дублируйте код. Переиспользуйте конструкторы - this(...)
        super(xLeft, yTop, xRight, yBottom);
        this.color = color;
    }

    public ColoredRectangle(int length, int width, int color) {
        //REVU: не дублируйте код. Переиспользуйте конструкторы - this(...)
        super(length, width);
        this.color = color;
    }

    public ColoredRectangle(int color) {
        //REVU: не дублируйте код. Переиспользуйте конструкторы - this(...)
        super();
        this.color = color;
    }

    public ColoredRectangle() {
        //REVU: не дублируйте код. Переиспользуйте конструкторы - this(...)
        super();
        color = 1;
    }
    //REVU: не нужно переопределять родительский метод, если вы не добавляете никакой логики.
    @Override
    public Point getTopLeft() {
        return super.getTopLeft();
    }
    //REVU: не нужно переопределять родительский метод, если вы не добавляете никакой логики.
    @Override
    public Point getBottomRight() {
        return super.getBottomRight();
    }

    public int getColor() {
        return color;
    }
    //REVU: не нужно переопределять родительский метод, если вы не добавляете никакой логики.
    @Override
    public void setTopLeft(Point topLeft) {
        super.setTopLeft(topLeft);
    }
    //REVU: не нужно переопределять родительский метод, если вы не добавляете никакой логики.
    @Override
    public void setBottomRight(Point bottomRight) {
        super.setBottomRight(bottomRight);
    }

    public void setColor(int color) {
        this.color = color;
    }
    //REVU: не нужно переопределять родительский метод, если вы не добавляете никакой логики.
    @Override
    public int getLength() {
        return super.getLength();
    }
    //REVU: не нужно переопределять родительский метод, если вы не добавляете никакой логики.
    @Override
    public int getWidth() {
        return super.getWidth();
    }
    //REVU: не нужно переопределять родительский метод, если вы не добавляете никакой логики.
    @Override
    public void moveRel(int dx, int dy) {
        super.moveRel(dx, dy);
    }
    //REVU: не нужно переопределять родительский метод, если вы не добавляете никакой логики.
    @Override
    public void resize(double ratio) {
        super.resize(ratio);
    }
    //REVU: не нужно переопределять родительский метод, если вы не добавляете никакой логики.
    @Override
    public void stretch(double xRatio, double yRatio) {
        super.stretch(xRatio, yRatio);
    }
    //REVU: не нужно переопределять родительский метод, если вы не добавляете никакой логики.
    @Override
    public double getArea() {
        return super.getArea();
    }
    //REVU: не нужно переопределять родительский метод, если вы не добавляете никакой логики.
    @Override
    public double getPerimeter() {
        return super.getPerimeter();
    }
    //REVU: не нужно переопределять родительский метод, если вы не добавляете никакой логики.
    @Override
    public boolean isInside(int x, int y) {
        return super.isInside(x, y);
    }
    //REVU: не нужно переопределять родительский метод, если вы не добавляете никакой логики.
    @Override
    public boolean isInside(Point point) {
        return super.isInside(point);
    }

    public boolean isIntersects(ColoredRectangle rectangle) {
        return super.isIntersects(rectangle);
    }

    public boolean isInside(ColoredRectangle rectangle) {
        return super.isInside(rectangle);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ColoredRectangle that = (ColoredRectangle) o;
        return color == that.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), color);
    }
}
