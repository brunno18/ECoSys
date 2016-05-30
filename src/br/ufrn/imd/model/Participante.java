package br.ufrn.imd.model;

public class Participante {
	
	private long id;
	
	private String nome;

	public Participante(String name) {
		this.nome = name;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}