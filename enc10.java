import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class FileCompression {

    public static void compressFile(String sourceFilePath, String destinationFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(destinationFilePath))) {

            Set<String> uniqueLines = new HashSet<>();
            int deletedLinesCount = 0;
            String line;

            while ((line = reader.readLine()) != null) {
                if (uniqueLines.contains(line)) {
                    // Строка уже встречалась, увеличиваем счетчик удаленных строк
                    deletedLinesCount++;
                } else {
                    // Строка встречается впервые, добавляем ее в множество
                    uniqueLines.add(line);
                    // Записываем в файл-назначение
                    writer.write(line);
                    writer.newLine();
                }
            }

            // Записываем количество удаленных строк в файл-назначение
            writer.write("Количество удаленных дублирующихся строк: " + deletedLinesCount);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void restoreFile(String compressedFilePath, String restoredFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(compressedFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(restoredFilePath))) {

            String line;
            boolean countingLines = false;

            while ((line = reader.readLine()) != null) {
                // Проверяем, началось ли подсчет удаленных строк
                if (line.startsWith("Количество удаленных дублирующихся строк:")) {
                    countingLines = true;
                    // Пропускаем эту строку восстановленного файла
                    continue;
                }

                // Если подсчет начался, игнорируем строки
                if (countingLines) {
                    continue;
                }

                // Записываем строки восстановленного файла
                writer.write(line);
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String sourceFilePath = "source.txt";
        String compressedFilePath = "compressed.txt";
        String restoredFilePath = "restored.txt";

        // Создаем текстовый файл для тестирования
        createTestFile(sourceFilePath);

        // Сжимаем файл и записываем результат в compressed.txt
        compressFile(sourceFilePath, compressedFilePath);
        System.out.println("Файл успешно сжат. Результат записан в compressed.txt");

        // Восстанавливаем файл и записываем результат в restored.txt
        restoreFile(compressedFilePath, restoredFilePath);
        System.out.println("Файл успешно восстановлен. Результат записан в restored.txt");
    }

    private static void createTestFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Line 1");
            writer.newLine();
            writer.write("Line 2");
            writer.newLine();
            writer.write("Line 1");
            writer.newLine();
            writer.write("Line 3");
            writer.newLine();
            writer.write("Line 2");
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
