package bookingSystem;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminSignUp extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
    private JPasswordField passwordField;
    private JButton btnNewButton;
    private JLabel label;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminSignUp frame = new AdminSignUp();
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
	public AdminSignUp() {
		setTitle("Admin Sign Up");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 190, 972, 632);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 JLabel lblNewLabel = new JLabel("Admin Sign Up");
	        lblNewLabel.setForeground(Color.BLACK);
	        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 41));
	        lblNewLabel.setBounds(332, 10,259, 93);
	        contentPane.add(lblNewLabel);
	        
	        textField = new JTextField();
	        textField.setFont(new Font("Tahoma", Font.PLAIN, 32));
	        textField.setBounds(481, 170, 281, 68);
	        contentPane.add(textField);
	        textField.setColumns(10);

	        passwordField = new JPasswordField();
	        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
	        passwordField.setBounds(481, 286, 281, 68);
	        contentPane.add(passwordField);

	        JLabel lblUsername = new JLabel("Username");
	        lblUsername.setBackground(Color.BLACK);
	        lblUsername.setForeground(Color.BLACK);
	        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 31));
	        lblUsername.setBounds(250, 166, 193, 52);
	        contentPane.add(lblUsername);

	        JLabel lblPassword = new JLabel("Password");
	        lblPassword.setForeground(Color.BLACK);
	        lblPassword.setBackground(Color.CYAN);
	        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 31));
	        lblPassword.setBounds(250, 286, 193, 52);
	        contentPane.add(lblPassword);
	
	
			 JButton btnNewButton_2 = new JButton("Click here to sign up");
		     btnNewButton_2.setForeground(Color.BLACK);
		     btnNewButton_2.setBounds(332, 452, 329, 73);
		     btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 26));
		     btnNewButton_2.addActionListener(new ActionListener() {
		     public void actionPerformed(ActionEvent e) 
		     {
	     		AdminAcc admin = new AdminAcc();
	     		admin.setName(textField.getText());
	     		admin.setPassword(passwordField.getText());

	             try {
	             	System.out.println("Sign up name " + admin.getName());
	                 System.out.println("Sign up password successfully");
	             	
	                 Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_demo",
	                     "root", "Yky010530");
	                
	                 PreparedStatement st = (PreparedStatement) con.prepareStatement("insert into admin (name, password) values (?,?)");
	
	                 st.setString(1, admin.getName());
	                 st.setString(2, admin.getPassword());
	                 st.execute();
	                 JOptionPane.showMessageDialog(btnNewButton, "Sign up successfully");    	
	                 AdminLogin el = new AdminLogin();
	                 dispose();
	                 el.setVisible(true); 
	             	} catch (SQLException sqlException) {
	             			sqlException.printStackTrace();
	             	}
     	}
     	});
     contentPane.add(btnNewButton_2);
}
}