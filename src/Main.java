import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение для подсчета:");
        String input = scanner.nextLine();
        try {
            System.out.println(calc(input));
        } catch (CalculatorException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String calc(String input) throws CalculatorException {
        String[] parts = input.split(" ");
        if (parts.length != 3) {
            throw new CalculatorException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }

        String operand1 = parts[0];
        String operator = parts[1];
        String operand2 = parts[2];



        boolean isOperand1Roman = RomanNumeral.isRoman(operand1);
        boolean isOperand2Roman = RomanNumeral.isRoman(operand2);

        if (isOperand1Roman && isOperand2Roman) {
            int num1 = RomanNumeral.valueOf(operand1).getValue();
            int num2 = RomanNumeral.valueOf(operand2).getValue();
            if (num1 < 1 || num2 < 1 || num1 >= 11 || num2 >= 11) {
                throw new CalculatorException("Один из введенных операндов не соответствует условиям. Введите числа от 1 до 10 включительно ");
            }

            int result = performOperation(num1, num2, operator);

            if (result < 1) {
                throw new CalculatorException("Результат работы меньше единицы.");
            }

            return RomanNumeral.toRoman(result);
        } else if (!isOperand1Roman && !isOperand2Roman) {
            int num1 = Integer.parseInt(operand1);
            int num2 = Integer.parseInt(operand2);
            if (num1 < 1 || num2 < 1 || num1 >= 11 || num2 >= 11) {
                throw new CalculatorException("Один из введенных операндов не соответствует условиям. Введите числа от 1 до 10 включительно ");
            }

            int result = performOperation(num1, num2, operator);

            return String.valueOf(result);
        } else {
            throw new CalculatorException("Используются одновременно разные системы счисления, либо введен некорректный формат чисел." +
                    " Пожалуйста, вводите корректно одновременно только арабские или одновременно римские числа.");
        }
    }

    private static int performOperation(int num1, int num2, String operator) throws CalculatorException {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":

                return num1 / num2;
            default:
                throw new CalculatorException("Неподходящая математическая операция.");
        }
    }
}