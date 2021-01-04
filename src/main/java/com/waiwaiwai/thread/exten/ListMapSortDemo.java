package com.waiwaiwai.thread.exten;

import com.waiwaiwai.mydesign.openandclose.common.Person;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class ListMapSortDemo {

    @Test
    public void test() {
        String[] str = {"sdf", "fsd", "'fsd'"};
        List<String> list = Arrays.asList(str);
        list.stream().map(s -> {
            return s + "fds";
        });

        Person p1 = new Person("1", "张三", "sss", "34");
        Person p2 = new Person("2", "张三", "sss", "45");
        Person p3 = new Person("3", "张三", "sss", "75");
        Person p4 = new Person("4", "张三", "sss", "90");

        List<Person> personList = new ArrayList<>();
        personList.add(p1);
        personList.add(p2);
        personList.add(p3);
        personList.add(p4);
        personList.stream().sorted(Comparator.comparing( p -> p.getGradeId()));
        List<Person> collect = personList.stream().sorted(Comparator.comparing(Person::getGradeId).reversed()).collect(Collectors.toList());

        List<Map<String, Object>> mapList = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("gradeId", "12");
        Map<String, Object> map2 = new HashMap<>();
        map2.put("gradeId", "523");
        Map<String, Object> map3 = new HashMap<>();
        map3.put("gradeId", "52");
        Map<String, Object> map4 = new HashMap<>();
        map4.put("gradeId", "765");
        Map<String, Object> map5 = new HashMap<>();
        map5.put("gradeId", "84");
        mapList.add(map1);
        mapList.add(map2);
        mapList.add(map3);
        mapList.add(map4);
        mapList.add(map5);
//        mapList.stream().sorted(Comparator.comparing(m -> m.get("gradeId")));
//        mapList.stream().map(m -> m.entrySet().stream().sorted(Comparator.comparing()))


    }


        public static void main (String[] args){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", "ZK");
            map.put("age", 13);
            Map<String, Object> map2 = new HashMap<String, Object>();
            map2.put("name", "ZA");
            map2.put("age", 15);
            Map<String, Object> map3 = new HashMap<String, Object>();
            map3.put("name", "CX");
            map3.put("age", 20);
            Map<String, Object> map4 = new HashMap<String, Object>();
            map4.put("name", "CX");
            map4.put("age", 18);
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            list.add(map);
            list.add(map2);
            list.add(map3);
            list.add(map4);

            List<Map<String, Object>> collect = list.stream().sorted(Comparator.comparing(ListMapSortDemo::comparingByAge).reversed())
                    .collect(Collectors.toList());

            List<Map<String, Object>> name1 =
                    list.stream().sorted(Comparator.comparing(stringObjectMap -> (Integer) stringObjectMap.get("age")))
                            .collect(Collectors.toList());

            List<Map<String, Object>> name2 =
                    list.stream().sorted(Comparator.comparing((Map<String, Object> stringObjectMap) -> ( (Integer) stringObjectMap.get("age"))).reversed())
                            .collect(Collectors.toList());

            list.sort(Comparator.comparing((Map<String, Object> h) -> ((String) h.get("lastOnlineTime"))).reversed()); //倒叙

            name2.forEach(System.out::println);
        }

        private static Integer comparingByAge (Map < String, Object > map){
            return (Integer) map.get("age");
        }

}