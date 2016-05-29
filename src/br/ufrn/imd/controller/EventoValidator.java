package br.ufrn.imd.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.ufrn.imd.model.Evento;

public abstract class EventoValidator {
	
	public final void validar(Evento evento) throws ValidateEventoException {
		baseValidation(evento);
		customValidation(evento);
	}
	
	private void baseValidation(Evento evento) throws ValidateEventoException {
		checkVoidFields(evento);
	}
	
	private void checkVoidFields(Evento evento) throws ValidateEventoException {
		
		if(evento.getTitulo() == null || evento.getTitulo().trim().equals("")) {
			throw new ValidateEventoException("O evento deve ter um título");
		}
		
		if (evento.getDescicao() == null || evento.getDescicao().trim().equals("")) {
			throw new ValidateEventoException("O evento deve ter uma descricao");
		}
	}
	
	public abstract void customValidation(Evento evento) throws ValidateEventoException;
}
