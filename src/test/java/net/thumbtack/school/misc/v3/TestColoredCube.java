package net.thumbtack.school.misc.v3;

import net.thumbtack.school.colors.v3.ColorErrorCode;
import net.thumbtack.school.colors.v3.ColorException;
import net.thumbtack.school.figures.v3.Point;
import net.thumbtack.school.colors.v3.Color;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class TestColoredCube {

    private static final double DOUBLE_EPS = 1E-6;

    @Test
    public void testColoredCube1() throws ColorException {
        Point topLeft = new Point(10, 20);
        ColoredCube coloredCube = new ColoredCube(topLeft, 10, 0, Color.GREEN);
        assertAll(
                () -> assertEquals(10, coloredCube.getTopLeftFront().getX()),
                () -> assertEquals(20, coloredCube.getTopLeftFront().getY()),
                () -> assertEquals(0, coloredCube.getTopLeftFront().getZ()),
                () -> assertEquals(20, coloredCube.getBottomRightBehind().getX()),
                () -> assertEquals(30, coloredCube.getBottomRightBehind().getY()),
                () -> assertEquals(-10, coloredCube.getBottomRightBehind().getZ()),
                () -> assertEquals(Color.GREEN, coloredCube.getColor()),
                () -> assertEquals(coloredCube.getLength(), 10))
        ;
    }

    @Test
    public void testColoredCube1_1() throws ColorException {
        Point topLeft = new Point(10, 20);
        ColoredCube coloredCube = new ColoredCube(topLeft, 10, 0, "GREEN");
        assertAll(
                () -> assertEquals(10, coloredCube.getTopLeftFront().getX()),
                () -> assertEquals(20, coloredCube.getTopLeftFront().getY()),
                () -> assertEquals(0, coloredCube.getTopLeftFront().getZ()),
                () -> assertEquals(20, coloredCube.getBottomRightBehind().getX()),
                () -> assertEquals(30, coloredCube.getBottomRightBehind().getY()),
                () -> assertEquals(-10, coloredCube.getBottomRightBehind().getZ()),
                () -> assertEquals(Color.GREEN, coloredCube.getColor()),
                () -> assertEquals(coloredCube.getLength(), 10))
        ;
    }

    @Test
    public void testColloredCube2() throws ColorException {
        ColoredCube coloredCube = new ColoredCube(10, 20, 10, 0, Color.GREEN);
        assertAll(
                () -> assertEquals(10, coloredCube.getTopLeftFront().getX()),
                () -> assertEquals(20, coloredCube.getTopLeftFront().getY()),
                () -> assertEquals(0, coloredCube.getTopLeftFront().getZ()),
                () -> assertEquals(20, coloredCube.getBottomRightBehind().getX()),
                () -> assertEquals(30, coloredCube.getBottomRightBehind().getY()),
                () -> assertEquals(-10, coloredCube.getBottomRightBehind().getZ()),
                () -> assertEquals(Color.GREEN, coloredCube.getColor()),
                () -> assertEquals(10, coloredCube.getLength())
        );
    }

    @Test
    public void testColloredCube2_2() throws ColorException {
        ColoredCube coloredCube = new ColoredCube(10, 20, 10, 0, "GREEN");
        assertAll(
                () -> assertEquals(10, coloredCube.getTopLeftFront().getX()),
                () -> assertEquals(20, coloredCube.getTopLeftFront().getY()),
                () -> assertEquals(0, coloredCube.getTopLeftFront().getZ()),
                () -> assertEquals(20, coloredCube.getBottomRightBehind().getX()),
                () -> assertEquals(30, coloredCube.getBottomRightBehind().getY()),
                () -> assertEquals(-10, coloredCube.getBottomRightBehind().getZ()),
                () -> assertEquals(Color.GREEN, coloredCube.getColor()),
                () -> assertEquals(10, coloredCube.getLength())
        );
    }

    @Test
    public void testColloredCube3() throws ColorException {
        ColoredCube coloredCube = new ColoredCube(10);
        assertAll(
                () -> assertEquals(0, coloredCube.getTopLeftFront().getX()),
                () -> assertEquals(-10, coloredCube.getTopLeftFront().getY()),
                () -> assertEquals(0, coloredCube.getTopLeftFront().getZ()),
                () -> assertEquals(10, coloredCube.getBottomRightBehind().getX()),
                () -> assertEquals(0, coloredCube.getBottomRightBehind().getY()),
                () -> assertEquals(-10, coloredCube.getBottomRightBehind().getZ()),
                () -> assertEquals(Color.RED, coloredCube.getColor()),
                () -> assertEquals(10, coloredCube.getLength())
        );
    }

    @Test
    public void testColloredCube4() throws ColorException {
        ColoredCube coloredCube = new ColoredCube();
        assertAll(
                () -> assertEquals(0, coloredCube.getTopLeftFront().getX()),
                () -> assertEquals(-1, coloredCube.getTopLeftFront().getY()),
                () -> assertEquals(0, coloredCube.getTopLeftFront().getZ()),
                () -> assertEquals(1, coloredCube.getBottomRightBehind().getX()),
                () -> assertEquals(0, coloredCube.getBottomRightBehind().getY()),
                () -> assertEquals(-1, coloredCube.getBottomRightBehind().getZ()),
                () -> assertEquals(Color.RED, coloredCube.getColor()),
                () -> assertEquals(1, coloredCube.getLength())
        );
        coloredCube.setColor("BLUE");
        assertEquals(Color.BLUE,coloredCube.getColor());
    }

    @Test
    public void testSetCoordinates() throws ColorException {
        ColoredCube coloredCube = new ColoredCube(10, 20, 30, 0, Color.GREEN);
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
    public void testColoredCube5() throws ColorException {
        assertThrows(ColorException.class, () -> new ColoredCube(1, 1, 1, 1,(Color) null));
    }

    @Test
    public void testColoredCube6() throws ColorException {
        assertThrows(ColorException.class, () -> new ColoredCube( 1, 1, 1, 1, (String) null));
    }

    @Test
    public void testSetColor1() throws ColorException {
        ColoredCube coloredCube = new ColoredCube( 1, 1, 1, 1, Color.GREEN);
        assertAll(
                () -> assertEquals(1, coloredCube.getTopLeftFront().getX()),
                () -> assertEquals(1, coloredCube.getTopLeftFront().getY()),
                () -> assertEquals(1, coloredCube.getLength()),
                () -> assertEquals(Color.GREEN, coloredCube.getColor())
        );
        coloredCube.setColor(Color.BLUE);
        assertEquals(Color.BLUE, coloredCube.getColor());
    }

    @Test
    public void testSetColor2() throws ColorException {
        ColoredCube coloredCube = new ColoredCube( 1, 1, 1, 1, "GREEN");
        assertAll(
                () -> assertEquals(1, coloredCube.getTopLeftFront().getX()),
                () -> assertEquals(1, coloredCube.getTopLeftFront().getY()),
                () -> assertEquals(1, coloredCube.getLength()),
                () -> assertEquals(Color.GREEN, coloredCube.getColor())
        );
        coloredCube.setColor(Color.BLUE);
        assertEquals(Color.BLUE, coloredCube.getColor());
    }
    @Test
    public void testSetColor3() throws ColorException {
        ColoredCube coloredCube = new ColoredCube( 1, 1, 1, 1, "GREEN");
        assertAll(
                () -> assertEquals(1, coloredCube.getTopLeftFront().getX()),
                () -> assertEquals(1, coloredCube.getTopLeftFront().getY()),
                () -> assertEquals(1, coloredCube.getLength()),
                () -> assertEquals(Color.GREEN, coloredCube.getColor())
        );
        assertThrows(ColorException.class, () -> coloredCube.setColor((Color) null));
    }
    @Test
    public void testSetColor4() throws ColorException {
        ColoredCube coloredCube = new ColoredCube( 1, 1, 1, 1, "GREEN");
        assertAll(
                () -> assertEquals(1, coloredCube.getTopLeftFront().getX()),
                () -> assertEquals(1, coloredCube.getTopLeftFront().getY()),
                () -> assertEquals(1, coloredCube.getLength()),
                () -> assertEquals(Color.GREEN, coloredCube.getColor())
        );
        assertThrows(ColorException.class, () -> coloredCube.setColor((String) null));
    }

    @Test
    @SuppressWarnings("unused")
    public void testSetWrongConstructor() {
        assertAll(
                () -> assertThrows(ColorException.class, () -> {
                    ColoredCube coloredCube = new ColoredCube(0, 20, 10, 0, "YELLOW");
                }),
                () -> assertThrows(ColorException.class, () -> {
                    ColoredCube coloredCube1 = new ColoredCube(0, 20, 10, 0, (String) null);
                }),
                () -> assertThrows(ColorException.class, () -> {
                    ColoredCube coloredCube2 = new ColoredCube(0, 20, 10, 0, (Color) null);
                })
        );
    }

    @Test
    @SuppressWarnings("unused")
    public void testErrorCode() throws ColorException {
        ColoredCube coloredCube = new ColoredCube(1, 1, 1,1 , "GREEN");
        ColorException colorException = assertThrows(ColorException.class, () -> {
                coloredCube.setColor("YELLOW");
        });
        assertEquals(ColorErrorCode.WRONG_COLOR_STRING, colorException.getErrorCode());
        colorException = assertThrows(ColorException.class, () -> {
            coloredCube.setColor((String) null);
        });
        assertEquals(ColorErrorCode.NULL_COLOR, colorException.getErrorCode());
        colorException = assertThrows(ColorException.class, () -> {
            coloredCube.setColor((Color) null);
        });
        assertEquals(ColorErrorCode.NULL_COLOR, colorException.getErrorCode());
    }
}
