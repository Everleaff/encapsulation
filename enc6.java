import java.util.Scanner;

public class CompoundInterestCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите действие:");
        System.out.println("1. Рассчитать будущую сумму");
        System.out.println("2. Рассчитать необходимый процент");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                calculateFutureValue();
                break;
            case 2:
                calculateRequiredRate();
                break;
            default:
                System.out.println("Неверный выбор.");
        }
    }

    // Метод для расчета будущей суммы
    private static void calculateFutureValue() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите начальное число:");
        double principal = scanner.nextDouble();

        System.out.println("Введите процентную ставку в год:");
        double rate = scanner.nextDouble() / 100; // Преобразуем проценты в десятичную дробь

        System.out.println("Введите количество периодов в годах:");
        int periods = scanner.nextInt();

        // Формула для расчета будущей суммы с использованием сложного процента
        double futureValue = principal * Math.pow(1 + rate, periods);

        System.out.println("Будущая сумма: " + futureValue);
    }

    // Метод для расчета необходимого процента
    private static void calculateRequiredRate() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите начальное число:");
        double principal = scanner.nextDouble();

        System.out.println("Введите конечное число:");
        double futureValue = scanner.nextDouble();

        System.out.println("Введите количество периодов в годах:");
        int periods = scanner.nextInt();

        // Формула для расчета необходимой процентной ставки
        double rate = Math.pow(futureValue / principal, 1.0 / periods) - 1;
        double ratePercentage = rate * 100;

        System.out.println("Необходимый процент: " + ratePercentage + "%");
    }
}
