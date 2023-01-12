package bookingSystem;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;

public class UserSignUp extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
    private JPasswordField passwordField;
    private JButton btnNewButton;
    private JLabel label;
    private JPasswordField confirmPasswordField;

	
	//Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserSignUp frame = new UserSignUp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
public UserSignUp() {
			//Create the frame of user sign up page 
			setResizable(false);
			setTitle("User Sign Up");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(300, 100, 1014, 597);
			contentPane = new JPanel();
			contentPane.setBackground(Color.WHITE);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("User Sign Up");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	        lblNewLabel.setForeground(Color.BLACK);
	        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 41));
	        lblNewLabel.setBounds(10, 10,980, 93);
	        contentPane.add(lblNewLabel);
	        
	        textField = new JTextField();
	        textField.setFont(new Font("Tahoma", Font.PLAIN, 32));
	        textField.setBounds(507, 150, 461, 68);
	        contentPane.add(textField);
	        textField.setColumns(10);

	        passwordField = new JPasswordField();
	        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
	        passwordField.setBounds(507, 250, 461, 68);
	        contentPane.add(passwordField);

	        JLabel lblUsername = new JLabel("Username");
	        lblUsername.setBackground(Color.BLACK);
	        lblUsername.setForeground(Color.BLACK);
	        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 31));
	        lblUsername.setBounds(124, 155, 256, 58);
	        contentPane.add(lblUsername);

	        JLabel lblPassword = new JLabel("Password");
	        lblPassword.setForeground(Color.BLACK);
	        lblPassword.setBackground(Color.CYAN);
	        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 31));
	        lblPassword.setBounds(124, 258, 193, 52);
	        contentPane.add(lblPassword);
	
	        JLabel lblConfirm = new JLabel("Confirm Password");
		    lblConfirm.setForeground(Color.BLACK);
		    lblConfirm.setFont(new Font("Tahoma", Font.PLAIN, 31));
		    lblConfirm.setBackground(Color.BLACK);
		    lblConfirm.setBounds(124, 350, 269, 52);
		    contentPane.add(lblConfirm);
		     
		    confirmPasswordField = new JPasswordField();
		    confirmPasswordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
		    confirmPasswordField.setBounds(507, 350, 461, 68);
		    contentPane.add(confirmPasswordField);
	        
		    //Sign Up button - click to call out sign up method
			JButton signUpButton = new JButton("Click here to sign up");
		    signUpButton.setForeground(Color.BLACK);
		    signUpButton.setBounds(613, 457, 329, 73);
		    signUpButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
		    contentPane.add(signUpButton);
		    signUpButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) 
		    {
		    	signUp(); //call out sign up method
		    }	 
            });
	     //This button is used to navigate to the user Login page
	     JButton backToLogInButton = new JButton("Back to log in");
	     backToLogInButton.setForeground(Color.BLACK);
	     backToLogInButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
	     backToLogInButton.setBounds(105, 457, 329, 73);
	     contentPane.add(backToLogInButton);
	     backToLogInButton.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent e) {
	     		UserLogin el = new UserLogin();
	            dispose(); //the current page (User Sign up page) close
	            el.setVisible(true);//display user login page
	     	}
	     });	     
		}
	
public void signUp() {
	 //Sign up a new user account into mysql database by read the input of the user
   	 UserAcc userAccount = new UserAcc();
   	 userAccount.setName(textField.getText()); //read input of the user name
   	 userAccount.setPassword(passwordField.getText()); // read the input of the password
   	 String confirmPassword = confirmPasswordField.getText(); // read the input of confirm password
   	 if(userAccount.getName().length()>=6 && userAccount.getPassword().length() >= 6)  //check the length of the user name and password not less than 6
   	 {
	    	 if(userAccount.getPassword().equals(confirmPassword))//check the both password same or not, if same ,an account sign up successfully
	    	 {
	             try 
	             {	 //connect to the mysql database                 	
		             Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_demo", "root", "Yky010530");
		             
		             //the statement will be carried out in the mysql application
		             PreparedStatement st = (PreparedStatement) con.prepareStatement("insert into user (name, password) values (?,?)");
		             st.setString(1, userAccount.getName());
		             st.setString(2, userAccount.getPassword());
		             st.execute(); //execute the statement
		             JOptionPane.showMessageDialog(null, "Sign up successfully"); //pop up a window to notify the user the account is sign up successfully 
		             UserLogin el = new UserLogin(); 
		             dispose(); //the current page (User Sign up page) close
		             el.setVisible(true); //display user login page 
	                    
	             } catch (SQLException sqlException) //prints the stack trace of the throwable object to the standard error stream
	             	{
	            	 sqlException.printStackTrace();
	            	 }
	    	 }
	    	 else
	    	 {	//If the password and confirm password are not same, a pop up window will be displayed
	    		 JOptionPane.showMessageDialog(null, "Both password do not match! Please re-enter the password");
	    		 confirmPasswordField.setText(""); //clear the confirm password text field
	    	 }
   	}
   	 else //if the user name or password to short , a pop up window will be displayed to notify the user
   	 {
   		 JOptionPane.showMessageDialog(null, "Username or password too short! These should be at least 5 characters");
   	 }
   
	}
}
