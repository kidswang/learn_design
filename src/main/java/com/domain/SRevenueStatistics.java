package com.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "s_revenue_statistics")
public class SRevenueStatistics implements Serializable {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 直接费用
     */
    @TableField(value = "directCost")
    private BigDecimal directCost;

    /**
     * 学生就餐人数
     */
    @TableField(value = "studentMealCount")
    private Integer studentMealCount;

    /**
     * 学生就餐餐费
     */
    @TableField(value = "studentMealPay")
    private BigDecimal studentMealPay;

    /**
     * 教师就餐人数
     */
    @TableField(value = "teacherMealCount")
    private Integer teacherMealCount;

    /**
     * 教师就餐餐费
     */
    @TableField(value = "teacherMealPay")
    // @ApiModelProperty(value = "教师就餐餐费")
    private BigDecimal teacherMealPay;

    /**
     * 其他就餐人数
     */
    @TableField(value = "othersMealCount")
    // @ApiModelProperty(value = "其他就餐人数")
    private Integer othersMealCount;

    /**
     * 其他就餐餐费
     */
    @TableField(value = "othersMealPay")
    // @ApiModelProperty(value = "其他就餐餐费")
    private BigDecimal othersMealPay;

    /**
     * 店长数量
     */
    @TableField(value = "shopManagerCount")
    // @ApiModelProperty(value = "店长数量")
    private Integer shopManagerCount;

    /**
     * 店长薪资
     */
    @TableField(value = "shopManagerCost")
    // @ApiModelProperty(value = "店长薪资")
    private BigDecimal shopManagerCost;

    /**
     * 厨师长数量
     */
    @TableField(value = "headChefCount")
    // @ApiModelProperty(value = "厨师长数量")
    private Integer headChefCount;

    /**
     * 厨师长薪资
     */
    @TableField(value = "headChefCost")
    // @ApiModelProperty(value = "厨师长薪资")
    private BigDecimal headChefCost;

    /**
     * 厨师数量
     */
    @TableField(value = "chefCount")
    // @ApiModelProperty(value = "厨师数量")
    private Integer chefCount;

    /**
     * 厨师薪资
     */
    @TableField(value = "chefCost")
    // @ApiModelProperty(value = "厨师薪资")
    private BigDecimal chefCost;

    /**
     * 面点师数量
     */
    @TableField(value = "pastryCookCount")
    // @ApiModelProperty(value = "面点师数量")
    private Integer pastryCookCount;

    /**
     * 面点师工资
     */
    @TableField(value = "pastryCookCost")
    // @ApiModelProperty(value = "面点师工资")
    private BigDecimal pastryCookCost;

    /**
     * 其他职工数量
     */
    @TableField(value = "othersEmployeeCount")
    // @ApiModelProperty(value = "其他职工数量")
    private Integer othersEmployeeCount;

    /**
     * 其他职工工资
     */
    @TableField(value = "othersEmployeeCost")
    // @ApiModelProperty(value = "其他职工工资")
    private BigDecimal othersEmployeeCost;

    /**
     * 粮食费用
     */
    @TableField(value = "cerealsCost")
    // @ApiModelProperty(value = "粮食费用")
    private BigDecimal cerealsCost;

    /**
     * 花生油费用
     */
    @TableField(value = "peanutOilCost")
    // @ApiModelProperty(value = "花生油费用")
    private BigDecimal peanutOilCost;

    /**
     * 各种调料费用
     */
    @TableField(value = "flavourCost")
    // @ApiModelProperty(value = "各种调料费用")
    private BigDecimal flavourCost;

    /**
     * 酸奶费用
     */
    @TableField(value = "yogurtCost")
    // @ApiModelProperty(value = "酸奶费用")
    private BigDecimal yogurtCost;

    /**
     * 蔬菜费用
     */
    @TableField(value = "vegetablesCost")
    // @ApiModelProperty(value = "蔬菜费用")
    private BigDecimal vegetablesCost;

    /**
     * 肉费用
     */
    @TableField(value = "meatCost")
    // @ApiModelProperty(value = "肉费用")
    private BigDecimal meatCost;

    /**
     * 鸡蛋费用
     */
    @TableField(value = "eggCost")
    // @ApiModelProperty(value = "鸡蛋费用")
    private BigDecimal eggCost;

    /**
     * 海鲜费用
     */
    @TableField(value = "seafoodCost")
    // @ApiModelProperty(value = "海鲜费用")
    private BigDecimal seafoodCost;

    /**
     * 水果费用
     */
    @TableField(value = "fruitCost")
    // @ApiModelProperty(value = "水果费用")
    private BigDecimal fruitCost;

    /**
     * 其他食材费用
     */
    @TableField(value = "othersIngredientCost")
    // @ApiModelProperty(value = "其他食材费用")
    private BigDecimal othersIngredientCost;

    /**
     * 设备杂品费
     */
    @TableField(value = "equipmentCharge")
    // @ApiModelProperty(value = "设备杂品费")
    private BigDecimal equipmentCharge;

    /**
     * 燃气费
     */
    @TableField(value = "gasCharge")
    // @ApiModelProperty(value = "燃气费")
    private BigDecimal gasCharge;

    /**
     * 水费
     */
    @TableField(value = "waterCharge")
    // @ApiModelProperty(value = "水费")
    private BigDecimal waterCharge;

    /**
     * 电费
     */
    @TableField(value = "electricCharge")
    // @ApiModelProperty(value = "电费")
    private BigDecimal electricCharge;

    /**
     * 服务团队管理费
     */
    @TableField(value = "serviceManagerCost")
    // @ApiModelProperty(value = "服务团队管理费")
    private BigDecimal serviceManagerCost;

    /**
     * 技术服务管理费
     */
    @TableField(value = "technologyManagerCost")
    // @ApiModelProperty(value = "技术服务管理费")
    private BigDecimal technologyManagerCost;

    /**
     * 其他管理费用
     */
    @TableField(value = "othersManagerCost")
    // @ApiModelProperty(value = "其他管理费用")
    private BigDecimal othersManagerCost;
}