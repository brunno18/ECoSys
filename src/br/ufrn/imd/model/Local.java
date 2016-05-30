package br.ufrn.imd.model;

public class Local {
	
	private long id;
	
	private String nome;
	
	private String endereco;
	
	//Somente alguns eventos precisam de capacidade para o local
	//Como eventos acadêmicos. Nesse caso capacidade tanto para o local quanto para o evento
	//private int capacidade;
	
	public Local(String nome, String endereco) {
		this.nome = nome;
		this.endereco = endereco;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
}