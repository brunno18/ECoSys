package br.ufrn.imd.model;

import java.util.Date;

public class Publicacao {
	
	private Evento evento;
	
	private Date data;
	
	private String titulo;
	
	private String mensagem;
	
	public Publicacao(Evento evento) {
		this.evento = evento;
	}
	
}
