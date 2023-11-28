public class BubbleSort {
    public static void main(String[] args) {
        int[] array = {64, 34, 25, 12, 22, 11, 90};

        System.out.println("Исходный массив:");
        printArray(array);

        bubbleSort(array);

        System.out.println("\nОтсортированный массив:");
        printArray(array);
    }

    // Метод для сортировки массива методом пузырька
    private static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Меняем элементы местами, если они находятся в неправильном порядке
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    swapped = true;
                }
            }

            // Если внутренний цикл не совершил ни одной замены, массив уже отсортирован
            if (!swapped) {
                break;
            }
        }
    }

    // Метод для вывода элементов массива
    private static void printArray(int[] arr) {
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
