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
import model.SalasModel;

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
		
		try {
			statement = connection.prepareStatement("DELETE FROM salaseventos WHERE id = ?");
			statement.setInt(1, sala.getId());

			
			statement.executeUpdate();
			
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

}
