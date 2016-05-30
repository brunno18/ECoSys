package br.ufrn.imd.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.ufrn.imd.dao.IDAOEvento;
import br.ufrn.imd.model.Evento;

public abstract class EventoValidator {
	
	protected IDAOEvento daoEvento;
	
	public EventoValidator (IDAOEvento daoEvento) {
		this.daoEvento = daoEvento;
	}
	
	public final void validar(Evento evento) throws ValidateEventoException {
		baseValidation(evento);
		customValidation(evento);
	}
	
	private void baseValidation(Evento evento) throws ValidateEventoException {
		checkVoidFields(evento);
		checkSameDateEvents(evento);
	}
	
	private void checkVoidFields(Evento evento) throws ValidateEventoException {
		
		if(evento.getTitulo() == null || evento.getTitulo().trim().equals("")) {
			throw new ValidateEventoException("O evento deve ter um título");
		}
		
		if (evento.getDescicao() == null || evento.getDescicao().trim().equals("")) {
			throw new ValidateEventoException("O evento deve ter uma descricao");
		}
	}
	
	private void checkSameDateEvents(Evento evento) throws ValidateEventoException {
		List<Evento> eventos = daoEvento.listar();
		
		for (Evento ev : eventos) {
			if (ev.getLocal().equals(evento.getLocal()) && checkDateOverlap(ev, evento)) {
				throw new ValidateEventoException("Não pode haver dois eventos no mesmo local na mesma data");
			}
		}
	}
	
	private boolean checkDateOverlap(Evento eventoOne, Evento eventoTwo) {
		Calendar dataInicioOne = eventoOne.getDataInicio();
		Calendar dataFimOne = eventoOne.getDataFim();
		Calendar dataInicioTwo = eventoTwo.getDataInicio();
		Calendar dataFimTwo = eventoTwo.getDataFim();
		
		return (dataInicioOne.compareTo(dataFimTwo) < 0 && dataFimOne.compareTo(dataInicioTwo) > 0);
	}
	
	public abstract void customValidation(Evento evento) throws ValidateEventoException;
}
