package br.ufrn.imd.view;

import java.text.ParseException;
import br.ufrn.imd.model.ValidatePartipationException;


public interface GerenciadorEventoGUI {
	
	public void criarEvento();
	
	public void listarEventos();
	
	public void atualizarEvento();
	
	public void cancelarEvento();
	
	public void publicarEvento();

	public void gerarRelatorioCustoEvento();
	
	public void notificarEventosProximos();
	
	public void inscreverParticipante();

}
