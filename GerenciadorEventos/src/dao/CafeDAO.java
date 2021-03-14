package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import model.CafeModel;

public class CafeDAO {

CafeDAO cafeDAO = new CafeDAO();
	
	Connection connection = ConnectionFactory.getConnection();
	PreparedStatement stmt = null;
	ResultSet result = null;
	
	//CREATE CONSTRUCTOR
	public void create(CafeModel cafe) {
		try {
			String sql = "insert into salasEventos " +
	                  "(nome, lotacao) " +
	                  "values (?,?)";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, cafe.getNome());
			stmt.setInt(2, cafe.getLotacao(0));
			
			stmt.execute(); 
			JOptionPane.showMessageDialog(null, cafe.getId() + " gerado com sucesso!");
			
		}catch(SQLException e){
			
			System.out.println(e);
			
		}finally {
			
			ConnectionFactory.closeConnection(connection, stmt);
			
          }
	}
	
	//UPDATE
	public void update(CafeModel cafe) {
			
			try {
				stmt = connection.prepareStatement("UPDATE salasEventos SET nome = ?, lotacao = ? WHERE id = ?");
				stmt.setString(1, cafe.getNome());
				stmt.setInt(2, cafe.getLotacao(0));
				stmt.setInt(3, cafe.getId());
				
				stmt.executeUpdate();
				
				JOptionPane.showMessageDialog(null, cafe.getId() + " gerado com sucesso!");
				
			} catch(SQLException e){
				
				System.out.println(e);
				
			}finally {
				
				ConnectionFactory.closeConnection(connection, stmt);
				
	          }
		}
		
		//DELETE
		public void delete(CafeModel cafe) {
			
			try {
				stmt = connection.prepareStatement("DELETE FROM salasEventos WHERE id = ?");
				stmt.setInt(1, cafe.getId());
	
				stmt.executeUpdate();
		
				JOptionPane.showMessageDialog(null, cafe.getId() + " deletado com sucesso!");
				
			} catch(SQLException e){
				
				System.out.println(e);
				
			}finally {
				
				ConnectionFactory.closeConnection(connection, stmt);
				
	          }
		
		}
		
		//Listar salas e capacidade de lotacao
		
		public  List<CafeModel> read() {
			List<CafeModel> cafe = new ArrayList<>();
			
			try {
				stmt = connection.prepareStatement("SELECT * FROM salasEventos");
				result = stmt.executeQuery();
				
				while (result.next()) {
					CafeModel cafes = new CafeModel();
					
					cafes.setId(result.getInt("id"));
					cafes.setNome(result.getString("nome"));
					cafes.setLotacao(result.getInt(""));	
				}
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro ao listar pessoas!"+ e);
			}finally {
				ConnectionFactory.closeConnection(connection, stmt, result);
			}
			
			return cafe;
		}
		
		
		
		//Pesquisa por partes
		public  List<CafeModel> readFroNome() {
			List<CafeModel> cafe = new ArrayList<>();
			
			try {
				stmt = connection.prepareStatement("SELECT * FROM salasEventos WHERE nome LIKE ? ");
				stmt.setString(1, "%"+cafe.get(0)+"%");
				result = stmt.executeQuery();
				
				while (result.next()) {
					CafeModel cafes = new CafeModel();
					
					cafes.setId(result.getInt("id"));
					cafes.setNome(result.getString("nome"));
					cafes.setLotacao(result.getInt(""));	
				}
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro ao listar pessoas!"+ e);
			}finally {
				ConnectionFactory.closeConnection(connection, stmt, result);
			}
			
			return cafe;
		}
	
}
