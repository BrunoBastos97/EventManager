package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import model.EtapaModel;
import model.VerificarLotacaoModel;

public class EventoDAO {
	Connection connection = ConnectionFactory.getConnection();
	PreparedStatement statement = null;
	ResultSet result = null;
	
	int maxSalasEventos;
	
	public int maxSalasEventos() {
		
		try {
			statement = connection.prepareStatement("SELECT MAX(id) as MaxId FROM salasEventos"); 
			result = statement.executeQuery();
			
			while (result.next()) {
				maxSalasEventos = result.getInt("MaxId");
			}
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Erro ao verificar o total de salas de eventos!"+ ex);
		}finally {
			ConnectionFactory.closeConnection(connection, statement, result);
		}
		
		return maxSalasEventos;
		
	}
	
	public VerificarLotacaoModel verificarLotacao(int id) {
		VerificarLotacaoModel verificar =  new VerificarLotacaoModel();
		
		try {
			statement = connection.prepareStatement("select se.nome, se.lotacao, COUNT(id_pessoas) as countPessoa from etapas e inner join salasEventos se on e.id_salasEventos = se.id  where se.id = ? group by e.id_pessoas and se.id"); 
			statement.setInt(1, id);
			result = statement.executeQuery();
			
			while (result.next()) {
				verificar.setCountPessoa(result.getInt("countPessoa"));
				verificar.setLotacao(result.getInt("lotacao"));
			}
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Erro ao verificar o total de salas de eventos!"+ ex);
		}finally {
			ConnectionFactory.closeConnection(connection, statement, result);
		}

		return verificar;
		
	}
	
	public VerificarLotacaoModel verificarLotacaoGeral() {
		VerificarLotacaoModel verificar =  new VerificarLotacaoModel();
		
		try {
			statement = connection.prepareStatement("select sum(lotacao) as lotacao from salasEventos"); 
			result = statement.executeQuery();
			
			while (result.next()) {
				verificar.setLotacaoGeral(result.getInt("lotacao"));
			}
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Erro ao verificar lotação!"+ ex);
		}finally {
			ConnectionFactory.closeConnection(connection, statement, result);
		}
		return verificar;
	}

}
