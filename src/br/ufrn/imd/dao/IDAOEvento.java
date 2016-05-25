package br.ufrn.imd.dao;

import java.util.List;

import br.ufrn.imd.model.Evento;
import br.ufrn.imd.model.StatusEvento;

public interface IDAOEvento {

	public void cadastrar(Evento evento);
	
	public Evento recuperar(int idEvento);
	
	public void atualizar(Evento evento);
	
	public List<Evento> listar();
	
	public List<Evento> listarByStatus(StatusEvento statusEvento);
	
}
