package com.lavr.fourth.interpreter;

/**
 * Created by 123 on 09.11.2016.
 */
public class TerminalExpressionPlus extends AbstractMathExpression {
    @Override
    public void interpret(Context c) {
        c.pushValue(c.popValue() + c.popValue());
    }
}