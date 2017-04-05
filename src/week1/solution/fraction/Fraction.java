package week1.solution.fraction;

/**
 * @author pascalstammer
 * @version 05.04.17.
 */
public class Fraction {
    private Integer numerator;
    private Integer denominator;

    public Fraction( final Integer numerator, final Integer denominator ) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Fraction( final Integer numerator ) {
        this(numerator, 1);
    }

    public Fraction multiply( final int factor ) {
        return multiply( new Fraction(factor) );
    }

    public Fraction multiply(Fraction factor) {
        return multiply( new Fraction[]{factor} );
    }

    public Fraction multiply(Fraction... factors) {
        Fraction buffer = this;
        for(Fraction fraction : factors) {
            buffer = new Fraction( buffer.numerator * fraction.numerator, buffer.denominator * fraction.denominator );
        }
        return buffer;
    }

    public Fraction divide(Fraction divisor) {
        return multiply( new Fraction( divisor.denominator, divisor.numerator ) );
    }

    public Integer getNumerator() {
        return numerator;
    }

    public void setNumerator( final Integer numerator ) {
        this.numerator = numerator;
    }

    public Integer getDenominator() {
        return denominator;
    }

    public void setDenominator( final Integer denominator ) {
        this.denominator = denominator;
    }
}
