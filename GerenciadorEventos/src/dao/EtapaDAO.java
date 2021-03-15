package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import model.EtapaModel;
import model.PessoaModel;
import model.VerificarLotacaoModel;

public class EtapaDAO {
	Connection connection = ConnectionFactory.getConnection();
	PreparedStatement statement = null;
	ResultSet result = null;
	
	int quantidadeDeSalas = 1;
	int idSalaEvento = 1;
	boolean lotado = false;
	
	public boolean idPessoa() {
		boolean estaLotado = false;
		
		EtapaModel etapa = new EtapaModel();
		try {
			statement = connection.prepareStatement("SELECT COUNT(id) as id FROM pessoas"); 
			result = statement.executeQuery();

			while (result.next()) {
				etapa.setIdPessoa(result.getInt("id") + 1);
			}
			
			estaLotado = create(etapa);
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar etapa!"+ ex);
		}finally {
			ConnectionFactory.closeConnection(connection, statement, result);
		}
		
		return estaLotado;
	}
	
	public boolean create(EtapaModel etapa) {
		EventoDAO eventoDao = new EventoDAO();
		int maxSalasEventos = eventoDao.maxSalasEventos();
		int lotados = 0;
		
		for(int NumeroDaEtapa = 1; NumeroDaEtapa <= 2; NumeroDaEtapa++) {
			for(int i = 1; maxSalasEventos >= i; i++) {
		
			VerificarLotacaoModel verificar = eventoDao.verificarLotacao(i);
			
			if(verificar.getLotacao() > verificar.getCountPessoa()) {
				try { 
					statement = connection.prepareStatement("INSERT INTO etapas(evento, id_salasEventos, id_espacosCafe, id_pessoas) VALUES(?, ?, ?, ?)");
					statement.setInt(1, NumeroDaEtapa);
					statement.setInt(2, i);
					statement.setInt(3, i);
					statement.setInt(4, etapa.getIdPessoa());
						
					statement.executeUpdate();
						
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Erro ao cadastrar na etapa!"+ ex);
				}finally {
					ConnectionFactory.closeConnection(connection, statement);
				}
				break;
			}else if(verificar.getLotacao() == 0 && verificar.getCountPessoa() == 0){
				try { 
					statement = connection.prepareStatement("INSERT INTO etapas(evento, id_salasEventos, id_espacosCafe, id_pessoas) VALUES(?, ?, ?, ?)");
					statement.setInt(1, NumeroDaEtapa);
					statement.setInt(2, i);
					statement.setInt(3, i);
					statement.setInt(4, etapa.getIdPessoa());
						
					statement.executeUpdate();
						
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Erro ao cadastrar na etapa!"+ ex);
				}finally {
					ConnectionFactory.closeConnection(connection, statement);
				}
				break;
			}else {
				lotados++;
			}
			
			}
		}
		VerificarLotacaoModel verificar = eventoDao.verificarLotacaoGeral();
		
		if(lotados == verificar.getLotacaoGeral()) {
			lotado = true;
		}
		
		return lotado;
	}
	
	public void readNumeroEventoECafe() {
		try {
			statement = connection.prepareStatement("SELECT COUNT(id) as countId FROM salasEventos"); 
			result = statement.executeQuery();
			
			while (result.next()) {
				quantidadeDeSalas = result.getInt("countId");
			}
			
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Erro ao verificar quantidade de salas!"+ ex);
		}finally {
			ConnectionFactory.closeConnection(connection, statement, result);
		}
		
		
	}
	
	public List<EtapaModel>  read() {
		List<EtapaModel> etapas = new ArrayList<>();
		
		try {
			statement = connection.prepareStatement("select p.nome as nomePessoa,  p.sobreNome, e.evento, se.nome as nomeEvento, ec.nome as nomeCafe from etapas e inner join pessoas p on p.id = e.id_pessoas \r\n"
					+ "									   inner join salasEventos se on e.id_salasEventos = se.id \r\n"
					+ "									   inner join espacosCafe ec on e.id_espacosCafe = ec.id order by p.id");
			result = statement.executeQuery();
			
			while (result.next()) {
				EtapaModel etapa = new EtapaModel();
				
				etapa.setNome(result.getString("nomePessoa"));
				etapa.setSobreNome(result.getString("sobreNome"));
				etapa.setEvento(result.getInt("evento"));
				etapa.setSalaDeEvento(result.getString("nomeEvento"));
				etapa.setEspacoDeCafe(result.getString("nomeCafe"));
				etapas.add(etapa);	
			}
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Erro ao listar etapas!"+ ex);
		}finally {
			ConnectionFactory.closeConnection(connection, statement, result);
		}
		
		return etapas;
	}
	
	public List<EtapaModel>  readPesquisa(EtapaModel pesquisa) {
		List<EtapaModel> etapas = new ArrayList<>();
		
		try {
			statement = connection.prepareStatement("select p.nome as nomePessoa,  p.sobreNome, e.evento, se.nome as nomeEvento, ec.nome as nomeCafe from etapas e inner join pessoas p on p.id = e.id_pessoas \r\n"
					+ "									   inner join salasEventos se on e.id_salasEventos = se.id \r\n"
					+ "									   inner join espacosCafe ec on e.id_espacosCafe = ec.id where p.nome like ? && p.sobreNome like ?" );
			statement.setString(1, pesquisa.getNome()+"%");
			statement.setString(2, pesquisa.getSobreNome()+"%");
			result = statement.executeQuery();
			
			while (result.next()) {
				EtapaModel etapa = new EtapaModel();
			
				etapa.setNome(result.getString("nomePessoa"));
				etapa.setSobreNome(result.getString("sobreNome"));
				etapa.setEvento(result.getInt("evento"));
				etapa.setSalaDeEvento(result.getString("nomeEvento"));
				etapa.setEspacoDeCafe(result.getString("nomeCafe"));
				etapas.add(etapa);	
			}
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Erro ao listar pessoas!"+ ex);
		}finally {
			ConnectionFactory.closeConnection(connection, statement, result);
		}
		
		return etapas;
	}
	
	public void delete(PessoaModel pessoa) {
		
		try {
			statement = connection.prepareStatement("DELETE FROM etapas WHERE id_Pessoas = ?");
			statement.setInt(1, pessoa.getId());

			statement.executeUpdate();
		
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Erro ao deletar!"+ ex);
		}finally {
			ConnectionFactory.closeConnection(connection, statement);
		}
	
	}
	


}
