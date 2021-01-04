package com.waiwaiwai.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 预订单表
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "s_reserve_order")
public class SReserveOrder {
    public static final String COL_ADDTIME = "addTime";
    private static final long serialVersionUID = 1L;
    /**
     * 订单表id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单编号
     */
    @TableField(value = "orderNumber")
    private Long orderNumber;

    /**
     * 餐别id
     */
    @TableField(value = "dinnerType")
    private Integer dinnerType;

    /**
     * 订单金额
     */
    @TableField(value = "amount")
    private BigDecimal amount;

    /**
     * 支付方式[0：支付宝；1:微信；2:刷脸支付; 3:一卡通]
     */
    @TableField(value = "payType")
    private String payType;

    /**
     * 订餐人姓名
     */
    @TableField(value = "orderUserName")
    private String orderUserName;

    /**
     * 订单人id
     */
    @TableField(value = "orderUserId")
    private String orderUserId;

    /**
     * 下单时间
     */
    @TableField(value = "orderTime")
    private Date orderTime;

    /**
     * 订单状态【0：预订单；1：收款订单；2:退款订单】
     */
    @TableField(value = "orderStatus")
    private String orderStatus;

    /**
     * 所属食堂id
     */
    @TableField(value = "diningRoomId")
    private Integer diningRoomId;

    /**
     * 订单来源【0：点餐应用；1：点餐机；2：电子班牌；3：移动端 4：班班通】
     */
    @TableField(value = "orderSource")
    private String orderSource;

    /**
     * 创建人id
     */
    @TableField(value = "addUser")
    private Integer addUser;

    /**
     * 修改人id
     */
    @TableField(value = "updateUser")
    private Integer updateUser;

    /**
     * 修改时间
     */
    @TableField(value = "updateTime")
    private Date updateTime;

    /**
     * 删除标志
     */
    @TableField(value = "delFlag")
    private String delFlag;

    /**
     * 对应学校id
     */
    @TableField(value = "schoolId")
    private String schoolId;

    /**
     * 退款时间
     */
    @TableField(value = "refundTime")
    private Date refundTime;

    /**
     * 支付时间
     */
    @TableField(value = "payTime")
    private Date payTime;

    /**
     * 退款状态 1退款中 2已到账 0非退款状态 3取消预约
     */
    @TableField(value = "refundStatus")
    private String refundStatus;

    /**
     * 实收金额
     */
    @TableField(value = "actualPayAmount")
    private BigDecimal actualPayAmount;

    /**
     * 实退金额
     */
    @TableField(value = "actualRefundAmount")
    private BigDecimal actualRefundAmount;

    /**
     * 食堂名称
     */
    @TableField(value = "diningRoomName")
    private String diningRoomName;

    /**
     * 预订单日期
     */
    @TableField(value = "orderDate")
    private Date orderDate;

    /**
     * 是否取餐 0待取餐 1已取餐 2未取餐
     */
    @TableField(value = "isGetFood")
    private String isGetFood;

    /**
     * 领餐时间
     */
    @TableField(value = "getFoodDate")
    private Date getFoodDate;

    /**
     * 用户类型
     */
    @TableField(value = "userType")
    private Integer userType;

    /**
     * 退款原因类型
     */
    @TableField(value = "refundTypeId")
    private String refundTypeId;

    /**
     * 退款説明
     */
    @TableField(value = "refundExplain")
    private String refundExplain;

    /**
     * 订单批次
     */
    @TableField(value = "batchNum")
    private String batchNum;

    /**
     * 支付状态
     */
    @TableField(value = "payStatus")
    private String payStatus;

    /**
     * 支付失败原因
     */
    @TableField(value = "remake")
    private String remake;

    /**
     * 取消来源【0：点餐应用；1：点餐机；2：电子班牌；3：移动端 4：班班通】
     */
    @TableField(value = "refundSource")
    private String refundSource;

    public static final String COL_ID = "id";

    public static final String COL_ORDERNUMBER = "orderNumber";

    public static final String COL_DINNERTYPE = "dinnerType";

    public static final String COL_AMOUNT = "amount";

    public static final String COL_PAYTYPE = "payType";

    public static final String COL_ORDERUSERNAME = "orderUserName";

    public static final String COL_ORDERUSERID = "orderUserId";

    public static final String COL_ORDERTIME = "orderTime";

    public static final String COL_ORDERSTATUS = "orderStatus";

    public static final String COL_DININGROOMID = "diningRoomId";

    public static final String COL_ORDERSOURCE = "orderSource";

    public static final String COL_ADDUSER = "addUser";

    public static final String COL_UPDATEUSER = "updateUser";

    public static final String COL_UPDATETIME = "updateTime";

    public static final String COL_DELFLAG = "delFlag";

    public static final String COL_SCHOOLID = "schoolId";

    public static final String COL_REFUNDTIME = "refundTime";

    public static final String COL_PAYTIME = "payTime";

    public static final String COL_REFUNDSTATUS = "refundStatus";

    public static final String COL_ACTUALPAYAMOUNT = "actualPayAmount";

    public static final String COL_ACTUALREFUNDAMOUNT = "actualRefundAmount";

    public static final String COL_DININGROOMNAME = "diningRoomName";

    public static final String COL_ORDERDATE = "orderDate";

    public static final String COL_ISGETFOOD = "isGetFood";

    public static final String COL_GETFOODDATE = "getFoodDate";

    public static final String COL_USERTYPE = "userType";

    public static final String COL_REFUNDTYPEID = "refundTypeId";

    public static final String COL_REFUNDEXPLAIN = "refundExplain";

    public static final String COL_BATCHNUM = "batchNum";

    public static final String COL_PAYSTATUS = "payStatus";

    public static final String COL_REMAKE = "remake";

    public static final String COL_REFUNDSOURCE = "refundSource";
}