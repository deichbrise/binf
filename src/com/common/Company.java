package com.common;

/**
 * Created by Julia on 19.04.2017.
 */
public class Company {

    private String name;
    private Double wert;
    private boolean insolvent = false;

    public Company (String name, double wert) {

        setName (name);
        setWert (wert);
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
            setWert (d);
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

    public String getName () { return name; }

    public Double getWert () { return wert; }

    private void setName (String name) {

        this.name = name;
    }

    private void setWert (double wert) {

        this.wert = wert;
    }
}
