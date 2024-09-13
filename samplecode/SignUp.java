import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.awt.Font;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SignUp extends JFrame
{
    private JLabel l1,l2,l3 ;

    private JButton b1,b2 ;

    private JTextField tf1 ;
    private JPasswordField pf1 ;

    private static JFrame f ;
    public SignUp()
    {
        setTitle("Guess the word");
        setSize(500,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        l1 = new JLabel("Welcome to word guessing game") ;
        l1.setBounds(80,30,350,30);
        l1.setFont(new Font("Serif", Font.PLAIN, 25)) ;

        l2 = new JLabel("Username  :") ;
        l2.setBounds(80,120,200,25);
        l2.setFont(new Font("Serif", Font.PLAIN, 22)) ;

        l3 = new JLabel("Password  :") ;
        l3.setBounds(80,180,200,25);
        l3.setFont(new Font("Serif", Font.PLAIN, 22)) ;

        tf1 = new JTextField() ;
        tf1.setBounds(220,125,100,25) ;
        tf1.setFont(new Font("Serif", Font.PLAIN, 22)) ;

        pf1 = new JPasswordField() ;
        pf1.setBounds(220,185,100,25) ;
        pf1.setFont(new Font("Serif", Font.PLAIN, 22)) ;

        b1 = new JButton("Sign Up") ;
        b1.setBounds(140,260,200,30) ;
        b1.setFont(new Font("Serif", Font.PLAIN, 22)) ;
        b1.setFocusable(false) ;
        b1.addActionListener(e ->
                {



                String data1 = tf1.getText() ;
                String data2 = new String(pf1.getPassword()) ;
                if(data1.equals("") || data2.equals(""))
                {
                    JOptionPane.showMessageDialog(this, "Please enter username and password", "Error", JOptionPane.ERROR_MESSAGE) ;
                }
                else
                {
                    saveToExcel(data1, data2);
                    tf1.setText("");
                    pf1.setText("");
                    //this.dispose() ;
                }

                }
                            ) ;


        add(l1) ;
        add(l2) ;
        add(l3) ;
        add(tf1) ;
        add(pf1) ;
        add(b1) ;

        setVisible(true);
    }

    private static void saveToExcel(String data1, String data2)
    {
        Workbook wb ;
        Sheet sheet ;
        int rowCount ;

        try(FileInputStream fis = new FileInputStream("C:\\Users\\user\\Desktop\\login.xlsx"))
        {
            wb = new XSSFWorkbook(fis) ;
            sheet = wb.getSheetAt(0) ;
            rowCount = sheet.getLastRowNum() ;
        }
        catch(IOException e)
        {
            wb = new XSSFWorkbook();
            sheet = wb.createSheet("Data") ;
            rowCount = -1;
        }

        boolean exists = false ;
        for(Row row : sheet)
        {
            Cell cell = row.getCell(0) ;
            if(cell != null && cell.getCellType() == CellType.STRING)
            {
                String cellValue = cell.getStringCellValue() ;
                if(data1.equals(cellValue))
                {
                    exists = true ;
                    break ;
                }
            }
        }
        if(exists)
        {
            JOptionPane.showMessageDialog(null, "Username already exists.", "Error", JOptionPane.ERROR_MESSAGE) ;
        }
        else
        {

            Row row = sheet.createRow(rowCount + 1);
            Cell cell1 = row.createCell(0);
            cell1.setCellValue(data1);
            Cell cell2 = row.createCell(1);
            cell2.setCellValue(data2);

            try (FileOutputStream fos = new FileOutputStream("C:\\Users\\user\\Desktop\\login.xlsx"))
            {
                wb.write(fos);
            } catch (IOException e) {
                e.printStackTrace();
            } finally
            {
                try
                {
                    wb.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main (String [] args)
    {
        new SignUp() ;
    }
}
