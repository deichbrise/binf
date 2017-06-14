package com.week8.hungergames;

import java.lang.Math;

/**
 * Coordinate system represents circular arena with a 3 miles range
 * Center of arena is origin of coordinate system
 * Tributes localised outside or on borders of arena will be assigned to non-existant sector -1
 * Tributes localised in the circle's center will be assigned to sector 0
 * Tributes localised on borders between two sectors will be assigned to bigger one
 * Created by Julia on 14.06.2017.
 */
public class Arena {
    //Usefull values
    final double FULL = Math.PI * 2.0;
    final double HALF = Math.PI;
    final double Thirty = Math.PI / 6.0;

    /**
     * Calculates the sector a tribute with given coordinates is placed in
     * @param x
     * @param y
     * @return
     */
    public int getArea(double x, double y) {
        final int QUADRANT = determineQuadrant(x, y);
        double angle = determineAngle(x, y, QUADRANT);
        return (int) (angle / Thirty);

    }

    /**
     * Checks what quadrant of coordinate system the tribute is in for further calculations
     * @param x x-coordinate
     * @param y y-coordinate
     * @return one of four regular quadrants
     *          special cases -1 (outside arena's range)
     *          or 0 (origin coordinate system)
     */
    protected int determineQuadrant(double x, double y) {
        if (Math.hypot(x, y) >= 1.5) return -1;
        if (x == 0 && y == 0) return 0;
        if (x >= 0 && y > 0) return 1;
        if (x > 0 && y <= 0) return 2;
        if (x <= 0 && y < 0) return 3;
        if (x < 0 && y >= 0) return 4;
        return -1;
    }

    /**
     * Calculates the angle of tangent of coordinates
     * Depending on quadrant, returns correct angle between tribute 12 o'clock
     * @param x x-coordinate
     * @param y y-coordinate
     * @param QUADRANT calculated quadrant of tribute
     * @return angle in degree or 0 for origin or -THIRTY (outside arena's range)
     */
    protected double determineAngle(double x,double y, final int QUADRANT) {
        double tan = Math.atan2(x, y);

        switch (QUADRANT) {
            case 0 : return 0;
            case 1 :case 2 : return tan;
            case 3 : case 4 :
                if (tan == HALF) return tan;
                return FULL + tan;

            default: return -Thirty;
            }
    }

}
