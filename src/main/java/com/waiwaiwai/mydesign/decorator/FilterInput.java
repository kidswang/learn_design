package com.waiwaiwai.mydesign.decorator;

public class FilterInput implements Input{

    protected Input input;

    public FilterInput(Input input) {
        this.input = input;
    }

    @Override
    public void read() {
        input.read();
    }
}
