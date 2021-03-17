package dao;


/** imports
 * @author Mateus Haverorth
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import model.CafeModel;
import model.SalasModel;
import model.VerificarLotacaoModel;

/** Classe das Salas de evento
 * @author Mateus Haverorth
 */

public class SalasDAO {
	
	SalasModel sala = new SalasModel();
	
	/** conexão com o banco
	 * @author Mateus Haverorth
	 */
	
    Connection connection = ConnectionFactory.getConnection();
    PreparedStatement statement = null;
    ResultSet result = null;

    
	int maxSalasEventos;
	
    /** constructor para criação de uma sala de evento
	 * @author Mateus Haverorth
	 */
    
    public void create (SalasModel sala){

        try {
			statement = connection.prepareStatement("INSERT INTO salaseventos(nome, lotacao) VALUES(? , ?)");
			statement.setString(1, sala.getNomeSala());
			statement.setInt(2, sala.getLotacao());
			statement.executeUpdate();
					
			JOptionPane.showMessageDialog(null, sala.getNomeSala() + " cadastrado com sucesso!");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar!"+ ex);
		}finally {
			ConnectionFactory.closeConnection(connection, statement);
		}

    }

    /** constructor para atualização de uma sala de evento
	 * @author Mateus Haverorth
	 */
    
    public void update(SalasModel sala) {
		
		try {
			statement = connection.prepareStatement("UPDATE salaseventos SET nome = ?, lotacao = ? WHERE id = ?");
			statement.setString(1, sala.getNomeSala());
			statement.setInt(2, sala.getLotacao());
			statement.setInt(3, sala.getId());
			
			statement.executeUpdate();
			
			
			JOptionPane.showMessageDialog(null, sala.getNomeSala() +" "+ sala.getLotacao() + " atualizado com sucesso!");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Erro ao atualizar!"+ ex);
		}finally {
			ConnectionFactory.closeConnection(connection, statement);
		}
	
	}
    
    /** constructor para deletar de uma sala de evento
	 * @author Mateus Haverorth
	 */
    
    public void delete(SalasModel sala) {
		CafeDAO cafeDao = new CafeDAO();
		CafeModel cafeModel = new CafeModel();
		
		cafeModel.setId(sala.getId());
		
		try {
			statement = connection.prepareStatement("DELETE FROM salaseventos WHERE id = ?");
			statement.setInt(1, sala.getId());

			
			statement.executeUpdate();
			cafeDao.delete(cafeModel);
			
			JOptionPane.showMessageDialog(null, sala.getNomeSala() + " deletado com sucesso!");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Erro ao deletar!"+ ex);
		}finally {
			ConnectionFactory.closeConnection(connection, statement);
		}
	
	}

    /** constructor para listar as salas de evento e a capacidade de lotacao
	 * @author Mateus Haverorth
	 */
    
    public List<SalasModel>  read() {
		List<SalasModel> salas = new ArrayList<>();
		
		try {
			statement = connection.prepareStatement("SELECT * FROM salaseventos");
			result = statement.executeQuery();
			
			while (result.next()) {
				SalasModel sala = new SalasModel();
				
				sala.setId(result.getInt("id"));
				sala.setNomeSala(result.getString("nome"));
				sala.setLotacao(result.getInt("lotacao"));
				salas.add(sala);	
			}
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Erro ao listar Salas!"+ ex);
		}finally {
			ConnectionFactory.closeConnection(connection, statement, result);
		}
		
		return salas;
	}
    
    /** constructor para listar as salas de evento e a capacidade de lotacao fazendo uma pesquisa por nome
   	 * @author Mateus Haverorth
   	 */
       

    public List<SalasModel>  readForNome(SalasModel nome) {
		List<SalasModel> salas = new ArrayList<>();
		
		try {
			
			statement = connection.prepareStatement("SELECT * FROM salaseventos WHERE nome LIKE ?");
			statement.setString(1, "%"+nome.getNomeSala()+"%");
			result = statement.executeQuery();
			
			while (result.next()) {
				SalasModel sala = new SalasModel();
				
				sala.setId(result.getInt("id"));
				sala.setNomeSala(result.getString("nome"));
				sala.setLotacao(result.getInt("lotacao"));
				salas.add(sala);	
			}
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Erro ao listar salas!"+ ex);
		}finally {
			ConnectionFactory.closeConnection(connection, statement, result);
		}
		
		return salas;
	}
    

	/**  Método verificar a quantidade de salas;
	 * @author Bruno Bastos
	 */
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
	

	/**  Método para verificar a lotação por sala;
	 * @author Bruno Bastos
	 */
	public VerificarLotacaoModel verificarLotacao(int id) {
		VerificarLotacaoModel verificar =  new VerificarLotacaoModel();
		EtapaDAO etapa = new EtapaDAO();
		
		try {
			statement = connection.prepareStatement("select se.nome, se.lotacao, COUNT(id_pessoas) as countPessoa from etapas e inner join salasEventos se on e.id_salasEventos = se.id  where se.id = ? group by e.id_pessoas and se.id"); 
			statement.setInt(1, id);
			result = statement.executeQuery();
			
			while (result.next()) {
				verificar.setId(id);
				verificar.setNome(result.getString("nome"));
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
	

	/**  Método para verificar a lotação por evento;
	 * @author Bruno Bastos
	 */
	public VerificarLotacaoModel verificarLotacaoSalaDeEvento(int id) {
		VerificarLotacaoModel verificar =  new VerificarLotacaoModel();
		
		try {
			statement = connection.prepareStatement("select nome, lotacao from salasEventos where id = ?"); 
			statement.setInt(1, id);
			result = statement.executeQuery();
			
			while (result.next()) {
				verificar.setId(id);
				verificar.setNome(result.getString("nome"));
				verificar.setLotacao(result.getInt("lotacao"));
			}
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Erro ao verificar o total de salas de eventos 2222!"+ ex);
		}finally {
			ConnectionFactory.closeConnection(connection, statement, result);
		}

		return verificar;
		
	}
	

	/**  Método para verificar a lotação geral das salas;
	 * @author Bruno Bastos
	 */
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
