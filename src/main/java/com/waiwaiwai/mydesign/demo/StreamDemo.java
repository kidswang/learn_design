package com.waiwaiwai.mydesign.demo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamDemo {

    @Test
    public void testSort() {
        List<Student> studentList = this.getList1();

        // 正序排序(默认正序排序)
        List<Student> sortedCollect = studentList.stream()
                .sorted(Comparator.comparing(Student::getAge))
                .collect(Collectors.toList());

        // 倒序排序
        List<Student> reversedSortedCollect = studentList.stream()
                .sorted(Comparator.comparing(Student::getAge).reversed())
                .collect(Collectors.toList());

        // 多条件排序
        List<Student> moreConfitionSortedCollect = studentList.stream()
                .sorted(Comparator.comparing(Student::getAge)
                        .thenComparing(Student::getGrade)
                        .thenComparing(Student::getClasses))
                .collect(Collectors.toList());

        sortedCollect.forEach(System.out::println);
        reversedSortedCollect.forEach(System.out::println);
        moreConfitionSortedCollect.forEach(System.out::println);
    }


    private List<Student> getList1() {
        List<Student> students = new ArrayList<>();
        Student student1 = new Student(1L, "张三", 18, 0, 2020, 12);
        Student student2 = new Student(2L, "李四", 20, 0, 2018, 5);
        Student student3 = new Student(3L, "王五", 21, 0, 2017, 5);
        Student student4 = new Student(4L, "赵六", 22, 1, 2016, 5);
        Student student5 = new Student(5L, "田七", 18, 1, 2018, 1);

        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);

        return students;
    }

    private List<Student> getList2() {
        Student student1 = new Student(1L, "张三", 18, 0, 2020, 12);
        Student student5 = new Student(5L, "张三1", 18, 1, 2018, 1);
        Student student2 = new Student(2L, "张三2", 20, 0, 2018, 5);
        Student student3 = new Student(3L, "张三3", 21, 0, 2017, 5);
        Student student4 = new Student(4L, "张三4", 22, 1, 2016, 5);

        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);

        return students;
    }


}
