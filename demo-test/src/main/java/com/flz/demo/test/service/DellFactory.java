package com.flz.demo.test.service;

public class DellFactory implements IPcFactory {

    @Override
    public IMouse createMouse() {
        return new DellMouse();
    }

    @Override
    public String getMax() {
        return null;
    }

    @Override
    public AbstractMouse createAbstractMouse() {
        return new DellMouse();
    }
}
