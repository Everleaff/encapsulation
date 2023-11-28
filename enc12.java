import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class TextEditor extends JFrame {

    private JTextArea textArea;
    private JFileChooser fileChooser;

    public TextEditor() {
        setTitle("Текстовый редактор");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание текстовой области
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);

        // Создание строки меню
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Создание меню "Файл"
        JMenu fileMenu = new JMenu("Файл");
        menuBar.add(fileMenu);

        // Создание элементов меню "Файл"
        JMenuItem openMenuItem = new JMenuItem("Открыть");
        JMenuItem saveMenuItem = new JMenuItem("Сохранить");
        JMenuItem exitMenuItem = new JMenuItem("Выход");

        // Добавление обработчиков событий для элементов меню "Файл"
        openMenuItem.addActionListener(new OpenFileListener());
        saveMenuItem.addActionListener(new SaveFileListener());
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Добавление элементов меню "Файл" в меню
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        // Инициализация компонента выбора файла
        fileChooser = new JFileChooser();

        // Отображение окна
        setVisible(true);
    }

    // Обработчик события для открытия файла
    private class OpenFileListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int result = fileChooser.showOpenDialog(TextEditor.this);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    Scanner scanner = new Scanner(selectedFile);
                    StringBuilder content = new StringBuilder();

                    while (scanner.hasNext()) {
                        content.append(scanner.nextLine()).append("\n");
                    }

                    textArea.setText(content.toString());
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(TextEditor.this, "Ошибка при открытии файла", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    // Обработчик события для сохранения файла
    private class SaveFileListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int result = fileChooser.showSaveDialog(TextEditor.this);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try (PrintWriter writer = new PrintWriter(selectedFile)) {
                    writer.write(textArea.getText());
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(TextEditor.this, "Ошибка при сохранении файла", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TextEditor());
    }
}
