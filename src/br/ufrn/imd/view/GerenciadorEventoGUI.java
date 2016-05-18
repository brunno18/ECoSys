package br.ufrn.imd.view;

import java.text.ParseException;
import br.ufrn.imd.model.ValidatePartipationException;


public interface GerenciadorEventoGUI {
	
	public void criarEvento() throws ParseException;
	
	public void atualizarEvento() throws ParseException;
	
	public void cancelarEvento();
	
	public void publicarEvento();

	public void gerarRelatorioCustoEvento();
	
	public void notificarEventosProximos();
	
	public void inscreverParticipante(int idEvento) throws ValidatePartipationException;

}
