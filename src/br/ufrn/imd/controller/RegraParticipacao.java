package br.ufrn.imd.controller;

import br.ufrn.imd.model.Participante;
import br.ufrn.imd.model.ValidatePartipationException;

public interface RegraParticipacao {
	
	public abstract void validarParticipacao(Participante participante) throws ValidatePartipationException;
	
}
