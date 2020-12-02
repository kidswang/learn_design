package com.waiwaiwai.excel;

import cn.hutool.poi.excel.ExcelUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.domain.SRevenueStatistics;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;

@Slf4j
public class ExcelTest {

    public static void main(String[] args) throws IOException {

        File file = new File("D:\\工作簿备份链接.xlsx");
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet("营收统计");
        if (sheet == null) {
            return;
        }

        SRevenueStatistics sRevenueStatistics = new SRevenueStatistics();
        // 只在第四行有数据
        XSSFRow row = sheet.getRow(3);
        //
        XSSFCell directCost = row.getCell(0);
        sRevenueStatistics.setDirectCost(new BigDecimal(getCellStringVal(directCost)));
        XSSFCell studentMealCount = row.getCell(1);
        sRevenueStatistics.setStudentMealCount(Integer.parseInt(getCellStringVal(studentMealCount)));
        XSSFCell studentMealPay = row.getCell(2);
        sRevenueStatistics.setStudentMealPay(new BigDecimal(getCellStringVal(studentMealPay)));
        XSSFCell teacherMealCount = row.getCell(3);
        sRevenueStatistics.setTeacherMealCount(Integer.parseInt(getCellStringVal(teacherMealCount)));
        XSSFCell teacherMealPay = row.getCell(4);
        sRevenueStatistics.setTeacherMealPay(new BigDecimal(getCellStringVal(teacherMealPay)));
        XSSFCell othersMealCount = row.getCell(5);
        sRevenueStatistics.setOthersMealCount(Integer.parseInt(getCellStringVal(othersMealCount)));
        XSSFCell othersMealPay = row.getCell(6);
        sRevenueStatistics.setOthersMealPay(new BigDecimal(getCellStringVal(othersMealPay)));
        XSSFCell shopManagerCount = row.getCell(7);
        sRevenueStatistics.setShopManagerCount(Integer.parseInt(getCellStringVal(shopManagerCount)));
        XSSFCell shopManagerCost = row.getCell(8);
        sRevenueStatistics.setShopManagerCost(new BigDecimal(getCellStringVal(shopManagerCost)));
        XSSFCell headChefCount = row.getCell(9);
        sRevenueStatistics.setHeadChefCount(Integer.parseInt(getCellStringVal(headChefCount)));
        XSSFCell headChefCost = row.getCell(10);
        sRevenueStatistics.setHeadChefCost(new BigDecimal(getCellStringVal(headChefCost)));
        XSSFCell chefCount = row.getCell(11);
        sRevenueStatistics.setChefCount(Integer.parseInt(getCellStringVal(chefCount)));
        XSSFCell chefCost = row.getCell(12);
        sRevenueStatistics.setChefCost(new BigDecimal(getCellStringVal(chefCost)));
        XSSFCell pastryCookCount = row.getCell(13);
        sRevenueStatistics.setPastryCookCount(Integer.parseInt(getCellStringVal(pastryCookCount)));
        XSSFCell pastryCookCost = row.getCell(14);
        sRevenueStatistics.setPastryCookCost(new BigDecimal(getCellStringVal(pastryCookCost)));
        XSSFCell othersEmployeeCount = row.getCell(15);
        sRevenueStatistics.setOthersEmployeeCount(Integer.parseInt(getCellStringVal(othersEmployeeCount)));
        XSSFCell othersEmployeeCost = row.getCell(16);
        sRevenueStatistics.setOthersEmployeeCost(new BigDecimal(getCellStringVal(othersEmployeeCost)));
        XSSFCell cerealsCost = row.getCell(17);
        sRevenueStatistics.setCerealsCost(new BigDecimal(getCellStringVal(cerealsCost)));
        XSSFCell peanutOilCost = row.getCell(18);
        sRevenueStatistics.setPeanutOilCost(new BigDecimal(getCellStringVal(peanutOilCost)));
        XSSFCell flavourCost = row.getCell(19);
        sRevenueStatistics.setFlavourCost(new BigDecimal(getCellStringVal(flavourCost)));
        XSSFCell yogurtCost = row.getCell(20);
        sRevenueStatistics.setYogurtCost(new BigDecimal(getCellStringVal(yogurtCost)));
        XSSFCell vegetablesCost = row.getCell(21);
        sRevenueStatistics.setVegetablesCost(new BigDecimal(getCellStringVal(vegetablesCost)));
        XSSFCell meatCost = row.getCell(22);
        sRevenueStatistics.setMeatCost(new BigDecimal(getCellStringVal(meatCost)));
        XSSFCell eggCost = row.getCell(23);
        sRevenueStatistics.setEggCost(new BigDecimal(getCellStringVal(eggCost)));
        XSSFCell seafoodCost = row.getCell(24);
        sRevenueStatistics.setSeafoodCost(new BigDecimal(getCellStringVal(seafoodCost)));
        XSSFCell fruitCost = row.getCell(25);
        sRevenueStatistics.setFruitCost(new BigDecimal(getCellStringVal(fruitCost)));
        XSSFCell othersIngredientCost = row.getCell(26);
        sRevenueStatistics.setOthersIngredientCost(new BigDecimal(getCellStringVal(othersIngredientCost)));
        XSSFCell equipmentCharge = row.getCell(27);
        sRevenueStatistics.setEquipmentCharge(new BigDecimal(getCellStringVal(equipmentCharge)));
        XSSFCell gasCharge = row.getCell(28);
        sRevenueStatistics.setGasCharge(new BigDecimal(getCellStringVal(gasCharge)));
        XSSFCell waterCharge = row.getCell(29);
        sRevenueStatistics.setWaterCharge(new BigDecimal(getCellStringVal(waterCharge)));
        XSSFCell electricCharge = row.getCell(30);
        sRevenueStatistics.setElectricCharge(new BigDecimal(getCellStringVal(electricCharge)));
        XSSFCell servicesManagerCost = row.getCell(31);
        sRevenueStatistics.setServiceManagerCost(new BigDecimal(getCellStringVal(servicesManagerCost)));
        XSSFCell technologyManagerCost = row.getCell(32);
        sRevenueStatistics.setTechnologyManagerCost(new BigDecimal(getCellStringVal(technologyManagerCost)));
        XSSFCell othersManagerCost = row.getCell(33);
        sRevenueStatistics.setOthersManagerCost(new BigDecimal(getCellStringVal(othersManagerCost)));

        System.out.println(sRevenueStatistics.toString());


    }
    
