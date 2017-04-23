package com.week2;

/**
 * Created by Julia on 20.04.2017.
 */
public class StackTest {
    /**
     * Testet ob der Copy-Constructor funktioniert
     * Referenzen und Eintraege werden verglichen
     * @param tester
     */
    public static void testCopyConstructor (StringStack tester) {
        StringStack copy = new StringStack (tester);
        System.out.print("Kopie und Original identisch?");
        if (tester == copy) {
            System.out.println(" --> ja");
        } else { System.out.println(" --> nein");}
        System.out.print("oberster Eintrag identisch?");
        if (tester.empty()) {
            System.out.println(" -->keine Eintraege");
        } else if (tester.peek() == copy.peek()) {
            System.out.println(" --> ja");
        } else {
            System.out.println(" --> nein");
        }
    }

    /**
     * Druckt obersten Eintrag
     * @param tester
     */
    public static void printPeek(StringStack tester) {
        System.out.println(tester.peek());
    }

    public static void main (String [] args) {

        StringStack keller = new StringStack();
        System.out.println("Leerer Keller:");
        testCopyConstructor (keller);

        System.out.println("Keller mit Eintraegen:");
        keller.push ("Ein");
        keller.push ("kleiner");
        keller.push ("Test");
        testCopyConstructor (keller);

        StringStack copy = new StringStack(keller);
        System.out.println("Oberste Einträge: ");
        printPeek(keller);
        printPeek(copy);
    }
}
