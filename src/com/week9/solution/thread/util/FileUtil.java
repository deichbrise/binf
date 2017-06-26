package com.week9.solution.thread.util;

import com.week9.solution.thread.collector.FileCollector;
import com.week9.solution.thread.collector.impl.FileCollectorImpl;

import java.io.File;
import java.math.BigInteger;
import java.util.List;

/**
 * @author pascalstammer
 * @version 24.06.17.
 */
public class FileUtil {

    private static final int NOT_FOUND = -1;
    public static final char EXTENSION_SEPARATOR = '.';
    private static final char UNIX_SEPARATOR = '/';
    private static final char WINDOWS_SEPARATOR = '\\';
    public static final long ONE_KB = 1024;
    public static final BigInteger ONE_KB_BI = BigInteger.valueOf( ONE_KB );
    public static final BigInteger ONE_MB_BI = ONE_KB_BI.multiply( ONE_KB_BI );
    public static final BigInteger ONE_GB_BI = ONE_KB_BI.multiply( ONE_MB_BI );
    public static final BigInteger ONE_TB_BI = ONE_KB_BI.multiply( ONE_GB_BI );
    public static final BigInteger ONE_PB_BI = ONE_KB_BI.multiply( ONE_TB_BI );
    public static final BigInteger ONE_EB_BI = ONE_KB_BI.multiply( ONE_PB_BI );

    public static List<File> listFilesRecursively( final File file ) {
        return listFilesRecursively( file, null );
    }

    public static List<File> listFilesRecursively( final File file, final String extension ) {
        final FileCollector fileCollector = new FileCollectorImpl();
        if ( extension != null ) {
            fileCollector.addFilter( file1 -> file1.isFile() && getExtension( file1.getAbsolutePath() ).equals( extension ) );
        }

        return fileCollector.collect( file );
    }

    public static String getExtension( final String filename ) {
        if ( filename == null ) {
            return null;
        }
        final int index = indexOfExtension( filename );
        if ( index == NOT_FOUND ) {
            return "";
        } else {
            return filename.substring( index + 1 );
        }
    }

    public static int indexOfExtension( final String filename ) {
        if ( filename == null ) {
            return NOT_FOUND;
        }
        final int extensionPos = filename.lastIndexOf( EXTENSION_SEPARATOR );
        final int lastSeparator = indexOfLastSeparator( filename );
        return lastSeparator > extensionPos ? NOT_FOUND : extensionPos;
    }

    public static int indexOfLastSeparator( final String filename ) {
        if ( filename == null ) {
            return NOT_FOUND;
        }
        final int lastUnixPos = filename.lastIndexOf( UNIX_SEPARATOR );
        final int lastWindowsPos = filename.lastIndexOf( WINDOWS_SEPARATOR );
        return Math.max( lastUnixPos, lastWindowsPos );
    }

    public static long getSize( File file ) {
        final List<File> files = listFilesRecursively( file );

        long size = 0;

        for ( final File file1 : files ) {
            size += file1.length();
        }

        return size;
    }

    public static String byteCountToDisplaySize( BigInteger size ) {
        String displaySize;

        if ( size.divide( ONE_EB_BI ).compareTo( BigInteger.ZERO ) > 0 ) {
            displaySize = String.valueOf( size.divide( ONE_EB_BI ) ) + " EB";
        } else if ( size.divide( ONE_PB_BI ).compareTo( BigInteger.ZERO ) > 0 ) {
            displaySize = String.valueOf( size.divide( ONE_PB_BI ) ) + " PB";
        } else if ( size.divide( ONE_TB_BI ).compareTo( BigInteger.ZERO ) > 0 ) {
            displaySize = String.valueOf( size.divide( ONE_TB_BI ) ) + " TB";
        } else if ( size.divide( ONE_GB_BI ).compareTo( BigInteger.ZERO ) > 0 ) {
            displaySize = String.valueOf( size.divide( ONE_GB_BI ) ) + " GB";
        } else if ( size.divide( ONE_MB_BI ).compareTo( BigInteger.ZERO ) > 0 ) {
            displaySize = String.valueOf( size.divide( ONE_MB_BI ) ) + " MB";
        } else if ( size.divide( ONE_KB_BI ).compareTo( BigInteger.ZERO ) > 0 ) {
            displaySize = String.valueOf( size.divide( ONE_KB_BI ) ) + " KB";
        } else {
            displaySize = String.valueOf( size ) + " bytes";
        }
        return displaySize;
    }


    public static String byteCountToDisplaySize( long size ) {
        return byteCountToDisplaySize( BigInteger.valueOf( size ) );
    }
}
