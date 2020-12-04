package com.waiwaiwai.mydesign.unit;

public class RedisDistributedLock {
    public static RedisDistributedLock getSingletonIntance() {
        return new RedisDistributedLock();
    }

    public void unlockTransction(String id) {

    }

    public boolean lockTransction(String id) {
        return false;
    }
}
