package com.waiwaiwai.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.waiwaiwai.entity.SReserveOrder;
import com.waiwaiwai.mapper.SReserveOrderMapper;
import com.waiwaiwai.service.SReserveOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SReserveOrderServiceImpl extends ServiceImpl<SReserveOrderMapper, SReserveOrder> implements SReserveOrderService {

    @Resource
    private SReserveOrderMapper sReserveOrderMapper;

    @Override
    public List<SReserveOrder> findList(long idx, long pageSize) {

        return sReserveOrderMapper.findList(idx, pageSize);
    }
}
