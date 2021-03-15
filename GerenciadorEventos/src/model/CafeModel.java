package model;

/**
 * Model da classe Cafe
 * @author mariana*/
public class CafeModel {
	
	private int id;
	private String nome;
	private int lotacao;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getLotacao() {
		return this.lotacao;
	}
	
	public void setLotacao(int lotacao) {
		this.lotacao = lotacao;
	}

}
