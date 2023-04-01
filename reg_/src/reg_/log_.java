package reg_;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class log_ {

	private JFrame frame;
	private JTextField t1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					log_ window = new log_();
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
	public log_() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 605, 391);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Your Sno");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(59, 46, 99, 37);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lb1 = new JLabel("Name ");
		lb1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lb1.setBounds(112, 170, 237, 37);
		frame.getContentPane().add(lb1);
		
		JLabel lb2 = new JLabel("Marks ");
		lb2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lb2.setBounds(112, 218, 99, 25);
		frame.getContentPane().add(lb2);
		
		t1 = new JTextField();
		t1.setBounds(192, 50, 180, 37);
		frame.getContentPane().add(t1);
		t1.setColumns(10);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Sno=t1.getText();
				int sno=Integer.parseInt(Sno);
				try {
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/reg_","root","mrec");
					String q="select name,marks from Student where Sno=?";
					PreparedStatement ps=con.prepareStatement(q);
					ps.setInt(1, sno);
					ResultSet rs=ps.executeQuery();
					rs.next();
					lb1.setText("Name:"+rs.getString(1));
					lb2.setText("Marks:"+rs.getInt(2));
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnNewButton.setBounds(220, 112, 117, 23);
		frame.getContentPane().add(btnNewButton);
	}

}
