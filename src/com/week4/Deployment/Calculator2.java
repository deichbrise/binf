package com.week4.Deployment;

/**
 * Created by Julia on 11.05.2017.
 */
/**
 * Contains a main class to execute a Calculate instance, which calculates an
 * operation on two Fraction instances.
 *
 * @author Mathias Menninghaus (mathias.menninghaus@uos.de)
 */
public class Calculator2 {

    /*
     * The Operators
     */
    public static final String ADD = "+";
    public static final String SUBSTRACT = "-";
    public static final String MULTIPLY = "*";
    public static final String DIVIDE = "/";
    public static final String REGEX_NOT_FRACTION = "-?\\d+(.\\d+)?";

    /**
     * @param args
     */
    public static void main(String[] args) {

        if (args.length != 3) {
            System.err.println("invalid number of arguments (must be three)");
            printUsage();
            System.exit(1);
        } else {
            Calculator2 calc = new Calculator2();
            String result = calc.calc(args[0], args[1], args[2]);
            if (result == null) {
                System.err.println(calc.getErrorMessage());
                printUsage();
                System.exit(1);
            } else {
                System.out.println("= " + result);
            }
        }
    }

    /**
     * Prints a short description of the usage on the standard console.
     */
    private static void printUsage() {
        System.out.println("Usage: java Calculator fraction operator fraction");
        System.out.println("a fraction is defined by " + Fraction.REGEX);
        System.out.println("valid operators are +,-, *, /");
    }

    /**
     * Holds the error message of the last call of
     * {@link #calc(String, String, String)} which went wrong.
     */
    private String errorMessage;

    private Double calc(double operand1, String operator, double operand2) {
       double result;
        switch (operator) {

            case ADD:
                result = operand1 + operand2;
                break;
            case SUBSTRACT:
                result = operand1 - operand2;
                break;
            case MULTIPLY:
                result = operand1 * operand2;
                break;
            case DIVIDE:
                if (operand2 == 0) {
                    this.errorMessage = "divides zero";
                    return null;
                } else {
                    result = operand1 / operand2;
                }
                break;
            default:
                this.errorMessage = "Operation " + operator + " unknown";
                return null;
        }

        return result;

    }

    /**
     * Calculates the formula given by <code>a operator b</code>. If a, operator
     * or b are not valid, null will be returned and errorMessage will hold a
     * description of the error that occurred.
     *
     * @param a        the first Fraction argument
     * @param operator operator to connect the arguments with
     * @param b        the second Fraction argument
     * @return The result of the operation as Fraction or null.
     */
    private Fraction calc(Fraction a, String operator, Fraction b) {

        Fraction result;
      /*
       * differentiate between operators and compute regarding operation.
       */
        switch (operator) {

            case ADD:
                result = a.add(b);
                break;
            case SUBSTRACT:
                result = a.subtract(b);
                break;
            case MULTIPLY:
                result = a.multiply(b);
                break;
            case DIVIDE:
                if (b.getNumerator() == 0) {
                    this.errorMessage = "divides zero";
                    return null;
                } else {
                    result = a.divide(b);
                }
                break;
            default:
                this.errorMessage = "Operation " + operator + " unknown";
                return null;
        }

        return result;

    }

    /**
     * Calculates the formula given by <code>a operator b</code>. If a, operator
     * or b are not valid, null will be returned and errorMessage will hold a
     * description of the error that occurred.
     *
     * @param a        String representation of the first argument
     * @param operator operator to connect the arguments with
     * @param b        String representation of the second argument
     * @return The result of the operation as String or null.
     */
    public String calc(String a, String operator, String b) {

        if (checkIfFraction(a) && checkIfFraction(b)) {
            Fraction fractionA = parseFraction(a);
            Fraction fractionB = parseFraction(b);

            if (fractionA == null || fractionB == null || operator == null) {
                return null;
            }

            Fraction result = calc(fractionA, operator, fractionB);

            if (result == null) {
                return null;
            }

            return result.toString();
        }

        double operand1;
        double operand2;
        if(checkIfFraction(a)) {
            operand1 = parseFraction(a).doubleValue();
        } else {
            operand1 = parseDouble(a);
        }
        if(checkIfFraction(b)) {
            operand2 = parseFraction(b).doubleValue();
        } else {
            operand2 = parseDouble(b);
        }
        return calc(operand1, operator, operand2).toString();
    }

    /**
     * Return the error message of the last call of
     * {@link #calc(String, String, String)} which went wrong.
     *
     * @return the last error message
     */
    public String getErrorMessage() {
        return this.errorMessage;
    }

    /**
     * Parses the given String to a Fraction and returns it. If it cannot be
     * parsed, null will be returned and errorMessage will hold a description of
     * the error that occurred.
     *
     * @param fraction String to be parsed to a fraction
     * @return A Fraction representing the given String or null.
     */
    private Fraction parseFraction(String fraction) {
        if (!checkIfFraction(fraction)) {
            errorMessage = fraction + " is not a valid Fraction";
            return null;
        }
        return Fraction.parseFraction(fraction);

    }

    private boolean checkIfFraction(String operand) {
        if (operand.matches(Fraction.REGEX)) return true;
        return false;
    }

    private double parseDouble(String operand) {
        if (!operand.matches(REGEX_NOT_FRACTION)) {
            throw new RuntimeException("Parsing error");
        }
        return Double.parseDouble(operand);
    }

}
