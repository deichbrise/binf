package com.week8.heap;

import com.common.test.AbstractTest;
import com.common.test.Assert;
import com.common.test.Test;

import java.beans.XMLDecoder;
import java.io.*;
import java.beans.XMLEncoder;

/**
 * Created by Julia on 15.06.2017.
 */
public class HeapTest extends AbstractTest {
    public static void main(String[] args) throws FileNotFoundException {
        HeapTest test = new HeapTest();
        test.runAllTests();
    }

    private Heap serializeXML(Heap heap) {
        try (XMLEncoder e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("Heap.xml")))) {
            e.writeObject(heap);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }


        try (XMLDecoder in= new XMLDecoder(new BufferedInputStream(new FileInputStream("Heap.xml")))){
            return (Heap)in.readObject();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    private Heap serialize(Heap heap) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Heap.xml"))) {
            out.writeObject(heap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("Heap.xml"))){
            return (Heap)in.readObject();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public void testSerializationInteger() throws FileNotFoundException {
        Heap heap = new Heap();
        heap.insert(3);
        heap.insert(7);
        heap.insert(-9);
        heap.insert(0);

        Heap deserialized = serializeXML(heap);
        for (int i = 0; i < heap.getSize(); i++) {
            Assert.assertEquals(heap.deleteFirst(), deserialized.deleteFirst());
        }

        deserialized = serialize(heap);
        for (int i = 0; i < heap.getSize(); i++) {
            Assert.assertEquals(heap.deleteFirst(), deserialized.deleteFirst());
        }
    }

    @Test
    public void testSizeInteger() {
        Heap heap = new Heap();
        heap.insert(3);
        heap.insert(7);
        heap.insert(-9);
        heap.insert(0);

        Heap deserialized = serializeXML(heap);
        Assert.assertEquals(heap.getSize(), deserialized.getSize());

        deserialized = serialize(heap);
        Assert.assertEquals(heap.getSize(), deserialized.getSize());
    }

    @Test
    public void testSerializationString() throws FileNotFoundException {
        Heap heap = new Heap();
        heap.insert("HI");
        heap.insert("DU");
        heap.insert("DU");
        heap.insert("Dada");

        Heap deserialized = serializeXML(heap);
        for (int i = 0; i < heap.getSize(); i++) {
            Assert.assertEquals(heap.deleteFirst(), deserialized.deleteFirst());
        }

        deserialized = serialize(heap);
        for (int i = 0; i < heap.getSize(); i++) {
            Assert.assertEquals(heap.deleteFirst(), deserialized.deleteFirst());
        }
    }

    @Test
    public void testSizeString() {
        Heap heap = new Heap();
        heap.insert("HI");
        heap.insert("DU");
        heap.insert("DU");
        heap.insert("Dada");

        Heap deserialized = serializeXML(heap);
        Assert.assertEquals(heap.getSize(), deserialized.getSize());

        deserialized = serialize(heap);
        Assert.assertEquals(heap.getSize(), deserialized.getSize());
    }
}
