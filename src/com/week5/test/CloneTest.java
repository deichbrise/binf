package com.week5.test;

import com.common.test.AbstractTest;
import com.common.test.Assert;
import com.common.test.Test;
import com.week5.solution.clone.LinkedList;

/**
 * Created by Julia on 21.05.2017.
 */
@Test
public class CloneTest extends AbstractTest {
    public static void main( String[] args ) {
        final CloneTest instance = new CloneTest();
        instance.runAllTests();
    }

    @Test
    public void listFunctions() {
        LinkedList<String> one = new LinkedList<>();
        Assert.assertTrue(one.empty());
        one.add("This");
        one.add("is");
        one.add("a");
        one.add("test");
        one.reset();
        Assert.assertEquals(one.elem(), "test");
        one.advance();
        one.advance();
        Assert.assertEquals(one.elem(), "is");
        one.advance();
        one.advance();
        Assert.assertTrue(one.endpos());
        one.reset();
        one.delete();
        one.delete();
        one.delete();
        one.delete();
        Assert.assertTrue(one.empty());
    }

    @Test
    public void testClone() throws CloneNotSupportedException {
        final LinkedList<String> original = new LinkedList<String>();
        final LinkedList<String> copy = original.clone();
        Assert.assertEquals(original.getClass(), copy.getClass());
        Assert.assertFalse(original == copy);

    }
}

