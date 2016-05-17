package br.ufrn.imd.dao;

import java.util.*;
import br.ufrn.imd.model.*;

public class DAOInscricaoMemory implements IDAOInscricao {
	
	static IDAOInscricao daoInscricao = null;
	private Set<Inscricao> inscricoes;
	
	
	public static IDAOInscricao getInstancia() {
		if(daoInscricao == null){
			daoInscricao = new DAOInscricaoMemory();
		}	
		return daoInscricao;
	}
		
	private DAOInscricaoMemory() {
		inscricoes = new HashSet<Inscricao>();
	}
	
	@Override
	public void cadastrar(Inscricao inscricao) {
		inscricoes.add(inscricao);
		
	}

	@Override
	public Inscricao recuperar(int idInscricao) {
		Iterator<Inscricao> iter = inscricoes.iterator();
		while(iter.hasNext()) {
			Inscricao inscri = iter.next();
			
			if(inscri.getId() == idInscricao) {
				return inscri;
			}
		}
		return null;
	}

	@Override
	public List<Inscricao> listarInscricoesEvento(int idEvento) {
		List<Inscricao> resultList = new ArrayList<Inscricao>();
		Iterator<Inscricao> iter = inscricoes.iterator();
		
		while(iter.hasNext()) {
			Inscricao insc = iter.next();
			if(insc.getEvento().getId() == idEvento) {
				resultList.add(insc);
			}
		}
		
		return resultList;
	}

	@Override
	public List<Inscricao> listarInscricoesParticipante(int idParticipante) {
		List<Inscricao> resultList = new ArrayList<Inscricao>();
		Iterator<Inscricao> iter = inscricoes.iterator();
		
		while(iter.hasNext()) {
			Inscricao insc = iter.next();
			if(insc.getParticipante().getId() == idParticipante) {
				resultList.add(insc);
			}
		}
		
		return resultList;
	}

}
