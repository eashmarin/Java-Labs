package lab2.tests;

import lab2.Calculator;
import lab2.exceptions.ArgumentException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.ArrayList;

public class ErrorsTests {
    private ArrayList<String> definitionData = new ArrayList<>();
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

    @Test (expectedExceptions = Exception.class)
    public void emptyStackTest() throws Exception {
        inputData.add("*");
        calculator = new Calculator(inputData);
        calculator.calculate();
    }

    @Test (expectedExceptions = Exception.class)
    public void undefVarTest() throws Exception {
        inputData.add("PUSH pp");
        calculator = new Calculator(inputData);
        calculator.calculate();
    }

    @Test (expectedExceptions = Exception.class)
    public void notEnoughOperandsTest() throws Exception {
        inputData.add("PUSH a");
        inputData.add("+");
        calculator = new Calculator(inputData);
        calculator.calculate();
    }

    @Test (expectedExceptions = Exception.class)
    public void invalidCmdTest() throws Exception {
        inputData.add("define A 15");
        inputData.add("SQRT");
        calculator = new Calculator(inputData);
        calculator.calculate();
    }

    @Test (expectedExceptions = Exception.class)
    public void invalidDefineTest() throws Exception {
        inputData.add("DEFINE A");
        inputData.add("SQRT");
        calculator = new Calculator(inputData);
        calculator.calculate();
    }

    @Test (expectedExceptions = Exception.class)
    public void invalidPushTest() throws Exception {
        inputData.add("PUSH");
        inputData.add("SQRT");
        calculator = new Calculator(inputData);
        calculator.calculate();
    }

    @Test (expectedExceptions = Exception.class)
    public void invalidDefValueTest() throws Exception {
        inputData.add("DEFINE var k");
        inputData.add("PUSH k");
        inputData.add("SQRT");
        calculator = new Calculator(inputData);
        calculator.calculate();
    }

    @Test (expectedExceptions = Exception.class)
    public void divisionByZeroTest() throws Exception {
        inputData.add("DEFINE A 0");
        inputData.add("PUSH a");
        inputData.add("PUSH A");
        inputData.add("/");
        calculator = new Calculator(inputData);
        calculator.calculate();
    }

}
