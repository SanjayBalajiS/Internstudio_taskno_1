import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BMICalculatorGUI extends JFrame {

    private JTextField weightField, heightField;
    private JLabel resultLabel, categoryLabel;

    public BMICalculatorGUI() {
        // Set up the window
        setTitle("BMI Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Adds space between components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title Label
        JLabel titleLabel = new JLabel("BMI Calculator", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.BLUE);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(titleLabel, gbc);

        // Weight Input
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Weight (kg):"), gbc);

        weightField = new JTextField();
        gbc.gridx = 1;
        add(weightField, gbc);

        // Height Input (in cm)
        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Height (cm):"), gbc);

        heightField = new JTextField();
        gbc.gridx = 1;
        add(heightField, gbc);

        // Calculate Button
        JButton calculateButton = new JButton("Calculate BMI");
        calculateButton.setBackground(new Color(0, 153, 76)); // Green button
        calculateButton.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        add(calculateButton, gbc);

        // Reset Button
        JButton resetButton = new JButton("Reset");
        resetButton.setBackground(new Color(255, 102, 0)); // Orange button
        resetButton.setForeground(Color.WHITE);
        gbc.gridy = 4;
        add(resetButton, gbc);

        // Exit Button
        JButton exitButton = new JButton("Exit");
        exitButton.setBackground(Color.RED);
        exitButton.setForeground(Color.WHITE);
        gbc.gridy = 5;
        add(exitButton, gbc);

        // Result Label
        resultLabel = new JLabel("Your BMI: --", JLabel.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridy = 6;
        add(resultLabel, gbc);

        // Category Label
        categoryLabel = new JLabel("Category: --", JLabel.CENTER);
        categoryLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridy = 7;
        add(categoryLabel, gbc);

        // Button Actions
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateBMI();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                weightField.setText("");
                heightField.setText("");
                resultLabel.setText("Your BMI: --");
                categoryLabel.setText("Category: --");
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Close the application
            }
        });

        setVisible(true); // Show the window
    }

    private void calculateBMI() {
        try {
            // Get user input
            double weight = Double.parseDouble(weightField.getText());
            double heightCm = Double.parseDouble(heightField.getText());

            // Validate input
            if (weight <= 0 || heightCm <= 0) {
                resultLabel.setText("Enter valid positive numbers.");
                categoryLabel.setText("Category: --");
                return;
            }

            // Convert height from cm to meters
            double heightMeters = heightCm / 100;

            // Calculate BMI
            double bmi = weight / (heightMeters * heightMeters);
            String category;
            Color categoryColor;

            // Determine BMI category
            if (bmi < 18.5) {
                category = "Underweight";
                categoryColor = Color.BLUE;
            } else if (bmi < 24.9) {
                category = "Normal weight";
                categoryColor = Color.GREEN;
            } else if (bmi < 29.9) {
                category = "Overweight";
                categoryColor = Color.ORANGE;
            } else {
                category = "Obese";
                categoryColor = Color.RED;
            }

            // Display result
            resultLabel.setText(String.format("Your BMI: %.2f", bmi));
            categoryLabel.setText("Category: " + category);
            categoryLabel.setForeground(categoryColor);

        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input! Enter numbers.");
            categoryLabel.setText("Category: --");
        }
    }

    public static void main(String[] args) {
        new BMICalculatorGUI();
    }
}
