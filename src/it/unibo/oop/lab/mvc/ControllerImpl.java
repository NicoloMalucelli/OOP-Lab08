package it.unibo.oop.lab.mvc;

import java.util.LinkedList;
import java.util.List;

public class ControllerImpl implements Controller {

    private final List<String> history;
    private String next;

    public ControllerImpl() {
        next = null;
        history = new LinkedList<>();
    }

    /**
     * @param s
     *          string to set as next

     */
    public void setNextString(final String s) {
        if (s == null) {
            throw new IllegalArgumentException("s can't be null");
        }
        this.next = s;
    }

    /**
     * @return the next string will be printed
     */
    public String getNextString() {
        return this.next;
    }

    /**
     * @return the next history of all printed strings
     */
    public List<String> getHistory() {
        return List.copyOf(history);
    }

    /**
     * print the next string.
     */
    public void print() {
        if (this.next == null) {
            throw new IllegalStateException("string is null");
        }
        history.add(next);
        System.out.println(next);
        next = null;
    }

}
