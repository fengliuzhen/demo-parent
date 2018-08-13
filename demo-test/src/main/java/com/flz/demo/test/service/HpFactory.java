package com.flz.demo.test.service;

public class HpFactory implements IPcFactory {
    @Override
    public IMouse createMouse() {
        return new HpMouse();
    }

    @Override
    public String getMax() {
        return null;
    }

    @Override
    public AbstractMouse createAbstractMouse() {
        return null;
    }
}
