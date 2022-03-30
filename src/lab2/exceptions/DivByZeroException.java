package lab2.exceptions;

public class DivByZeroException extends CalculatorException{
    public DivByZeroException(String msg) {
        super(msg);
    }
}
