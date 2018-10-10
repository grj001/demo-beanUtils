package com.demo.beanUtils.beanMapConverter;


import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class BeanMapConverter {


    /**
     * bean 转化为 Map
     */
    public static void beanToMap(){

        Object o = new Object();
        System.out.println("初始化Object o");

        HashMap<String, Object> m = new HashMap<String, Object>();
        System.out.println("初始化Map m, 并放入Key, Value");

        m.put("key", "value");
        try {

            BeanUtils.populate(o, m);
            System.out.println("进行 Bean Map 转换");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("进行打印...");
        printMap(m);
    }


    private static void printMap(Map m){
        m.forEach((k,v) -> {
            System.out.println(k+"-"+v);
        });
    }


    public static void main(String[] args){
        beanToMap();
    }
}
