import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleCalculator extends JFrame implements ActionListener {

    private JTextField textField;
    private double firstNumber, secondNumber, result;
    private char operator;

    public SimpleCalculator() {
        // Настройка основного фрейма
        setTitle("Калькулятор");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Создание текстового поля
        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 20));
        textField.setHorizontalAlignment(JTextField.RIGHT);

        // Создание кнопок для цифр и операций
        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 10, 10));

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            button.setFont(new Font("Arial", Font.PLAIN, 18));
            buttonPanel.add(button);
        }

        // Добавление компонентов на фрейм
        setLayout(new BorderLayout());
        add(textField, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (Character.isDigit(command.charAt(0))) {
            textField.setText(textField.getText() + command);
        } else if (command.equals(".")) {
            if (!textField.getText().contains(".")) {
                textField.setText(textField.getText() + command);
            }
        } else if (command.equals("=")) {
            secondNumber = Double.parseDouble(textField.getText());

            switch (operator) {
                case '+':
                    result = firstNumber + secondNumber;
                    break;
                case '-':
                    result = firstNumber - secondNumber;
                    break;
                case '*':
                    result = firstNumber * secondNumber;
                    break;
                case '/':
                    if (secondNumber != 0) {
                        result = firstNumber / secondNumber;
                    } else {
                        textField.setText("Ошибка");
                        return;
                    }
                    break;
            }

            textField.setText(String.valueOf(result));
            firstNumber = result;
            operator = '\u0000'; // Сброс оператора
        } else {
            operator = command.charAt(0);
            firstNumber = Double.parseDouble(textField.getText());
            textField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SimpleCalculator calculator = new SimpleCalculator();
            calculator.setVisible(true);
        });
    }
}
