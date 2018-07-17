package com.flz.demo.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class SerializableUtils {

    private static Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    /**
     * 反序列化
     * @param strInput
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T strToObj(String strInput,Class<T> tClass)
    {
        return gson.fromJson(strInput,tClass);
    }
    /**
     * 反序列化
     * @param strInput
     * @param <T>
     * @return
     */
    public static <T> List<T> strToList(String strInput)
    {
        return gson.fromJson(strInput,new TypeToken<List<T>>(){}.getType());
    }
}
