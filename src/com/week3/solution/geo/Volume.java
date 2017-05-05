package com.week3.solution.geo;

/**
 * Um unerwünschte Seiteneffekte und ungültige Zustände zu verhindern wurde diese Klasse bewusst als Immutable
 * designed. Alle verwendeten Klassen sind außerdem immutable.
 *
 * @author pascalstammer
 * @version 04.05.17.
 */
public class Volume extends Geometry implements Comparable<Geometry> {

    // Immutable
    private final Point minCorner;

    // Immutable
    private final Point maxCorner;

    /**
     *
     * @param minCorner
     * @param maxCorner
     */
    public Volume(final Point minCorner, final Point maxCorner) {
        super(minCorner.dimensions());
        if(minCorner.dimensions() != maxCorner.dimensions()) {
            throw new RuntimeException( "Dimensions of minCorner and maxCorner must be equal but are minCorner: " + minCorner.dimensions()
                    + " maxCorner: " + maxCorner.dimensions());
        }

        final int dim = minCorner.dimensions();
        final double[] minBuffer = new double[dim];
        final double[] maxBuffer = new double[dim];

        for(int i = 0; i < dim; i++) {
            minBuffer[i] = (minCorner.getCoordinate( i ) <= maxCorner.getCoordinate( i )) ? minCorner.getCoordinate( i ) : maxCorner.getCoordinate( i );
            maxBuffer[i] = (minCorner.getCoordinate( i ) <= maxCorner.getCoordinate( i )) ? maxCorner.getCoordinate( i ) : minCorner.getCoordinate( i );
        }

        this.minCorner = new Point( minBuffer );
        this.maxCorner = new Point( maxBuffer );
    }


    @Override
    public int compareTo( final Geometry o ) {
        return (int)(this.volume() - o.volume());
    }

    @Override
    public double volume() {
        double volumen = 1.0;
        final int dimension = minCorner.dimensions();
        for(int i = 0; i < dimension; i++) {
            volumen = maxCorner.getCoordinate( i ) - minCorner.getCoordinate( i );
        }
        return volumen;
    }

    @Override
    public Geometry encapsulate( final Geometry paramGeometry ) {
        if(this.dimensions() != paramGeometry.dimensions()) {
            return null;
        }

        if(!(paramGeometry instanceof Volume)) {
            throw new RuntimeException( "Only two Volumes can be encapsulate in each other." );
        }

        final Volume volume = (Volume)paramGeometry;
        final int dim = minCorner.dimensions();
        final double[] minBuffer = new double[dim];
        final double[] maxBuffer = new double[dim];

        for(int i = 0; i < dim; i++) {
            minBuffer[i] = (minCorner.getCoordinate( i ) <= volume.minCorner.getCoordinate( i )) ? minCorner.getCoordinate( i ) : volume.minCorner.getCoordinate( 0 );
            maxBuffer[i] = (maxCorner.getCoordinate( i ) >= volume.maxCorner.getCoordinate( i )) ? maxCorner.getCoordinate( i ) : volume.maxCorner.getCoordinate( 0 );
        }

        return new Volume( new Point( minBuffer ), new Point( maxBuffer ) );
    }
}
