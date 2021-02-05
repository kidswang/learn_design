package com.waiwaiwai.controller;

import com.waiwaiwai.config.WordTool;
import lombok.Data;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.junit.Test;

import java.beans.IntrospectionException;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Demo{
    @Data
    public static class Person {
        private String name;
        private int age;
        private String sex;
        public Person(String name,int age,String sex){
            this.name = name;
            this.age = age;
            this.sex = sex;
        }
    }
    @Test
    public void replaceAndInset() throws IOException, IllegalAccessException, IntrospectionException, InvocationTargetException {


        Map<String, String> map = new HashMap<>();
        map.put("${room}", "德玛西亚公司");
        map.put("${name}", "天上人间德玛西亚公司德玛西亚公司德玛西亚公司德玛西亚公司");
        map.put("${yusuan}", "2020");
//        map.put("${month}", "06");
//        map.put("${day}", "21");
//        map.put("${money}", "1000");

//        List<Person> dtos=new ArrayList<>();
//        for (int i = 0; i <20 ; i++) {
//            Person dto=new Person("张三"+i,22,"男");
//            dtos.add(dto);
//        }
//
//        List<String> head=new ArrayList<>();
//        head.add("姓名");
//        head.add("年龄");
//        head.add("性别");
//
//        String key = "$key";// 在文档中需要替换插入表格的位置

        XWPFDocument doc = null;
        BufferedOutputStream bos = null;
        try {
            // 获取站位符列表
//            List<String> placeholders = WordTool.getPlaceholderList(new FileInputStream("D:/testModel.docx"));
//            if(CollectionUtils.isNotEmpty(placeholders)){
//                placeholders.forEach(System.out::println);
//            }

            FileInputStream fileInputStream = new FileInputStream(new File("E:\\模板.docx"));
            doc = new XWPFDocument(fileInputStream);
            // 获取行内容
            List<XWPFParagraph> paragraphList = doc.getParagraphs();
            //获取占位符，并且将占位符需要替换的值写入
            List<int[]> placeholderList = WordTool.getPlaceholderList(paragraphList, map);
            //清除占位符信息
            WordTool.clearPlaceholder(placeholderList, paragraphList);
            // 插入表格
//            WordTool.insertTableBg(head,dtos,doc,key);
            FileOutputStream fileOutputStream = new FileOutputStream("E:\\testModel1.docx");
            bos = new BufferedOutputStream(fileOutputStream);
            doc.write(bos);
        } finally {
            bos.flush();
            bos.close();
            doc.close();
        }
    }
}
