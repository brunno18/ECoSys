package br.ufrn.imd.view;

import java.text.ParseException;

public interface GerenciadorEventoGUI {
	
	public void criarEvento() throws ParseException;
	
	public void atualizarEvento();
	
	public void cancelarEvento();
	
	public void publicarEvento();

	public void gerarRelatorioCustoEvento();
	
	public void notificarEventosProximos();
	
	public void inscreverParticipante();

}
