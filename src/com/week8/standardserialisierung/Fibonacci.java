package com.week8.standardserialisierung;

import java.io.*;
import java.util.HashMap;

/**
 * Class to calculate the Fibonacci number. Uses a HashMap to reduce the
 * calculation cost.
 * 
 * @author Mathias Menninghaus (mathias.menninghaus@uos.de)
 * 
 */
public class Fibonacci {

   private static String path = "FibonacciHashMap.ser";
   private static File file;

   private static HashMap<Integer, Long> fibonacciHash = generateHashMap();

   /**
    * Fill HashMap with initial key-value-pairs.
    */
   static {
      fibonacciHash.put(0, 0L);
      fibonacciHash.put(1, 1L);
   }

   /**
    * Calculates the fibonacci value of n.
    * 
    * @param n
    *           a natural number >= 0 to calculate the fibonacci value of
    * 
    * @return fibonacci value of n
    */
   public static long fibonacci(int n) {
      if (n < 0) {
         throw new IllegalArgumentException("n = " + n);
      }
      return getFibonacci(n);
   }

   private static long getFibonacci(int n) {
      if (fibonacciHash.containsKey(n)) {
         return fibonacciHash.get(n);
      } else {
         long nMinus1 = getFibonacci(n - 1);
         long nMinus2 = getFibonacci(n - 2);
         long fibonacci = nMinus1 + nMinus2;

         fibonacciHash.put(n, fibonacci);
         return fibonacci;
      }
   }

   /**
    * Deserialisation: Reads existing HashMap out of file
    *    or creates new HashMap at the first use
    * @return HashMap for static variable fibonacciHash
    */
   private static HashMap<Integer, Long> generateHashMap() {
      try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))) {
         return (HashMap) in.readObject();
      } catch (FileNotFoundException e) {
         file = new File (path);
         return new HashMap<Integer, Long>();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      }
      return new HashMap<Integer, Long>();
   }

   /**
    * Serialisation: Empties file used for HashMap and writes HashMap into it
    * Used at end of programm
    */
   private static void saveHashMap() {
      file = new File(path);

      try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path))) {
         out.writeObject(fibonacciHash);
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public static void main(String[] args) {

      if (args.length != 1) {
         printUsage();
      }
      try {
         Integer number = Integer.parseInt(args[0]);
         long result = fibonacci(number);
         System.out.println(result);
         fibonacciHash.put(number, result);

      } catch (IllegalArgumentException ex) {
         printUsage();
      }
      finally {
         saveHashMap();
      }
   }

   private static void printUsage() {
      System.out.println("java calc/Fiboncci n");
      System.out.println("n must be a natural number >= 0");
   }

}
