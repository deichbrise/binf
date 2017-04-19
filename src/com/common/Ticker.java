package com.common;

/**
 * Created by Julia on 19.04.2017.
 */
public class Ticker {

    private static Ticker ticker;
    private static String current = "+++";

    private Ticker () {}

    public static Ticker getInstance() {

        if (ticker == null) ticker = new Ticker ();
        return ticker;

    }

    public static void print (String text) {

        current = "+++"+text;
        System.out.print(current);
    }
}
