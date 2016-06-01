package br.ufrn.imd.model;

import java.util.Date;

public class Inscricao {
	
	private long id;
	
	private Date dataInscricacao;
	
	private Evento evento;
	
	private Participante participante;
	
	private String tipoInscricao;
	
	public Inscricao(Evento evento, Participante participante, String tipoInscricao) {
		this.evento = evento;
		this.participante = participante;
		this.tipoInscricao = tipoInscricao;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDataInscricacao() {
		return dataInscricacao;
	}

	public void setDataInscricacao(Date dataInscricacao) {
		this.dataInscricacao = dataInscricacao;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

	public String getTipo() {
		return tipoInscricao;
	}

	public void setTipo(String tipoInscricao) {
		this.tipoInscricao = tipoInscricao;
	}
}
