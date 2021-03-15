package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Help {

	private JFrame frameHelp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Help window = new Help();
					window.frameHelp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Help() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameHelp = new JFrame();
		frameHelp.setBounds(100, 100, 1038, 574);
		frameHelp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameHelp.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 61, 1002, 463);
		frameHelp.getContentPane().add(scrollPane);
		
		JTextPane txtpnAProwayVai = new JTextPane();
		txtpnAProwayVai.setText("A ProWay vai realizar um treinamento para uma grande empresa de TI de Blumenau. O treinamento ser\u00E1 realizado em 2 etapas e as pessoas ser\u00E3o divididas em salas com lota\u00E7\u00E3o vari\u00E1vel. Ser\u00E3o realizados tamb\u00E9m dois intervalos de caf\u00E9 em 2 espa\u00E7os distintos.\r\n    Voc\u00EA precisa criar o sistema que gerenciar\u00E1 este evento. O sistema precisa permitir:\r\n    - o cadastro de pessoas, com nome e sobrenome\r\n    - o cadastro das salas do evento, com nome e lota\u00E7\u00E3o\r\n    - o cadastro dos espa\u00E7os de caf\u00E9 pelo nome\r\n    A diferen\u00E7a de pessoas em cada sala dever\u00E1 ser de no m\u00E1ximo 1 pessoa. Para estimular a troca de conhecimentos, metade das pessoas precisam trocar de sala entre as duas etapas do treinamento.\r\n    Ao consultar uma pessoa cadastrada no treinamento, o sistema dever\u00E1 retornar a sala em que a pessoa ficar\u00E1 em cada etapa e o espa\u00E7o onde ela realizar\u00E1 cada intervalo de caf\u00E9.\r\n    Ao consultar uma sala cadastrada ou um espa\u00E7o de caf\u00E9, o sistema dever\u00E1 retornar uma lista das pessoas que estar\u00E3o naquela sala ou espa\u00E7o em cada etapa do evento.\r\nRequisitos obrigat\u00F3rios:\r\n    Crie uma interface que permita: \r\n    - o cadastro de pessoas, com nome e sobrenome\r\n    - o cadastro das salas do evento, com nome e lota\u00E7\u00E3o\r\n    - o cadastro dos espa\u00E7os de caf\u00E9 com lota\u00E7\u00E3o\r\n    - a consulta de cada pessoa\r\n    - a consulta de cada sala/espa\u00E7o");
		txtpnAProwayVai.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scrollPane.setViewportView(txtpnAProwayVai);
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameHelp.setVisible(false);
			}
		});
		btnHome.setBounds(10, 11, 89, 23);
		frameHelp.getContentPane().add(btnHome);
	}
}
