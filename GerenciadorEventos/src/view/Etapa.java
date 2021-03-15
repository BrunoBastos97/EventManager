package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import dao.EtapaDAO;
import dao.PessoaDAO;
import model.EtapaModel;
import model.PessoaModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Color;

public class Etapa {

	private JFrame frameEtapa;
	private JTable tableEtapa;
	private JTextField textNome;
	private JTextField textSobreNome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Etapa window = new Etapa();
					window.frameEtapa.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Etapa() {
		initialize();
		readTable();
	}
	
	public void readTable() {
		DefaultTableModel modelo = (DefaultTableModel) tableEtapa.getModel();
		tableEtapa.setRowSorter(new TableRowSorter(modelo));
		
		EtapaDAO etapa = new EtapaDAO();
		
		for(EtapaModel e: etapa.read()) {
			modelo.addRow(new Object[] {
				e.getNome(),
				e.getSobreNome(),
				e.getEvento(),
				e.getSalaDeEvento(),
				e.getEspacoDeCafe()
			});
		}
	}
	
	public void readTablePesquisa(EtapaModel pesquisa){
		DefaultTableModel modelo = (DefaultTableModel) tableEtapa.getModel();
		tableEtapa.setRowSorter(new TableRowSorter(modelo));
		
		EtapaDAO etapa = new EtapaDAO();
		
		for(EtapaModel e: etapa.readPesquisa(pesquisa)) {
			modelo.addRow(new Object[] {
					e.getNome(),
					e.getSobreNome(),
					e.getEvento(),
					e.getSalaDeEvento(),
					e.getEspacoDeCafe()
			});
		}
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameEtapa = new JFrame();
		frameEtapa.setBounds(100, 100, 1013, 543);
		frameEtapa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameEtapa.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 226, 912, 205);
		frameEtapa.getContentPane().add(scrollPane);
		
		tableEtapa = new JTable();
		tableEtapa.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome", "Sobre Nome", "Evento", "Sala de Eventos", "Espa\u00E7o de Caf\u00E9"
			}
		));
		scrollPane.setViewportView(tableEtapa);
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameEtapa.setVisible(false);
			}
		});
		btnHome.setBounds(45, 11, 89, 23);
		frameEtapa.getContentPane().add(btnHome);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 255, 255));
		panel.setBounds(45, 45, 912, 142);
		frameEtapa.getContentPane().add(panel);
		panel.setLayout(null);
		
		textNome = new JTextField();
		textNome.setBounds(120, 83, 170, 20);
		panel.add(textNome);
		textNome.setColumns(10);
		
		textSobreNome = new JTextField();
		textSobreNome.setBounds(442, 83, 170, 20);
		panel.add(textSobreNome);
		textSobreNome.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EtapaModel etapaModel = new EtapaModel();
				
				etapaModel.setNome(textNome.getText());
				etapaModel.setSobreNome(textSobreNome.getText());
				
				((DefaultTableModel) tableEtapa.getModel()).setRowCount(0);
				readTablePesquisa(etapaModel);
			}
		});
		btnPesquisar.setBounds(803, 68, 99, 51);
		panel.add(btnPesquisar);
		
		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setBounds(10, 86, 46, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Sobre Nome");
		lblNewLabel_1_1.setBounds(333, 86, 99, 14);
		panel.add(lblNewLabel_1_1);
		
		JButton btnLimpa = new JButton("Limpa");
		btnLimpa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textNome.setText("");
				textSobreNome.setText("");
			}
		});
		btnLimpa.setBackground(new Color(255, 153, 153));
		btnLimpa.setBounds(803, 11, 99, 23);
		panel.add(btnLimpa);
		
		JLabel lblNewLabel = new JLabel("Pesquisar");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewLabel.setBounds(375, 11, 118, 32);
		panel.add(lblNewLabel);
	}
}
