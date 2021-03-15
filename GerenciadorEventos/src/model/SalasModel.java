package model;

/**
 * Model da classe Salas
 * @author Mateus Haverroth*/

public class SalasModel {

	private String nomeSala;	
	private int id, lotacao; 
	
	public String getNomeSala() {
		return nomeSala;
	}
	public void setNomeSala(String nomeSala) {
		this.nomeSala = nomeSala;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLotacao() {
		return lotacao;
	}
	public void setLotacao(int lotacao) {
		this.lotacao = lotacao;
	}
		
}
