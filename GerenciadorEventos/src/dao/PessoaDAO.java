package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import model.EtapaModel;
import model.PessoaModel;

public class PessoaDAO {
	
	EtapaDAO etapa = new EtapaDAO();
	
	Connection connection = ConnectionFactory.getConnection();
	PreparedStatement statement = null;
	ResultSet result = null;
	
	public void create(PessoaModel pessoa) {
		boolean lotado;
		try {
			statement = connection.prepareStatement("INSERT INTO pessoas(nome, sobreNome) VALUES(?, ?)");
			statement.setString(1, pessoa.getNome());
			statement.setString(2, pessoa.getSobreNome());
			
			lotado = etapa.idPessoa();
			
			if(lotado == false) {
				statement.executeUpdate();
				JOptionPane.showMessageDialog(null, pessoa.getNome() +" "+ pessoa.getSobreNome() + " cadastrado com sucesso!");
			}else {
				JOptionPane.showMessageDialog(null, "Não existe mas vagas!");
				ConnectionFactory.closeConnection(connection, statement);
			}
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar!"+ ex);
		}finally {
			ConnectionFactory.closeConnection(connection, statement);
		}
	
	}
	
	public void update(PessoaModel pessoa) {
		
		try {
			statement = connection.prepareStatement("UPDATE pessoas SET nome = ?, sobreNome = ? WHERE id = ?");
			statement.setString(1, pessoa.getNome());
			statement.setString(2, pessoa.getSobreNome());
			statement.setInt(3, pessoa.getId());
			
			statement.executeUpdate();
			
			
			JOptionPane.showMessageDialog(null, pessoa.getNome() +" "+ pessoa.getSobreNome() + " atualizado com sucesso!");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Erro ao atualizar!"+ ex);
		}finally {
			ConnectionFactory.closeConnection(connection, statement);
		}
	
	}
	
	public void delete(PessoaModel pessoa) {
		
		try {
			statement = connection.prepareStatement("DELETE FROM pessoas WHERE id = ?");
			statement.setInt(1, pessoa.getId());

			
			statement.executeUpdate();
		
			etapa.delete(pessoa);
			
			JOptionPane.showMessageDialog(null, pessoa.getNome() +" "+ pessoa.getSobreNome() + " deletado com sucesso!");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Erro ao deletar!"+ ex);
		}finally {
			ConnectionFactory.closeConnection(connection, statement);
		}
	
	}
	
	public List<PessoaModel>  read() {
		List<PessoaModel> pessoas = new ArrayList<>();
		
		try {
			statement = connection.prepareStatement("SELECT * FROM pessoas");
			result = statement.executeQuery();
			
			while (result.next()) {
				PessoaModel pessoa = new PessoaModel();
				
				pessoa.setId(result.getInt("id"));
				pessoa.setNome(result.getString("nome"));
				pessoa.setSobreNome(result.getString("sobreNome"));
				pessoas.add(pessoa);	
			}
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Erro ao listar pessoas!"+ ex);
		}finally {
			ConnectionFactory.closeConnection(connection, statement, result);
		}
		
		return pessoas;
	}
	
	public List<PessoaModel>  readFroNome(PessoaModel nome) {
		List<PessoaModel> pessoas = new ArrayList<>();
		
		try {
			
			statement = connection.prepareStatement("SELECT * FROM pessoas WHERE nome LIKE ?");
			statement.setString(1, "%"+nome.getNome()+"%");
			result = statement.executeQuery();
			
			while (result.next()) {
				PessoaModel pessoa = new PessoaModel();
				
				pessoa.setId(result.getInt("id"));
				pessoa.setNome(result.getString("nome"));
				pessoa.setSobreNome(result.getString("sobreNome"));
				pessoas.add(pessoa);	
			}
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Erro ao listar pessoas!"+ ex);
		}finally {
			ConnectionFactory.closeConnection(connection, statement, result);
		}
		
		return pessoas;
	}
	
	
	
}
