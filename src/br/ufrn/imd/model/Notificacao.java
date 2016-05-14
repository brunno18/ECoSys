package br.ufrn.imd.model;

public class Notificacao {
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
	
	public void enviar(){
		System.out.println("Enviando notificacao...");
		System.out.println(getMensagem());
	}
	
}
