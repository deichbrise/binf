package com.week3.solution.geo;

/**
 * @author pascalstammer
 * @version 04.05.17.
 */
public class Rectangle extends Volume implements Comparable<Geometry> {

    public Rectangle( final Point2D leftTop, final Point2D rightBottom ) {
        super(leftTop, rightBottom);
    }
}
