package com.demo.beanUtils.beanMapConverter;

import com.alibaba.fastjson.JSON;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BeanMapConverterJ {




    /**
     * Map转换成对象（JDK实现）
     * @param map
     * @param t
     * @param <T>
     * @return
     */
    public static <T> T map2BeanJava(Map<String, Object> map, T t) {
        if(map == null || t == null) {
            return null;
        }

        /*
            1.
         */

        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(t.getClass());
            //创建一属性描述器
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();

            for(PropertyDescriptor pd : pds) {
                String key = pd.getName();

                if(map.containsKey(key)) {
                    Object value = map.get(key);
                    //得到setter属性
                    Method setter = pd.getWriteMethod();
                    setter.invoke(t, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return t;
    }

    /**
     * 对象转换成Map（JDK实现）
     * @param obj
     * @return
     */
    public static Map<String, Object> bean2MapJava(Object obj) {
        if(obj == null) {
            return null;
        }

        Map<String, Object> rtnMap = new HashMap<>();

        try{
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
            for(PropertyDescriptor pd : pds) {
                String key = pd.getName();

                if(!key.equals("class")) {
                    Method getter = pd.getReadMethod();
                    Object value = getter.invoke(obj);

                    rtnMap.put(key, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rtnMap;
    }

    public static void main(String[] args) {


        Map<String,Object> m = new HashMap<String, Object>();
        m.put("name","Tom");
        MyObject o = new MyObject();

        System.out.println( JSON.toJSONString(map2BeanJava(m, o)) );

    }




}
