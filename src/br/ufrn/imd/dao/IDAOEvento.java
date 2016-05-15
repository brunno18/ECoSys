package br.ufrn.imd.dao;

import br.ufrn.imd.model.Evento;

public interface IDAOEvento {

	public void cadastrar(Evento evento);
	
	public Evento recuperar(int idEvento);
	
}
