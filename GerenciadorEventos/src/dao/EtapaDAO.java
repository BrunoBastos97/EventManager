package dao;


/** imports
 * @author mariana
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import model.EtapaModel;
import model.PessoaModel;

/** Classe da Etapa
 * @author mariana
 */


public class EtapaDAO {
	
	/** conexão com o banco
	 * @author mariana 
	 */
	
	Connection connection = ConnectionFactory.getConnection();
	PreparedStatement statement = null;
	ResultSet result = null;
	
	/** variaveis globais
	 * @author mariana
	 */
	
	int quantidadeDeSalas = 1;
	int idSalaEvento = 1;
	
	/** constructor para selecionar pessoa por etapa
	 * @author mariana
	 */
	
	public void idPessoa() {
		
		EtapaModel etapa = new EtapaModel();
		try {
			statement = connection.prepareStatement("SELECT MAX(id) as id FROM pessoas"); 
			result = statement.executeQuery();
			
			while (result.next()) {
				etapa.setIdPessoa(result.getInt("id"));
			}
			
			createEtapas(etapa);
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar etapa!"+ ex);
		}finally {
			ConnectionFactory.closeConnection(connection, statement, result);
		}
		
	}
	
	/** constructor para criacao de uma etapa
	 * @author mariana
	 */

	public void createEtapas(EtapaModel etapa) {
		
		EventoDAO eventoDao = new EventoDAO();
		int maxSalasEventos = eventoDao.maxSalasEventos();
		
		for(int NumeroDaEtapa = 1; NumeroDaEtapa <= 2; NumeroDaEtapa++) {
			Random random = new Random();
			int numero = (random.nextInt(maxSalasEventos) + 1);
			try { 
				statement = connection.prepareStatement("INSERT INTO etapas(evento, id_salasEventos, id_espacosCafe, id_pessoas) VALUES(?, ?, ?, ?)");
				statement.setInt(1, NumeroDaEtapa);
				statement.setInt(2, numero);
				statement.setInt(3, numero);
				statement.setInt(4, etapa.getIdPessoa());
				
				statement.executeUpdate();
				
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Erro ao cadastrar na etapa!"+ ex);
			}finally {
				ConnectionFactory.closeConnection(connection, statement);
			}
		}
	
	}
	
	public void readNumeroEventoECafe() {
		try {
			statement = connection.prepareStatement("SELECT COUNT(id) as countId FROM salasEventos"); 
			result = statement.executeQuery();
			
			while (result.next()) {
				quantidadeDeSalas = result.getInt("countId");
			}
			
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Erro ao verificar quantidade de salas!"+ ex);
		}finally {
			ConnectionFactory.closeConnection(connection, statement, result);
		}
		
		
	}
	
	public List<PessoaModel>  read() {
		List<PessoaModel> pessoas = new ArrayList<>();
		
		try {
			statement = connection.prepareStatement("SELECT * FROM etapas");
			result = statement.executeQuery();
			
			while (result.next()) {
				PessoaModel pessoa = new PessoaModel();
				
				pessoa.setId(result.getInt("id"));
				pessoa.setNome(result.getString("nome"));
				pessoa.setSobreNome(result.getString("sobreNome"));
				pessoas.add(pessoa);	
			}
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Erro ao listar pessoas!"+ ex);
		}finally {
			ConnectionFactory.closeConnection(connection, statement, result);
		}
		
		return pessoas;
	}
	
	public void delete(PessoaModel pessoa) {
		
		try {
			statement = connection.prepareStatement("DELETE FROM etapas WHERE id_Pessoas = ?");
			statement.setInt(1, pessoa.getId());

			statement.executeUpdate();
		
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Erro ao deletar!"+ ex);
		}finally {
			ConnectionFactory.closeConnection(connection, statement);
		}
	
	}

}