    @Test
    public void test() {
        File file = new File("D:\\工作簿备份链接.xlsx");
        InputStream fis = null;
        XSSFWorkbook wb = null;
        try {
            fis = new FileInputStream(file);
            wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheet("营收统计");

            SRevenueStatistics sRevenueStatistics = new SRevenueStatistics();
            // 只在第四行有数据
            XSSFRow row = sheet.getRow(3);
            //
//        XSSFCell directCost = row.getCell(0);
//        sRevenueStatistics.setDirectCost(new BigDecimal(getCellStringVal(directCost).equals("") ? "0" : getCellStringVal(directCost)));
            XSSFCell studentMealCount = row.getCell(0);
            sRevenueStatistics.setStudentMealCount((int) Double.parseDouble(getCellStringVal(studentMealCount).equals("") ? "0" : getCellStringVal(studentMealCount)));
            XSSFCell studentMealPay = row.getCell(1);
            sRevenueStatistics.setStudentMealPay(new BigDecimal(getCellStringVal(studentMealPay).equals("") ? "0" : getCellStringVal(studentMealPay)));
            XSSFCell teacherMealCount = row.getCell(2);
            sRevenueStatistics.setTeacherMealCount((int) Double.parseDouble(getCellStringVal(teacherMealCount).equals("") ? "0" : getCellStringVal(teacherMealCount)));
            XSSFCell teacherMealPay = row.getCell(3);
            sRevenueStatistics.setTeacherMealPay(new BigDecimal(getCellStringVal(teacherMealPay).equals("") ? "0" : getCellStringVal(teacherMealPay)));
            XSSFCell othersMealCount = row.getCell(4);
            sRevenueStatistics.setOthersMealCount((int) Double.parseDouble(getCellStringVal(othersMealCount).equals("") ? "0" : getCellStringVal(othersMealCount)));
            XSSFCell othersMealPay = row.getCell(5);
            sRevenueStatistics.setOthersMealPay(new BigDecimal(getCellStringVal(othersMealPay).equals("") ? "0" : getCellStringVal(othersMealPay)));
            XSSFCell shopManagerCount = row.getCell(6);
            sRevenueStatistics.setShopManagerCount((int) Double.parseDouble(getCellStringVal(shopManagerCount).equals("") ? "0" : getCellStringVal(shopManagerCount)));
            XSSFCell shopManagerCost = row.getCell(7);
            sRevenueStatistics.setShopManagerCost(new BigDecimal(getCellStringVal(shopManagerCost).equals("") ? "0" : getCellStringVal(shopManagerCost)));
            XSSFCell headChefCount = row.getCell(8);
            sRevenueStatistics.setHeadChefCount((int) Double.parseDouble(getCellStringVal(headChefCount).equals("") ? "0" : getCellStringVal(headChefCount)));
            XSSFCell headChefCost = row.getCell(9);
            sRevenueStatistics.setHeadChefCost(new BigDecimal(getCellStringVal(headChefCost).equals("") ? "0" : getCellStringVal(headChefCost)));
            XSSFCell chefCount = row.getCell(10);
            sRevenueStatistics.setChefCount((int) Double.parseDouble(getCellStringVal(chefCount).equals("") ? "0" : getCellStringVal(chefCount)));
            XSSFCell chefCost = row.getCell(11);
            sRevenueStatistics.setChefCost(new BigDecimal(getCellStringVal(chefCost).equals("") ? "0" : getCellStringVal(chefCost)));
            XSSFCell pastryCookCount = row.getCell(12);
            sRevenueStatistics.setPastryCookCount((int) Double.parseDouble(getCellStringVal(pastryCookCount).equals("") ? "0" : getCellStringVal(pastryCookCount)));
            XSSFCell pastryCookCost = row.getCell(13);
            sRevenueStatistics.setPastryCookCost(new BigDecimal(getCellStringVal(pastryCookCost).equals("") ? "0" : getCellStringVal(pastryCookCost)));
            XSSFCell othersEmployeeCount = row.getCell(14);
            sRevenueStatistics.setOthersEmployeeCount((int) Double.parseDouble(getCellStringVal(othersEmployeeCount).equals("") ? "0" : getCellStringVal(othersEmployeeCount)));
            XSSFCell othersEmployeeCost = row.getCell(15);
            sRevenueStatistics.setOthersEmployeeCost(new BigDecimal(getCellStringVal(othersEmployeeCost).equals("") ? "0" : getCellStringVal(othersEmployeeCost)));
            XSSFCell cerealsCost = row.getCell(16);
            sRevenueStatistics.setCerealsCost(new BigDecimal(getCellStringVal(cerealsCost).equals("") ? "0" : getCellStringVal(cerealsCost)));
            XSSFCell peanutOilCost = row.getCell(17);
            sRevenueStatistics.setPeanutOilCost(new BigDecimal(getCellStringVal(peanutOilCost).equals("") ? "0" : getCellStringVal(peanutOilCost)));
            XSSFCell flavourCost = row.getCell(18);
            sRevenueStatistics.setFlavourCost(new BigDecimal(getCellStringVal(flavourCost).equals("") ? "0" : getCellStringVal(flavourCost)));
            XSSFCell yogurtCost = row.getCell(19);
            sRevenueStatistics.setYogurtCost(new BigDecimal(getCellStringVal(yogurtCost).equals("") ? "0" : getCellStringVal(yogurtCost)));
            XSSFCell vegetablesCost = row.getCell(20);
            sRevenueStatistics.setVegetablesCost(new BigDecimal(getCellStringVal(vegetablesCost).equals("") ? "0" : getCellStringVal(vegetablesCost)));
            XSSFCell meatCost = row.getCell(21);
            sRevenueStatistics.setMeatCost(new BigDecimal(getCellStringVal(meatCost).equals("") ? "0" : getCellStringVal(meatCost)));
            XSSFCell eggCost = row.getCell(22);
            sRevenueStatistics.setEggCost(new BigDecimal(getCellStringVal(eggCost).equals("") ? "0" : getCellStringVal(eggCost)));
            XSSFCell seafoodCost = row.getCell(23);
            sRevenueStatistics.setSeafoodCost(new BigDecimal(getCellStringVal(seafoodCost).equals("") ? "0" : getCellStringVal(seafoodCost)));
            XSSFCell fruitCost = row.getCell(24);
            sRevenueStatistics.setFruitCost(new BigDecimal(getCellStringVal(fruitCost).equals("") ? "0" : getCellStringVal(fruitCost)));
            XSSFCell othersIngredientCost = row.getCell(25);
            sRevenueStatistics.setOthersIngredientCost(new BigDecimal(getCellStringVal(othersIngredientCost).equals("") ? "0" : getCellStringVal(othersIngredientCost)));
            XSSFCell equipmentCharge = row.getCell(26);
            sRevenueStatistics.setEquipmentCharge(new BigDecimal(getCellStringVal(equipmentCharge).equals("") ? "0" : getCellStringVal(equipmentCharge)));
            XSSFCell gasCharge = row.getCell(27);
            sRevenueStatistics.setGasCharge(new BigDecimal(getCellStringVal(gasCharge).equals("") ? "0" : getCellStringVal(gasCharge)));
            XSSFCell waterCharge = row.getCell(28);
            sRevenueStatistics.setWaterCharge(new BigDecimal(getCellStringVal(waterCharge).equals("") ? "0" : getCellStringVal(waterCharge)));
            XSSFCell electricCharge = row.getCell(29);
            sRevenueStatistics.setElectricCharge(new BigDecimal(getCellStringVal(electricCharge).equals("") ? "0" : getCellStringVal(electricCharge)));
            XSSFCell servicesManagerCost = row.getCell(30);
            sRevenueStatistics.setServiceManagerCost(new BigDecimal(getCellStringVal(servicesManagerCost).equals("") ? "0" : getCellStringVal(servicesManagerCost)));
            XSSFCell technologyManagerCost = row.getCell(31);
            sRevenueStatistics.setTechnologyManagerCost(new BigDecimal(getCellStringVal(technologyManagerCost).equals("") ? "0" : getCellStringVal(technologyManagerCost)));
            XSSFCell othersManagerCost = row.getCell(32);
            sRevenueStatistics.setOthersManagerCost(new BigDecimal(getCellStringVal(othersManagerCost).equals("") ? "0" : getCellStringVal(othersManagerCost)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    private static String getCellStringVal(XSSFCell cell) {
        CellType cellType = cell.getCellTypeEnum();
        switch (cellType) {
            case NUMERIC:
                return cell.getStringCellValue();
            case STRING:
                return cell.getStringCellValue();
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getRawValue();
            case BLANK:
                return "";
            case ERROR:
                return String.valueOf(cell.getErrorCellValue());
            default:
                return StringUtils.EMPTY;
        }
    }


}
