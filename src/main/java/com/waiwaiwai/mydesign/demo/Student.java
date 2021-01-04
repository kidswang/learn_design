package com.waiwaiwai.mydesign.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {

    /**
     * 学生ID
     */
    private Long id;

    /**
     * 学生名称
     */
    private String name;

    /**
     * 学生年龄
     */
    private Integer age;

    /**
     * 性别;0=男,1=女
     */
    private Integer sex;

    /**
     * 入学年份
     */
    private Integer grade;

    /**
     * 班级
     */
    private Integer classes;


}
