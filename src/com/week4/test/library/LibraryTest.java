package com.week4.test.library;


import com.common.test.AbstractTest;
import com.common.test.Assert;
import com.common.test.Test;
import com.week4.solution.library.BluRay;
import com.week4.solution.library.Book;
import com.week4.solution.library.Library;
import com.week4.solution.library.List;

/**
 * Created by Julia on 15.05.2017.
 */

@Test
public class LibraryTest extends AbstractTest {

    public static void main( String[] args ) {
        final LibraryTest instance = new LibraryTest();
        instance.runAllTests();
    }

    @Test
    public void testBook() {
        final Book one = new Book( "Harry Potter", "J. K. Rowling" );
        Assert.assertEquals( one.getTitle(), "Harry Potter" );
        Assert.assertEquals( one.getAuthor(), "J. K. Rowling" );
        Assert.assertEquals( "Book: Harry Potter, J. K. Rowling", one.getDescription() );
        one.setBorrowed( true );
        Assert.assertTrue( one.isBorrowed() );
    }

    @Test
    public void testBluRay() {
        final BluRay one = new BluRay( "Inception", "Christopher Nolan" );
        Assert.assertEquals( one.getTitle(), "Inception" );
        Assert.assertEquals( one.getDirector(), "Christopher Nolan" );
        Assert.assertEquals( "BluRay: Inception, Christopher Nolan", one.getDescription() );
        Assert.assertFalse( one.isBorrowed() );
    }

    @Test
    public void testLibrary() {
        final BluRay one = new BluRay( "Inception", "Christopher Nolan" );
        final Book two = new Book( "Sense and Sensibility", "Jane Austen" );
        final Book three = new Book( "The Crucible", "Athur Miller" );
        final BluRay four = new BluRay( "Tarzan and Jane", "Steve Loter" );
        final Library instance = new Library();
        instance.addItem( one );
        instance.addItem( two );
        instance.addItem( four );
        instance.addItem( three );
        List list = instance.search( "Sherlock" );
        Assert.assertTrue( list.empty() );
        list = instance.search( "Jane" );
        list.advance();
        list.advance();
        Assert.assertTrue( list.endpos() );
        instance.deleteItem( four );
        list = instance.search( "Tarzan" );
        Assert.assertTrue( list.empty() );
    }
}
