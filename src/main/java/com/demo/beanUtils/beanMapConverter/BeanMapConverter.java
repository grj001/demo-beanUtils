package com.demo.beanUtils.beanMapConverter;


import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class BeanMapConverter {


    /**
     * Map 转化为 bean
     */
    public static void mapToBean(){

        MyObject o = new MyObject();
        System.out.println("初始化Object o");

        HashMap<String, Object> m = new HashMap<String, Object>();
        m.put("name","Tom");
        m.put("age", 11);
        System.out.println("初始化Map m, 并放入Key, Value");

        try {
            System.out.println("进行 Map --> bean 转换");
            BeanUtils.populate(o, m);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("进行打印...");
        printObj(o);
    }


    private static void printMap(Map m){
        m.forEach((k,v) -> {
            System.out.println(k+"-"+v);
        });
    }

    private static void printObj(Object o){
        System.out.println("将Object类型 转换为 map类型");
        try {
            BeanUtils.describe(o).forEach((k,v) -> {
                System.out.println(k+"-"+v);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args){
        mapToBean();
    }
}
