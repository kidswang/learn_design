package com.waiwaiwai.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlCursor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;
import org.springframework.util.CollectionUtils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WordTool {

    /**
     * 说明, 占位符写法规范  ${param}
     */

    // 标识忽略
    public static final int ignoreTg = 0;
    // 标识已读取到'$'字符
    public static final int startTg = 1;
    // 标识已读取到'{'字符
    public static final int readTg = 2;
    // 文件类型
    public static final String contentType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";

    /**
     * 替换占位符返回字节输入流, 用于文件上传
     * @param inputStream 输入流
     * @param map         数据
     * @return
     * @throws Exception
     */
    public static ByteArrayInputStream replacePlaceholder(InputStream inputStream, Map<String, String> map) throws Exception{
        XWPFDocument doc = null;
        BufferedOutputStream bos = null;
        ByteArrayInputStream byteArrayInputStream = null;
        if (null !=inputStream && !map.isEmpty()) {
            try {
                doc = new XWPFDocument(inputStream);
                //获取文档内容
                List<XWPFParagraph> paragraphList = doc.getParagraphs();
                //获取占位符，并且将占位符需要替换的值写入
                List<int[]> placeholderList = getPlaceholderList(paragraphList, map);
                //清除占位符信息
                clearPlaceholder(placeholderList, paragraphList);
                // 字节输出流
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                bos = new BufferedOutputStream(outputStream);
                doc.write(bos);
                byteArrayInputStream = new ByteArrayInputStream(outputStream.toByteArray());
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                bos.flush();
                bos.close();
                doc.close();
            }
        }
        return byteArrayInputStream;
    }

    //创建一个表格插入到key标记的位置
    public static <T>void insertTableBg(List<String> head, List<T> data, XWPFDocument doc,String key) throws IOException, IllegalAccessException, IntrospectionException, InvocationTargetException {
        List<XWPFParagraph> paragraphList = doc.getParagraphs();
        if (paragraphList != null && paragraphList.size() > 0) {
            for (XWPFParagraph paragraph : paragraphList) {
                List<XWPFRun> runs = paragraph.getRuns();
                for (int i = 0; i < runs.size(); i++) {
                    String text = runs.get(i).getText(0);
                    if (text != null) {
                        text = text.trim();
                        if (text.indexOf(key) >= 0) {
                            runs.get(i).setText(text.replace(key, ""), 0);
                            XmlCursor cursor = paragraph.getCTP().newCursor();
                            // 在指定游标位置插入表格
                            XWPFTable table = doc.insertNewTbl(cursor);
                            CTTblPr tablePr = table.getCTTbl().getTblPr();
                            CTTblWidth width = tablePr.addNewTblW();
                            width.setW(BigInteger.valueOf(8500));
                            // 表格插入数据
                            insertInfo(table,head,data);
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * 清除占位符信息
     *
     * @param placeholderList 占位符位置信息
     * @param paragraphList   行数据
     * @return void
     * @author liuchao
     * @date 2020/6/10
     */
    public static void clearPlaceholder(List<int[]> placeholderList, List<XWPFParagraph> paragraphList) {
        if (CollectionUtils.isEmpty(placeholderList)) {
            return;
        }
        int[] currentPlaceholder = placeholderList.get(0);
        StringBuilder tempSb = new StringBuilder();
        for (int i = 0; i < paragraphList.size(); i++) {
            XWPFParagraph p = paragraphList.get(i);
            List<XWPFRun> runs = p.getRuns();
            for (int j = 0; j < runs.size(); j++) {
                XWPFRun run = runs.get(j);
                String text = run.getText(run.getTextPosition());
                StringBuilder nval = new StringBuilder();
                char[] textChars = text.toCharArray();
                for (int m = 0; m < textChars.length; m++) {
                    char c = textChars[m];
                    if (null == currentPlaceholder) {
                        nval.append(c);
                        continue;
                    }
                    // 排除'$'和'}'两个字符之间的字符
                    int start = currentPlaceholder[0] * 1000000 + currentPlaceholder[1] * 500 + currentPlaceholder[2];
                    int end = currentPlaceholder[3] * 1000000 + currentPlaceholder[4] * 500 + currentPlaceholder[5];
                    int cur = i * 1000000 + j * 500 + m;
                    if (!(cur >= start && cur <= end)) {
                        nval.append(c);
                    } else {
                        tempSb.append(c);
                    }
                    //判断是否是占位符结尾，如果是那获取新的占位符
                    if (tempSb.toString().endsWith("}")) {
                        placeholderList.remove(0);
                        if (CollectionUtils.isEmpty(placeholderList)) {
                            currentPlaceholder = null;
                            continue;
                        }
                        currentPlaceholder = placeholderList.get(0);
                        tempSb = new StringBuilder();
                    }
                }
                run.setText(nval.toString(), run.getTextPosition());

            }
        }
    }

    /**
     * 获取占位符信息，并且在占位符后面填充值
     *
     * @param paragraphList 行数据
     * @param map           要替换的占位符key\value
     * @return java.util.List<int [ ]>
     * @author liuchao
     * @date 2020/6/10
     */
    public static List<int[]> getPlaceholderList( List<XWPFParagraph> paragraphList, Map<String, String> map) {
        // 存储占位符 位置信息集合
        List<int[]> placeholderList = new ArrayList<>();
        // 当前占位符 0：'$'字符在XWPFParagraph集合中下标
        //          1：'$'字符在XWPFRun集合中下标
        //          2：'$'字符在text.toCharArray()数组下标
        //          3: '}'字符在XWPFParagraph集合中下标
        //          4: '}'字符在XWPFRun集合中下标
        //          5：'}'字符在text.toCharArray()数组下标
        int[] currentPlaceholder = new int[6];

        // 当前标识
        int modeTg = ignoreTg;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < paragraphList.size(); i++) {
            XWPFParagraph p = paragraphList.get(i);
            List<XWPFRun> runs = p.getRuns();
            for (int j = 0; j < runs.size(); j++) {
                XWPFRun run = runs.get(j);
                String text = run.getText(run.getTextPosition());
                char[] textChars = text.toCharArray();
                String newVal = "";
                StringBuilder textSofar = new StringBuilder();
                for (int m = 0; m < textChars.length; m++) {
                    char c = textChars[m];
                    textSofar.append(c);
                    switch (c) {
                        case '$': {
                            modeTg = startTg;
                            sb.append(c);
                        }
                        break;
                        case '{': {
                            if (modeTg == startTg) {
                                sb.append(c);
                                modeTg = readTg;
                                currentPlaceholder[0] = i;
                                currentPlaceholder[1] = j;
                                currentPlaceholder[2] = m - 1;
                            } else {
                                if (modeTg == readTg) {
                                    sb = new StringBuilder();
                                    modeTg = ignoreTg;
                                }
                            }
                        }
                        break;
                        case '}': {
                            if (modeTg == readTg) {
                                modeTg = ignoreTg;
                                sb.append(c);
                                String val = map.get(sb.toString());
                                if (StringUtils.isNotEmpty(val)) {
                                    newVal += textSofar.toString() + val;
                                    placeholderList.add(currentPlaceholder);
                                    textSofar = new StringBuilder();
                                }
                                currentPlaceholder[3] = i;
                                currentPlaceholder[4] = j;
                                currentPlaceholder[5] = m;
                                currentPlaceholder = new int[6];
                                sb = new StringBuilder();
                            } else if (modeTg == startTg) {
                                modeTg = ignoreTg;
                                sb = new StringBuilder();
                            }
                        }
                        default: {
                            if (modeTg == readTg) {
                                sb.append(c);
                            } else if (modeTg == startTg) {
                                modeTg = ignoreTg;
                                sb = new StringBuilder();
                            }
                        }
                    }
                }
                newVal += textSofar.toString();
                run.setTextPosition(0);
                run.setText(newVal, run.getTextPosition());
            }
        }
        return placeholderList;
    }

    /**
     * 获取占位符信息
     * @param inputStream
     * @return
     * @author liuchao
     * @date 2020/6/10
     */
    public static List<String> getPlaceholderList(InputStream inputStream) throws IOException {
        XWPFDocument doc = new XWPFDocument(inputStream);
        List<XWPFParagraph> paragraphList = doc.getParagraphs();
        // 存储占位符 位置信息集合
        List<String> placeholderList = new ArrayList<>();
        // 当前占位符 0：'$'字符在XWPFParagraph集合中下标
        //          1：'$'字符在XWPFRun集合中下标
        //          2：'$'字符在text.toCharArray()数组下标
        //          3: '}'字符在XWPFParagraph集合中下标
        //          4: '}'字符在XWPFRun集合中下标
        //          5：'}'字符在text.toCharArray()数组下标
        int[] currentPlaceholder = new int[6];

        // 当前标识
        int modeTg = ignoreTg;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < paragraphList.size(); i++) {
            XWPFParagraph p = paragraphList.get(i);
            List<XWPFRun> runs = p.getRuns();
            for (int j = 0; j < runs.size(); j++) {
                XWPFRun run = runs.get(j);
                String text = run.getText(run.getTextPosition());
                char[] textChars = text.toCharArray();
                StringBuilder textSofar = new StringBuilder();
                for (int m = 0; m < textChars.length; m++) {
                    char c = textChars[m];
                    textSofar.append(c);
                    switch (c) {
                        case '$': {
                            modeTg = startTg;
                            sb.append(c);
                        }
                        break;
                        case '{': {
                            if (modeTg == startTg) {
                                sb.append(c);
                                modeTg = readTg;
                                currentPlaceholder[0] = i;
                                currentPlaceholder[1] = j;
                                currentPlaceholder[2] = m - 1;
                            } else {
                                if (modeTg == readTg) {
                                    sb = new StringBuilder();
                                    modeTg = ignoreTg;
                                }
                            }
                        }
                        break;
                        case '}': {
                            if (modeTg == readTg) {
                                modeTg = ignoreTg;
                                sb.append(c);
                                String str = sb.toString();
                                if (StringUtils.isNotEmpty(str)) {
                                    placeholderList.add(str);
                                    textSofar = new StringBuilder();
                                }
                                currentPlaceholder[3] = i;
                                currentPlaceholder[4] = j;
                                currentPlaceholder[5] = m;
                                currentPlaceholder = new int[6];
                                sb = new StringBuilder();
                            } else if (modeTg == startTg) {
                                modeTg = ignoreTg;
                                sb = new StringBuilder();
                            }
                        }
                        default: {
                            if (modeTg == readTg) {
                                sb.append(c);
                            } else if (modeTg == startTg) {
                                modeTg = ignoreTg;
                                sb = new StringBuilder();
                            }
                        }
                    }
                }
            }
        }
        return placeholderList;
    }

    //创建一个表格插入到key标记的位置
    public static <T> void exportBg(List<String> head, List<T> data, InputStream inputStream, OutputStream outputStream, String key) {
        XWPFDocument doc = null;
        try {
            doc = new XWPFDocument(inputStream);
            List<XWPFParagraph> paragraphList = doc.getParagraphs();
            if (paragraphList != null && paragraphList.size() > 0) {
                for (XWPFParagraph paragraph : paragraphList) {
                    List<XWPFRun> runs = paragraph.getRuns();
                    for (int i = 0; i < runs.size(); i++) {
                        String text = runs.get(i).getText(0);
                        if (text != null) {
                            text = text.trim();
                            if (text.indexOf(key) >= 0) {
                                runs.get(i).setText(text.replace(key, ""), 0);
                                XmlCursor cursor = paragraph.getCTP().newCursor();
                                // 在指定游标位置插入表格
                                XWPFTable table = doc.insertNewTbl(cursor);

                                CTTblPr tablePr = table.getCTTbl().getTblPr();
                                CTTblWidth width = tablePr.addNewTblW();
                                width.setW(BigInteger.valueOf(8500));

                                insertInfo(table,head,data);
                                break;
                            }
                        }
                    }
                }
            }
            doc.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException | IllegalAccessException | IntrospectionException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    /**
     * 把信息插入表格
     *
     */
    private static <T> void insertInfo(XWPFTable table, List<String> head, List<T> data) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        //获取第一行
        XWPFTableRow row = table.getRow(0);
        //获取这个东西用来改表格宽度
        CTTblPr tblPr = table.getCTTbl().getTblPr();
        //改变长度策略为自己调整 默认为auto
        tblPr.getTblW().setType(STTblWidth.DXA);
        //设置表格宽度为7000
        tblPr.getTblW().setW(BigInteger.valueOf(8500));
        //根据头创建表格head
        for (int col = 1; col < head.size(); col++) {//默认会创建一列，即从第2列开始
            // 第一行创建了多少列，后续增加的行自动增加列
            CTTcPr cPr = row.createCell().getCTTc().addNewTcPr();
            //设置单元格高度为400
            row.getCtRow().addNewTrPr().addNewTrHeight().setVal(BigInteger.valueOf(400));
            //可以用来设置单元格长度
            //CTTblWidth width = cPr.addNewTcW();
            //width.setW(BigInteger.valueOf(2000));
        }
        //循环给表格添加头信息
        for (int i = 0; i < head.size(); i++) {
            //往表格里面写入头信息
            row.getCell(i).setText(head.get(i));
        }
        //计数器
        int i=0;
        //循环填充body列表（列表为vo数组）
        for (T item : data) {
            //获取类
            Class<?> clazz = item.getClass();
            //获取item字段
            Field[] fields = item.getClass().getDeclaredFields();
            //创建行
            row = table.createRow();
            //修改行高为500
            row.getCtRow().addNewTrPr().addNewTrHeight().setVal(BigInteger.valueOf(400));
            //循环获取vo类的属性
            for (Field field : fields) {
                //获取当前field的属性描述器
                PropertyDescriptor descriptor = new PropertyDescriptor(field.getName(), clazz);
                //获取field字段的get方法
                Method readMethod = descriptor.getReadMethod();
                //执行get方法并填充到表格中
                row.getCell(i).setText(String.valueOf(readMethod.invoke(item)));
                //计数器+1
                i++;
            }
            //执行完一行计数器归零
            i=0;
        }
    }

}
