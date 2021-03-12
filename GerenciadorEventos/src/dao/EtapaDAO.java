package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import model.EtapaModel;
import model.PessoaModel;

public class EtapaDAO {
	Connection connection = ConnectionFactory.getConnection();
	PreparedStatement statement = null;
	ResultSet result = null;
	
	
	public void etapa() {
		
		EtapaModel etapa = new EtapaModel();
		try {
			statement = connection.prepareStatement("SELECT MAX(id) as id FROM pessoas");
			result = statement.executeQuery();
			
			while (result.next()) {
				etapa.setIdPessoa(result.getInt("id"));//result.getInt("id")
				etapa.setIdSalaDeEvento(1);
				etapa.setIdEspacosCafe(1);
				etapa.setTurno(1);
			}
			
			createEtapas(etapa);
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar etapa 1!"+ ex);
		}finally {
			ConnectionFactory.closeConnection(connection, statement, result);
		}
		
	}

	public void createEtapas(EtapaModel etapa) {
		
		try {
			statement = connection.prepareStatement("INSERT INTO etapas(turno, id_salasEventos, id_espacosCafe, id_pessoas) VALUES(?, ?, ?, ?)");
			statement.setInt(1, etapa.getTurno());
			statement.setInt(2, etapa.getIdSalaDeEvento());
			statement.setInt(3, etapa.getIdEspacosCafe());
			statement.setInt(4, etapa.getIdPessoa());
			
			statement.executeUpdate();
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar na etapa 2!"+ ex);
		}finally {
			ConnectionFactory.closeConnection(connection, statement);
		}
	
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
