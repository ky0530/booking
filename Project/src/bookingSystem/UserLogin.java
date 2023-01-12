package bookingSystem;

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
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.SwingConstants;

public class UserLogin extends JFrame {
	/**
	 * Create the frame.
	 */
	private JTextField textField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UserLogin frame = new UserLogin();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

     
    public UserLogin() {
        //Create the frame.
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 100, 1014, 597);
        setTitle("Login");
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("UTHM EXAMINATION HALL BOOKING SYSTEM");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 35));
        lblNewLabel.setBounds(10, 10,980, 93);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        textField.setBounds(481, 200, 281, 68);
        contentPane.add(textField);
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        passwordField.setBounds(481, 325, 281, 68);
        contentPane.add(passwordField);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBackground(Color.BLACK);
        lblUsername.setForeground(Color.BLACK);
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblUsername.setBounds(216, 200, 193, 68);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setForeground(Color.BLACK);
        lblPassword.setBackground(Color.CYAN);
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblPassword.setBounds(216, 325, 193, 68);
        contentPane.add(lblPassword);
        
        JLabel lblNewLabel_1 = new JLabel("User Login");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setForeground(Color.BLACK);
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 35));
        lblNewLabel_1.setBounds(282, 100, 429, 76);
        contentPane.add(lblNewLabel_1);
        
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
        loginButton.setBounds(589, 446, 281, 73);
        contentPane.add(loginButton);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
            	//Login to the system and navigate the user home page
            	login();
            }
        });
        
        
        JButton signUpButton = new JButton("Click here to sign up");
        signUpButton.setBackground(UIManager.getColor("Button.background"));
        signUpButton.setForeground(Color.BLUE);
        signUpButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
        signUpButton.setBounds(138, 446, 281, 73);
        contentPane.add(signUpButton);
        signUpButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {  //Sign up button - navigate to user sign up page   		
                    UserSignUp el = new UserSignUp();
                    dispose();
                    el.setVisible(true); //display the user sign up page
        	}
        	});
        
       
       
    }
    
    public void login() { //login the system function
    	UserAcc account = new UserAcc();
        account.setName(textField.getText()); //read the user name that input by user
        account.setPassword(passwordField.getText());//read the password that input by user
        try {
        	//connect to the my sql server
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_demo", "root", "Yky010530");
            //this is used to retrieve the data in my sql database
            PreparedStatement st = (PreparedStatement) connection.prepareStatement("Select * from user where name=? and password=?");
            
            st.setString(1, account.getName());
            st.setString(2, account.getPassword());
  
            ResultSet rs = st.executeQuery(); //execute the statement
            if (rs.next()) { // it shifts the cursor to the next row of the result set from the database and returns true if there is any row, otherwise false
            	account.setId(rs.getInt("id")) ; //get id from database
                dispose(); //current frame (user login page) close
                UserHome ho = new UserHome(account.getId(), account.getName());//the user home page with the different user ID,different userID show different detail
                ho.setVisible(true); //display the user home page based on the different user ID
                JOptionPane.showMessageDialog(null, "You have successfully logged in");
            } else {
                JOptionPane.showMessageDialog(null, "Wrong Username & Password");
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
