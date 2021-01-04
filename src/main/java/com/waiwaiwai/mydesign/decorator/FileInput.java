package com.waiwaiwai.mydesign.decorator;

public class FileInput implements Input{

    public FileInput(String msg) {
        System.out.println(msg);
    }

    @Override
    public void read() {
        System.out.println("file input");
    }

}
