package com.common.util.table;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pascalstammer
 * @version 04.04.17.
 */
public class AsciiTableObsolete {


    private final List<Column> columns = new ArrayList<>();
    private final List<Row> data = new ArrayList<>();
    private int maxColumnWidth = Integer.MAX_VALUE;

    public void print() {
        calculateColumnWidth();

        StringBuilder sb = new StringBuilder();

        writeSeparator( columns, sb );
        writeColumnNames( columns, sb );
        writeSeparator( columns, sb );

        writeValues( columns, data, sb );

        writeSeparator( columns, sb );

        System.out.println( sb.toString() );
    }

    private void writeColumnNames( final List<Column> columns, final StringBuilder sb ) {
        sb.append( "|" );
        for ( Column column : columns ) {
            sb.append( String.format( " %-" + (column.width) + "s", column.name ) );
            sb.append( "|" );
        }
        sb.append( "\n" );
    }

    private void writeSeparator( final List<Column> columns, final StringBuilder sb ) {
        sb.append( "+" );
        for ( Column column : columns ) {
            sb.append( String.format( "%-" + (column.width + 1) + "s", "" ).replace( ' ', '-' ) );
            sb.append( "+" );
        }
        sb.append( "\n" );
    }

    private void writeValues( final List<Column> columns, final List<Row> rows, final StringBuilder sb ) {
        for ( Row row : rows ) {
            int columnIdx = 0;
            sb.append( "|" );
            for ( String value : row.values ) {

                if ( value != null && value.length() > maxColumnWidth )
                    value = value.substring( 0, maxColumnWidth - 1 );

                sb.append( String.format( " %-" + columns.get( columnIdx ).width + "s", value ) );
                sb.append( "|" );

                columnIdx++;
            }
            sb.append( "\n" );
        }
    }

    private void calculateColumnWidth() {

        for ( Column column : columns ) {
            column.width = column.name.length() + 1;
        }

        for ( Row row : data ) {
            int columnIndex = 0;
            for ( String value : row.values ) {
                Column column = columns.get( columnIndex );
                if ( value == null ) {
                    continue;
                }

                column.width = Math.max( column.width, value.length() + 1 );
                columnIndex++;
            }
        }

        for ( Column column : columns ) {
            column.width = Math.min( column.width, maxColumnWidth );
        }
    }

    public Column addColumn(final String name) {
        final Column column = new Column( name );
        getColumns().add( column );
        return column;
    }

    public Column addColumn(final Column column) {
        getColumns().add( column );
        return column;
    }

    public Row newRow() {
        final Row row = new Row();
        getData().add( row );
        return row;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public List<Row> getData() {
        return data;
    }

    public int getMaxColumnWidth() {
        return maxColumnWidth;
    }

    public void setMaxColumnWidth( final int maxColumnWidth ) {
        this.maxColumnWidth = maxColumnWidth;
    }

    public static class Column {

        private String name;
        private int width;

        public Column( final String name ) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Column{" +
                    "name='" + name + '\'' +
                    ", width=" + width +
                    '}';
        }
    }

    public static class Row {

        private final List<String> values = new ArrayList<>();

        public List<String> getValues() {
            return values;
        }

        public Row addValue(final String value) {
            getValues().add( value );
            return this;
        }

        @Override
        public String toString() {
            return "Row{" +
                    "values=" + values +
                    '}';
        }
    }
}
