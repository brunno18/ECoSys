package br.ufrn.imd.controller;

import java.util.List;

import br.ufrn.imd.dao.DAOException;
import br.ufrn.imd.dao.IDAOInscricao;
import br.ufrn.imd.dao.IDAOParticipante;
import br.ufrn.imd.model.Inscricao;
import br.ufrn.imd.model.Participante;

public class GerenciadorParticipante {
	
	private IDAOParticipante daoParticipante;
	
	private IDAOInscricao daoInscricao;
	
	public GerenciadorParticipante(IDAOParticipante daoParticipante, IDAOInscricao daoInscricao) {
		this.daoParticipante = daoParticipante;
		this.daoInscricao = daoInscricao;
	}
	
	public void cadastrarParticipante(Participante participante) throws DAOException{
		daoParticipante.cadastrar(participante);
	}
	
	public List<Participante> listarParticipantes() {
		return daoParticipante.listarParticipantes();
	}
	
	public List<Inscricao> listarInscricoes(long idParticipante, String tipoInscricao) {
		return daoInscricao.listarInscricoesParticipante(idParticipante, tipoInscricao);
	}
	
	public Participante getParticipante(long idParticipante){
		Participante participante;
		participante = daoParticipante.recuperar(idParticipante);
		
		return participante;
	}
}
