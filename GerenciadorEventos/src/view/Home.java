package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JScrollPane;

public class Home {

	private JFrame frameHome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
					window.frameHome.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Home() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameHome = new JFrame();
		frameHome.setBounds(100, 100, 928, 554);
		frameHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameHome.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		frameHome.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);
		
		JMenuItem mnPessoa = new JMenuItem("Pessoa");
		mnPessoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroPessoa pessoa = new CadastroPessoa();
				pessoa.main(null);
			}
		});
		mnNewMenu.add(mnPessoa);
		
		JMenuItem mnCafe = new JMenuItem("Cafe");
		mnCafe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroCafe cafe = new CadastroCafe();
				cafe.main(null);
			}
		});
		mnNewMenu.add(mnCafe);
		
		JMenuItem mnEvento = new JMenuItem("Evento");
		mnEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SalaDeEventos evento = new SalaDeEventos();
				evento.main(null);
			}
		});
		mnNewMenu.add(mnEvento);
		
		JMenu mnEtapas = new JMenu("Etapa");
		menuBar.add(mnEtapas);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Etapas");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Etapa etapa = new Etapa();
				etapa.main(null);
			}
		});
		mnEtapas.add(mntmNewMenuItem_1);
		
		JMenuItem mnHelp = new JMenuItem("Help");
		mnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Help help = new Help();
				help.main(null);
			}
		});
		menuBar.add(mnHelp);
	}
}
