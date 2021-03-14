package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import model.EtapaModel;

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

}
