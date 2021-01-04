package com.waiwaiwai.controller;

import com.waiwaiwai.entity.SReserveOrder;
import com.waiwaiwai.service.SReserveOrderService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author: wangzhenglei
 * @DateTime: 2020/12/23 9:03
 * @Description: 导出大量数据
 */
@RestController
@RequestMapping(value = "/poi")
public class PoiDemo {

    @Resource
    private SReserveOrderService sReserveOrderService;

    @RequestMapping(value = "/downLoad")
    public void downLoad(HttpServletResponse response) throws IOException, ClassNotFoundException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        String[] columnTitles = {"订餐人姓名", "餐别id", "订单状态", "对应学校id", "退款时间", "支付时间", "实收金额", "食堂名称", "是否取餐", "用户类型"};
        long dataCount = sReserveOrderService.count();
        long pageSize = 50000;
        long export_times = dataCount % pageSize > 0 ? dataCount / pageSize + 1 : dataCount / pageSize;
        int count = 1;
        for (long i = 0; i < export_times; i++) {
            List<SReserveOrder> list = sReserveOrderService.findList(i, pageSize);
            for (int j = 0; j < 50; j++) {
                int index = 0;
                String sheetName = "sheet" + count;
                HSSFSheet sheet = drawExcelSheetAndTitle(workbook, sheetName, columnTitles);
                count++;
                for (SReserveOrder sReserveOrder : list) {
                    index++;
                    HSSFRow rowInfo = sheet.createRow(index);
                    rowInfo.createCell(0).setCellValue(sReserveOrder.getOrderUserName());
                    rowInfo.createCell(1).setCellValue(sReserveOrder.getDinnerType());
                    rowInfo.createCell(2).setCellValue(sReserveOrder.getOrderStatus());
                    rowInfo.createCell(3).setCellValue(sReserveOrder.getSchoolId());
    //                rowInfo.createCell(4).setCellValue(sReserveOrder.getRefundTime().toString());
    //                rowInfo.createCell(5).setCellValue(sReserveOrder.getOrderTime().toString());
    //                rowInfo.createCell(6).setCellValue(sReserveOrder.getActualPayAmount().toString());
                    rowInfo.createCell(7).setCellValue(sReserveOrder.getDiningRoomName());
                    rowInfo.createCell(8).setCellValue(sReserveOrder.getIsGetFood());
                    rowInfo.createCell(9).setCellValue(sReserveOrder.getUserType());
                }
            }
        }
        String examName = "班级表.xls";
        String fileName = new String(examName.getBytes("utf-8"), "iso-8859-1");
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        workbook.write(response.getOutputStream());
        if (workbook != null) {
            workbook.close();
        }


    }

    private HSSFSheet drawExcelSheetAndTitle(HSSFWorkbook workbook, String sheetName, String[] columnTitles) {
        int columnIndex = 0;
        int index = 0;
        HSSFSheet sheet = workbook.createSheet(sheetName);
        HSSFRow row = sheet.createRow(index);
        for (String title : columnTitles) {
            row.createCell(columnIndex).setCellValue(title);
            columnIndex ++;
        }
        return sheet;
    }
}
