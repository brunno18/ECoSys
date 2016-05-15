package br.ufrn.imd.dao;

import br.ufrn.imd.model.Inscricao;

public interface IDAOInscricao {
	
	public void cadastrar(Inscricao inscricao);
	
	public Inscricao recuperar(int idInscricao);
	
}
