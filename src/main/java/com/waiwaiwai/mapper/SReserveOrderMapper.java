package com.waiwaiwai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.waiwaiwai.entity.SReserveOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SReserveOrderMapper extends BaseMapper<SReserveOrder> {

    List<SReserveOrder> findList(@Param("idx") long idx,@Param("pageSize") long pageSize);
}
