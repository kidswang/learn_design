package com.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 积分操作流水
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "s_integral_record")
public class SIntegralRecord {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 流水编号
     */
    @TableField(value = "integral_number")
    private Integer integralNumber;

    /**
     * 操作时间
     */
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    /**
     * 学生id
     */
    @TableField(value = "user_id")
    private String userId;

    /**
     * 学校id
     */
    @TableField(value = "school_id")
    private String schoolId;

    /**
     * 内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 变动数量
     */
    @TableField(value = "change_number")
    private Integer changeNumber;

    /**
     * 变动类型 1加 2减
     */
    @TableField(value = "variation_type")
    private String variationType;

    /**
     * 创建人id
     */
    @TableField(value = "create_user")
    private Integer createUser;

    public static final String COL_ID = "id";

    public static final String COL_INTEGRAL_NUMBER = "integral_number";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_SCHOOL_ID = "school_id";

    public static final String COL_CONTENT = "content";

    public static final String COL_CHANGE_NUMBER = "change_number";

    public static final String COL_VARIATION_TYPE = "variation_type";

    public static final String COL_CREATE_USER = "create_user";
}