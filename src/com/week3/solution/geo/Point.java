package com.week3.solution.geo;

/**
 * @author pascalstammer
 * @version 04.05.17.
 */
public class Point extends Geometry implements Comparable<Geometry> {
    private final double[] coordinates;

    public Point( final double... coordinates ) {
        super(coordinates.length);
        this.coordinates = coordinates;
    }

    public double getCoordinate(int dim) {
        return coordinates[dim];
    }

    /**
     * A point have no volume
     * @return
     */
    @Override
    public double volume() {
        return 0;
    }

    @Override
    public Geometry encapsulate( final Geometry paramGeometry ) {
        if(this.dimensions() != paramGeometry.dimensions()) {
            return null;
        }

        if(!(paramGeometry instanceof Point)) {
            throw new RuntimeException( "Only two Points can be encapsulate in each other." );
        }

        final Point point = (Point)paramGeometry;
        return new Volume( this, point );
    }

    @Override
    public int compareTo( final Geometry o ) {
        return (int)(this.volume() - o.volume());
    }
}
