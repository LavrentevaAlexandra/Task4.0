package com.lavr.fourth.interpreter;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by 123 on 09.11.2016.
 */
public class ExpressionClient {
    private ArrayList<AbstractMathExpression> listExpression;
    public ExpressionClient(String expression) {
        listExpression = new ArrayList<>();
        parse(expression);
    }
    private void parse(String expression) { // синтаксический анализ
        for (String lexeme : expression.split(" +")) {
            if (lexeme.isEmpty()) {
                continue;
            }
            switch (lexeme) {
                case "+":
                    listExpression.add(new TerminalExpressionPlus());
                    break;
                case "-":
                    listExpression.add(new TerminalExpressionMinus());
                    break;
                case "*":
                    listExpression.add(new TerminalExpressionMultiply());
                    break;
                case "/":
                    listExpression.add(new TerminalExpressionDivide());
                    break;
                default:
                    Scanner scan = new Scanner(lexeme);
                    if (scan.hasNextInt()) {
                        listExpression.add( new NonterminalExpressionNumber(scan.nextDouble()));
                    }
            }
        }
    }
    public Number calculate() {
        Context context = new Context();
        // выполнение простых задач и сборка результата
        for (AbstractMathExpression terminal : listExpression) {
            terminal.interpret(context);
        }
        return (int)context.popValue();
    }
}
