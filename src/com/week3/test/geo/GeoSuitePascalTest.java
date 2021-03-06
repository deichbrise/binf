package com.week3.test.geo;

import com.common.test.AbstractTest;
import com.common.test.Assert;
import com.common.test.Test;
import com.week3.solution.geo.Geometry;
import com.week3.solution.geo.Point;
import com.week3.solution.geo.Point2D;
import com.week3.solution.geo.Rectangle;
import com.week3.solution.geo.Volume;
import org.w3c.dom.css.Rect;

/**
 * @author pascalstammer
 * @version 05.05.17.
 */
@Test
public class GeoSuitePascalTest extends AbstractTest {


    public static void main( String[] args ) {
        final GeoSuitePascalTest instance = new GeoSuitePascalTest();
        instance.runAllTests();
    }

    @Test
    public void testPointConstructor() {
        final Point point = new Point( 1, 2, 3, 4, 5, -5, 6 );

        Assert.assertEquals(1.0, point.getCoordinate( 0 ));
        Assert.assertEquals(2.0, point.getCoordinate( 1 ));
        Assert.assertEquals(3.0, point.getCoordinate( 2 ));
        Assert.assertEquals(4.0, point.getCoordinate( 3 ));
        Assert.assertEquals(5.0, point.getCoordinate( 4 ));
        Assert.assertEquals(-5.0, point.getCoordinate( 5 ));
        Assert.assertEquals(6.0, point.getCoordinate( 6 ));
    }

    @Test
    public void testPoint2DContructor() {
        final Point2D point2D = new Point2D( 1, -4 );

        Assert.assertEquals( 1.0, point2D.getX() );
        Assert.assertEquals( -4.0, point2D.getY() );
    }

    @Test
    public void testRectangleConstructor() {
        final Rectangle rectangle1 = new Rectangle( new Point2D( 1, 1 ), new Point2D( 2, 2 ) );

        Assert.assertEquals( 1.0, rectangle1.getMinCorner().getCoordinate( 0 ) );
        Assert.assertEquals( 1.0, rectangle1.getMinCorner().getCoordinate( 1 ) );
        Assert.assertEquals( 2.0, rectangle1.getMaxCorner().getCoordinate( 0 ) );
        Assert.assertEquals( 2.0, rectangle1.getMaxCorner().getCoordinate( 1 ) );

        final Rectangle rectangle2 = new Rectangle( new Point2D( 2, 2 ), new Point2D( 1, 1 ) );

        Assert.assertEquals( 1.0, rectangle2.getMinCorner().getCoordinate( 0 ) );
        Assert.assertEquals( 1.0, rectangle2.getMinCorner().getCoordinate( 1 ) );
        Assert.assertEquals( 2.0, rectangle2.getMaxCorner().getCoordinate( 0 ) );
        Assert.assertEquals( 2.0, rectangle2.getMaxCorner().getCoordinate( 1 ) );
    }

    @Test
    public void testVolumeContructor() {
        final Volume volume1 = new Volume( new Point( 1, 1, 1 ), new Point( 2, 2, 2 ) );
        final Volume volume2 = new Volume( new Point( 2, 2, 2 ), new Point( 1, 1, 1 ) );

        Assert.assertEquals( 1.0, volume1.getMinCorner().getCoordinate( 0 ) );
        Assert.assertEquals( 1.0, volume1.getMinCorner().getCoordinate( 1 ) );
        Assert.assertEquals( 1.0, volume1.getMinCorner().getCoordinate( 2 ) );
        Assert.assertEquals( 2.0, volume1.getMaxCorner().getCoordinate( 0 ) );
        Assert.assertEquals( 2.0, volume1.getMaxCorner().getCoordinate( 1 ) );
        Assert.assertEquals( 2.0, volume1.getMaxCorner().getCoordinate( 2 ) );

        Assert.assertEquals( 1.0, volume2.getMinCorner().getCoordinate( 0 ) );
        Assert.assertEquals( 1.0, volume2.getMinCorner().getCoordinate( 1 ) );
        Assert.assertEquals( 1.0, volume2.getMinCorner().getCoordinate( 2 ) );
        Assert.assertEquals( 2.0, volume2.getMaxCorner().getCoordinate( 0 ) );
        Assert.assertEquals( 2.0, volume2.getMaxCorner().getCoordinate( 1 ) );
        Assert.assertEquals( 2.0, volume2.getMaxCorner().getCoordinate( 2 ) );
    }

    @Test
    public void testPointCompareTo() {
        final Point point1 = new Point( 1, 1, 1 );
        final Point point2 = new Point( 2, 2, 2 );

        Assert.assertEquals( 0, point1.compareTo( point1 ) );
        Assert.assertEquals( -1, point1.compareTo( new Volume( point1, point2 ) ) );
    }

    @Test
    public void testPoint2DCompareTo() {
        final Point2D point2D1 = new Point2D( 1, 1 );
        final Point2D point2D2 = new Point2D( 2, 2 );

        Assert.assertEquals( 0, point2D1.compareTo( point2D2 ) );
        Assert.assertEquals( -1, point2D1.compareTo( new Rectangle( point2D1, point2D2 ) ) );
    }

