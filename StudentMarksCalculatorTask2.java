import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Interface defining student marks calculation behavior
interface StudentMarksCalculator {
    void inputMarks();
    void calculateTotalMarks();
    void calculateAveragePercentage();
    void calculateGrade();
    void displayResults();
}

// Implementation class
class StudentMarksGUI implements StudentMarksCalculator {
    private int numberOfSubjects;
    private int[] marks;
    private int totalMarks;
    private double averagePercentage;
    private String grade;
    private JFrame frame;
    private JTextArea resultArea;
    private JTextField subjectField;
    private JButton submitButton;

    public StudentMarksGUI() {
        frame = new JFrame("Student Marks Calculator");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panel with background color
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 165, 100)); // Light orange
        panel.setLayout(new FlowLayout());

        JLabel label = new JLabel("Enter the number of subjects:");
        label.setFont(new Font("Arial", Font.BOLD, 14));
        subjectField = new JTextField(10);
        submitButton = new JButton("Submit");
        submitButton.setBackground(new Color(255, 99, 71)); // Light red
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font("Arial", Font.BOLD, 12));

        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Arial", Font.PLAIN, 14));
        resultArea.setBackground(new Color(255, 228, 196)); // Light peach

        panel.add(label);
        panel.add(subjectField);
        panel.add(submitButton);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(new JScrollPane(resultArea), BorderLayout.CENTER);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputMarks();
            }
        });

        frame.setVisible(true);
    }

    @Override
    public void inputMarks() {
        try {
            numberOfSubjects = Integer.parseInt(subjectField.getText());
            marks = new int[numberOfSubjects];
            for (int i = 0; i < numberOfSubjects; i++) {
                String input = JOptionPane.showInputDialog("Enter marks obtained in subject " + (i + 1) + " (out of 100):");
                marks[i] = Integer.parseInt(input);
                if (marks[i] < 0 || marks[i] > 100) {
                    JOptionPane.showMessageDialog(null, "Invalid marks! Marks should be between 0 and 100.", "Error", JOptionPane.ERROR_MESSAGE);
                    i--; // Repeat the current iteration
                }
            }
            calculateTotalMarks();
            calculateAveragePercentage();
            calculateGrade();
            displayResults();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void calculateTotalMarks() {
        totalMarks = 0;
        for (int mark : marks) {
            totalMarks += mark;
        }
    }

    @Override
    public void calculateAveragePercentage() {
        averagePercentage = (double) totalMarks / numberOfSubjects;
    }

    @Override
    public void calculateGrade() {
        if (averagePercentage >= 90) {
            grade = "AA";
        } else if (averagePercentage >= 80) {
            grade = "AB";
        } else if (averagePercentage >= 70) {
            grade = "BB";
        } else if (averagePercentage >= 60) {
            grade = "BC";
        } else if (averagePercentage >= 50) {
            grade = "CC";
        } else {
            grade = "DD";
        }
    }

    @Override
    public void displayResults() {
        resultArea.setText("Results:\n");
        resultArea.append("Total Marks: " + totalMarks + "\n");
        resultArea.append("Average Percentage: " + String.format("%.2f", averagePercentage) + "%\n");
        resultArea.append("Grade: " + grade + "\n");
    }
}

// Main class to run the program
public class StudentMarksCalculatorTask2 {
    public static void main(String[] args) {
        new StudentMarksGUI();
    }
}