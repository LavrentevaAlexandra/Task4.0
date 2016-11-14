package com.lavr.fourth.interpreter;

import java.util.ArrayDeque;

/**
 * Created by 123 on 09.11.2016.
 */
public class Context {
    private ArrayDeque<Double> contextValues = new ArrayDeque<>();
    double popValue() {
        return contextValues.pop();
    }
    void pushValue(double value) {
        this.contextValues.push(value);
    }
}
