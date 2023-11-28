import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AutoReplaceTextEditor extends JFrame {

    private JTextArea textArea;
    private AutoReplaceWorker autoReplaceWorker;

    public AutoReplaceTextEditor() {
        setTitle("Текстовый редактор с автозаменой");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание текстовой области
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);

        // Создание кнопки для запуска автозамены
        JButton autoReplaceButton = new JButton("Автозамена");
        autoReplaceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startAutoReplace();
            }
        });

        // Добавление кнопки на панель
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(autoReplaceButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Отображение окна
        setVisible(true);
    }

    private void startAutoReplace() {
        if (autoReplaceWorker != null && !autoReplaceWorker.isDone()) {
            autoReplaceWorker.cancel(true);
        }

        autoReplaceWorker = new AutoReplaceWorker();
        autoReplaceWorker.execute();
    }

    private class AutoReplaceWorker extends SwingWorker<Void, Void> {

        private String originalText;

        @Override
        protected Void doInBackground() throws Exception {
            // Получаем текст из JTextArea
            originalText = textArea.getText();
            // Здесь можно добавить логику автозамены слов
            // В данном примере просто заменим "Java" на "Swing"
            String newText = originalText.replaceAll("\\bJava\\b", "Swing");

            // Обновляем текст в EDT (Event Dispatch Thread)
            SwingUtilities.invokeLater(() -> textArea.setText(newText));
            return null;
        }

        @Override
        protected void done() {
            // Метод вызывается по завершении фоновой задачи
            JOptionPane.showMessageDialog(AutoReplaceTextEditor.this,
                    "Автозамена завершена", "Информация", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AutoReplaceTextEditor::new);
    }
}
