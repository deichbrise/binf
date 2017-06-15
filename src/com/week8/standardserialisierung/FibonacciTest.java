package com.week8.standardserialisierung;

import com.common.test.AbstractTest;
import com.common.test.Assert;
import com.common.test.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

/**
 * Created by Julia on 15.06.2017.
 */
public class FibonacciTest extends AbstractTest{
    public static  void main(String[] args) throws IOException, ClassNotFoundException {
        FibonacciTest test = new FibonacciTest();
        test.runAllTests();
    }

    @Test
    public void testSerialisation() {

        //Start with no serialised HashMap
        File file = new File("FibonacciHashMap.ser");
        file.delete();

        //Create new HashMap
        Fibonacci.main(new String[] {"0"});
        HashMap<Integer, Long> actual = new HashMap<>();
        actual.put(0, 0L);
        actual.put(1, 1L);
        tryCompare(actual);

        Fibonacci.main(new String[] {"10"});
        actual.put(2, 1L);
        actual.put(3, 2L);
        actual.put(4, 3L);
        actual.put(5, 5L);
        actual.put(6, 8L);
        actual.put(7, 13L);
        actual.put(8, 21L);
        actual.put(9, 34L);
        actual.put(10, 55L);
        tryCompare(actual);

        Fibonacci.main(new String[] {"11"});
        actual.put(11, 89L);
        tryCompare(actual);

        Fibonacci.main(new String[] {"11"});
        tryCompare(actual);

    }

    private void tryCompare(HashMap actual) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("FibonacciHashMap.ser"))) {
            HashMap fibi = (HashMap)in.readObject();
            Assert.assertEquals(actual, fibi);
        } catch (FileNotFoundException e) {
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
