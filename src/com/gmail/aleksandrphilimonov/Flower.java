package com.gmail.aleksandrphilimonov;

public class Flower implements Fieldable {

    private int transistors;

    public Flower(int transistors) {
        this.transistors = transistors;
    }

    public int getTransistors() {
        return transistors;
    }

    @Override
    public String getSymbol() {
        return " " + transistors + " ";
    }
}
