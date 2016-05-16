package br.ufrn.imd.dao;

import java.util.List;

import br.ufrn.imd.model.Inscricao;

public interface IDAOInscricao {
	
	public void cadastrar(Inscricao inscricao);
	
	public Inscricao recuperar(int idInscricao);
	
	public List<Inscricao> listarInscricoesEvento(int idEvento);
	
	public List<Inscricao> listarInscricoesParticipante(int idParticipante);
	
}
