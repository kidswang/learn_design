package com.waiwaiwai.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.waiwaiwai.entity.SReserveOrder;

import java.util.List;

public interface SReserveOrderService extends IService<SReserveOrder> {

    List<SReserveOrder> findList(long i, long pageSize);
}
