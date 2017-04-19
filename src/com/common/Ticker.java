package com.common;

/**
 * Created by Julia on 19.04.2017.
 */
public class Ticker {

    private static Ticker ticker;

    private Ticker () {}

    public static Ticker getInstance() {

        if (ticker == null) ticker = new Ticker ();
        return ticker;

    }

    public static void print (String text) {

        System.out.print("+++"+text);
    }
}
