import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScientificCalculator extends JFrame implements ActionListener {
    private JTextField displayField;
    private double firstOperand;
    private String operator;

    public ScientificCalculator() {
        setTitle("Scientific Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setLayout(new BorderLayout());

        displayField = new JTextField();
        displayField.setEditable(false);
        add(displayField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4));

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "√", "log", "C"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();

        if (action.matches("[0-9.]")) {
            displayField.setText(displayField.getText() + action);
        } else if (action.equals("C")) {
            displayField.setText("");
        } else if (action.matches("[+\\-*/]")) {
            firstOperand = Double.parseDouble(displayField.getText());
            operator = action;
            displayField.setText("");
        } else if (action.equals("=")) {
            double secondOperand = Double.parseDouble(displayField.getText());
            double result = calculate(firstOperand, secondOperand, operator);
            displayField.setText(String.valueOf(result));
        } else if (action.equals("√")) {
            double operand = Double.parseDouble(displayField.getText());
            double result = Math.sqrt(operand);
            displayField.setText(String.valueOf(result));
        } else if (action.equals("log")) {
            double operand = Double.parseDouble(displayField.getText());
            double result = Math.log10(operand);
            displayField.setText(String.valueOf(result));
        }
    }

    private double calculate(double firstOperand, double secondOperand, String operator) {
        switch (operator) {
            case "+":
                return firstOperand + secondOperand;
            case "-":
                return firstOperand - secondOperand;
            case "*":
                return firstOperand * secondOperand;
            case "/":
                return firstOperand / secondOperand;
            default:
                return 0;
        }
    }
 

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ScientificCalculator());
    }
}
