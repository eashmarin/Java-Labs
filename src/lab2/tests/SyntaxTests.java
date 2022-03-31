package lab2.tests;

import lab2.Calculator;
import lab2.exceptions.CalculatorException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class SyntaxTests {
    private ArrayList<String> inputData;
    private Calculator calculator;

    @BeforeMethod
    private void init() {
        inputData = new ArrayList<>();
    }

    @Test
    public void spaceTest() throws CalculatorException {
        inputData.add("   ");
        inputData.add("DEFINE Abc 3.4");
        inputData.add("");
        inputData.add("      ");
        inputData.add("PUSH Abc");
        inputData.add(" ");
        inputData.add("SQRT");
        inputData.add("PRINT");
        inputData.add("POP");

        calculator = new Calculator(inputData);
        calculator.calculate();
        Assert.assertEquals(calculator.getDataToPrint(), Math.sqrt(3.4));
    }

    @Test
    public void commentTest() throws CalculatorException {
        inputData.add("DEFINE A 3.4");
        inputData.add("PUSH A");
        inputData.add("#DEFINE B 10");
        inputData.add("#PUSH B 10");
        inputData.add("SQRT");
        inputData.add("PRINT");
        inputData.add("POP");

        calculator = new Calculator(inputData);
        calculator.calculate();
        Assert.assertEquals(calculator.getDataToPrint(), Math.sqrt(3.4));
    }

    @Test
    public void pushDoubleTest() throws CalculatorException {
        inputData.add("DEFINE some_num 34.6");
        inputData.add("PUSH some_num");
        inputData.add("PUSH 30.4");
        inputData.add("-");
        inputData.add("PRINT");

        calculator = new Calculator(inputData);
        calculator.calculate();
        Assert.assertEquals(calculator.getDataToPrint(), 34.6 - 30.4);
    }
}
