package com.week7.test.enumeration;

import com.common.test.AbstractTest;
import com.common.test.Test;
import com.week7.solution.enumeration.DirectoryStructurePrinter;
import com.week7.solution.enumeration.FileEnumeration;

import java.io.File;
import java.nio.file.Files;

/**
 * @author pascalstammer
 * @version 11.06.17.
 */
@Test
public class FileEnumerationTest  extends AbstractTest {

    public static void main( String[] args ) {
        new FileEnumerationTest().runAllTests();
    }

    @Test
    public void testPrint() {
        final FileEnumeration fileEnumeration = new FileEnumeration( new File("/Users/pascalstammer/Documents/Uni/Semester 4") );
        fileEnumeration.accept( new DirectoryStructurePrinter() );
    }
}
