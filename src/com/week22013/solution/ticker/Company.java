package com.week22013.solution.ticker;

import com.week22013.solution.ticker.impl.SimpleTicker;

import java.util.StringJoiner;

/**
 * @author pascalstammer
 * @version 13.04.17.
 */
public class Company {
    private String name;
    private double currentStockPrice = 0;
    private double previousStockPrice = 0;
    private Ticker ticker;

    public Company( final String name ) {
        setName( name );
        ticker = SimpleTicker.getInstance();
    }

    public void changeStockPrice( double d ) {
        previousStockPrice = currentStockPrice;
        currentStockPrice = d;
        onStockPriceChange();
    }

    protected void onStockPriceChange() {
        if(currentStockPrice != previousStockPrice) {
            pushStockPriceChangeTickerMessage();
        }
    }

    protected void pushStockPriceChangeTickerMessage() {
        final String message = buildStockPriceChangeMessage();
        ticker.print( message );
    }

    protected String buildStockPriceChangeMessage() {
        StringJoiner joiner = new StringJoiner( " " );
        joiner.add( "Aktienpreis der Firma" );
        joiner.add( getName() );
        joiner.add( "auf" );
        joiner.add( Double.toString( currentStockPrice ) );
        if(currentStockPrice > previousStockPrice) {
            joiner.add( "gestiegen" );
        } else {
            joiner.add( "gefallen" );
        }

        return joiner.toString();
    }

    public String getName() {
        return name;
    }

    public void setName( final String name ) {
        this.name = name;
    }
}
