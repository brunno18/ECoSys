package br.ufrn.imd.controller;

import java.util.Date;
import java.util.List;

import br.ufrn.imd.model.Evento;
//import br.ufrn.imd.model.Evento;
import br.ufrn.imd.model.Participante;
import br.ufrn.imd.model.ValidatePartipationException;

public interface RegraParticipacao {
	
	public abstract void validarParticipacao(Participante participante, Evento evento) throws ValidatePartipationException;
	
}
