package br.ufrn.imd.controller;

import java.util.List;

import br.ufrn.imd.dao.DAOException;
import br.ufrn.imd.dao.IDAOLocal;
import br.ufrn.imd.model.Local;

public class GerenciadorLocal {
	
	private IDAOLocal daoLocal;
	
	public GerenciadorLocal (IDAOLocal daoLocal) {
		this.daoLocal = daoLocal;
	}
	
	public void cadastrarLocal(Local local) throws DAOException {
		daoLocal.cadastrar(local);
	}
	
	public List<Local> listarLocais() {
		return daoLocal.listar();
	}
	
	public Local recuperarLocal(long idLocal) {
		return daoLocal.recuperar(idLocal);
	}
	
}
