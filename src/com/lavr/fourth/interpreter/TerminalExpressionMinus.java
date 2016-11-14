package com.lavr.fourth.interpreter;

/**
 * Created by 123 on 09.11.2016.
 */
public class TerminalExpressionMinus extends AbstractMathExpression {
    @Override
    public void interpret(Context c) {
        double a=c.popValue();
        double b=c.popValue();
        c.pushValue(b - a);
    }
}