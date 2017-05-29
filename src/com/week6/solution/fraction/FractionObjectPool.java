package com.week6.solution.fraction;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pascalstammer
 * @version 27.05.17.
 */
public class FractionObjectPool {

    private Map<Double, Fraction> registry = new HashMap<>();
    public boolean has(final Double key) {
        return registry.containsKey( key );
    }

    public void add(final Double key, final Fraction fraction) {
        registry.put( key, fraction );
    }

    public Fraction get(final Double key) {
        return registry.get( key );
    }

}
