import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.awt.Font;
import java.io.InputStream;
import java.io.IOException;

public class Login extends JFrame {
    private JLabel l1, l2, l3;
    private JTextField tf1;
    private JPasswordField pf1;
    private JButton b1;

    Login() {
        setTitle("Guess the word");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);

        l1 = new JLabel("Welcome to word guessing game");
        l1.setBounds(80, 30, 350, 30);
        l1.setFont(new Font("Serif", Font.PLAIN, 25));

        l2 = new JLabel("Username  :");
        l2.setBounds(80, 120, 200, 25);
        l2.setFont(new Font("Serif", Font.PLAIN, 22));

        l3 = new JLabel("Password  :");
        l3.setBounds(80, 180, 200, 25);
        l3.setFont(new Font("Serif", Font.PLAIN, 22));

        tf1 = new JTextField();
        tf1.setBounds(220, 125, 150, 25);
        tf1.setFont(new Font("Serif", Font.PLAIN, 22));

        pf1 = new JPasswordField();
        pf1.setBounds(220, 185, 150, 25);
        pf1.setFont(new Font("Serif", Font.PLAIN, 22));

        b1 = new JButton("Login");
        b1.setBounds(140, 260, 200, 30);
        b1.setFont(new Font("Serif", Font.PLAIN, 22));
        b1.setFocusable(false);
        b1.addActionListener(e -> {
            String username = tf1.getText();
            char[] passwordChars = pf1.getPassword();
            String password = new String(passwordChars);
            matchPassword(username, password);
        });

        add(l1);
        add(l2);
        add(l3);
        add(tf1);
        add(pf1);
        add(b1);
        setVisible(true);
    }

    private void matchPassword(String username, String password) {
        Workbook wb;
        Sheet sheet;
        int usernameIndex = 0;
        int passwordIndex = 1;

        // Load the file from resources
        try (InputStream is = Login.class.getClassLoader().getResourceAsStream("login.xlsx")) {
            if (is == null) {
                JOptionPane.showMessageDialog(null, "login.xlsx not found in resources.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            wb = new XSSFWorkbook(is);
            sheet = wb.getSheetAt(0);

            boolean found = false;

            for (Row row : sheet) {
                Cell cellUsername = row.getCell(usernameIndex);
                Cell cellPassword = row.getCell(passwordIndex);

                if (cellUsername != null && cellPassword != null) {
                    String storedUsername = cellUsername.getStringCellValue();
                    String storedPassword = cellPassword.getStringCellValue();

                    if (username.equals("") || password.equals("")) {
                        JOptionPane.showMessageDialog(null, "Incorrect username or password.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (username.equals(storedUsername) && password.equals(storedPassword)) {
                        found = true;
                        JOptionPane.showMessageDialog(null, "Login successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        new WordGuessingGame();
                        break;
                    }
                }
            }
            if (!found) {
                JOptionPane.showMessageDialog(null, "Incorrect username or password.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
