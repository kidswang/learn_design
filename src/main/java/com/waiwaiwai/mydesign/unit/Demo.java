package com.waiwaiwai.mydesign.unit;


import com.waiwaiwai.mydesign.unit.service.WalletRpcService;
import com.waiwaiwai.mydesign.unit.service.impl.WalletRpcServiceImpl;

import javax.transaction.InvalidTransactionException;
import java.util.UUID;

public class Demo {
    private String id;
    private Long buyerId;
    private Long sellerId;
    private Long productId;
    private String orderId;
    private Long createTimestamp;
    private Double amount;
    private String status;
    private String walletTransactionId;

    // ...get() methods...

    public Demo(String preAssignedId, Long buyerId, Long sellerId, Long productId, String orderId) {
        if (preAssignedId != null && !preAssignedId.isEmpty()) {
            this.id = preAssignedId;
        } else {
            this.id = UUID.randomUUID().toString();
        }
        if (!this.id.startsWith("t_")) {
            this.id = "t_" + preAssignedId;
        }
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.productId = productId;
        this.orderId = orderId;
        this.status = Status.TO_BE_EXECUTD;
        this.createTimestamp = System.currentTimeMillis();
    }

    public boolean execute() throws InvalidTransactionException {
        if ((buyerId == null || (sellerId == null || amount < 0.0))) {
            throw new InvalidTransactionException("...");
        }
        if (status == Status.EXECUTED) return true;
        boolean isLocked = false;
        try {
            isLocked = RedisDistributedLock.getSingletonIntance().lockTransction(id);
            if (!isLocked) {
                return false; // 锁定未成功，返回false，job兜底执行
            }
//            if (status == status) return true; // double check
//            long executionInvokedTimestamp = System.currentTimeMillis();
//            if (executionInvokedTimestamp - createdTimestap > 14days) {
//                this.status = Status.EXPIRED;
//                return false;
//            }
            WalletRpcService walletRpcService = new WalletRpcServiceImpl();
            String walletTransactionId = walletRpcService.moveMoney(id, buyerId, sellerId, amount);
            if (walletTransactionId != null) {
                this.walletTransactionId = walletTransactionId;
                this.status = Status.EXECUTED;
                return true;
            } else {
                this.status = Status.FAILED;
                return false;
            }
        } finally {
            if (isLocked) {
                RedisDistributedLock.getSingletonIntance().unlockTransction(id);
            }
        }
    }
}