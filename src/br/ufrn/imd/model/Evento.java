package br.ufrn.imd.model;
import java.util.*;

public class Evento {
	
	private int id;
	
	private String title;
	
	private String descricao;
	
	private  Local local;
	
	private StatusEvento status;
	
	private Date dataInicio;
	
	private Date dataFim;
	
	
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

	public StatusEvento getStatus() {
		return status;
	}
	
	public void setStatus(StatusEvento status) {
		this.status = status;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	
	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
}

