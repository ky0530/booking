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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class UserChangePassword extends JFrame {
	private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JLabel lblEnterNewPassword;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    
    
     
    public UserChangePassword(int userID) {
         //Create the frame.
    	setBounds(300, 200, 1024, 234);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 34));
        textField.setBounds(373, 35, 609, 67);
        contentPane.add(textField);
        textField.setColumns(10);      

        lblEnterNewPassword = new JLabel("Enter New Password :");
        lblEnterNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblEnterNewPassword.setBounds(45, 37, 326, 67);
        contentPane.add(lblEnterNewPassword);
        
        JButton btnSearch = new JButton("Enter"); 
        btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 29));
        btnSearch.setBackground(new Color(240, 240, 240));
        btnSearch.setBounds(438, 127, 170, 59);
        contentPane.add(btnSearch);
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { // user can change password by update the password to the database
                String pstr = textField.getText(); //read the new password form the user
                if(pstr.length()>=6)  //check the length of new password 
                {	//if more than 6, update password
	                try {
	                	//connect to the my sql server
	                    Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_demo","root", "Yky010530");
	                    //statement that will be executed to update the password in the database
	                    PreparedStatement st = (PreparedStatement) con.prepareStatement("Update user set password=? where id=?");	
	                    st.setString(1, pstr); //password
	                    st.setInt(2, userID);//userid - need to search the related password
	                    st.executeUpdate(); //execute the update of the  data
	                    JOptionPane.showMessageDialog(btnSearch, "Password has been successfully changed");
	                    dispose();
	
	                } catch (SQLException sqlException) {
	                    sqlException.printStackTrace();
	                }
	
	            }
                else
                { //if password length less than 6 ,cannot update password
                	JOptionPane.showMessageDialog(btnSearch, "New password too short! Please make sure the password at least 6 character");
                	textField.setText("");
                }
            }
        });
     
    }
}
