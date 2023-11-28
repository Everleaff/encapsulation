// Реализовать калькулятор для четырех базовых арифметических действий, который на вход получает строку вида 2 * 6.5 и выдает результат вычисления на консоль.
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите арифметическое выражение (например, 2 * 6.5):");
        String input = scanner.nextLine();

        try {
            double result = evaluateExpression(input);
            System.out.println("Результат: " + result);
        } catch (ArithmeticException | IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static double evaluateExpression(String expression) {
        String[] tokens = expression.split(" ");

        if (tokens.length != 3) {
            throw new IllegalArgumentException("Некорректное выражение");
        }

        double operand1 = Double.parseDouble(tokens[0]);
        double operand2 = Double.parseDouble(tokens[2]);
        char operator = tokens[1].charAt(0);

        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                if (operand2 == 0) {
                    throw new ArithmeticException("Деление на ноль");
                }
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Неподдерживаемая операция: " + operator);
        }
    }
}
