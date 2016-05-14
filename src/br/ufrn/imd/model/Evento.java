package br.ufrn.imd.model;
import java.util.*;

public class Evento {
	Local local;
	
	Participante participante;
	
	//Publicacao publicacao;
	List<Participante> participantes;
	
	private String title;
	private String descricao;
	
	private int id;
	private boolean cancelado;
	private Date dataEvento;
	
	public void setTitulo(String title){
		this.title = title;
	}
	
	public void setDescricao(String descricao){
		this.descricao = descricao;
	}
	
	public void setLocal(Local local){
		this.local = local;
	}
	
	public void setParticipante(List<Participante> participantes){
		this.participantes = participantes;
	}

	public Local getLocal(){
		return local;
	}
	public List<Participante> getParticipante(){
		return participantes;
	}
	
	public String getTitulo(){
		return title;
	}
	
	public String getDescicao(){
		return descricao;
	}

	public String getDescricao(){
		return descricao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isCancelado() {
		return cancelado;
	}

	public void setCancelado(boolean cancelado) {
		this.cancelado = cancelado;
	}

	public Date getDataEvento() {
		return dataEvento;
	}

	public void setDataEvento(Date dataEvento) {
		this.dataEvento = dataEvento;
	}
}

