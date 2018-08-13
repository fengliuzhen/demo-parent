package com.flz.demo.test.service;

public class Singleton {
    private static Singleton instance=new Singleton();
    private Singleton(){}
    public static Singleton getInstance()
    {
        return instance;
    }
    public String getMessage()
    {
        return "我是单例";
    }
}
