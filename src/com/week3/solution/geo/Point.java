package com.week3.solution.geo;

/**
 * Stellt einen n-dimensionalen Punkt dar. Diese Klasse und alle abgeleiteten Klassen müssen immutable sein um
 * unerwünschte Seiteneffekte zu verhindern.
 *
 * @author pascalstammer
 * @version 04.05.17.
 */
public class Point extends Geometry implements Comparable<Geometry> {
    private final double[] coordinates;

    /**
     * Erstellt einen neuen n-dimensionalen Punkt basiertend auf den übergebenen Koordinaten
     * @param coordinates die Koordinaten, die den Punkt representieren
     */
    public Point( final double... coordinates ) {
        super(coordinates.length);
        this.coordinates = coordinates;
    }

    public double getCoordinate(int dim) {
        return coordinates[dim];
    }

    @Override
    public double volume() {
        // Volumen eines Punktes ist 0
        return 0;
    }

    /**
     * Erstellt eine Figur, die beide Körper einschließt. Dadraus resultiert ein n-dimensionales Volumina. Wenn
     * die Dimensionen ungleich sein, dann wird null zurückgegeben.
     * @param paramGeometry die miteinder zusammenzuführende Geometry
     * @return einen neuen Körper der beide Körper umschließt oder null, wenn Dimensionen nicht übereinstimmen
     */
    @Override
    public Geometry encapsulate( final Geometry paramGeometry ) {
        if(this.dimensions() != paramGeometry.dimensions()) {
            return null;
        }

        if(paramGeometry instanceof Point) {
            final Point point = (Point)paramGeometry;
            return new Volume( this, point );
        } else {
            return paramGeometry.encapsulate( new Volume( this, this ) );
        }
    }

    /**
     * Vergleich über die Volumen der Geometrien.
     * @param o
     * @return
     */
    @Override
    public int compareTo( final Geometry o ) {
        return (int)(this.volume() - o.volume());
    }
}
