package br.ufrn.imd.dao;

import java.util.List;

import br.ufrn.imd.model.Inscricao;

public interface IDAOInscricao {
	
	public abstract void  cadastrar(Inscricao inscricao);
	
	public abstract Inscricao recuperar(int idInscricao);
	
	public abstract List<Inscricao> listarInscricoesEvento(int idEvento);
	
	public abstract List<Inscricao> listarInscricoesParticipante(int idParticipante);
	
}
