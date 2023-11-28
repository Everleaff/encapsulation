import javax.swing.*;
import java.awt.*;

public class LayoutManagerExample extends JFrame {

    public LayoutManagerExample() {
        setTitle("Пример разных менеджеров компоновки");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        // Создание JPanel с FlowLayout
        JPanel flowLayoutPanel = new JPanel(new FlowLayout());
        flowLayoutPanel.add(new JButton("Кнопка 1"));
        flowLayoutPanel.add(new JButton("Кнопка 2"));
        flowLayoutPanel.add(new JButton("Кнопка 3"));

        // Создание JPanel с BorderLayout
        JPanel borderLayoutPanel = new JPanel(new BorderLayout());
        borderLayoutPanel.add(new JButton("Север"), BorderLayout.NORTH);
        borderLayoutPanel.add(new JButton("Юг"), BorderLayout.SOUTH);
        borderLayoutPanel.add(new JButton("Запад"), BorderLayout.WEST);
        borderLayoutPanel.add(new JButton("Восток"), BorderLayout.EAST);
        borderLayoutPanel.add(new JButton("Центр"), BorderLayout.CENTER);

        // Создание основного контейнера
        Container container = getContentPane();
        container.setLayout(new GridLayout(1, 2));

        // Добавление JPanel с FlowLayout
        container.add(flowLayoutPanel);

        // Добавление JPanel с BorderLayout
        container.add(borderLayoutPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LayoutManagerExample::new);
    }
}