    @Test
    public void testRectangleCompareTo() {
        final Rectangle rectangle1 = new Rectangle( new Point2D( 1, 1 ), new Point2D( 2, 2 ) );
        final Rectangle rectangle2 = new Rectangle( new Point2D( 1, 1 ), new Point2D( 3, 3 ) );

        Assert.assertEquals( 3, rectangle2.compareTo( rectangle1 ) );
    }

    @Test
    public void testVolumeCompareTo() {
        final Volume volume1 = new Volume( new Point( 1, 1, 1 ), new Point( 2, 2, 2 ) );
        final Volume volume2 = new Volume( new Point( 1, 1, 1 ), new Point( 3, 3, 3 ) );

        Assert.assertEquals( 7, volume2.compareTo( volume1 ) );
    }

    @Test
    public void testPointVolume() {
        final Point point = new Point( 1, 2, 3, 4 );

        Assert.assertEquals( 0.0, point.volume() );
    }

    @Test
    public void testPoint2DVolume() {
        final Point2D point2D = new Point2D( 1, 2 );

        Assert.assertEquals( 0.0, point2D.volume() );
    }

    @Test
    public void testRectangleVolume() {
        final Rectangle rectangle1 = new Rectangle( new Point2D( 1, 2 ), new Point2D( 4, 8 ) );
        final Rectangle rectangle2 = new Rectangle( new Point2D( -1, -2 ), new Point2D( -4, -8 ) );

        Assert.assertEquals( 18.0, rectangle1.volume() );
        Assert.assertEquals( rectangle1.volume(), rectangle2.volume() );
    }

    @Test
    public void testVolumeVolume() {
        final Volume volume1 = new Volume( new Point( 1, 2, 3 ), new Point(0, 3, 4) );
        final Volume volume2 = new Volume( new Point( -1, -2, -3 ), new Point(0, -3, -4) );

        Assert.assertEquals( 1.0, volume1.volume() );
        Assert.assertEquals( volume1.volume(), volume2.volume() );
    }

    @Test
    public void testPointEncapsulate() {
        final Point point1 = new Point( 1, 1, 1 );
        final Point point2 = new Point( 2, 2, 2 );

        final Volume volume = new Volume( new Point( 2, 2, 2 ), new Point( 4, 4, 4 ) );

        final Geometry encapsulation1 = point1.encapsulate( point2 );

        Assert.assertEquals( 3, encapsulation1.dimensions() );
        Assert.assertEquals( 1.0, encapsulation1.volume() );

        final Geometry encapsulation2 = point1.encapsulate( volume );

        Assert.assertEquals( 3, encapsulation2.dimensions() );
        Assert.assertEquals( 27.0, encapsulation2.volume() );
    }

    @Test
    public void testPoint2DEncapsulate() {
        final Point point1 = new Point2D( 1, 1 );
        final Point point2 = new Point2D( 2, 2 );

        final Rectangle rectangle = new Rectangle( new Point2D( 2, 2 ), new Point2D( 4, 4 ) );

        final Geometry encapsulation1 = point1.encapsulate( point2 );

        Assert.assertEquals( 2, encapsulation1.dimensions() );
        Assert.assertEquals( 1.0, encapsulation1.volume() );

        final Geometry encapsulation2 = point1.encapsulate( rectangle );

        Assert.assertEquals( 2, encapsulation2.dimensions() );
        Assert.assertEquals( 9.0, encapsulation2.volume() );
    }

    @Test
    public void testRectangleEncapsulate() {
        final Rectangle rectangle1 = new Rectangle( new Point2D( 1, 1 ), new Point2D( 2, 2 ) );
        final Rectangle rectangle2 = new Rectangle( new Point2D( 3, 3 ), new Point2D( 4, 4 ) );

        final Volume result = (Volume)rectangle1.encapsulate( rectangle2 );

        Assert.assertEquals( 2, result.dimensions() );
        Assert.assertEquals( 9.0, result.volume() );
        Assert.assertEquals( 1.0, result.getMinCorner().getCoordinate( 0 ) );
        Assert.assertEquals( 1.0, result.getMinCorner().getCoordinate( 1 ) );
        Assert.assertEquals( 4.0, result.getMaxCorner().getCoordinate( 0 ) );
        Assert.assertEquals( 4.0, result.getMaxCorner().getCoordinate( 1 ) );
    }

    @Test
    public void testVolumeEncapsulate() {
        final Volume volume1 = new Volume( new Point( 1, 1, 1 ), new Point( 2, 2, 2 ) );
        final Volume volume2 = new Volume( new Point( 3, 3, 3 ), new Point( 4, 4, 4 ) );

        final Volume result = (Volume)volume1.encapsulate( volume2 );

        Assert.assertEquals( 3, result.dimensions() );
        Assert.assertEquals( 27.0, result.volume() );
        Assert.assertEquals( 1.0, result.getMinCorner().getCoordinate( 0 ) );
        Assert.assertEquals( 1.0, result.getMinCorner().getCoordinate( 1 ) );
        Assert.assertEquals( 1.0, result.getMinCorner().getCoordinate( 2 ) );
        Assert.assertEquals( 4.0, result.getMaxCorner().getCoordinate( 0 ) );
        Assert.assertEquals( 4.0, result.getMaxCorner().getCoordinate( 1 ) );
        Assert.assertEquals( 4.0, result.getMaxCorner().getCoordinate( 2 ) );
    }
}
