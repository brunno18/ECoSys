package br.ufrn.imd.dao;

import java.util.List;

import br.ufrn.imd.model.Evento;

public interface IDAOEvento {

	public abstract void cadastrar(Evento evento);
	
	public abstract Evento recuperar(int idEvento);
	
	public abstract List<Evento> listar();
	
}
