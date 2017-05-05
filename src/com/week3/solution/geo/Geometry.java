package com.week3.solution.geo;

public abstract class Geometry
{
    private int dimension;

    public Geometry(int dimension)
    {
        if (dimension < 2) {
            throw new RuntimeException("dimension is < 2");
        }
        this.dimension = dimension;
    }

    public int dimensions()
    {
        return this.dimension;
    }

    public abstract double volume();

    public abstract Geometry encapsulate(Geometry paramGeometry);
}