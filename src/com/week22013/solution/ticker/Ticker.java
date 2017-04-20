package com.week22013.solution.ticker;

/**
 * @author pascalstammer
 * @version 13.04.17.
 */
public interface Ticker {

    final String SEPERATOR = " +++ ";
    /**
     * Gibt den Wert text in der Konsole getrennt durch +++ aus. Dabei werden alle Umbrüche entfernt.
     * @param text der auszugebene Text
     */
    void print(String text);
}
