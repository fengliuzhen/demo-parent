package com.flz.demo.zkms.entity;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class ResultEntity {
    private ResultEntity() {}

    public static Map<String,Object> ResultEntity(boolean isSuccess, String code, String strMessage)
    {
        Map<String,Object> jobg = new HashMap<>();
        jobg.put("result", isSuccess);
        jobg.put("code", code);
        jobg.put("msg", strMessage);
        return jobg;
    }
    public static String JsonToStr(Map<String,Object> jsonObj)
    {
        Gson gson=new Gson();
        return gson.toJson(jsonObj);
    }
}
