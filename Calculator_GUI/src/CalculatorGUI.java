

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI extends JFrame {
    private JTextField display;
    private double operand1;
    private String operator;

    public CalculatorGUI() {
        setTitle("Calculator");
        setIconImage(new ImageIcon("C:\\Users\\n" + //
                "ourh\\Desktop\\vector-calculator-icon-design.jpg").getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        pack();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.BLACK);
        display = new JTextField();
        display.setPreferredSize(new Dimension(300, 40));
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setBackground(Color.DARK_GRAY);
        display.setForeground(Color.WHITE);
        panel.add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 4, 10, 10));
        buttonPanel.setBackground(Color.BLACK);
        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setPreferredSize(new Dimension(60, 60));
            button.setFont(new Font("Arial", Font.BOLD, 16));
            button.setBackground(Color.PINK);
            button.setForeground(Color.WHITE);
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }

        panel.add(buttonPanel, BorderLayout.CENTER);
        getContentPane().add(panel);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String command = event.getActionCommand();

            if (command.matches("[0-9.]")) {
                display.setText(display.getText() + command);
            } else if (command.matches("[+\\-*/]")) {
                operand1 = Double.parseDouble(display.getText());
                operator = command;
                display.setText("");
            } else if (command.equals("=")) {
                double operand2 = Double.parseDouble(display.getText());
                double result = calculateResult(operand1, operand2, operator);
                display.setText(String.valueOf(result));
            }
        }

        private double calculateResult(double operand1, double operand2, String operator) {
            switch (operator) {
                case "+":
                    return operand1 + operand2;
                case "-":
                    return operand1 - operand2;
                case "*":
                    return operand1 * operand2;
                case "/":
                    if (operand2 == 0) {
                        JOptionPane.showMessageDialog(null, "Division by zero is not allowed");
                        return 0;
                    }
                    return operand1 / operand2;
                default:
                    return 0;
            }
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new CalculatorGUI().setVisible(true));
    }
}



