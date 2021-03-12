package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;

import dao.PessoaDAO;
import model.PessoaModel;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CadastroPessoa {

	private JFrame frmPessoa;
	private JTextField textNome;
	private JTextField textSobreNome;
	private JTextField textPesquisa;
	private JTable tablePessoas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroPessoa window = new CadastroPessoa();
					window.frmPessoa.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CadastroPessoa() {
		initialize();
		readTtable();
	}
	
	public void readTtable(){
		DefaultTableModel modelo = (DefaultTableModel) tablePessoas.getModel();
		tablePessoas.setRowSorter(new TableRowSorter(modelo));
		
		PessoaDAO pessoa = new PessoaDAO();
		
		for(PessoaModel p: pessoa.read()) {
			modelo.addRow(new Object[] {
				p.getNome(),
				p.getSobreNome()
			});
		}
		
	}
	
	public void readTtableFroNome(PessoaModel nome){
		DefaultTableModel modelo = (DefaultTableModel) tablePessoas.getModel();
		tablePessoas.setRowSorter(new TableRowSorter(modelo));
		
		PessoaDAO pessoa = new PessoaDAO();
		
		for(PessoaModel p: pessoa.readFroNome(nome)) {
			modelo.addRow(new Object[] {
				p.getNome(),
				p.getSobreNome()
			});
		}
		
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPessoa = new JFrame();
		frmPessoa.setTitle("Pessoa");
		frmPessoa.setBounds(100, 100, 861, 510);
		frmPessoa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPessoa.getContentPane().setLayout(null);
		
		JButton btnCreat = new JButton("Adicionar");
		btnCreat.setBackground(new Color(153, 255, 153));
		btnCreat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PessoaModel pessoaModel = new PessoaModel();
				PessoaDAO pessoDao = new PessoaDAO();
				
				pessoaModel.setNome(textNome.getText());
				pessoaModel.setSobreNome(textSobreNome.getText());
				
				pessoDao.create(pessoaModel);
				
				((DefaultTableModel) tablePessoas.getModel()).setRowCount(0);
				readTtable();
				
			}
		});
		btnCreat.setBounds(47, 362, 89, 23);
		frmPessoa.getContentPane().add(btnCreat);
		
		textNome = new JTextField();
		textNome.setBounds(119, 84, 183, 20);
		frmPessoa.getContentPane().add(textNome);
		textNome.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(47, 87, 46, 14);
		frmPessoa.getContentPane().add(lblNewLabel);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PessoaModel pessoaModel = new PessoaModel();
				PessoaDAO pessoDao = new PessoaDAO();
				
				pessoaModel.setNome(textNome.getText());
				pessoaModel.setSobreNome(textSobreNome.getText());
								
				pessoDao.create(pessoaModel);
				
				((DefaultTableModel) tablePessoas.getModel()).setRowCount(0);
				readTtable();
			}
		});
		btnAtualizar.setBackground(new Color(255, 255, 153));
		btnAtualizar.setBounds(386, 362, 89, 23);
		frmPessoa.getContentPane().add(btnAtualizar);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.setBackground(new Color(255, 153, 153));
		btnDeletar.setBounds(720, 362, 89, 23);
		frmPessoa.getContentPane().add(btnDeletar);
		
		JLabel lblNewLabel_1 = new JLabel("Sobre Nome");
		lblNewLabel_1.setBounds(342, 87, 100, 14);
		frmPessoa.getContentPane().add(lblNewLabel_1);
		
		textSobreNome = new JTextField();
		textSobreNome.setBounds(422, 84, 231, 20);
		frmPessoa.getContentPane().add(textSobreNome);
		textSobreNome.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Pesquisa");
		lblNewLabel_2.setBounds(437, 11, 63, 14);
		frmPessoa.getContentPane().add(lblNewLabel_2);
		
		textPesquisa = new JTextField();
		textPesquisa.setBounds(537, 8, 162, 20);
		frmPessoa.getContentPane().add(textPesquisa);
		textPesquisa.setColumns(10);
		
		JButton btnPesquisa = new JButton("Pesquisa");
		btnPesquisa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PessoaModel pessoaModel = new PessoaModel();
				PessoaDAO pessoDao = new PessoaDAO();
				
				pessoaModel.setNome(textPesquisa.getText());
				
				((DefaultTableModel) tablePessoas.getModel()).setRowCount(0);
				readTtableFroNome(pessoaModel);
			}
		});
		btnPesquisa.setBounds(720, 7, 89, 23);
		frmPessoa.getContentPane().add(btnPesquisa);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 158, 762, 158);
		frmPessoa.getContentPane().add(scrollPane);
		
		tablePessoas = new JTable();
		tablePessoas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				textSobreNome.setText(tablePessoas.getModel().getValueAt(tablePessoas.getSelectedRow(), 0).toString());
				textNome.setText(tablePessoas.getModel().getValueAt(tablePessoas.getSelectedRow(), 1).toString());

			}
		});
		tablePessoas.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nome", "Sobre Nome"
			}
		));
		scrollPane.setViewportView(tablePessoas);
		
		JButton btnHome = new JButton("Home");
		btnHome.setBounds(47, 7, 89, 23);
		frmPessoa.getContentPane().add(btnHome);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBackground(new Color(204, 255, 255));
		btnLimpar.setBounds(720, 84, 89, 23);
		frmPessoa.getContentPane().add(btnLimpar);
	}
}
