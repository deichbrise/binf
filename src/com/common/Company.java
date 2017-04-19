package com.common;

/**
 * Created by Julia on 19.04.2017.
 */
public class Company {

    private String name;
    private Double wert;
    private boolean insolvent = false;

    public Company (String name, double wert) {

        this.name = name;
        this.wert = wert;
    }

    public Company (String name) {

        this(name, 0);
    }

    public Company () {

        this("unknown", 0);
    }

    public Company (double wert) {

        this ("unknown", wert);
    }

    public void changeStockPrice (double d) {
        if (!insolvent) {
            Ticker.print(name + " " + d);
            wert = d;
        }
    }

    protected void finalize () {

        Ticker.print (name+" is insolvent");
        insolvent = true;
    }

    public String toString () {

        if (!insolvent) {
            return name + " " + wert;
        } else {
            return " Company is insolvent";
        }
    }
}
