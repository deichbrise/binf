package com.week2;

/**
 * Created by Julia on 19.04.2017.
 */
public class Ticker {

    private static Ticker ticker;

    private Ticker() {}

    /**
     * Methode um Instanz zu erzeugen, benutzt den private Constructor
     * Erstellt einmalig einen Singleton oder gibt bestehenden Ticker zurück
     * @return ticker, neu oder bereits vorhanden
     */
    public static Ticker getInstance() {
        if (ticker == null) {
            ticker = new Ticker();
        }
        return ticker;
    }

    /**
     * Gibt ohne Zeilenbruch den gegebenen Text aus
     * @param text
     */
    public void print(String text) {
        System.out.print("+++" + text);
    }
}
