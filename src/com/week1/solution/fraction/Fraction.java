package com.week1.solution.fraction;

import com.common.util.NumberUtil;

/**
 * Representiert einen Bruch. Der Status dieses Bruches ist immutable um Seiteneffekte zu verhindern. Alle Operationen
 * die den Bruch veraendern, erzeugen daraufhin einen neuen Bruch. Beim Erzeugen eines neuen Bruches wird dieses gekuerzt.
 *
 * @author pascalstammer
 * @version 05.04.17.
 */
public class Fraction {
    final private Integer numerator;
    final private Integer denominator;

    /**
     * Erstellt einen neuen Bruch. Beim Erstellen wird der Bruch ggf. gekuerzt.
     *
     * @param numerator Zaehler
     * @param denominator Nenner
     */
    public Fraction( final Integer numerator, final Integer denominator ) {
        if(denominator == 1) {
            this.numerator = numerator;
            this.denominator = denominator;
        } else {
            final int gcd = NumberUtil.greatestCommonDivisor( numerator, denominator );
            this.numerator = numerator / gcd;
            this.denominator = denominator / gcd;
        }
    }

    /**
     * Erstellt einen neuen Bruch mit Nenner numerator und Zaehler 1.
     *
     * @param numerator Zaehler
     */
    public Fraction( final Integer numerator ) {
        this(numerator, 1);
    }

    /**
     * Multipliziert den Bruch mit einem einfachen Faktor.
     *
     * @param factor der Faktor mit dem multipliziert werden soll.
     * @return einen neuen gekuerzten Bruch
     */
    public Fraction multiply( final int factor ) {
        return multiply( new Fraction(factor) );
    }

    /**
     * Multipliziert einen Bruch mit einem weiteren Bruch.
     *
     * @param factor der Bruch mit dem multipliziert werden soll
     * @return einen neuen gekuerzten Bruch
     */
    public Fraction multiply(Fraction factor) {
        return multiply( new Fraction[]{factor} );
    }

    /**
     * Multipliziert nacheinandern den aktuellen Bruch mit weiteren Bruechen.
     *
     * @param factors die Brueche mit denen multipliziert wird
     * @return einen neuen gekuerzten Bruch
     */
    public Fraction multiply(Fraction... factors) {
        Fraction buffer = this;
        for(Fraction fraction : factors) {
            buffer = new Fraction( buffer.numerator * fraction.numerator, buffer.denominator * fraction.denominator );
        }
        return buffer;
    }

    /**
     * Dividiert den aktuellen Bruch durch den gegeben Bruch.
     *
     * @param divisor den Teiler
     * @return einen neuen gekuerzten Bruch
     */
    public Fraction divide(Fraction divisor) {
        return multiply( new Fraction( divisor.denominator, divisor.numerator ) );
    }

    public Integer getNumerator() {
        return numerator;
    }

    public Integer getDenominator() {
        return denominator;
    }

    @Override
    public String toString() {
        return numerator.toString() + "/" + denominator.toString();
    }

    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        final Fraction fraction = (Fraction) o;

        if ( !numerator.equals( fraction.numerator ) ) return false;
        return denominator.equals( fraction.denominator );

    }

    @Override
    public int hashCode() {
        int result = numerator.hashCode();
        result = 31 * result + denominator.hashCode();
        return result;
    }
}
