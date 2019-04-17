package com.baiyajin.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReflectUtil {


    public static <T> T mapToObjcet(Map<String,Object> map, Class<T> entity) {
        T t = null;
        try {
            Method[] ms= entity.getDeclaredMethods();
            t = entity.newInstance();
            System.out.println(map);
            for (String key : map.keySet()) {
                String keym="set"+key.substring(0,1).toUpperCase()+key.substring(1);
                for(Method m:ms){
                    if(m.getName().equals(keym)){
                        m.invoke(t,map.get(key));
                    }
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }


    public static <T> List<T> mapToObjcet(List<Map<String,Object>> listMap , Class<T> entity){
        List<T> listObject = new ArrayList<T>();
        for(Map<String,Object> m:listMap){
            listObject.add(mapToObjcet(m,entity));
        }
        return listObject;
    }




}