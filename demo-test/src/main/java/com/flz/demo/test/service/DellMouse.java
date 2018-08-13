package com.flz.demo.test.service;

public class DellMouse extends AbstractMouse implements IMouse {
    @Override
    public String sayHi() {
        return "hi,my name is DellMouse!";
    }

    @Override
    public String getMax() {
        return null;
    }

    @Override
    public String doubbleClick() {
        return "我是重写后的双击";
    }
}
