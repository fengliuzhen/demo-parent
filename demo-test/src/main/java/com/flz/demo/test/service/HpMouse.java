package com.flz.demo.test.service;

public class HpMouse implements IMouse {
    @Override
    public String sayHi() {
        return "hi,my name is HpMouse!";
    }

    @Override
    public String getMax() {
        return null;
    }
}
