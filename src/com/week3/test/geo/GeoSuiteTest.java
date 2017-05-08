package com.week3.test.geo;

import com.common.test.AbstractTest;
import com.common.test.Assert;
import com.common.test.Test;
import com.week3.solution.geo.Geometry;
import com.week3.solution.geo.Point;
import com.week3.solution.geo.Point2D;
import com.week3.solution.geo.Rectangle;
import com.week3.solution.geo.Volume;

/**
 * @author pascalstammer
 * @version 05.05.17.
 */
@Test
public class GeoSuiteTest extends AbstractTest {

    public static void main( String[] args ) {
        final GeoSuiteTest instance = new GeoSuiteTest();
        instance.runAllTests();
    }

    @Test
    public void testPointConstructor() {
        Point point = new Point(6, 4.3, 4.2, -5);
        double[] expected = {6, 4.3, 4.2, -5};
        int dim = point.dimensions();
        double[] actual = new double[dim];
        for (int i = 0; i < dim; i++) {
            actual[i] = point.getCoordinate(i);
        }
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void testPoint2DContructor() {
        Point2D point2 = new Point2D(42, -3.5);
        double[] expected = {42, -3.5};
        double[] actual = {point2.getX(), point2.getY()};
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void testRectangleConstructor() {
        Point2D one = new Point2D(1.5, 2.5);
        Point2D two = new Point2D(-6.9, 13.1);
        Rectangle rectangle = new Rectangle(one, two);
        // wie testet man das am besten
    }

    @Test
    public void testVolumeContructor() {
        Point one = new Point(2, 4, -1);
        Point two = new Point(0, 5.2, -5);
        Volume volume = new Volume(one, two);
        //wie testet man am besten)
    }

    @Test
    public void testPointCompareTo() {
        Point point = new Point (1, -2, 3, 4.5);
        Point same = new Point (1, -2, 3, 4.5);
        Point other = new Point(1, -2, -3, 4.5);
        Assert.assertTrue(point.compareTo(same) == 0);
        Assert.assertFalse(point.compareTo(other) == 0);
    }

    @Test
    public void testPoint2DCompareTo() {
        Point2D point = new Point2D(5.6, -2);
        Point2D same = new Point2D(5.6, -2);
        Point2D other = new Point2D(-5.6, -2);
        Assert.assertTrue(point.compareTo(same) == 0);
        Assert.assertFalse(point.compareTo(other) == 0);
    }

    @Test
    public void testRectangleCompareTo() {
        Point2D one = new Point2D(5, 7.8);
        Point2D two = new Point2D(2, -7.8);
        Point2D three = new Point2D(3, 1.8);
        Rectangle rectangle = new Rectangle(one, two);
        Rectangle same = new Rectangle(one, two);
        Rectangle other = new Rectangle(one, three);
        Assert.assertTrue(rectangle.compareTo(same) == 0);
        Assert.assertFalse(rectangle.compareTo(other) == 0);
    }

    @Test
    public void testVolumeCompareTo() {
        Point one = new Point(42, 6.7, 4.1, -2.2);
        Point two = new Point(24, 6.7, 1.4, -2.2);
        Point three = new Point(0, 1.7, -9.1, 2.2);
        Volume volume = new Volume(one, two);
        Volume same = new Volume(one, two);
        Volume other = new Volume(one, three);
        Assert.assertTrue(volume.compareTo(same) == 0);
        Assert.assertFalse(volume.compareTo(other) == 0);
    }

    @Test
    public void testPointVolume() {
        Point tester = new Point(5, -7.8, 0, -5.2);
        Assert.assertTrue(tester.volume() == 0);
    }

    @Test
    public void testPoint2DVolume() {
        Point2D tester = new Point2D(5, -7.8);
        Assert.assertTrue(tester.volume() == 0);
    }

    @Test
    public void testRectangleVolume() {
        Point2D one = new Point2D(0, 0);
        Point2D two = new Point2D(5, 4);
        Rectangle tester = new Rectangle(one, two);
        Assert.assertTrue(tester.volume() == 20);
    }

    @Test
    public void testVolumeVolume() {
        Point one = new Point(0, 0, 0, 0, 0);
        Point two = new Point(5, 4, 2, 1, 2);
        Volume tester = new Volume(one, two);
        Assert.assertTrue(tester.volume() == 80);
    }

    @Test
    public void testPointEncapsulate() {
        Point one = new Point(1,-3.4,0,8);
        Point two = new Point(3,-2,9.1,0);
        Volume expected = new Volume(one, two);
        Geometry actual = one.encapsulate(two);
        Assert.assertTrue(expected.volume() == actual.volume());
    }

    @Test
    public void testPoint2DEncapsulate() {
        Point2D one = new Point2D(1,-3.4);
        Point2D two = new Point2D(3,-2);
        Rectangle expected = new Rectangle(one, two);
        Geometry actual = one.encapsulate(two);
        Assert.assertTrue(expected.volume() == actual.volume());
    }

    @Test
    public void testRectangleEncapsulate() {
        Point2D one = new Point2D(1, 2.3);
        Point2D two = new Point2D(-10, 3);
        Point2D three = new Point2D(0, 6.3);
        Point2D four = new Point2D(4.5, 23);
        Point2D min = new Point2D(-10, 2.3);
        Point2D max = new Point2D(4.5, 23);
        Rectangle first = new Rectangle(one, two);
        Rectangle second = new Rectangle(three, four);
        Volume expected = new Volume(min, max);
        Geometry actual = first.encapsulate(second);
        Assert.assertTrue(expected.volume() == actual.volume());
    }

    @Test
    public void testVolumeEncapsulate() {
        Point one = new Point(1, 2.3, 24);
        Point two = new Point(-10, 3, 0);
        Point three = new Point(0, 6.3, 7);
        Point four = new Point(4.5, 23, 2);
        Point min = new Point(-10, 2.3, 0);
        Point max = new Point(4.5, 23, 24);
        Volume first = new Volume(one, two);
        Volume second = new Volume(three, four);
        Volume expected = new Volume(min, max);
        Geometry actual = first.encapsulate(second);
        Assert.assertTrue(expected.volume() == actual.volume());
    }
}
