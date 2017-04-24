package com.week2;

/**
 * A Stack that holds Strings. Works after the LIFO (Last in first out)
 * principle.
 *
 * @author Mathias Menninghaus (mathias.menninghaus@uos.de)
 *
 */
public class StringStack {

    public StringStack() {
        this.first = null;
    }

    /**
     * Erweiterung: Copy-Constructor erzeugt Kopie des StringStacks
     * dabei reicht einfache Wertzuweisung aus, denn Strings sind immutable
     *
     */
    public StringStack (StringStack original) {
        this ();
        if (!original.empty()) {
            StringStack help = reverse(original);
            while (!help.empty()) {
                this.push(help.peek());
                original.push(help.peek());
                help.pop();
            }
        }
    }

    /**
     * Erstellt einen Hilfskeller mit umgedrehter Reihenfolge
     * Gewuenschter Seiteneffekt: Originalkeller wird leer
     * @param original Keller mit richtiger Reihenfolge
     * @return Hilfskeller mit umgedrehter Reihenfolge
     */
    private StringStack reverse(StringStack original) {
        StringStack help = new StringStack();
        while (!original.empty()) {
            help.push(original.peek());
            original.pop();
        }
        return help;
    }

    private StringStackEntry first;

    /**
     * Tests, whether this StringStack is empty or not.
     *
     * @return true if this StringStack is empty, else false
     */
    public boolean empty() {
        return this.first == null;
    }

    /**
     * Returns the first element in the stack. Throws Exception when stack is
     * empty
     *
     * @return First element or null if stack is empty
     * @throws RuntimeException
     *            if stack is empty.
     */
    public String peek() {
        if (!this.empty()) {
            return first.getString();
        } else {
            throw new RuntimeException("Stack is empty");
        }
    }

    /**
     * Deletes the first element in the stack and returns it. Throws Exception
     * when stack is empty
     *
     * @return first element in the stack
     * @throws RuntimeException
     *            if stack is empty.
     */
    public String pop() {
        if (!this.empty()) {
            String ret = first.getString();
            this.first = this.first.getNext();
            return ret;
        } else {
            throw new RuntimeException("Stack is empty");
        }
    }

    /**
     * Puts the String s on the stack.
     *
     * @param s
     *           String to be added.
     */
    public void push(String s) {
        if (this.empty()) {
            first = new StringStackEntry(s, null);
        } else {
            first = new StringStackEntry(s, first);
        }
    }

}
