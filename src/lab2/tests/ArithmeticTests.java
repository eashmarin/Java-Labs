package lab2.tests;

import lab2.Calculator;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class ArithmeticTests {
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

    @Test
    public void plusTest() throws Exception {
        inputData.add("PUSH " + 'k');
        inputData.add("PUSH " + 'm');
        inputData.add("+");
        inputData.add("PRINT");
        inputData.add("POP");

        calculator = new Calculator(inputData);
        calculator.calculate();
        Assert.assertEquals(calculator.getDataToPrint(), 'k' + 'm');
    }
    @Test
    public void minusTest() throws Exception {
        inputData.add("PUSH " + 'a');
        inputData.add("PUSH " + 'h');
        inputData.add("-");
        inputData.add("PRINT");
        inputData.add("POP");

        Calculator calculator = new Calculator(inputData);
        calculator.calculate();
        Assert.assertEquals(calculator.getDataToPrint(), 'a' - 'h');
    }

    @Test
    public void divideTest() throws Exception {


        inputData.add("PUSH " + 'z');
        inputData.add("PUSH " + 'd');
        inputData.add("/");
        inputData.add("PRINT");
        inputData.add("POP");

        calculator = new Calculator(inputData);
        calculator.calculate();
        Assert.assertEquals(calculator.getDataToPrint(), (double)'z' / 'd');
    }

    @Test
    public void multiplyTest() throws Exception {
        inputData.add("PUSH " + 'k');
        inputData.add("PUSH " + 'm');
        inputData.add("*");
        inputData.add("PRINT");
        inputData.add("POP");

        calculator = new Calculator(inputData);
        calculator.calculate();
        Assert.assertEquals(calculator.getDataToPrint(), (double)('k' * 'm'));
    }

    @Test
    public void sqrtTest() throws Exception {
        inputData.add("PUSH " + 'h');
        inputData.add("SQRT");
        inputData.add("PRINT");
        inputData.add("POP");

        Calculator calculator = new Calculator(inputData);
        calculator.calculate();
        Assert.assertEquals(calculator.getDataToPrint(), Math.sqrt('h'));
    }

}