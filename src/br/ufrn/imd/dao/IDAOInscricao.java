package br.ufrn.imd.dao;

import java.util.List;

import br.ufrn.imd.model.Inscricao;

public interface IDAOInscricao {
	
	public void  cadastrar(Inscricao inscricao);
	
	public Inscricao recuperar(long idInscricao);
	
	public List<Inscricao> listarInscricoesEvento(long idEvento);
	
	public List<Inscricao> listarInscricoesParticipante(long idParticipante);
	
}
