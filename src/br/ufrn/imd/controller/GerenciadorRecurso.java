package br.ufrn.imd.controller;

import java.util.List;

import br.ufrn.imd.dao.IDAORecurso;
import br.ufrn.imd.model.Recurso;

public class GerenciadorRecurso {
	private IDAORecurso daoRecurso;
	
	public void cadastrarRecurso(Recurso recurso) {
		daoRecurso.cadastrar(recurso);
	}
	
	public List<Recurso> listarRecursos() {
		return daoRecurso.listar();
	}
	
	public Recurso recuperarRecurso(int idRecurso) {
		return daoRecurso.recuperar(idRecurso);
	}
}
