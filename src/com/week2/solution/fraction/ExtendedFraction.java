package com.week2.solution.fraction;

import com.week1.solution.fraction.Fraction;

/**
 * @author pascalstammer
 * @version 20.04.17.
 */
public class ExtendedFraction extends Fraction {

    /**
     * Erstellt einen neuen Bruch. Beim Erstellen wird der Bruch ggf. gekuerzt.
     *
     * @param numerator   Zaehler
     * @param denominator Nenner
     */
    public ExtendedFraction( final Integer numerator, final Integer denominator ) {
        super( numerator, denominator );
    }

    /**
     * Erstellt einen neuen Bruch mit Nenner numerator und Zaehler 1.
     *
     * @param numerator Zaehler
     */
    public ExtendedFraction( final Integer numerator ) {
        super( numerator );
    }

    public ExtendedFraction add(final Fraction fraction) {
        final Integer newNumerator = this.getNumerator() * fraction.getDenominator() + fraction.getNumerator() * this.getDenominator();
        final Integer newDenominator = this.getDenominator() * fraction.getDenominator();

        return new ExtendedFraction( newNumerator, newDenominator );
    }

    public ExtendedFraction substract(final Fraction fraction) {
        return add(fraction.multiply( -1 ));
    }

    public static Fraction parseFraction(final String s) {
        if(s.matches( "-?[\\d]+/[1-9]\\d*" )) {
            boolean isPositive = !s.contains( "-" );
            final String prep = (!isPositive ? s.replace( "-", "" ) : s);
            final String[] split = prep.split( "/" );
            final Integer numerator = Integer.parseInt( split[0] );
            final Integer denominator = Integer.parseInt( split[1] );
            return new Fraction( (isPositive ? 1 : -1) * numerator, denominator );
        } else {
            throw new RuntimeException( "Cannot parse " + s );
        }
    }
}
