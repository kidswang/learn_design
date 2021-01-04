package com.waiwaiwai.mydesign.decorator;

public class DataInput extends FilterInput{

    public DataInput(Input input) {
        super(input);
    }

    @Override
    public void read() {
        input.read();
        System.out.println("Data input");
    }
}
