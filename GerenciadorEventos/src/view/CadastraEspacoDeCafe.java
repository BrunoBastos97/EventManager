package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.Window.Type;

public class CadastraEspacoDeCafe {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastraEspacoDeCafe window = new CadastraEspacoDeCafe();
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
	public CadastraEspacoDeCafe() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setType(Type.UTILITY);
		frame.setBounds(100, 100, 914, 477);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(29, 11, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(29, 80, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(129, 77, 86, 20);
		frame.getContentPane().add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(519, 77, 86, 20);
		frame.getContentPane().add(textField_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(435, 80, 46, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(467, 15, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(577, 12, 188, 22);
		frame.getContentPane().add(textField_2);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(778, 11, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_5 = new JButton("New button");
		btnNewButton_5.setBounds(778, 76, 89, 23);
		frame.getContentPane().add(btnNewButton_5);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 135, 838, 215);
		frame.getContentPane().add(scrollPane);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setBounds(778, 386, 89, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_4 = new JButton("New button");
		btnNewButton_4.setBounds(384, 386, 89, 23);
		frame.getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton_3 = new JButton("New button");
		btnNewButton_3.setBounds(29, 386, 89, 23);
		frame.getContentPane().add(btnNewButton_3);
	}

}
