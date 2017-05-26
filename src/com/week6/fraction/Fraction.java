package com.week6.fraction;

/**
 * Every instance of Fraction represents a fraction with numerator and
 * denominator.
 * 
 * @author Mathias Menninghaus (mathias.menninghaus@uos.de) (week4)
 *
 * altered by Julia Friesen (week6)
 */
public class Fraction extends Number {

   /**
    * The regular expression that defines the String representation of a
    * Fraction.
    */
   public static final String REGEX = "-?\\d+/[1-9]\\d*";

   /**
     * HashMap which contains all created instances of Fraction,
     * Key is the hashCode of the fraction's value (double)
     */
   private static java.util.Map<Double, Fraction> pool= new java.util.HashMap<Double, Fraction>();

   /**
    * Creates greatest common divisor for a and b.
    * 
    * @param a
    * @param b
    * @return the greatest common divisor for a and b.
    */
   public static int gcd(int a, int b) {
      return b == 0 ? a : gcd(b, a % b);
   }

   /**
    * Parses a Fraction from a String by using REGEX. Throws RuntimeException if
    * String s does not contain valid Fraction.
    * 
    * @param s
    *           String to be parsed
    * @return parsed String as Fraction
    * @throws RuntimeException
    *            if String s is not a valid Fraction
    */
   public static Fraction parseFraction(String s) {
      if (!s.matches(REGEX)) {
         throw new RuntimeException("Parsing error");
      }
      String[] splitted = s.split("/");
      return getInstance(Integer.parseInt(splitted[0]),
            Integer.parseInt(splitted[1]));
   }

   private int numerator;

   private int denominator;

   /**
    * Returns a Fraction object with numerator and denominator 1.
    * 
    * @param numerator
    */
   public static Fraction getInstance(int numerator) {

       return getInstance(numerator, 1);
   }

   /**
    * Creates a new Fraction object with numerator and denominator for the hashmap.
    * private constructor, activated by method getInstance
    * @param numerator
    * @param denominator
    */
   private Fraction(int numerator, int denominator) {
      /*
       * creates greatest common divisior.
       */
          int gcd = Fraction.gcd(numerator, denominator);
      /*
       * check sign, make denominator positive
       */
          if (denominator / gcd < 0) {
              gcd *= -1;
          }

          this.numerator = numerator / gcd;

          this.denominator = denominator / gcd;
   }

    /**
     * Returns existing fraction from hashmap
     * or creates new fraction by using private constructor.
     * Adds new fraction to hashmap.
     * @param numerator
     * @param denominator
     * @return fraction
     * @throws new RuntimeException if denominator == 0
     */
   public static Fraction getInstance(int numerator, int denominator) {
       if (denominator == 0) {
           throw new RuntimeException("denominator == 0 is not possible");
       }
       Double check = (double)numerator / denominator;
       if (pool.containsKey(check)) {
           return pool.get(check);
       } else {
           Fraction newone = new Fraction(numerator, denominator);
           pool.put(check, newone);
           return newone;
       }
   }
   /**
    * Adds addend to this Fraction and return the result as a new Fraction.
    * 
    * @param addend
    *           Fraction to be added
    * @return the sum as a new Fraction
    */
   public Fraction add(Fraction addend) {
      return getInstance(this.numerator * addend.denominator
            + this.denominator * addend.numerator, this.denominator
            * addend.denominator);
   }

   /**
    * Divides this Fraction with another Fraction
    * 
    * @param another
    *           Fraction to divide this Fraction by
    * @return quotient as a new Fraction
    */
   public Fraction divide(Fraction another) {
      return getInstance(this.numerator * another.denominator,
            this.denominator * another.numerator);
   }

   /**
    * 
    * @return denominator of this Fraction
    */
   public int getDenominator() {
      return this.denominator;
   }

   /**
    * 
    * @return numerator of this Fraction
    */
   public int getNumerator() {
      return this.numerator;
   }

   /**
    * Multiplies this Fraction with another Fraction
    * 
    * @param another
    *           Fraction to multiply this Fraction with
    * @return product as a new Fraction
    */
   public Fraction multiply(Fraction another) {
      return getInstance(this.numerator * another.numerator, this.denominator
            * another.denominator);
   }

   /**
    * Multiplies this Fraction with n.
    * 
    * @param n
    *           factor to multiply with
    * @return product as a new Fraction
    */
   public Fraction multiply(int n) {
      return getInstance(this.numerator * n, this.denominator);
   }

   /**
    * Multiplies this Fraction with all other Fraction instances given by
    * factors
    * 
    * @param factors
    *           Fraction instances to multiply this Fraction with
    * @return product as a new Fraction
    */
   public Fraction multiply(Fraction... factors) {
      Fraction result = this;
      for (int i = 0; i < factors.length; i++) {
         result = result.multiply(factors[i]);
      }
      return result;
   }

   /**
    * Subtracts subtrahend from this Fraction an returns the result as a new
    * Fraction.
    * 
    * @param subtrahend
    *           to be substracted Fraction
    * @return the difference as a new Fraction
    */
   public Fraction substract(Fraction subtrahend) {
      return getInstance(this.numerator * subtrahend.denominator
              - (this.denominator * subtrahend.numerator), this.denominator
            * subtrahend.denominator);
   }

   /**
    * Returns a string representation of this Fraction such as
    * numerator/denominator.
    * 
    * @return This Fraction as a String
    */
   public String toString() {
      return numerator + "/" + denominator;
   }

   @Override
   public int intValue() {
      return (int) (doubleValue() + 0.5);
   }

   @Override
   public long longValue() {
      return (long) (doubleValue() + 0.5);
   }

   @Override
   public float floatValue() {
      return (float) doubleValue();
   }

   @Override
   public double doubleValue() {
      return (double) this.getNumerator() / (double) this.getDenominator();
   }
}
