package com.week3.solution.geo;

/**
 * Subclass of volume. Consists of two 2-dimensional points. Has same behaviour as Volume.
 *
 * @author pascalstammer
 * @version 04.05.17.
 */
public class Rectangle extends Volume implements Comparable<Geometry> {

    public Rectangle( final Point2D leftTop, final Point2D rightBottom ) {
        super(leftTop, rightBottom);
    }
}
