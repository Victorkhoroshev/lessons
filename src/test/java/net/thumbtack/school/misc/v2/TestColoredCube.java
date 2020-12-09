package net.thumbtack.school.misc.v2;

import net.thumbtack.school.figures.v2.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestColoredCube {

    private static final double DOUBLE_EPS = 1E-6;

    @Test
    public void testColoredCube1() {
        Point topLeft = new Point(10, 20);
        ColoredCube coloredCube = new ColoredCube(topLeft, 10, 0, 1);
        assertAll(
                () -> assertEquals(10, coloredCube.getTopLeftFront().getX()),
                () -> assertEquals(20, coloredCube.getTopLeftFront().getY()),
                () -> assertEquals(0, coloredCube.getTopLeftFront().getZ()),
                () -> assertEquals(20, coloredCube.getBottomRightBehind().getX()),
                () -> assertEquals(30, coloredCube.getBottomRightBehind().getY()),
                () -> assertEquals(-10, coloredCube.getBottomRightBehind().getZ()),
                () -> assertEquals(1, coloredCube.getColor()),
                () -> assertEquals(coloredCube.getLength(), 10))
        ;
        coloredCube.setColor(2);
        assertEquals(2, coloredCube.getColor());
    }

    @Test
    public void testColloredCube2() {
        ColoredCube coloredCube = new ColoredCube(10, 20, 10, 0, 1);
        assertAll(
                () -> assertEquals(10, coloredCube.getTopLeftFront().getX()),
                () -> assertEquals(20, coloredCube.getTopLeftFront().getY()),
                () -> assertEquals(0, coloredCube.getTopLeftFront().getZ()),
                () -> assertEquals(20, coloredCube.getBottomRightBehind().getX()),
                () -> assertEquals(30, coloredCube.getBottomRightBehind().getY()),
                () -> assertEquals(-10, coloredCube.getBottomRightBehind().getZ()),
                () -> assertEquals(1, coloredCube.getColor()),
                () -> assertEquals(10, coloredCube.getLength())
        );
    }

    @Test
    public void testColloredCube3() {
        ColoredCube coloredCube = new ColoredCube(10);
        assertAll(
                () -> assertEquals(0, coloredCube.getTopLeftFront().getX()),
                () -> assertEquals(-10, coloredCube.getTopLeftFront().getY()),
                () -> assertEquals(0, coloredCube.getTopLeftFront().getZ()),
                () -> assertEquals(10, coloredCube.getBottomRightBehind().getX()),
                () -> assertEquals(0, coloredCube.getBottomRightBehind().getY()),
                () -> assertEquals(-10, coloredCube.getBottomRightBehind().getZ()),
                () -> assertEquals(1, coloredCube.getColor()),
                () -> assertEquals(10, coloredCube.getLength())
        );
    }

    @Test
    public void testColloredCube4() {
        ColoredCube coloredCube = new ColoredCube();
        assertAll(
                () -> assertEquals(0, coloredCube.getTopLeftFront().getX()),
                () -> assertEquals(-1, coloredCube.getTopLeftFront().getY()),
                () -> assertEquals(0, coloredCube.getTopLeftFront().getZ()),
                () -> assertEquals(1, coloredCube.getBottomRightBehind().getX()),
                () -> assertEquals(0, coloredCube.getBottomRightBehind().getY()),
                () -> assertEquals(-1, coloredCube.getBottomRightBehind().getZ()),
                () -> assertEquals(1, coloredCube.getColor()),
                () -> assertEquals(1, coloredCube.getLength())
        );
    }

    @Test
    public void testSetCoordinates() {
        ColoredCube coloredCube = new ColoredCube(10, 20, 30, 0, 1);
        coloredCube.setLeftTopFrond(new Point3D(100, 50, 0));
        assertAll(
                () -> assertEquals(100, coloredCube.getTopLeftFront().getX()),
                () -> assertEquals(50, coloredCube.getTopLeftFront().getY()),
                () -> assertEquals(0, coloredCube.getTopLeftFront().getZ()),
                () -> assertEquals(130, coloredCube.getBottomRightBehind().getX()),
                () -> assertEquals(80, coloredCube.getBottomRightBehind().getY()),
                () -> assertEquals(-30, coloredCube.getBottomRightBehind().getZ()),
                () -> assertEquals(30, coloredCube.getLength())
        );
    }

    @Test
    public void testEqualsColoredCube() {
        ColoredCube coloredCube1 = new ColoredCube(new Point(10, 20), 30, 0, 1);
        ColoredCube coloredCube2 = new ColoredCube(10, 20, 30, 0, 1);
        ColoredCube coloredCube3 = new ColoredCube(20, 30, 40, 0, 1);
        assertEquals(coloredCube1, coloredCube2);
        assertNotEquals(coloredCube1, coloredCube3);
    }
}
