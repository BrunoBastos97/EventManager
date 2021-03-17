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
import model.VerificarLotacaoCafeModel;
import model.VerificarLotacaoEtapaModel;
import model.VerificarLotacaoModel;

/** Classe da Etapa
 * @author Bruno Bastos
 */
public class EtapaDAO {
	/** conexão com o banco
	 * @author Bruno Bastos
	 */
	Connection connection = ConnectionFactory.getConnection();
	PreparedStatement statement = null;
	ResultSet result = null;
	
	VerificarLotacaoEtapaModel verificarEtapa = new VerificarLotacaoEtapaModel();
	
	int quantidadeDeSalas = 1;
	int idSalaEvento = 1;
	boolean lotado = false;
	
	/**  Método para obter a quantidade de pessoas cadastradas;
	 * @author Bruno Bastos
	 */
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
	
	/**  Método para criar etapas;
	 * @author Bruno Bastos
	 */
	public boolean create(EtapaModel etapa) {
		SalasDAO salasDao = new SalasDAO();
		//CafeDAO cafeDao = new CafeDAO();
		
		int maxSalasEventos = salasDao.maxSalasEventos();
		int lotados = 0;
		//int id = 0;
		/**  Processo onde vai estar verificando as etapas e criando uma etapa com uma sala de café e espaço de evento
		 * 	 A sala de cafe e o espaço de evento tem ter vagas para que a pessoa seja adicionada na etapa
		 * @author Bruno Bastos
		 */
		for(int NumeroDaEtapa = 1; NumeroDaEtapa <= 2; NumeroDaEtapa++) {
			for(int i = 1; maxSalasEventos >= i; i++) {
				
			Random random1 = new Random();
			int random = (random1.nextInt(salasDao.maxSalasEventos()) + 1);
				
			VerificarLotacaoModel verificarEvento = salasDao.verificarLotacao(i, random);
			
			
		
			if(verificarEvento.getLotacao() > verificarEvento.getCountPessoa()) {
				try { 
					statement = connection.prepareStatement("INSERT INTO etapas(evento, id_salasEventos, id_espacosCafe, id_pessoas) VALUES(?, ?, ?, ?)");
					statement.setInt(1, NumeroDaEtapa);
					statement.setInt(2, random);
					statement.setInt(3, random);
					statement.setInt(4, etapa.getIdPessoa());
						
					statement.executeUpdate();
						
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Erro ao cadastrar na etapa!"+ ex);
				}finally {
					ConnectionFactory.closeConnection(connection, statement);
				}
				break;
			}else if(verificarEvento.getLotacao() == 0 && verificarEvento.getCountPessoa() == 0){
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
			}else{
				lotados++;
			}
			
			}
		}
		VerificarLotacaoModel verificar = salasDao.verificarLotacaoGeral();
		
		if(lotados >= verificar.getLotacaoGeral()) {
			lotado = true;
		}
		
		return lotado;
	}
	
	/**  Método para obter a quantidade de salas de eventos cadastradas;
	 * @author Bruno Bastos
	 */
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
	
	/**  Método para listar as etapas cadastradas;
	 * @author Bruno Bastos
	 */
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
	
	/**  Método para listar as etapas pesquisadas;
	 * @author Bruno Bastos
	 */
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
	

	/**  Método para deletar uma etapa;
	 * @author Bruno Bastos
	 */
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
