package bookingSystem;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AdminLogin extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
    private JPasswordField passwordField;
    private JButton btnNewButton;
    private JPanel contentPane;
    private JButton btnNewButton_2;
    private JLabel lblUsername_1;
    private JPasswordField passwordField_1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AdminLogin frame = new AdminLogin();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public AdminLogin() {
    	setTitle("Admin Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 972, 746);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Admin Login");
        lblNewLabel.setBounds(353, 20, 273, 93);
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 46));
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(481, 170, 281, 68);
        textField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        contentPane.add(textField);
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(481, 286, 281, 68);
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        contentPane.add(passwordField);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(250, 166, 193, 52);
        lblUsername.setBackground(Color.BLACK);
        lblUsername.setForeground(Color.BLACK);
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 31));
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(250, 286, 193, 52);
        lblPassword.setForeground(Color.BLACK);
        lblPassword.setBackground(Color.CYAN);
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 31));
        contentPane.add(lblPassword);
        
        JButton btnNewButton_2 = new JButton("Click here to sign up");
        btnNewButton_2.setForeground(Color.BLUE);
        btnNewButton_2.setBounds(170, 552, 329, 73);
        btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnNewButton_2.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent e) 
        	{
                    AdminSignUp su = new AdminSignUp();
                    dispose();
                    su.setVisible(true);
            }
        }
        );
        
        btnNewButton = new JButton("Login");
        btnNewButton.setBounds(583, 552, 162, 73);
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnNewButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String userName = textField.getText();
                String password = passwordField.getText();
                try {
                    Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_demo",
                        "root", "Yky010530");

                    PreparedStatement st = (PreparedStatement) connection
                        .prepareStatement("Select name, password from admin where name=? and password=?");

                    st.setString(1, userName);
                    st.setString(2, password);
                    ResultSet rs = st.executeQuery();
                    if (rs.next()) {
                    	setVisible(false);
                        AdminHome home = new AdminHome();
                        home.setTitle("Welcome");
                        home.setVisible(true);
                        JOptionPane.showMessageDialog(btnNewButton, "You have successfully logged in");
                    } else {
                        JOptionPane.showMessageDialog(btnNewButton, "Wrong Username & Password");
                    }
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        });

        contentPane.add(btnNewButton);
        contentPane.add(btnNewButton_2);
        
        lblUsername_1 = new JLabel("Security Code");
        lblUsername_1.setBounds(250, 396, 193, 52);
        lblUsername_1.setForeground(Color.BLACK);
        lblUsername_1.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblUsername_1.setBackground(Color.BLACK);
        contentPane.add(lblUsername_1);
        
        passwordField_1 = new JPasswordField();
        passwordField_1.setBounds(481, 388, 281, 68);
        passwordField_1.setFont(new Font("Tahoma", Font.PLAIN, 32));
        contentPane.add(passwordField_1);
        

        
    }
}