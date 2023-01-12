package bookingSystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class UserType extends JFrame {
	private static final long serialVersionUID = 1L;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserType window = new UserType();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserType() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBackground(Color.CYAN);
		frame.setResizable(false);
		frame.setTitle("UserType");
		frame.setBounds(450, 190, 715, 456);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Who are you?");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(280, 94, 123, 60);
		frame.getContentPane().add(lblNewLabel);
		
		JButton adminButton = new JButton("Admin");
		adminButton.setBackground(UIManager.getColor("inactiveCaptionBorder"));
		adminButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		adminButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminLogin ad = new AdminLogin();
                frame.dispose();
                ad.setVisible(true);
			}
		});
		adminButton.setBounds(280, 177, 123, 60);
		frame.getContentPane().add(adminButton);
		
		JButton uuserButton = new JButton("User");
		uuserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				UserLogin el = new UserLogin();
                frame.dispose();
                el.setVisible(true);
			}
		});
		uuserButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		uuserButton.setBounds(280, 280, 123, 60);
		frame.getContentPane().add(uuserButton);
		
		JLabel lblUthmE = new JLabel("UTHM EXAMINATION HALL BOOKING SYSTEM");
		lblUthmE.setHorizontalAlignment(SwingConstants.CENTER);
		lblUthmE.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUthmE.setBounds(135, 24, 456, 60);
		frame.getContentPane().add(lblUthmE);
	}

}
