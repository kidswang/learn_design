package com.waiwaiwai.thread.forkjoin;

public abstract class Abstract1 {

    protected int flag;

    public Abstract1() {
    }

    public Abstract1(int flag) {
        this.flag = flag;
    }

    public abstract void task();
    public void task1() {
        System.out.println("wo shi fu lei");
    }
}
