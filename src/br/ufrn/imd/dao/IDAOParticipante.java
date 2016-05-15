package br.ufrn.imd.dao;

import br.ufrn.imd.model.Participante;

public interface IDAOParticipante {
	
	public void cadastrar(Participante participante);
	
	public Participante recuperar(int idParticipante);
}
