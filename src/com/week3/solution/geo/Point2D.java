package com.week3.solution.geo;

/**
 * @author pascalstammer
 * @version 04.05.17.
 */
public class Point2D extends Point implements Comparable<Geometry> {

    public Point2D( final double x, final double y ) {
        super( x,y );
    }

    public double getX() {
        return getCoordinate( 0 );
    }

    public double getY() {
        return getCoordinate( 1 );
    }
}
