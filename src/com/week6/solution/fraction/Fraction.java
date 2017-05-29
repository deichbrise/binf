package com.week6.solution.fraction;

import com.common.util.NumberUtil;

/**
 * Representiert einen Bruch. Der Status dieses Bruches ist immutable um Seiteneffekte zu verhindern. Alle Operationen
 * die den Bruch veraendern, erzeugen daraufhin einen neuen Bruch. Beim Erzeugen eines neuen Bruches wird dieses gekuerzt.
 *
 * @author pascalstammer
 * @version 05.04.17.
 */
public class Fraction extends Number {

    public static final String REGEX_FRACTION = "-?[\\d]+(/[1-9]\\d*)?";
    public static final String REGEX_PURE_FRACTION = "-?[\\d]+(/[1-9]\\d*)";
    public static final FractionObjectPool FRACTION_OBJECT_POOL = new FractionObjectPool();

    final private Integer numerator;
    final private Integer denominator;
    final private boolean positive;

    /**
     * Erstellt einen neuen Bruch. Beim Erstellen wird der Bruch ggf. gekuerzt.
     *
     * @param numerator Zaehler
     * @param denominator Nenner
     */
    private Fraction( final Integer numerator, final Integer denominator ) {
        positive = determineIsPositive( numerator, denominator );
        if(denominator == 1) {
            this.numerator = Math.abs( numerator ) * (positive ? 1 : -1);
            this.denominator = Math.abs( denominator );
        } else {
            final int gcd = gcd(numerator, denominator);
            this.numerator = Math.abs( numerator ) / gcd * (positive ? 1 : -1);;
            this.denominator = Math.abs( denominator ) / gcd;
        }
    }

    /**
     * Erstellt einen neuen Bruch mit Nenner numerator und Zaehler 1.
     *
     * @param numerator Zaehler
     */
    private Fraction( final Integer numerator ) {
        this(numerator, 1);
    }

    public static Fraction getInstance( final Integer numerator) {
        return getInstance( numerator, 1 );
    }

    public static Fraction getInstance( final Integer numerator, final Integer denominator) {
        final double value = numerator / (double)denominator;
        if( FRACTION_OBJECT_POOL.has( value )) {
            return FRACTION_OBJECT_POOL.get( value );
        } else {
            final Fraction fraction = new Fraction( numerator, denominator );
            FRACTION_OBJECT_POOL.add( fraction.doubleValue(), fraction );
            return fraction;
        }
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
    public Fraction multiply( Fraction factor) {
        return multiply( new Fraction[]{factor} );
    }

    /**
     * Multipliziert nacheinandern den aktuellen Bruch mit weiteren Bruechen.
     *
     * @param factors die Brueche mit denen multipliziert wird
     * @return einen neuen gekuerzten Bruch
     */
    public Fraction multiply( Fraction... factors) {
        Fraction buffer = this;
        for(Fraction fraction : factors) {
            buffer = Fraction.getInstance( buffer.numerator * fraction.numerator , buffer.denominator * fraction.denominator );
        }
        return buffer;
    }

    /**
     * Dividiert den aktuellen Bruch durch den gegeben Bruch.
     *
     * @param divisor den Teiler
     * @return einen neuen gekuerzten Bruch
     */
    public Fraction divide( Fraction divisor) {
        return multiply( Fraction.getInstance( divisor.denominator, divisor.numerator ) );
    }

    /**
     * Addition zweier Brueche
     * @param fraction Bruch der hinzugefügt werden soll
     * @return den Ergebnisbruch
     */
    public Fraction add( final Fraction fraction) {
        final Integer newNumerator = this.getNumerator() * fraction.getDenominator() + fraction.getNumerator() * this.getDenominator();
        final Integer newDenominator = this.getDenominator() * fraction.getDenominator();

        return Fraction.getInstance( newNumerator, newDenominator );
    }

    /**
     * Substraktion zweiter Brueche
     * @param fraction Bruch der vom anderen Bruch substrahiert werden soll
     * @return den Ergebnisbruch
     */
    public Fraction substract( final Fraction fraction) {
        return add(fraction.multiply( -1 ));
    }

    /**
     * Erzeugt aus einem String einen Bruch, wenn String valide
     * @param s den zu parsenden String
     * @return den geparsten Bruch
     * @throws NumberFormatException wenn der String nicht richtig formatiert ist
     */
    public static Fraction parseFraction( final String s) {
        if(s.matches( REGEX_FRACTION )) {
            boolean isPositive = !s.contains( "-" );
            final String[] split = s.replace( "-", "" ).split( "/" );
            if(split.length > 1) {
                final Integer numerator = Integer.parseInt( split[0] );
                final Integer denominator = Integer.parseInt( split[1] );
                return Fraction.getInstance( (isPositive ? 1 : -1) * numerator, denominator );
            } else {
                final Integer numerator = Integer.parseInt( split[0] );
                return Fraction.getInstance( (isPositive ? 1 : -1) * numerator );
            }
        } else {
            throw new NumberFormatException( "Cannot parse " + s );
        }
    }

    /**
     * Returns the value of the specified number as an {@code int},
     * which may involve rounding or truncation.
     *
     * @return the numeric value represented by this object after conversion
     * to type {@code int}.
     */
    @Override
    public int intValue() {
        return (int) (getNumerator() / (double)getDenominator());
    }

    /**
     * Returns the value of the specified number as a {@code long},
     * which may involve rounding or truncation.
     *
     * @return the numeric value represented by this object after conversion
     * to type {@code long}.
     */
    @Override
    public long longValue() {
        return (long) (getNumerator() / (double)getDenominator());
    }

    /**
     * Returns the value of the specified number as a {@code float},
     * which may involve rounding.
     *
     * @return the numeric value represented by this object after conversion
     * to type {@code float}.
     */
    @Override
    public float floatValue() {
        return (float) (getNumerator() / (double)getDenominator());
    }

    /**
     * Returns the value of the specified number as a {@code double},
     * which may involve rounding.
     *
     * @return the numeric value represented by this object after conversion
     * to type {@code double}.
     */
    @Override
    public double doubleValue() {
        return (getNumerator() / (double)getDenominator());
    }

    protected boolean determineIsPositive( Integer numerator, Integer denominator) {
        return numerator < 0 && denominator < 0 || numerator > 0 && denominator > 0;
    }

    protected int gcd(final Integer numerator, final Integer denominator) {
        return NumberUtil.greatestCommonDivisor( Math.abs( numerator ), Math.abs( denominator ) );
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
        if ( o == null || !getClass().isAssignableFrom( o.getClass() ) ) return false;

        final Fraction fraction = (Fraction) o;

        if ( positive != fraction.positive ) return false;
        if ( numerator != null ? !numerator.equals( fraction.numerator ) : fraction.numerator != null ) return false;
        return !(denominator != null ? !denominator.equals( fraction.denominator ) : fraction.denominator != null);

    }

    @Override
    public int hashCode() {
        int result = numerator != null ? numerator.hashCode() : 0;
        result = 31 * result + (denominator != null ? denominator.hashCode() : 0);
        result = 31 * result + (positive ? 1 : 0);
        return result;
    }
}
