package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import dao.CafeDAO;
import dao.PessoaDAO;
import model.CafeModel;
import model.PessoaModel;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CadastroCafe {

	private JFrame frame;
	private JTextField textPesquisa;
	private JTable tableCafe;
	private JTextField textLotacao;
	private JTextField textNome;
	
	int id = 0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroCafe window = new CadastroCafe();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void readTtableFroNome(CafeModel nome){
		DefaultTableModel modelo = (DefaultTableModel) tableCafe.getModel();
		tableCafe.setRowSorter(new TableRowSorter(modelo));
		
		CafeDAO cafeDao = new CafeDAO();
		
		for(CafeModel p: cafeDao.readFroNome(nome)) {
			modelo.addRow(new Object[] {
				
				p.getId(),		
				p.getNome(),
				p.getLotacao()
			});
		}
		
	}

	/**
	 * Create the application.
	 */
	public CadastroCafe() {
		initialize();
		readTtable();
	}
	
	public void readTtable(){
		DefaultTableModel modelo = (DefaultTableModel) tableCafe.getModel();
		tableCafe.setRowSorter(new TableRowSorter(modelo));
		
		CafeDAO cafe = new CafeDAO();
		
		for(CafeModel c: cafe.read()) {
			modelo.addRow(new Object[] {
				
				c.getId(),	
				c.getNome(),
				c.getLotacao()
			});
		}
		
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 783, 471);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnPesquisa = new JButton("Pesquisa");
		btnPesquisa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CafeModel cafeModel = new CafeModel();
				CafeDAO cafeDao = new CafeDAO();
				
				cafeModel.setNome(textPesquisa.getText());
				
				((DefaultTableModel) tableCafe.getModel()).setRowCount(0);
				readTtableFroNome(cafeModel);
			}
		});
		btnPesquisa.setBounds(668, 11, 89, 23);
		frame.getContentPane().add(btnPesquisa);
		
		textPesquisa = new JTextField();
		textPesquisa.setBounds(505, 12, 134, 20);
		frame.getContentPane().add(textPesquisa);
		textPesquisa.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 165, 737, 189);
		frame.getContentPane().add(scrollPane);
		
		tableCafe = new JTable();
		tableCafe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				id = Integer.valueOf(tableCafe.getModel().getValueAt(tableCafe.getSelectedRow(), 0).toString());
				textNome.setText(tableCafe.getModel().getValueAt(tableCafe.getSelectedRow(), 1).toString());
				textLotacao.setText(tableCafe.getModel().getValueAt(tableCafe.getSelectedRow(), 2).toString());
			}
		});
		tableCafe.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nome", "Lota\u00E7\u00E3o"
			}
		));
		scrollPane.setViewportView(tableCafe);
		
		JButton btnHome = new JButton("Home");
		btnHome.setBounds(20, 11, 89, 23);
		frame.getContentPane().add(btnHome);
		
		textLotacao = new JTextField();
		textLotacao.setBounds(445, 88, 145, 20);
		frame.getContentPane().add(textLotacao);
		textLotacao.setColumns(10);
		
		textNome = new JTextField();
		textNome.setBounds(131, 88, 164, 20);
		frame.getContentPane().add(textNome);
		textNome.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Lota\u00E7\u00E3o");
		lblNewLabel.setBounds(369, 91, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(41, 91, 46, 14);
		frame.getContentPane().add(lblNome);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textNome.setText("");
				textLotacao.setText("");
			}
		});
		btnLimpar.setBounds(668, 87, 89, 23);
		frame.getContentPane().add(btnLimpar);
		
		JLabel lblNewLabel_1 = new JLabel("Pesquisa");
		lblNewLabel_1.setBounds(418, 15, 59, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CafeModel cafeModel = new CafeModel();
				CafeDAO cafeDao = new CafeDAO();
							
				if(id == 0) {
					JOptionPane.showMessageDialog(null, "É preciso selecionar um registro!");
				}else {
					cafeModel.setId(id);
					cafeModel.setNome(textNome.getText());				
					cafeDao.delete(cafeModel);
					
					((DefaultTableModel) tableCafe.getModel()).setRowCount(0);
					readTtable();
				}
				
			}
		});
		btnDeletar.setBackground(new Color(255, 153, 153));
		btnDeletar.setBounds(668, 371, 89, 23);
		frame.getContentPane().add(btnDeletar);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				CafeModel cafeModel = new CafeModel();
				CafeDAO cafeDao = new CafeDAO();
				
				if(id == 0) {
					JOptionPane.showMessageDialog(null, "É preciso selecionar um registro!");
				}else if(textNome.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "É preciso preencher o campo nome!");
				}
				else if(textLotacao.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "É preciso preencher o campo lotação!");
				}else {
					cafeModel.setId(id);
					cafeModel.setNome(textNome.getText());
					cafeModel.setLotacao(Integer.valueOf(textLotacao.getText()));
					
					cafeDao.update(cafeModel);
					
					((DefaultTableModel) tableCafe.getModel()).setRowCount(0);
					readTtable();
				}
			}
		});
		btnAtualizar.setBackground(new Color(255, 255, 204));
		btnAtualizar.setBounds(326, 371, 89, 23);
		frame.getContentPane().add(btnAtualizar);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CafeModel cafeModel = new CafeModel();
				CafeDAO cafeDao = new CafeDAO();
				
				if(textNome.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "É preciso preencher o campo nome!");
				}
				else if(textLotacao.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "É preciso preencher o campo lotação!");
				}else {
					cafeModel.setNome(textNome.getText());
					cafeModel.setLotacao(Integer.valueOf(textLotacao.getText()));
					
					cafeDao.create(cafeModel);
					
					((DefaultTableModel) tableCafe.getModel()).setRowCount(0);
					readTtable();
				}
			}
		});
		btnAdicionar.setBackground(new Color(204, 255, 204));
		btnAdicionar.setBounds(20, 371, 89, 23);
		frame.getContentPane().add(btnAdicionar);
	}

}
