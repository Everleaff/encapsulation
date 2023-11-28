import java.util.InputMismatchException;
import java.util.Scanner;

public class LeapYearChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Введите год для проверки:");
            int year = scanner.nextInt();

            if (isLeapYear(year)) {
                System.out.println(year + " - високосный год.");
            } else {
                System.out.println(year + " - не високосный год.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Ошибка: Введите целое число.");
        }
    }

    // Метод для проверки, является ли год високосным
    private static boolean isLeapYear(int year) {
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
            return true;
        } else {
            return false;
        }
    }
}
