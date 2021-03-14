package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import dao.CafeDAO;
import dao.PessoaDAO;
import model.CafeModel;
import model.PessoaModel;

public class Cafe {

	private JFrame frame;
	private JTextField textPesquisa;
	private JTable table;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JTextField textNome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cafe window = new Cafe();
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
	public Cafe() {
		initialize();
	}
	
	public void readTtable(){
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		table.setRowSorter(new TableRowSorter(modelo));
		
		CafeDAO cafeDAO = new CafeDAO();
		
		for(CafeModel c: cafe.read()) {
			modelo.addRow(new Object[] {
				
				p.getId(),	
				p.getNome()
			});
		}
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 564, 496);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Home");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBackground(new Color(153, 204, 204));
		btnNewButton.setBounds(10, 11, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		textPesquisa = new JTextField();
		textPesquisa.setBounds(371, 12, 142, 20);
		frame.getContentPane().add(textPesquisa);
		textPesquisa.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 132, 528, 266);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Sala 1", "Sala 2"
			}
		));
		scrollPane.setViewportView(table);
		
		btnNewButton_1 = new JButton("Adcionar");
		btnNewButton_1.setBackground(new Color(204, 255, 153));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CafeModel cafeModel = new CafeModel();
				CafeDAO cafeDao = new CafeDAO();
				
				if(textNome.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "É preciso preencher o campo nome!");
				}else {
					cafeModel.setNome(textNome.getText());
					
					cafeDao.create(cafeModel);
					
					((DefaultTableModel) table.getModel()).setRowCount(0);
					readTtable();
				}
			}
			
		});
		btnNewButton_1.setBounds(20, 409, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("Deletar");
		btnNewButton_2.setBackground(new Color(255, 153, 153));
		btnNewButton_2.setBounds(132, 409, 89, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		textNome = new JTextField();
		textNome.setBounds(23, 60, 198, 20);
		frame.getContentPane().add(textNome);
		textNome.setColumns(10);
		
	}
}
