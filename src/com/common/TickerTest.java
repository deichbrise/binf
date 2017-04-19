package com.common;

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

        adidas.changeStockPrice (401.5);
        fake.changeStockPrice(2);
        google.changeStockPrice(687);
        unknown.changeStockPrice(1);
        adidas.changeStockPrice(-24);

        fake.finalize();
        System.out.println();
        System.out.println(fake);
        System.out.println("sollte sein: Company is insolvent");
        fake = null;
        System.out.println(fake);
        System.out.println("sollte sein : null");
        System.gc();



    }
}
