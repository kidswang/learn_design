package com.waiwaiwai.thread.forkjoin;

public class AbstractExtend extends Abstract1{

    public AbstractExtend() {

    }
    public AbstractExtend(int flag) {
        super(flag);
    }

    @Override
    public void task() {
        System.out.println("wo shi zi lei");
    }
}
