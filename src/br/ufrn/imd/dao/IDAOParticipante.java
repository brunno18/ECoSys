package br.ufrn.imd.dao;

import java.util.List;

import br.ufrn.imd.model.Participante;

public interface IDAOParticipante {
	
	public void cadastrar(Participante participante);
	
	public List<Participante> listarParticipantes();
	
	public Participante recuperar(int idParticipante);
}
