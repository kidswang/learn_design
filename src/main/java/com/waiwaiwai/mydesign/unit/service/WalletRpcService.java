package com.waiwaiwai.mydesign.unit.service;

public interface WalletRpcService {
    String moveMoney(String id, Long buyerId, Long sellerId, Double amount);
}
