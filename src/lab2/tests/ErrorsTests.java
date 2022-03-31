package lab2.tests;

import lab2.Calculator;
import lab2.exceptions.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class ErrorsTests {
    private final ArrayList<String> definitionData = new ArrayList<>();
    private ArrayList<String> inputData;
    private Calculator calculator;


    @BeforeTest
    private void defineVars() {
        for (int i = 'a'; i <= (int)'z'; i++) {
            definitionData.add("DEFINE " + (char)i + " " + i);
        }
    }

    @BeforeMethod
    private void init() {
        inputData = new ArrayList<>(definitionData);
    }

    @Test (expectedExceptions = Exception.class)
    public void nullInputTest() throws Exception {
        calculator = new Calculator(null);
        calculator.calculate();
    }

    @Test (expectedExceptions = StackException.class)
    public void emptyStackTest() throws CalculatorException {
        inputData.add("*");
        calculator = new Calculator(inputData);
        calculator.calculate();
    }

    @Test (expectedExceptions = ArgumentException.class)
    public void undefVarTest() throws CalculatorException {
        inputData.add("PUSH pp");
        calculator = new Calculator(inputData);
        calculator.calculate();
    }

    @Test (expectedExceptions = StackException.class)
    public void notEnoughOperandsTest() throws CalculatorException {
        inputData.add("PUSH a");
        inputData.add("+");
        calculator = new Calculator(inputData);
        calculator.calculate();
    }

    @Test (expectedExceptions = NoPropertyException.class)
    public void invalidCmdTest() throws CalculatorException {
        inputData.add("define A 15");
        inputData.add("SQRT");
        calculator = new Calculator(inputData);
        calculator.calculate();
    }

    @Test (expectedExceptions = ArgumentException.class)
    public void invalidDefineTest() throws CalculatorException {
        inputData.add("DEFINE A");
        inputData.add("SQRT");
        calculator = new Calculator(inputData);
        calculator.calculate();
    }

    @Test (expectedExceptions = ArgumentException.class)
    public void invalidPushTest() throws CalculatorException {
        inputData.add("PUSH");
        inputData.add("SQRT");
        calculator = new Calculator(inputData);
        calculator.calculate();
    }

    @Test (expectedExceptions = ArgumentException.class)
    public void invalidDefValueTest() throws CalculatorException {
        inputData.add("DEFINE var k");
        inputData.add("PUSH k");
        inputData.add("SQRT");
        calculator = new Calculator(inputData);
        calculator.calculate();
    }

    @Test (expectedExceptions = DivByZeroException.class)
    public void divisionByZeroTest() throws CalculatorException {
        inputData.add("DEFINE A 0");
        inputData.add("PUSH a");
        inputData.add("PUSH A");
        inputData.add("/");
        calculator = new Calculator(inputData);
        calculator.calculate();
    }

    @Test (expectedExceptions = NegativeRootException.class)
    public void negativeRootTest() throws CalculatorException {
        inputData.add("PUSH -9");
        inputData.add("SQRT");
        calculator = new Calculator(inputData);
        calculator.calculate();
    }

}
