package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import dao.PessoaDAO;
import dao.SalasDAO;
import model.PessoaModel;
import model.SalasModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SalaDeEventos {

	private JFrame frame;
	private JTextField pesquisa;
	private JTextField nomeDaSala;
	private JTextField lotacao;		
	private JTable tableSala;
	private int id;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalaDeEventos window = new SalaDeEventos();
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
	public SalaDeEventos() {
		initialize();
		readTtable();
	}
	
	public void readTtable(){
		DefaultTableModel modelo = (DefaultTableModel) tableSala.getModel();
		tableSala.setRowSorter(new TableRowSorter(modelo));
		
		SalasDAO sala = new SalasDAO();
		
		for(SalasModel s: sala.read()) {
			modelo.addRow(new Object[] {
				
				s.getId(),	
				s.getNomeSala(),
				s.getLotacao()
			});
		}
			
	}
	
	public void readTtableForNome(SalasModel nome){
		DefaultTableModel modelo = (DefaultTableModel) tableSala.getModel();
		tableSala.setRowSorter(new TableRowSorter(modelo));
		
		SalasDAO sala = new SalasDAO();
		
		for(SalasModel s: sala.readForNome(nome)) {
			modelo.addRow(new Object[] {
				
				s.getId(),		
				s.getNomeSala(),
				s.getLotacao()
			});
		}
		
	}
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 862, 510);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnhome = new JButton("Home");
		btnhome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		btnhome.setBounds(47, 7, 89, 23);
		frame.getContentPane().add(btnhome);
		
		JButton btnlimpar = new JButton("Limpar");
		btnlimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nomeDaSala.setText("");
				lotacao.setText("");
			}
		});
		btnlimpar.setBackground(new Color(204, 255, 255));
		btnlimpar.setBounds(720, 91, 89, 23);
		frame.getContentPane().add(btnlimpar);
		
		pesquisa = new JTextField();
		pesquisa.setBounds(537, 8, 162, 20);
		frame.getContentPane().add(pesquisa);
		pesquisa.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Pesquisa");
		lblNewLabel.setBounds(437, 11, 60, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnpesquisa = new JButton("Pesquisar");
		btnpesquisa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SalasModel salaModel = new SalasModel();
				SalasDAO salaDao = new SalasDAO();
				
				salaModel.setNomeSala(pesquisa.getText());
				
				((DefaultTableModel) tableSala.getModel()).setRowCount(0);
				readTtableForNome(salaModel);
			}
		});
		btnpesquisa.setBounds(720, 7, 94, 23);
		frame.getContentPane().add(btnpesquisa);
		
		JLabel lblNewLabel_1 = new JLabel("Nome da sala");
		lblNewLabel_1.setBounds(10, 95, 95, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Lota\u00E7\u00E3o");
		lblNewLabel_2.setBounds(10, 131, 46, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		nomeDaSala = new JTextField();
		nomeDaSala.setBounds(107, 92, 128, 20);
		frame.getContentPane().add(nomeDaSala);
		nomeDaSala.setColumns(10);
		
		lotacao = new JTextField();
		lotacao.setBounds(107, 128, 46, 20);
		frame.getContentPane().add(lotacao);
		lotacao.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 190, 762, 158);
		frame.getContentPane().add(scrollPane);
		
		tableSala = new JTable();
		tableSala.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				id = Integer.valueOf(tableSala.getModel().getValueAt(tableSala.getSelectedRow(), 0).toString());
				nomeDaSala.setText(tableSala.getModel().getValueAt(tableSala.getSelectedRow(), 1).toString());
				lotacao.setText(tableSala.getModel().getValueAt(tableSala.getSelectedRow(), 2).toString());
			}
		});
		tableSala.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nome da Sala", "Lota\u00E7\u00E3o"
			}
		));
		scrollPane.setViewportView(tableSala);
		
		JButton btnedit = new JButton("Editar");
		btnedit.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			SalasModel salaModel = new SalasModel();
			SalasDAO salaDao = new SalasDAO();
			
			salaModel.setId(id);
			salaModel.setNomeSala(nomeDaSala.getText());
			salaModel.setLotacao(Integer.parseInt(lotacao.getText()));
			
									
			salaDao.update(salaModel);
					
			((DefaultTableModel) tableSala.getModel()).setRowCount(0);
			readTtable();
		}
		});
		btnedit.setBackground(new Color(255, 255, 153));
		btnedit.setBounds(47, 395, 89, 23);
		frame.getContentPane().add(btnedit);
		
		JButton btnadicionar = new JButton("Adicionar");
		btnadicionar.setBackground(new Color(153, 255, 153));
		btnadicionar.setBounds(332, 383, 178, 46);
		btnadicionar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			SalasModel salaModel = new SalasModel();
			SalasDAO salaDao = new SalasDAO();
			
			salaModel.setId(id);
			salaModel.setNomeSala(nomeDaSala.getText());
			salaModel.setLotacao(Integer.parseInt(lotacao.getText()));
			
			salaDao.create(salaModel);
			
			((DefaultTableModel) tableSala.getModel()).setRowCount(0);
			readTtable();
		}
		});
		frame.getContentPane().add(btnadicionar);
		
			 
		JButton btndelete = new JButton("Deletar");
		btndelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SalasModel salaModel = new SalasModel();
				SalasDAO salaDao = new SalasDAO();
				
				salaModel.setId(id);
				salaModel.setNomeSala(nomeDaSala.getText());
				salaModel.setLotacao(Integer.parseInt(lotacao.getText()));
								
				salaDao.delete(salaModel);
				
				((DefaultTableModel) tableSala.getModel()).setRowCount(0);
				readTtable();
			}
		});
		btndelete.setBackground(new Color(255, 153, 153));
		btndelete.setBounds(720, 395, 89, 23);
		frame.getContentPane().add(btndelete);
	}
}
