package com.flz.demo.test.service;

public abstract class AbstractMouse {
    private int width;
    private int height;
    private int length;
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }

    public String singleClick()
    {
        return "我是鼠标的单击";
    }
    public abstract String doubbleClick();
}
