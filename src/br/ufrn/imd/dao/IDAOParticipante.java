package br.ufrn.imd.dao;

import br.ufrn.imd.model.Participante;

public interface IDAOParticipante {
	
	public abstract void cadastrar(Participante participante);
	
	public abstract Participante recuperar(int idParticipante);
}
