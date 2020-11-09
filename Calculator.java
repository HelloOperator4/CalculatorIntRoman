import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Calculator {
    private final static String S_INPUT_MATH_OPERATION = "Введите математическую операцию: ";
    private final static String S_ERROR_INPUT = "Ошибка ввода: %s";
    private final static String S_ERROR_UNKNOWN_OPERATION = "Неизвестная операция";
    private final static char ADD_OPER_TYPE = '+';
    private final static char SUB_OPER_TYPE = '-';
    private final static char MUL_OPER_TYPE = '*';
    private final static char DIV_OPER_TYPE = '/';

    public static void main(String[] args) {
        System.out.println(S_INPUT_MATH_OPERATION);
        final Scanner scan = new Scanner(System.in);
        final String operationStr = scan.nextLine();
        System.out.println(processOperation(operationStr));
    }

    private static String processOperation(final String operationStr) {
        final List<Character> operTypes = Arrays.asList(ADD_OPER_TYPE, SUB_OPER_TYPE, MUL_OPER_TYPE, DIV_OPER_TYPE);
        for (char operType : operTypes) {
            final int index = operationStr.indexOf(operType);
            if (index > -1) {
                try {
                    NumberParser numParser1 = new NumberParser(operationStr.substring(0, index).trim());
                    NumberParser numParser2 = new NumberParser(operationStr.substring(index + 1).trim());
                    if (numParser1.getIsRoman() == numParser2.getIsRoman()) {
                        final int num1 = numParser1.getValue();
                        final int num2 = numParser2.getValue();
                        int result;
                        switch (operType) {
                            case ADD_OPER_TYPE: result = num1 + num2; break;
                            case SUB_OPER_TYPE: result = num1 - num2; break;
                            case MUL_OPER_TYPE: result = num1 * num2; break;
                            case DIV_OPER_TYPE: result = num1 / num2; break;
                            default:
                                return S_ERROR_UNKNOWN_OPERATION;
                        }
                        return numParser1.getIsRoman() ? String.valueOf(result) : Converter.intToRoman(result);
                    }
                    else {
                        throw new NumberFormatException("Разный тип чисел");
                    }
                } catch (Exception e) {
                    return String.format(S_ERROR_INPUT, e.getMessage());
                }
            }
        }
       return S_ERROR_UNKNOWN_OPERATION;
    }
}




