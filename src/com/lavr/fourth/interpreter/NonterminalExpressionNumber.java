package com.lavr.fourth.interpreter;

/**
 * Created by 123 on 09.11.2016.
 */
public class NonterminalExpressionNumber extends AbstractMathExpression {
    private double number;
    public NonterminalExpressionNumber(Double number) {
        this.number = number;
    }
    @Override
    public void interpret(Context c) {
        c.pushValue(number);
    }
}