package com.waiwaiwai.mydesign.decorator;

public class BufferedInput extends FilterInput{

    public BufferedInput(Input input) {
        super(input);
    }

    @Override
    public void read() {
        input.read();
        System.out.println("buffered input");
    }
}
