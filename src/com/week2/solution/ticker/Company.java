package com.week2.solution.ticker;

/**
 * Created by Julia on 19.04.2017.
 */
public class Company {

    private String name;
    private Double wert;
    private boolean insolvent = false;
    private Ticker ticker;

    public Company( String name, double wert ) {
        setName( name );
        this.wert = wert;
    }

    public Company( String name ) {
        this( name, 0 );
    }

    public Company() {
        this( "unknown", 0 );
    }

    public Company( double wert ) {
        this( "unknown", wert );
    }

    public void changeStockPrice( double d ) {
        if ( !insolvent ) {
            Ticker.getInstance().print( name + " " + d );
            wert = d;
        }
    }

    public void finalize() {
        Ticker.getInstance().print( name + " is insolvent" );
        insolvent = true;
    }

    public String toString() {
        if ( !insolvent ) {
            return name + " " + wert;
        } else {
            return " Company is insolvent";
        }
    }

    public String getName() {
        return name;
    }

    public Double getWert() {
        return wert;
    }

    private void setName( String name ) {
        this.name = name;
    }

    protected Ticker getTicker() {
        if(ticker == null) {
            ticker = Ticker.getInstance();
        }
        return ticker;
    }
}
