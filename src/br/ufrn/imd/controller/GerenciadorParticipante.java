package br.ufrn.imd.controller;

import java.util.List;

import br.ufrn.imd.dao.IDAOInscricao;
import br.ufrn.imd.dao.IDAOParticipante;
import br.ufrn.imd.model.Inscricao;
import br.ufrn.imd.model.Participante;

public class GerenciadorParticipante {
	
	private IDAOParticipante daoParticipante;
	
	private IDAOInscricao daoInscricao;
	
	public GerenciadorParticipante(IDAOParticipante daoParticipante) {
		this.daoParticipante = daoParticipante;
	}
	
	public void cadastrarParticipante(Participante participante) {
		daoParticipante.cadastrar(participante);
	}
	
	public List<Participante> listarParticipantes() {
		return daoParticipante.listarParticipantes();
	}
	
	public List<Inscricao> listarInscricoes(int idParticipante) {
		return daoInscricao.listarInscricoesParticipante(idParticipante);
	}

}
