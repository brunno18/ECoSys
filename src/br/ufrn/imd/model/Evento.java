package br.ufrn.imd.model;
import java.util.*;

public class Evento {
	
	private long id;
	
	private String title;
	
	private String descricao;
	
	private  Local local;
	
	private StatusEvento status;
	
	private Calendar dataInicio;
	
	private Calendar dataFim;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public StatusEvento getStatus() {
		return status;
	}
	
	public void setStatus(StatusEvento status) {
		this.status = status;
	}

	public Calendar getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}
	
	public Calendar getDataFim() {
		return dataFim;
	}

	public void setDataFim(Calendar dataFim) {
		this.dataFim = dataFim;
	}
}

