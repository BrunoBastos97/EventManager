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
import model.EtapaModel;

/** Classe da Sala de Cafe
 * @author mariana
 */

public class CafeDAO {

	CafeModel cafe = new CafeModel();
	
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
			String sql = "insert into espacosCafe " +
	                  "(nome, lotacao) " +
	                  "values (?,?)";
			stmt = connection.prepareStatement(sql);
			
			/** get pega os valores da model enquanto o set incere os valores na model
			 * @author mariana
			 */
			
			stmt.setString(1, cafe.getNome());
			stmt.setInt(2, cafe.getLotacao());
			
			stmt.execute(); 
			JOptionPane.showMessageDialog(null, cafe.getNome() + " gerado com sucesso!");
			
		}catch(SQLException e){
			
			System.out.println(e);
			
		}finally {
			
			/** terminando o try ele fecha a conexao
			 * @author mariana
			 */
			ConnectionFactory.closeConnection(connection, stmt);
			
          }
	}
	
	/** constructor para atualizar uma sala de café
	 * @author mariana
	 */
	public void update(CafeModel cafe) {
			
			try {
				stmt = connection.prepareStatement("UPDATE espacosCafe SET nome = ?, lotacao = ? WHERE id = ?");
				stmt.setString(1, cafe.getNome());
				stmt.setInt(2, cafe.getLotacao());
				stmt.setInt(3, cafe.getId());
				
				stmt.executeUpdate();
				
				JOptionPane.showMessageDialog(null, cafe.getNome()+ " atualizado com sucesso!");
			}catch(SQLException e){
				JOptionPane.showMessageDialog(null, "Erro ao atualizar!"+ e);
			}finally {
				
				ConnectionFactory.closeConnection(connection, stmt);
	        }
		}
		
	/** constructor para deletar uma sala de café espécifica por id
	 * @author mariana
	 */
		public void delete(CafeModel cafe) {
			
			try {
				stmt = connection.prepareStatement("DELETE FROM espacosCafe WHERE id = ?");
				stmt.setInt(1, cafe.getId());
	
				stmt.executeUpdate();
		
				JOptionPane.showMessageDialog(null, cafe.getNome() + " deletado com sucesso!");
				
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
			List<CafeModel> cafes = new ArrayList<>();
			
			try {
				stmt = connection.prepareStatement("SELECT * FROM espacosCafe");
				result = stmt.executeQuery();
				
				while (result.next()) {
					CafeModel cafe = new CafeModel();
					
					cafe.setId(result.getInt("id"));
					cafe.setNome(result.getString("nome"));
					cafe.setLotacao(result.getInt("lotacao"));
					cafes.add(cafe);
					
				}
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro ao listar pessoas!"+ e);
			}finally {
				ConnectionFactory.closeConnection(connection, stmt, result);
			}
			
			return cafes;
		}
		
		
		
		/** constructor para listar as salas de café e a capacidade de lotacao fazendo uma pesquisa por nome
		 * @author mariana
		 */
		public  List<CafeModel> readFroNome(CafeModel nome) {
			List<CafeModel> cafes = new ArrayList<>();
			
			try {
				stmt = connection.prepareStatement("SELECT * FROM espacosCafe WHERE nome LIKE ? ");
				stmt.setString(1, "%"+nome.getNome()+"%");
				result = stmt.executeQuery();
				
				while (result.next()) {
					CafeModel cafe = new CafeModel();
					
					cafe.setId(result.getInt("id"));
					cafe.setNome(result.getString("nome"));
					cafe.setLotacao(result.getInt("lotacao"));	
					cafes.add(cafe);
				}
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro ao listar pessoas!"+ e);
			}finally {
				ConnectionFactory.closeConnection(connection, stmt, result);
			}
			
			return cafes;
		}
		
		/** Método verificar a quantidade de espaço de café cadastrada
		 * @author Bruno Bastos
		 */
		public int countIdespassoCafe() {
			int countIdEspassoCafe = 0;
			
			EtapaModel etapa = new EtapaModel();
			try {
				stmt = connection.prepareStatement("SELECT COUNT(id) as countId FROM espacosCafe"); 
				result = stmt.executeQuery();

				while (result.next()) {
					etapa.setIdPessoa(result.getInt("countId"));
				}
				
				//estaLotado = create(etapa);
				
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Erro ao contar etapa!"+ ex);
			}finally {
				ConnectionFactory.closeConnection(connection, stmt, result);
			}
			
			return countIdEspassoCafe;
		}
	
}
