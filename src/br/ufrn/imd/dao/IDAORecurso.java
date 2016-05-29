package br.ufrn.imd.dao;

import java.util.List;

import br.ufrn.imd.model.Recurso;

public interface IDAORecurso {
	public void cadastrar(Recurso recurso);
	
	public List<Recurso> listar();
	
	public Recurso recuperar(int idRecurso);
}
