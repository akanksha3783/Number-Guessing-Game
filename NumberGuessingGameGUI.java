import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class NumberGuessingGameGUI extends JFrame implements ActionListener {

    private int numberToGuess;
    private int attempts;
    private final int maxAttempts = 7;

    private JTextField inputField;
    private JButton guessButton, resetButton;
    private JLabel messageLabel, attemptsLabel, titleLabel;

    public NumberGuessingGameGUI() {
        setTitle("🎮 Number Guessing Game");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // center screen

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 10, 10));
        panel.setBackground(new Color(30, 30, 60));

        titleLabel = new JLabel("Guess The Number", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);

        messageLabel = new JLabel("Enter number between 1 to 100", JLabel.CENTER);
        messageLabel.setForeground(Color.LIGHT_GRAY);

        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.BOLD, 16));

        guessButton = new JButton("Guess");
        resetButton = new JButton("Reset");

        guessButton.setBackground(new Color(0, 153, 76));
        guessButton.setForeground(Color.WHITE);

        resetButton.setBackground(new Color(204, 0, 0));
        resetButton.setForeground(Color.WHITE);

        attemptsLabel = new JLabel("Attempts left: 10", JLabel.CENTER);
        attemptsLabel.setForeground(Color.YELLOW);

        panel.add(titleLabel);
        panel.add(messageLabel);
        panel.add(inputField);
        panel.add(guessButton);
        panel.add(resetButton);
        panel.add(attemptsLabel);

        add(panel);

        Random rand = new Random();
        numberToGuess = rand.nextInt(100) + 1;
        attempts = 0;

        guessButton.addActionListener(this);
        resetButton.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == guessButton) {
            try {
                int guess = Integer.parseInt(inputField.getText());
                attempts++;

                if (guess == numberToGuess) {
                    messageLabel.setText("🎉 Correct! You Win!");
                    guessButton.setEnabled(false);
                } else if (attempts >= maxAttempts) {
                    messageLabel.setText("😢 Game Over! Number was: " + numberToGuess);
                    guessButton.setEnabled(false);
                } else if (guess < numberToGuess) {
                    messageLabel.setText("Too Low!");
                } else {
                    messageLabel.setText("Too High!");
                }

                attemptsLabel.setText("Attempts left: " + (maxAttempts - attempts));
                inputField.setText("");

            } catch (Exception ex) {
                messageLabel.setText("⚠️ Enter valid number!");
            }
        }

        if (e.getSource() == resetButton) {
            numberToGuess = new Random().nextInt(100) + 1;
            attempts = 0;
            messageLabel.setText("Game Reset! Try again");
            attemptsLabel.setText("Attempts left: 10");
            guessButton.setEnabled(true);
            inputField.setText("");
        }
    }

    public static void main(String[] args) {
        new NumberGuessingGameGUI();
    }
}