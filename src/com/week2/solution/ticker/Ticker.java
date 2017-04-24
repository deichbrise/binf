package com.week2.solution.ticker;

/**
 * Created by Julia on 19.04.2017.
 */
public class Ticker {

    private static Ticker ticker;
    private static final String SEP = " +++ ";

    private Ticker () {}

    public static Ticker getInstance() {
        if (ticker == null) {
            ticker = new Ticker ();
        }
        return ticker;
    }

    public void print(final String text) {
        final StringBuilder builder = new StringBuilder();
        builder.append(SEP);
        builder.append(text);
        builder.append(SEP);

        System.out.println(builder.toString());
    }
}
