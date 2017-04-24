package com.week2.solution.fraction;

import com.week1.solution.fraction.Fraction;

/**
 * @author pascalstammer
 * @version 20.04.17.
 */
public class ExtendedFraction extends Fraction {

    public static final String REGEX_FRACTION = "-?[\\d]+(/[1-9]\\d*)?";

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

    /**
     * Addition zweier Brueche
     * @param fraction Bruch der hinzugefügt werden soll
     * @return den Ergebnisbruch
     */
    public ExtendedFraction add(final Fraction fraction) {
        final Integer newNumerator = this.getNumerator() * fraction.getDenominator() + fraction.getNumerator() * this.getDenominator();
        final Integer newDenominator = this.getDenominator() * fraction.getDenominator();

        return new ExtendedFraction( newNumerator, newDenominator );
    }

    /**
     * Substraktion zweiter Brueche
     * @param fraction Bruch der vom anderen Bruch substrahiert werden soll
     * @return den Ergebnisbruch
     */
    public ExtendedFraction substract(final Fraction fraction) {
        return add(fraction.multiply( -1 ));
    }

    /**
     * Erzeugt aus einem String einen Bruch, wenn String valide
     * @param s den zu parsenden String
     * @return den geparsten Bruch
     * @throws NumberFormatException wenn der String nicht richtig formatiert ist
     */
    public static ExtendedFraction parseFraction(final String s) {
        if(s.matches( REGEX_FRACTION )) {
            boolean isPositive = !s.contains( "-" );
            final String[] split = s.replace( "-", "" ).split( "/" );
            if(split.length > 1) {
                final Integer numerator = Integer.parseInt( split[0] );
                final Integer denominator = Integer.parseInt( split[1] );
                return new ExtendedFraction( (isPositive ? 1 : -1) * numerator, denominator );
            } else {
                final Integer numerator = Integer.parseInt( split[0] );
                return new ExtendedFraction( (isPositive ? 1 : -1) * numerator );
            }
        } else {
            throw new NumberFormatException( "Cannot parse " + s );
        }
    }
}
