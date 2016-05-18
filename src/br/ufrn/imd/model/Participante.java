package br.ufrn.imd.model;

public class Participante {
	private int id;
	private String nome;
	private int idade;

	public Participante(int idNum, String name, int idadeNum) {
		this.id = idNum;
		this.nome = name;
		this.idade = idadeNum;
	}
	
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

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}
	
}