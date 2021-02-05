package com.waiwaiwai.controller;

import com.waiwaiwai.config.WordTemplate2;
import org.apache.poi.xwpf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class WordDemoController {

    public static void main(String[] args) throws IOException {

        Map<String, Object> wordDataMap = new HashMap<String, Object>();// 存储报表全部数据
        Map<String, Object> parametersMap = new HashMap<String, Object>();// 存储报表中不循环的数据

        parametersMap.put("room", "第一收发室");
        parametersMap.put("name", "我时项目名称");
        parametersMap.put("yusuan", "12121");


        wordDataMap.put("parametersMap", parametersMap);
        File file = new File("E:\\模板.docx");//改成你本地文件所在目录


        // 读取word模板
        FileInputStream fileInputStream = new FileInputStream(file);
        WordTemplate2 template = new WordTemplate2(fileInputStream);
//        XWPFDocument document = new XWPFDocument(fileInputStream);
        template.replaceDocument(wordDataMap);
        // 替换数据
//        replaceDocument(document, wordDataMap);


        //生成文件
        File outputFile = new File("E:\\输出.docx");//改成你本地文件所在目录
        FileOutputStream fos = new FileOutputStream(outputFile);
        template.getDocument().write(fos);

    }

    private static void replaceDocument(XWPFDocument document, Map<String, Object> wordDataMap) {
        if (!wordDataMap.containsKey("parametersMap")) {
            System.out.println("数据源错误--数据源(parametersMap)缺失");
            return;
        }

        Map<String, Object> parametersMap = (Map<String, Object>) wordDataMap.get("parametersMap");
        // 所有对象（段落+表格）
        List<IBodyElement> bodyElements = document.getBodyElements();
        // 标记模板文件（段落+表格）总个数
        int templateBodySize = bodyElements.size();

        // 当前操作表格对象的索引
        int curT = 0;
        // 当前操作段落对象的索引
        int curP = 0;

        for (int a = 0; a < templateBodySize; a++) {
            IBodyElement body = bodyElements.get(a);
            // 处理表格
            if (BodyElementType.TABLE.equals(body.getElementType())) {
                List<XWPFTable> tables = body.getBody().getTables();
                XWPFTable xwpfTable = tables.get(curT);
                if (xwpfTable != null) {
                    // 处理表格
                    // 获取到模板表格第一行，用来判断表格类型
                    List<XWPFTableCell> tableCells = xwpfTable.getRows().get(0).getTableCells();
                    // 表格中的所有文本
                    String tableText = xwpfTable.getText();
                    System.out.println(tableText);

                    if (tableText.contains("##{foreach}")) {
                        // 该表格需要循环处理
                        if (tableCells.size() != 2 || tableCells.get(0).getText().trim().length() == 0) {
                            System.out.println("文档中第"
                                    + (curT + 1)
                                    + "个表格模板错误,模板表格第一行需要设置2个单元格，"
                                    + "第一个单元格存储表格类型(##{foreachTable}## 或者 ##{foreachTableRow}##)，第二个单元格定义数据源。");
                            return;
                        }

                        String tableType = tableCells.get(0).getText();
                        String dataSource = tableCells.get(1).getText();
                        if (!wordDataMap.containsKey(dataSource)) {
                            System.out.println("文档中第" + (curT + 1) + "个表格模板数据源缺失");
                            return;
                        }

                        List<Map<String, Object>> tableDataList = (List<Map<String, Object>>) wordDataMap.get(dataSource);
                        if ("##{foreachTable}##".equals(tableType)) {
                            // System.out.println("循环生成表格");
                            addTableInDocFooter(xwpfTable, tableDataList, parametersMap, 1);
                        } else if ("##{foreachTableRow}##".equals(tableType)) {
                            // System.out.println("循环生成表格内部的行");
                            addTableInDocFooter(xwpfTable, tableDataList, parametersMap, 2);
                        }



                    } else if (tableText.indexOf("{") > -1) {
                        // 没有查找到##{foreach标签，查找到了普通替换数据的{}标签，该表格只需要简单替换
                        addTableInDocFooter(xwpfTable, null, parametersMap, 3);
                    } else {
                        // 没有查找到任何标签，该表格是一个静态表格，仅需要复制一个即可。
                        addTableInDocFooter(xwpfTable, null, null, 0);
                    }
                    curT++;
                }
            } else if (BodyElementType.PARAGRAPH.equals(body.getElementType())) {// 处理段落
                // System.out.println("获取到段落");
                XWPFParagraph ph = body.getBody().getParagraphArray(curP);
                if (ph != null) {
                    // htmlText = htmlText+readParagraphX(ph);
                    addParagraphInDocFooter(ph, null, parametersMap, 0);
                    curP++;
                }
            }
        }
    }

    public static void addTableInDocFooter(XWPFTable templateTable, List<Map<String, Object>> list,
                                    Map<String, Object> parametersMap, int flag) {
        XWPFDocument document = new XWPFDocument();
        // 循环生成整个表格
        if (flag == 1) {
            for (Map<String, Object> map : list) {
                // 获取表格整个表格
                List<XWPFTableRow> templateTableRows = templateTable.getRows();
                // 创建表格  一行一列
                XWPFTable newTable = document.createTable();
                for (int i = 0; i < templateTableRows.size(); i++) {
                    XWPFTableRow newTableRow = newTable.createRow();
                    // 复制模板
                    CopyTableRow(newTableRow, templateTableRows.get(i));
                }
                // 移除多出来的第一行
                newTable.removeRow(0);
                // 添加回车行
                document.createParagraph();
                // 替换标签
                replaceTable(newTable, map);
            }

        }




    }

    /**
     * 根据 map 替换表格中的{key}标签
     * @param newTable
     * @param parametersMap
     */
    private static void replaceTable(XWPFTable newTable, Map<String, Object> parametersMap) {
        // 获取表格中的所有行
        List<XWPFTableRow> rows = newTable.getRows();
        for (XWPFTableRow xwpfTableRow : rows) {
        // 获取表格行中的所有单元格
            List<XWPFTableCell> tableCells = xwpfTableRow.getTableCells();
            for (XWPFTableCell tableCell : tableCells) {
            // 获取表格行中单元格的所有段落
                List<XWPFParagraph> paragraphs = tableCell.getParagraphs();
                for (XWPFParagraph paragraph : paragraphs) {
                    // 使用 map 替换段落中的${key}
                    replaceParagraph(paragraph, parametersMap);
                }
            }
        }
    }

    /**
     * 替换标签
     * @param paragraph
     * @param parametersMap
     */
    private static void replaceParagraph(XWPFParagraph paragraph, Map<String, Object> parametersMap) {
        List<XWPFRun> runs = paragraph.getRuns();
        String paragraphText = paragraph.getText();
        String regEx = "\\{.+?}";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(paragraphText);

        if (matcher.find()) {
            // 标签开始 run 位置
            int beginRunIndex = paragraph.searchText("{", new PositionInParagraph()).getBeginRun();
            // 标签结束 run 位置
            int endRunIndex = paragraph.searchText("}", new PositionInParagraph()).getEndRun();

            StringBuffer key = new StringBuffer();
            // 如果 {key} 在一个run标签内
            if (beginRunIndex == endRunIndex) {
                XWPFRun beginRun = runs.get(beginRunIndex);
                String beginRunText = beginRun.text();
                int beginIndex = beginRunText.indexOf("{");
                int endIndex = beginRunText.indexOf("}");
                int length = beginRunText.length();
                // 标签里面只有 ${key}
                if (beginIndex == 0 && endIndex == length - 1) {
                    XWPFRun xwpfRun = paragraph.insertNewRun(beginRunIndex);
                }

            }

        }

    }

    /**
     * 复制模板到新行
     * @param newTableRow
     * @param xwpfTableRow
     */
    private static void CopyTableRow(XWPFTableRow newTableRow, XWPFTableRow xwpfTableRow) {
        // 模板行的列数
        int tempRowCells = xwpfTableRow.getTableCells().size();
        for (int i = 0; i < tempRowCells; i++) {
            // 添加与模板相同个数的单元格
            newTableRow.addNewTableCell();
        }
        // 复制样式
        newTableRow.getCtRow().setTrPr(xwpfTableRow.getCtRow().getTrPr());
        // 复制单元格
        for (int i = 0; i < newTableRow.getTableCells().size(); i++) {
            copyTableCell(newTableRow.getCell(i), xwpfTableRow.getCell(i));
        }

    }

    /**
     * 复制单元格
     * @param newTableCell
     * @param tempTableCell
     */
    private static void copyTableCell(XWPFTableCell newTableCell, XWPFTableCell tempTableCell) {
        // 列属性
        newTableCell.getCTTc().setTcPr(tempTableCell.getCTTc().getTcPr());
        // 删除新建单元格的所有文本段落
        for (int i = 0; i < newTableCell.getParagraphs().size(); i++) {
            newTableCell.removeParagraph(i);
        }
        // 添加新的文本段落
        for (XWPFParagraph temParagraph : tempTableCell.getParagraphs()) {
            XWPFParagraph targetP = newTableCell.addParagraph();
            copyParagraph(targetP, temParagraph);
        }

    }

    /**
     * 复制段落
     * @param targetP
     * @param tempParagraph
     */
    private static void copyParagraph(XWPFParagraph targetP, XWPFParagraph tempParagraph) {
        // 复制段落样式
        targetP.getCTP().setPPr(tempParagraph.getCTP().getPPr());
        // 添加run标签
        for (XWPFRun temParagraphRun : tempParagraph.getRuns()) {
            XWPFRun targetRun = targetP.createRun();
            CopyRun(targetRun, temParagraphRun);
        }
    }

    /**
     * 复制文本节点run
     * @param targetRun
     * @param tempParagraphRun
     */
    private static void CopyRun(XWPFRun targetRun, XWPFRun tempParagraphRun) {
        // 复制段落的样式
        targetRun.getCTR().setRPr(tempParagraphRun.getCTR().getRPr());
        targetRun.setText(tempParagraphRun.text());
    }

    private static void addParagraphInDocFooter(XWPFParagraph ph, Object o, Map<String, Object> parametersMap, int i) {

    }

}