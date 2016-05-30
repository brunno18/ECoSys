package br.ufrn.imd.dao;

import java.util.List;

import br.ufrn.imd.model.Local;

public interface IDAOLocal {

	public void cadastrar(Local local) throws DAOException;
	
	public Local recuperar(long idLocal);
		
	public List<Local> listar();
	
	public void atualizar(Local local);
	
	public void remover(long idLocal);
		
}
