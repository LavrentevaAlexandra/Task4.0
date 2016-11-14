package test.com.lavr.fourth;

import com.lavr.fourth.action.PolskaFormParser;
import com.lavr.fourth.action.PolskaFormException;
import com.lavr.fourth.action.TextReader;
import com.lavr.fourth.interpreter.ExpressionClient;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by 123 on 14.11.2016.
 */
public class TestRunner {
    @Test(expected = RuntimeException.class)
    public void checkFileAbsence(){
        final String FILE="wrong.txt";
        TextReader reader=new TextReader();
        reader.loadText(FILE);
    }

    @Test
    public void checkCalculation()throws PolskaFormException{
        PolskaFormParser pol = new PolskaFormParser();
        String expression="-3-5++*(4/--3)";
        ExpressionClient interpreter = new ExpressionClient(pol.format(expression));
        String actual=interpreter.calculate().toString();
        String expected="-15";
        Assert.assertTrue(expected.equals(actual));

        expression="(0-(2*2*(3*(2-1/2*2)-2)-10/2))*(++5)";
        interpreter = new ExpressionClient(pol.format(expression));
        actual=interpreter.calculate().toString();
        expected="6";
        Assert.assertTrue(expected.equals(actual));

    }


}
