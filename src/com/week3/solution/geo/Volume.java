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
     * Erstellt ein neues Volumina aus zwei n-dimensionalen Punkten, die in ihrer Dimension übereinstimmen müssen.
     * Außerdem werden die Koordinaten der zwei Ecken so miteinander verglichen, sodass danach in der einen Ecke die
     * kleinsten Werte stehen und in der anderen Ecke die groeßten Werte. So lässt sich später einfacher rechnen.
     *
     * @param minCorner kleine Ecke
     * @param maxCorner große Ecke
     */
    public Volume(final Point minCorner, final Point maxCorner) {
        super(minCorner.dimensions());
        if(minCorner.dimensions() != maxCorner.dimensions()) {
            throw new RuntimeException( "Dimensions of minCorner and maxCorner must be equal but are minCorner: "
                    + minCorner.dimensions() + " maxCorner: " + maxCorner.dimensions());
        }

        final int dim = minCorner.dimensions();
        final double[] minBuffer = new double[dim];
        final double[] maxBuffer = new double[dim];

        for(int i = 0; i < dim; i++) {
            minBuffer[i] = (minCorner.getCoordinate( i ) <= maxCorner.getCoordinate( i ))
                    ? minCorner.getCoordinate( i )
                    : maxCorner.getCoordinate( i );

            maxBuffer[i] = (minCorner.getCoordinate( i ) <= maxCorner.getCoordinate( i ))
                    ? maxCorner.getCoordinate( i )
                    : minCorner.getCoordinate( i );
        }

        this.minCorner = new Point( minBuffer );
        this.maxCorner = new Point( maxBuffer );
    }


    @Override
    public int compareTo( final Geometry o ) {
        return (int)(this.volume() - o.volume());
    }

    /**
     * Berechnet sich aus dem Produkt aller Kantenlängen
     * @return das Volumen
     */
    @Override
    public double volume() {
        double volumen = 1.0;
        final int dimension = minCorner.dimensions();
        for(int i = 0; i < dimension; i++) {
            volumen *= maxCorner.getCoordinate( i ) - minCorner.getCoordinate( i );
        }
        return volumen;
    }

    /**
     * Für alle Volumina gleich: Finde größte und kleinste Ecke und erstelle neues Volumina. Das funktioniert sogar
     * für ein Volumen, dass aus zwei gleichen Punkten aufgespannt wird.
     *
     * @param paramGeometry die zu enkapsulierende Geometry
     * @return ein neues Volumina mit der gleichen Dimension oder null, wenn Dimensionen nicht übereinstimmen
     */
    @Override
    public Geometry encapsulate( final Geometry paramGeometry ) {
        if(this.dimensions() != paramGeometry.dimensions()) {
            return null;
        }

        // Create a Volume if paramGeometry is Point
        Volume volume;
        if(paramGeometry instanceof Volume) {
            volume = (Volume)paramGeometry;
        } else {
            final Point point = (Point)paramGeometry;
            volume = new Volume( point, point );
        }

        final int dim = minCorner.dimensions();
        final double[] minBuffer = new double[dim];
        final double[] maxBuffer = new double[dim];

        for(int i = 0; i < dim; i++) {
            minBuffer[i] = (minCorner.getCoordinate( i ) <= volume.minCorner.getCoordinate( i ))
                    ? minCorner.getCoordinate( i )
                    : volume.minCorner.getCoordinate( i );

            maxBuffer[i] = (maxCorner.getCoordinate( i ) >= volume.maxCorner.getCoordinate( i ))
                    ? maxCorner.getCoordinate( i )
                    : volume.maxCorner.getCoordinate( i );
        }

        return new Volume( new Point( minBuffer ), new Point( maxBuffer ) );
    }

    public Point getMinCorner() {
        return minCorner;
    }

    public Point getMaxCorner() {
        return maxCorner;
    }
}
