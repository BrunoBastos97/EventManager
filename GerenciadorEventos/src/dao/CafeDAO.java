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
import model.VerificarLotacaoCafeModel;
import model.VerificarLotacaoModel;

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
	
	int maxSalasEventos;
	int countIdEspassoCafe = 0;
	/** constructor para criacao de uma sala de café
	 * @author mariana
	 */
	public void create(CafeModel cafe) {
		SalasDAO salasDao = new SalasDAO();
		
		int countIdEspassoCafe = countIdespassoCafe();
		int maxSala = salasDao.maxSalasEventos();
		
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
			
			VerificarLotacaoModel verificarEvento = salasDao.verificarLotacaoSalaDeEvento(countIdEspassoCafe == 0 ? 1 : countIdEspassoCafe);	
			
			/**  condição para verificar adicionar o café;
			 * @author Bruno Bastos
			 */
			if(verificarEvento.getLotacao() == cafe.getLotacao() && countIdEspassoCafe < 0 && countIdEspassoCafe < maxSala) {
				stmt.execute(); 
				JOptionPane.showMessageDialog(null,  cafe.getNome() + " cadastrado com sucesso!");
			}else if(verificarEvento.getLotacao() == cafe.getLotacao() && countIdEspassoCafe < maxSala) {	
				stmt.execute(); 
				JOptionPane.showMessageDialog(null,  cafe.getNome() + " cadastrado com sucesso!");
			}else if(verificarEvento.getLotacao() != cafe.getLotacao() && countIdEspassoCafe > -1){
				JOptionPane.showMessageDialog(null, "A lotação do espaço de café "+ cafe.getNome() + " tem que ser a mesma do " + verificarEvento.getNome());
	
			}else {
				JOptionPane.showMessageDialog(null, "Os espaços de café devem conter a mesma quantidade de salas de eventos. Por favor adicione mais uma "
													+ "sala de eventos, para poder adicionar mais um espaço de café");
			}
			
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
				JOptionPane.showMessageDialog(null, cafe.getNome() + " Não pode ser deletado"+ e);
				
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
			int ultimoIdCafe = 0;
			
			VerificarLotacaoCafeModel ultimoId = new VerificarLotacaoCafeModel();
			try {
				stmt = connection.prepareStatement("SELECT COUNT(id) as countId FROM espacosCafe"); 
				result = stmt.executeQuery();

				while (result.next()) {
					ultimoId.setUltimoIdCafe(result.getInt("countId"));
				}
				
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Erro ao contar etapa!"+ ex);
			}finally {
				ConnectionFactory.closeConnection(connection, stmt, result);
			}
			
			ultimoIdCafe = ultimoId.getUltimoIdCafe();
			
			return ultimoIdCafe;
		}
	
		/**  Método para verificar a quantidade de espaços de café;
		 * @author Bruno Bastos
		 */
		   public int maxSalasEventos() {
				
				try {
					stmt = connection.prepareStatement("SELECT MAX(id) as MaxId FROM espacosCafe"); 
					result = stmt.executeQuery();
					
					while (result.next()) {
						maxSalasEventos = result.getInt("MaxId");
					}
					
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Erro ao verificar o total de salas de eventos!"+ ex);
				}finally {
					ConnectionFactory.closeConnection(connection, stmt, result);
				}
				
				return maxSalasEventos;
				
			}
			
			public VerificarLotacaoCafeModel verificarLotacao(int id) {
				VerificarLotacaoCafeModel verificar =  new VerificarLotacaoCafeModel();
				
				try {
					stmt = connection.prepareStatement("select c.nome, c.lotacao, COUNT(id_pessoas) as countPessoa from etapas e inner join espacosCafe c on e.id_espacosCafe = c.id  where c.id = ? group by e.id_pessoas and c.id"); 
					stmt.setInt(1, id);
					result = stmt.executeQuery();
					
					while (result.next()) {
						verificar.setCountPessoa(result.getInt("countPessoa"));
						verificar.setLotacao(result.getInt("lotacao"));
					}
					
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Erro ao verificar o total de salas de eventos!"+ ex);
				}finally {
					ConnectionFactory.closeConnection(connection, stmt, result);
				}

				return verificar;
				
			}
			
			/**  Método para verificar a lotação geral das espaços de café;
			 * @author Bruno Bastos
			 */
			
			public VerificarLotacaoModel verificarLotacaoGeral() {
				VerificarLotacaoModel verificar =  new VerificarLotacaoModel();
				
				try {
					stmt = connection.prepareStatement("select sum(lotacao) as lotacao from espacosCafe"); 
					result = stmt.executeQuery();
					
					while (result.next()) {
						verificar.setLotacaoGeral(result.getInt("lotacao"));
					}
					
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Erro ao verificar lotação!"+ ex);
				}finally {
					ConnectionFactory.closeConnection(connection, stmt, result);
				}
				return verificar;
			}		
}
