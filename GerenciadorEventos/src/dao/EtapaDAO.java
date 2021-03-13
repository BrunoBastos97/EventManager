package dao;

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

public class EtapaDAO {
	Connection connection = ConnectionFactory.getConnection();
	PreparedStatement statement = null;
	ResultSet result = null;
	
	private int idSala = 1;
	int quantidadeDeEvento = 0;
	
	public void etapa() {
		
		EtapaModel etapa = new EtapaModel();
		try {
			statement = connection.prepareStatement("SELECT MAX(id) as id FROM pessoas"); 
			result = statement.executeQuery();
			
			while (result.next()) {
				etapa.setIdPessoa(result.getInt("id"));
				etapa.setIdSalaDeEvento(1);
				etapa.setIdEspacosCafe(1);
				etapa.setEvento(1);
			}
			
			createEtapas(etapa);
		///	createEtapasRandom(etapa);/////////////
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar etapa!"+ ex);
		}finally {
			ConnectionFactory.closeConnection(connection, statement, result);
		}
		
	}

	public void createEtapas(EtapaModel etapa) {
		Random random = new Random();
		int numero = (random.nextInt(5) * 1) > 5 ? 2 : 1;
		
		try { 
			statement = connection.prepareStatement("INSERT INTO etapas(evento, id_salasEventos, id_espacosCafe, id_pessoas) VALUES(?, ?, ?, ?)");
			statement.setInt(1, etapa.getEvento());
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
	
	public List<PessoaModel>  read() {
		List<PessoaModel> pessoas = new ArrayList<>();
		
		try {
			statement = connection.prepareStatement("SELECT MAX(evento) as maxEvento, * FROM etapas");
			result = statement.executeQuery();
			
			int quantidadeDeEvento;
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
	
	/*public void createEtapasRandom(EtapaModel etapa) {
		Random random = new Random();
		int numero = (random.nextInt(5) * 1) > 5 ? 1 : 2;
		
		try {
			statement = connection.prepareStatement("INSERT INTO etapas(turno, id_salasEventos, id_espacosCafe, id_pessoas) VALUES(?, ?, ?, ?)");
			statement.setInt(1, 2);
			statement.setInt(2, numero);
			statement.setInt(3, numero);
			statement.setInt(4, etapa.getIdPessoa());
			
			statement.executeUpdate();
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar na etapa!"+ ex);
		}finally {
			ConnectionFactory.closeConnection(connection, statement);
		}
	}*/
	
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
	
	public void idSala() {
		if(idSala == 2) {
			idSala--;
		}else{
			idSala++;
		}
	}
	
	

}
