package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import model.SalaModel;

public class SalaDAO {

SalaDAO salaDAO = new SalaDAO();
	
	Connection connection = ConnectionFactory.getConnection();
	PreparedStatement stmt = null;
	ResultSet result = null;
	
	//CREATE CONSTRUCTOR
	public void create(SalaModel sala) {
		try {
			String sql = "insert into salasEventos " +
	                  "(nome, lotacao) " +
	                  "values (?,?)";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, sala.getNome());
			stmt.setInt(2, sala.getLotacao(0));
			
			stmt.execute(); 
			JOptionPane.showMessageDialog(null, sala.getId() + " gerado com sucesso!");
			
		}catch(SQLException e){
			
			System.out.println(e);
			
		}finally {
			
			ConnectionFactory.closeConnection(connection, stmt);
			
          }
	}
	
	//UPDATE
	public void update(SalaModel sala) {
			
			try {
				stmt = connection.prepareStatement("UPDATE salasEventos SET nome = ?, lotacao = ? WHERE id = ?");
				stmt.setString(1, sala.getNome());
				stmt.setInt(2, sala.getLotacao(0));
				stmt.setInt(3, sala.getId());
				
				stmt.executeUpdate();
				
				JOptionPane.showMessageDialog(null, sala.getId() + " gerado com sucesso!");
				
			} catch(SQLException e){
				
				System.out.println(e);
				
			}finally {
				
				ConnectionFactory.closeConnection(connection, stmt);
				
	          }
		}
		
		//DELETE
		public void delete(SalaModel sala) {
			
			try {
				stmt = connection.prepareStatement("DELETE FROM salasEventos WHERE id = ?");
				stmt.setInt(1, sala.getId());
	
				stmt.executeUpdate();
		
				JOptionPane.showMessageDialog(null, sala.getId() + " deletado com sucesso!");
				
			} catch(SQLException e){
				
				System.out.println(e);
				
			}finally {
				
				ConnectionFactory.closeConnection(connection, stmt);
				
	          }
		
		}
		
		//Listar salas e capacidade de lotacao
		
		public  List<SalaModel> read() {
			List<SalaModel> sala = new ArrayList<>();
			
			try {
				stmt = connection.prepareStatement("SELECT * FROM salasEventos");
				result = stmt.executeQuery();
				
				while (result.next()) {
					SalaModel salas = new SalaModel();
					
					salas.setId(result.getInt("id"));
					salas.setNome(result.getString("nome"));
					salas.setLotacao(result.getInt(""));	
				}
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro ao listar pessoas!"+ e);
			}finally {
				ConnectionFactory.closeConnection(connection, stmt, result);
			}
			
			return sala;
		}
		
		
		
		//Pesquisa por partes
		public  List<SalaModel> readFroNome() {
			List<SalaModel> sala = new ArrayList<>();
			
			try {
				stmt = connection.prepareStatement("SELECT * FROM salasEventos WHERE nome LIKE ? ");
				stmt.setString(1, "%"+sala.get(0)+"%");
				result = stmt.executeQuery();
				
				while (result.next()) {
					SalaModel salas = new SalaModel();
					
					salas.setId(result.getInt("id"));
					salas.setNome(result.getString("nome"));
					salas.setLotacao(result.getInt(""));	
				}
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro ao listar pessoas!"+ e);
			}finally {
				ConnectionFactory.closeConnection(connection, stmt, result);
			}
			
			return sala;
		}
	
}
