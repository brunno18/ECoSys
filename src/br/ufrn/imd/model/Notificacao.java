package br.ufrn.imd.model;

public abstract class Notificacao {
	
	private String titulo;
	
	private String mensagem;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public abstract void notificar(Participante participante);
	
}
