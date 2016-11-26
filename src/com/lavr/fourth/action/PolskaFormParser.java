package com.lavr.fourth.action;

import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 123 on 09.11.2016.
 */
public class PolskaFormParser {    // add replacement of increment & decrement as ++x=1+x, --x(--)= x-1
    private static final String LEFT_REGEX_INC = "\\+\\+((\\d)+)";
    private static final String RIGHT_REGEX_INC = "((\\d)+)\\+\\+";
    private static final String LEFT_REGEX_DEC = "--((\\()+(-)?(\\d)+)|(--(\\d)+)";
    private static final String RIGHT_REGEX_DEC = "((\\d)+(\\))*)--";
    private static final String NUMBER_REGEX="[0-9]+";


    private String replaceIncAndDec(String expression){
        Pattern leftIncrementPattern=Pattern.compile(LEFT_REGEX_INC);
        Pattern rightIncrementPattern=Pattern.compile(RIGHT_REGEX_INC);
        Pattern leftDecrementPattern=Pattern.compile(LEFT_REGEX_DEC);
        Pattern rightDecrementPattern=Pattern.compile(RIGHT_REGEX_DEC);

        String suitable,ultimate;
        Matcher matcher=leftIncrementPattern.matcher(expression);
        while (matcher.find()) {
            suitable=matcher.group();
            ultimate=suitable.substring(2);
            expression=expression.replaceFirst("\\+\\+"+ultimate,"("+ultimate+"+1)");
        }
        matcher=rightIncrementPattern.matcher(expression);
        while (matcher.find()) {
            suitable=matcher.group();
            ultimate=suitable.substring(0,suitable.length()-2);
            expression=expression.replaceFirst(ultimate+"\\+\\+","("+ultimate+"+1)");
        }
        matcher=leftDecrementPattern.matcher(expression);
        while (matcher.find()) {
            suitable=matcher.group();
            ultimate=suitable.substring(2);
            expression=expression.replaceFirst(suitable,"("+ultimate+"-1)");
        }
        matcher=rightDecrementPattern.matcher(expression);
        while (matcher.find()) {
            suitable=matcher.group();
            ultimate=suitable.substring(0,suitable.length()-2);
            expression=expression.replaceFirst(suitable,"("+ultimate+"-1)");
        }
        return expression;
    }

    public String format(String expression) throws PolskaFormException {
        StringBuilder polskaExpression = new StringBuilder();
        ArrayDeque<Character> stack = new ArrayDeque<>();

        expression=replaceIncAndDec(expression);

        ArrayList<Integer>numbers=new ArrayList<>();
        Pattern numberPattern=Pattern.compile(NUMBER_REGEX);
        Matcher numberMatcher=numberPattern.matcher(expression);
        while(numberMatcher.find()){
            numbers.add(Integer.valueOf(numberMatcher.group()));
        }
        int numberCounter=0;
        int signCounter=0;

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (Character.isDigit(ch)) {
                polskaExpression.append(numbers.get(numberCounter));
                polskaExpression.append(" ");
                i+=numbers.get(numberCounter).toString().length()-1;
                numberCounter++;
            } else if(ch=='('){
                stack.push(ch);
            }else if (ch == ')') {
                if (!stack.isEmpty()) {
                    char temp = stack.pop();
                    while (temp != '(') {
                        polskaExpression.append(temp);
                        polskaExpression.append(" ");
                        temp = stack.pop();
                    }
                }
            } else if (ch == '+' || ch == '-' || ch == '/' || ch == '*') {
                if(ch=='-'&& (i==0 || expression.charAt(i-1)=='(')){
                    polskaExpression.append("-");
                    polskaExpression.append(numbers.get(numberCounter));
                    polskaExpression.append(" ");
                    i+=numbers.get(numberCounter).toString().length();
                    numberCounter++;
                }else {
                    signCounter++;
                    if (!stack.isEmpty()) {
                        char temp = stack.pop();
                        while (temp == '*' || temp == '/') {
                            polskaExpression.append(temp);
                            polskaExpression.append(" ");
                            temp = stack.pop();
                        }
                        stack.push(temp);
                        stack.push(ch);
                    } else {
                        stack.push(ch);
                    }
                }
            }
        }
        while(!stack.isEmpty()){
            polskaExpression.append(stack.pop());
            polskaExpression.append(" ");
        }
        if(numberCounter-signCounter!=1){
            throw new PolskaFormException("Wrong expression. Be sure all brackets are closed and negative number are in brackets.");
        }
        return polskaExpression.toString();
    }
}
