package com.week2.test.ticker;

import com.common.test.AbstractTest;
import com.common.test.Test;
import com.week2.solution.ticker.Company;
import com.week2.solution.ticker.Ticker;

/**
 * Created by Julia on 19.04.2017.
 */
@Test
public class TickerTest extends AbstractTest {


    @Test
    public void testInstantiation() {
        Ticker testTicker = Ticker.getInstance ();
        Ticker neuTicker = Ticker.getInstance ();

        if (testTicker == neuTicker) {
            System.out.println ("Singleton klappt");
        }
        else {
            System.out.println ("Singleton Fehler");
        }
    }

    @Test
    public void testTicker() {
        Company adidas = new Company ("Adidas", 301);
        Company fake = new Company ();
        Company google = new Company ("Google");
        Company unknown = new Company (501.2);

        adidas.changeStockPrice (401.5);
        fake.changeStockPrice(2);
        google.changeStockPrice(687);
        unknown.changeStockPrice(1);
        adidas.changeStockPrice(24);
    }

    @Test
    public void testFinalize() {
        Company fake = new Company ();
        fake.changeStockPrice(2);

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
