package com.week2.solution.ticker;

/**
 * Created by Julia on 19.04.2017.
 */
public class Company {

    private String name;
    private Double wert;
    private boolean insolvent = false;

    public Company (String name, double wert) {
        setName (name);
        setStockPrice (wert);
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

    /**
     * Aktualisiert den Wert einer Company, falls sie noch nicht insolvent ist
     * und ruft die Print-Methode des Tickers auf
     * @param d ein Wert
     */
    public void changeStockPrice (double d) {
        if (!insolvent) {
            setStockPrice(d);
            Ticker.getInstance().print(name + " " + d);
        }
    }

    /**
     * Destruktor, wird aufgerufen kurz bevor der Garbage Collector das Object zerstoert
     * Setzt die Company in den Insolvent-Zustand und gibt eine Mitteilung ueber den Ticker aus
     */
    protected void finalize () {
        Ticker.getInstance().print(name + " is insolvent");
        insolvent = true;
    }

    /**
     * Dastellung der Company
     * @return String der ueber Name und Wert oder ueber Insolvenz informiert
     */
    public String toString () {
        if (!insolvent) {
            return name + " " + wert;
        } else {
            return " Company is insolvent";
        }
    }

    /**
     * Erlaubt es von aussen den Namen der Company zu erfahren ohne ihn zu veraendern
     * @return String name
     */
    public String getName() { return name; }

    /**
     * Erlaubt es von aussen den Wert der Company zu erfahren, ohne ihn zu veraendern
     * @return Double wert
     */
    public Double getStockPrice() { return wert; }

    /**
     * Setzt einmalig den Namen der Firma auf uebergebenen Text
     * @param name
     */
    private void setName (String name) {
        this.name = name;
    }

    /**
     * Setzt den Wert der Firma solange er positiv, also gueltig ist
     * @param wert
     * @throws RuntimeException falls uebergebener Wert negativ
     */
    private void setStockPrice (double wert) {
        if (wert < 0) throw new RuntimeException("Stock Value cannot be negative ");
        this.wert = wert;
    }
}
