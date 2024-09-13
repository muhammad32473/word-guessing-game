import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class WordGuessingGame implements ActionListener {
    Random rand = new Random();

    static String word;
    static StringBuilder hint;
    int tries = 10;

    // Path to the Excel file in the classpath
    String path = "/demo.xlsx";

    InputStream fis = getClass().getResourceAsStream(path);
    XSSFWorkbook wb = new XSSFWorkbook(fis);
    XSSFSheet sheet = wb.getSheetAt(0);
    int randomNum1 = rand.nextInt(sheet.getLastRowNum() + 1);
    // words
    XSSFRow row1 = sheet.getRow(randomNum1);
    XSSFCell cell1 = row1.getCell(0);
    // Hints
    XSSFRow row2 = sheet.getRow(randomNum1);
    XSSFCell cell2 = row2.getCell(1);

    JFrame f = new JFrame();
    JLabel l1, l2, l3;
    JTextField guess = new JTextField();
    JButton button1;
    JButton button2;

    WordGuessingGame() throws IOException {

        // int randomNum = rand.nextInt(words.size());

        word = cell1.toString();
        hint = new StringBuilder(cell2.toString());

        l1 = new JLabel("Enter a letter or a full word");
        l1.setBounds(110, 30, 200, 100);
        l1.setForeground(Color.green);
        l2 = new JLabel("Current word:\n " + getHint());
        l2.setBounds(110, 50, 200, 100);
        l2.setForeground(Color.green);
        l3 = new JLabel("Attempts left: " + tries);
        l3.setBounds(140, 10, 200, 100);
        l3.setForeground(Color.green);

        guess.setHorizontalAlignment(JTextField.CENTER);
        guess.setBounds(160, 120, 50, 20);
        guess.requestFocusInWindow();
        guess.setForeground(Color.green);
        guess.setBackground(Color.black);
        guess.setBorder(new LineBorder(Color.green));

        button1 = new JButton("Guess");
        button1.setFocusable(false);
        button1.setBackground(Color.BLACK);
        button1.setForeground(Color.WHITE);
        button1.setBounds(130, 150, 110, 15);
        button1.addActionListener(this);
        button1.setForeground(Color.green);
        button1.setBorder(new LineBorder(Color.green));

        button2 = new JButton("Skip");
        button2.setFocusable(false);
        button2.setBackground(Color.BLACK);
        button2.setForeground(Color.WHITE);
        button2.setBounds(130, 180, 110, 15);
        button2.addActionListener(e -> restartGame());
        button2.setForeground(Color.green);
        button2.setBorder(new LineBorder(Color.green));

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(400, 400);
        f.setLayout(null);
        f.setVisible(true);
        f.setTitle("Word Guess");
        f.add(l1);
        f.add(l2);
        f.add(l3);
        f.add(guess);
        f.add(button1);
        f.add(button2);
        f.getContentPane().setBackground(Color.black);
        f.setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            String strGuess = guess.getText().toUpperCase();
            if (strGuess.length() == 1 && word.contains(strGuess)) {
                updateHint(strGuess);
            } else if (strGuess.equals(word)) {
                JOptionPane.showMessageDialog(f, "Good guess", "Guess Result", JOptionPane.INFORMATION_MESSAGE);
                restartGame();
            } else {
                JOptionPane.showMessageDialog(f, "Incorrect guess", "Guess Result", JOptionPane.ERROR_MESSAGE);
                tries--;
            }
            if (tries == 0) {
                JOptionPane.showMessageDialog(f, "Game Over", "Guess Result", JOptionPane.ERROR_MESSAGE);
                f.dispose();
            }

            l2.setText("Current word: " + getHint());
            l3.setText("Attempts left: " + tries);
            guess.setText("");
        }
    }

    public void restartGame() {
        tries = 10;
        int randomNum2 = rand.nextInt(sheet.getLastRowNum() + 1);

        XSSFRow row2 = sheet.getRow(randomNum2);
        XSSFCell cell1 = row2.getCell(0);
        XSSFCell cell2 = row2.getCell(1);

        word = cell1.toString();
        hint = new StringBuilder(cell2.toString());

        l2.setText("Current word: " + getHint());
        guess.setText("");
    }

    public static String getHint() {
        return hint.toString();
    }

    public void updateHint(String guess) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess.charAt(0)) {
                hint.setCharAt(i, guess.charAt(0));
                if (hint.toString().equals(word)) {
                    l2.setText("Current word: " + getHint());
                    JOptionPane.showMessageDialog(null, "Good guess", "Guess Result", JOptionPane.INFORMATION_MESSAGE);
                    restartGame();
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            new WordGuessingGame();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
