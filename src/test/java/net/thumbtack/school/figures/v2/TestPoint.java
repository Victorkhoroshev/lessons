package net.thumbtack.school.figures.v2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPoint {
    @Test
    public void testPoint() {
        Point point = new Point();
        assertAll(
                () -> assertEquals(0, point.getX()),
                () -> assertEquals(0, point.getY())
        );
        point.setX(1);
        point.setY(1);
        assertAll(
                () -> assertEquals(1, point.getX()),
                () -> assertEquals(1, point.getY())
        );
    }
}
