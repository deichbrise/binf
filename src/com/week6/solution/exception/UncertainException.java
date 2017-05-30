package com.week6.solution.exception;

import java.io.IOException;

public class UncertainException {

   private int i = 0;

   public static void uncertain(int number) {
      UncertainException uncertain = new UncertainException();
      int result = 0;

      try {
         System.out.println("uncertain" + number + "()");
         switch (number) {
            case 1:
               result = uncertain.uncertain1();
               break;
            case 2:
               result = uncertain.uncertain2();
               break;
            case 3:
               result = uncertain.uncertain3();
               break;
            case 4:
               result = uncertain.uncertain4();
               break;
            case 5:
               result = uncertain.uncertain5();
               break;
            case 6:
               result = uncertain.uncertain6();
               break;
            case 7:
               result = uncertain.uncertain7();
               break;
            case 8:
               result = uncertain.uncertain8();
               break;
            case 9:
               result = uncertain.uncertain9();
               break;
            case 10:
               result = uncertain.uncertain10();
               break;
         }
         System.out.println("result = " + result + ", i = " + uncertain.i);
      } catch (Exception e) {
         System.out.println("i = " + uncertain.i + " Exception (" + e.getClass()
               .getName() + ")");
      } finally {
      }
   }

   public static void main(String args[]) {
      for (int i = 1; i <= 10; ++i) {
         uncertain(i);
      }
   }

   public int uncertain1() {
      try {
         throw new RuntimeException();
      } catch (RuntimeException e) {
         i++; // wird ausgeführt
         throw new ClassCastException();
      } finally {
         i++; // wird ausgeführt
         throw new NumberFormatException(); // wird geworfen und überdeckt ClassCastException
      }
   }

   public int uncertain2() {
      for (; ; ) {
         try {
            break;
         } catch (RuntimeException e) {
            i++; // wird nicht ausgeführt, da im try block keine exception geworfen wird
         } finally {
            i++; // wird ausgeführt, da finally immer ausgeführt wirde
            throw new RuntimeException(); // wird geworfen
         }
      }
      //return i++;
       // java: unreachable statement, da im finally-Block bereits ein Wert retourniert wird
   }

   public int uncertain3() {
      do {
         try {
            throw new RuntimeException();
         } catch (RuntimeException e) {
            i++; // wird ausgeführt, da im try block exception geworfen wurde
            continue;
         } finally {
            i++; // wird ausgeführt, da finally immer ausgeführt wirde
         }
      } while (false); // ein schleifendurchlauf
      return i++; // wird erst zurückgegeben, dann um 1 erhöht
   }

   public int uncertain4() {
      try {
         return i++; // wird erst zurückgegeben, dann um 1 erhöht
      } catch (RuntimeException e) {
         i++;
      } finally {
         i++; // wird ausgeführt, da finally immer ausgeführt wird
      }
      return i++;
   }

   public int uncertain5() {
      try {
         return i; // wird 0 zurückgegeben
      } finally {
         throw new RuntimeException(); // finally wird immer ausgeführt
      }
   }

   public int uncertain6() {
      try {
         throw new RuntimeException(); // exception wird einfach durch finally ignoriert
      } finally {
         return ++i; // wird um 1 erhöht und zurückgegeben
      }
   }

   public int uncertain7() {
      try {
         throw new java.io.IOException(); // wird nicht durch RuntimeException abgefangen
      } catch (RuntimeException e) {
         i++; // wird nicht ausgeführt
      } finally {
         return i++; // wird ausgeführt
      }
   }

   public int uncertain8() {
      try {
         throw new NumberFormatException(); // wird im catch block behandelt, da Subklasse von RuntimeException
      } catch (RuntimeException e) {
         i++; // wird um 1 erhöht
         throw new RuntimeException(); // wird geworfen
      } finally {
         i++; // wird im 1 erhöht
      }
   }

   public int uncertain9() {
      try {
         throw new ClassCastException(); // wird abgefangen, da RuntimeException
      } catch (RuntimeException e) {
         return i++; // wird um 1 erhöht
      } finally {
         return i++; // wird erst zurückgegeben, dann um 1 erhöht
      }
   }

   public int uncertain10() throws IOException{
      try {
          //java: unreported exception java.io.IOException; must be caught or declared to be thrown
          //Korrektur: Methodenkopf um throws IOException erweitert
         throw new java.io.IOException(); // wird nicht behandelt, deswegen wird die exception geworfen und kann von außen abgefangen werden
      } catch (RuntimeException e) {
      }
      return 1; // wird nicht ausgeführt

   }
}