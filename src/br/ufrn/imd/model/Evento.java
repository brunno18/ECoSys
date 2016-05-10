package br.ufrn.imd.model;

public class Evento {
	Local local;
	Participante participante;
	//Publicacao publicacao;
	
	private String title;
	
	private String descricao;
	
	public void setTitulo(String title){
		this.title = title;
	}
	
	public void setDescricao(String descricao){
		this.descricao = descricao;
	}
	
	public void setLocal(Local local){
		this.local = local;
	}

	public void setParticipante(Participante participante){
		this.participante = participante;
	}

	public Local getLocal(){
		return local;
	}
	
	public Participante getParticipante(){
		return participante;
	}
	
	public String getTitulo(){
		return title;
	}
	
	public String getDescicao(){
		return descricao;
	}
}

