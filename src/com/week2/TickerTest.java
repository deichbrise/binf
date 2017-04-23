package com.week2;

/**
 * Created by Julia on 19.04.2017.
 */
public class TickerTest {

    public static void main( String[] args ) {

        Ticker testTicker = Ticker.getInstance ();
        Ticker neuTicker = Ticker.getInstance ();

        if (testTicker == neuTicker) {
            System.out.println ("Singleton klappt");
        }
        else {
            System.out.println ("Singleton Fehler");
        }

        Company adidas = new Company ("Adidas", 301);
        Company fake = new Company ();
        Company google = new Company ("Google");
        Company unknown = new Company (501.2);

        System.out.print ("toString-Methode klappt");
        if (!adidas.toString().equals("Adidas 301.0")) System.out.println(" nicht");
        System.out.println();

        System.out.println("Sollte eine Zeile sein:");
        adidas.changeStockPrice (401.5);
        fake.changeStockPrice(2);
        google.changeStockPrice(687);
        unknown.changeStockPrice(1);
        adidas.changeStockPrice(42);

        System.out.println();
        System.out.println("58 Companies werden auf null gesetzt, Garbage Collector wird aufgerufen:");

        for (int i = 65; i <= 122; i++) {
            char c = (char) i;
            Company bla = new Company(Character.toString((char) i), 1);
            bla = null;
        }
        System.gc();




    }
}
