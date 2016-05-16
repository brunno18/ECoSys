package br.ufrn.imd.dao;

import java.util.List;

import br.ufrn.imd.model.Evento;

public interface IDAOEvento {

	public void cadastrar(Evento evento);
	
	public Evento recuperar(int idEvento);
	
	public List<Evento> listar();
	
}
