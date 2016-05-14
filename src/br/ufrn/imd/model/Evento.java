package br.ufrn.imd.model;
import java.util.*;

public class Evento {
	
	private int id;
	
	private String title;
	
	private String descricao;
	
	private  Local local;
	
	private List<Participante> participantes;
	
	private boolean cancelado;
	
	private Date dataEvento;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitulo(){
		return title;
	}
	
	public void setTitulo(String title){
		this.title = title;
	}	
	
	public String getDescicao(){
		return descricao;
	}
	
	public void setDescricao(String descricao){
		this.descricao = descricao;
	}
	
	public Local getLocal(){
		return local;
	}
	
	public void setLocal(Local local){
		this.local = local;
	}
	
	public List<Participante> getParticipantes(){
		return participantes;
	}
	
	public void setParticipantes(List<Participante> participantes){
		this.participantes = participantes;
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

