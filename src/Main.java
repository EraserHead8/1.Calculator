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
            int result = performOperation(num1, num2, operator);

            if (result < 1) {
                throw new CalculatorException("Результат работы меньше единицы, выбрасывается исключение.");
            }

            return RomanNumeral.toRoman(result);
        } else if (!isOperand1Roman && !isOperand2Roman) {
            int num1 = Integer.parseInt(operand1);
            int num2 = Integer.parseInt(operand2);
            int result = performOperation(num1, num2, operator);

            return String.valueOf(result);
        } else {
            throw new CalculatorException("Используются одновременно разные системы счисления.");
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
                if (num2 == 0) {
                    throw new CalculatorException("Деление на ноль.");
                }
                return num1 / num2;
            default:
                throw new CalculatorException("Неподходящая математическая операция.");
        }
    }
}