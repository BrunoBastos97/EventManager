package dao;

/** imports
 * @author mariana
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import model.CafeModel;

/** Classe da Sala de Cafe
 * @author mariana
 */

public class CafeDAO {

CafeDAO cafeDAO = new CafeDAO();
	
	/** conexão com o banco
	 * @author mariana
	 */
	Connection connection = ConnectionFactory.getConnection();
	PreparedStatement stmt = null;
	ResultSet result = null;
	
	/** constructor para criacao de uma sala de café
	 * @author mariana
	 */
	public void create(CafeModel cafe) {
		try {
			/** query para o insert no banco
			 * @author mariana
			 */
			String sql = "insert into salasEventos " +
	                  "(nome, lotacao) " +
	                  "values (?,?)";
			stmt = connection.prepareStatement(sql);
			
			/** get pega os valores da model enquanto o set incere os valores na model
			 * @author mariana
			 */
			
			stmt.setString(1, cafe.getNome());
			stmt.setInt(2, cafe.getLotacao(0));
			
			stmt.execute(); 
			JOptionPane.showMessageDialog(null, cafe.getId() + " gerado com sucesso!");
			
		}catch(SQLException e){
			
			System.out.println(e);
			
		}finally {
			
			/** terminando o try ele fecha a conexao
			 * @author mariana
			 */
			ConnectionFactory.closeConnection(connection, stmt);
			
          }
	}
	
	/*/** constructor para atualizar uma sala de café
	 * @author mariana
	 */
	/*public void update(CafeModel cafe) {
			
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
		}*/
		
	/** constructor para deletar uma sala de café espécifica por id
	 * @author mariana
	 */
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
		
		/** constructor para listar as salas de café e a capacidade de lotacao
		 * @author mariana
		 */
		
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
		
		
		/*
		/** constructor para listar as salas de café e a capacidade de lotacao fazendo uma pesquisa por nome
		 * @author mariana
		 */
		/*public  List<CafeModel> readFroNome() {
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
		}*/
	
}
