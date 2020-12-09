package net.thumbtack.school.misc.v3;

import net.thumbtack.school.figures.v3.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestBoll {

    private static final double DOUBLE_EPS = 1E-6;

    @Test
    public void testBoll1() {
        Point center = new Point(10, 20);
        Boll boll = new Boll(center, 10);
        assertAll(
                () -> assertEquals(10, boll.getCenter().getX()),
                () -> assertEquals(20, boll.getCenter().getY()),
                () -> assertEquals(0, boll.getCenter().getZ()),
                () -> assertEquals(10, boll.getRadius())
        );
    }
    @Test
    public void testBoll2() {
        Boll boll = new Boll(10, 20, 10, 0);
        assertAll(
                () -> assertEquals(10, boll.getCenter().getX()),
                () -> assertEquals(20, boll.getCenter().getY()),
                () -> assertEquals(0, boll.getCenter().getZ()),
                () -> assertEquals(10, boll.getRadius())
        );
    }

    @Test
    public void testBoll3() {
        Boll boll = new Boll(10);
        assertAll(
                () -> assertEquals(0, boll.getCenter().getX()),
                () -> assertEquals(0, boll.getCenter().getY()),
                () -> assertEquals(0, boll.getCenter().getZ()),
                () -> assertEquals(10, boll.getRadius())
        );
    }

    @Test
    public void testBoll4() {
        Boll boll = new Boll();
        assertAll(
                () -> assertEquals(0, boll.getCenter().getX()),
                () -> assertEquals(0, boll.getCenter().getY()),
                () -> assertEquals(0, boll.getCenter().getZ()),
                () -> assertEquals(1, boll.getRadius())
        );
    }

    @Test
    public void testPoint3D() {
        Point3D point3D = new Point3D();
        assertAll(
                () -> assertEquals(0, point3D.getX()),
                () -> assertEquals(0, point3D.getY()),
                () -> assertEquals(0, point3D.getZ())
        );
    }

    @Test
    public void testPoint3D2() {
        Point3D point3D = new Point3D();
        point3D.setZ(1);
        assertAll(
                () -> assertEquals(0, point3D.getX()),
                () -> assertEquals(0, point3D.getY()),
                () -> assertEquals(1, point3D.getZ())
        );
    }

    @Test
    public void testSetCenterAndRadius() {
        Boll boll = new Boll(10, 20, 10, 10);
        boll.setCenter(new Point3D(100, 50, 20));
        boll.setRadius(200);
        assertAll(
                () -> assertEquals(100, boll.getCenter().getX()),
                () -> assertEquals(50, boll.getCenter().getY()),
                () -> assertEquals(20, boll.getCenter().getZ()),
                () -> assertEquals(200, boll.getRadius())
        );
    }
    @Test
    public void testMoveBoll() {
        Boll boll = new Boll(10, 20, 10,10);
        boll.moveRel(100, 50, 60);
        assertAll(
                () -> assertEquals(110, boll.getCenter().getX()),
                () -> assertEquals(70, boll.getCenter().getY()),
                () -> assertEquals(70, boll.getCenter().getZ()),
                () -> assertEquals(10, boll.getRadius())
        );
        boll.moveTo(100, 200, 0);
        assertAll(
                () -> assertEquals(100, boll.getCenter().getX()),
                () -> assertEquals(200, boll.getCenter().getY()),
                () -> assertEquals(0, boll.getCenter().getZ()),
                () -> assertEquals(10, boll.getRadius())
        );
        boll.moveTo(new Point(1100, 1200), 2000);
        assertAll(
                () -> assertEquals(1100, boll.getCenter().getX()),
                () -> assertEquals(1200, boll.getCenter().getY()),
                () -> assertEquals(2000, boll.getCenter().getZ()),
                () -> assertEquals(10, boll.getRadius())
        );
    }

    @Test
    public void testResizeBoll1() {
        Boll boll = new Boll(10, 20, 10, 30);
        boll.resize(5);
        assertAll(
                () -> assertEquals(10, boll.getCenter().getX()),
                () -> assertEquals(20, boll.getCenter().getY()),
                () -> assertEquals(30, boll.getCenter().getZ()),
                () -> assertEquals(50, boll.getRadius())
        );
    }

    @Test
    public void testResizeBoll2() {
        Boll boll = new Boll(10, 20, 10, 30);
        boll.resize(0.3);
        assertAll(
                () -> assertEquals(10, boll.getCenter().getX()),
                () -> assertEquals(20, boll.getCenter().getY()),
                () -> assertEquals(30, boll.getCenter().getZ()),
                () -> assertEquals(3, boll.getRadius())
        );
    }
    @Test
    public void testAreaBoll() {
        Boll boll = new Boll(10, 20, 10, 0);
        assertEquals(Math.PI * 400, boll.getArea(), DOUBLE_EPS);
    }

    @Test
    public void testVolumeBoll() {
        Boll boll = new Boll(10, 20, 10, 0);
        assertEquals( 4 / 3d * Math.PI * 1000, boll.getVolume(), DOUBLE_EPS);
    }

    @Test
    public void testIsPointInsideBoll1() {
        Boll boll = new Boll(10, 10, 10, 0);
        assertAll(
                () -> assertTrue(boll.isInside(10, 10, 0)),
                () -> assertTrue(boll.isInside(20, 10, 0)),
                () -> assertTrue(boll.isInside(10, 20, 0)),
                () -> assertTrue(boll.isInside(15, 15, 0)),
                () -> assertFalse(boll.isInside(18, 18 , 0))
        );
    }

    @Test
    public void testIsPointInsideBoll2() {
        Boll boll = new Boll(new Point3D(10, 10, 1), 10);
        assertAll(
                () -> assertTrue(boll.isInside(new Point3D(10, 10, 10))),
                () -> assertTrue(boll.isInside(new Point3D(20, 10, 0))),
                () -> assertTrue(boll.isInside(new Point3D(10, 20, 0))),
                () -> assertTrue(boll.isInside(new Point3D(15, 15, 0))),
                () -> assertFalse(boll.isInside(new Point3D(18, 18, 0)))
        );
    }

    @Test
    public void testEqualsBoll() {
        Boll boll1 = new Boll(new Point3D(10, 10, 0), 10);
        Boll boll2 = new Boll(new Point3D(10, 10,0), 10);
        Boll boll3 = new Boll(new Point3D(10, 10,0), 20);
        Boll boll4 = new Boll(new Point3D(0, 0,0), 10);
        assertEquals(boll1, boll2);
        assertNotEquals(boll1, boll3);
        assertNotEquals(boll1, boll4);
    }



}
