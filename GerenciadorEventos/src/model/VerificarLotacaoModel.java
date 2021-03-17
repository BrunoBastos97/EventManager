package model;

public class VerificarLotacaoModel {
	
	private int id;
	private int lotacao;
	private int countPessoa;
	private int lotacaoGeral;
	private String nome;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLotacaoGeral() {
		return lotacaoGeral;
	}
	public void setLotacaoGeral(int lotacaoGeral) {
		this.lotacaoGeral = lotacaoGeral;
	}
	public int getLotacao() {
		return lotacao;
	}
	public void setLotacao(int lotacao) {
		this.lotacao = lotacao;
	}
	public int getCountPessoa() {
		return countPessoa;
	}
	public void setCountPessoa(int countPessoa) {
		this.countPessoa = countPessoa;
	}
	
	

}
