package model;

public class EtapaModel {
	private int id;
	private int evento;
	private int idPessoa;
	private int idSalaDeEvento;
	private int idEspacosCafe;
	
	
	private String nome;
	private String sobreNome;
	private String salaDeEvento;
	private String espacoDeCafe;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobreNome() {
		return sobreNome;
	}
	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}
	public String getSalaDeEvento() {
		return salaDeEvento;
	}
	public void setSalaDeEvento(String salaDeEvento) {
		this.salaDeEvento = salaDeEvento;
	}
	public String getEspacoDeCafe() {
		return espacoDeCafe;
	}
	public void setEspacoDeCafe(String espacoDeCafe) {
		this.espacoDeCafe = espacoDeCafe;
	}
	public int getEvento() {
		return evento;
	}
	public void setEvento(int evento) {
		this.evento = evento;
	}
	public int getIdPessoa() {
		return idPessoa;
	}
	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}
	public int getIdSalaDeEvento() {
		return idSalaDeEvento;
	}
	public void setIdSalaDeEvento(int idSalaDeEvento) {
		this.idSalaDeEvento = idSalaDeEvento;
	}
	public int getIdEspacosCafe() {
		return idEspacosCafe;
	}
	public void setIdEspacosCafe(int idEspacosCafe) {
		this.idEspacosCafe = idEspacosCafe;
	}
	
	
	
}
