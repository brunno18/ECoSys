package clinica;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import br.ufrn.imd.dao.IDAOInscricao;
import br.ufrn.imd.model.Inscricao;

public class DAOInscricaoMemory implements IDAOInscricao {
	
	private static long nextId = 0;
	
	static DAOInscricaoMemory daoInscricaoMemory = null;
	
	private Set<Inscricao> inscricoes;
	
	private DAOInscricaoMemory() {
		inscricoes = new HashSet<Inscricao>();
	}
	
	public static DAOInscricaoMemory getInstancia() {
		if(daoInscricaoMemory == null){
			daoInscricaoMemory = new DAOInscricaoMemory();
		}	
		return daoInscricaoMemory;
	}
	
	@Override
	public void cadastrar(Inscricao inscricao) {
		inscricao.setId(nextId);
		inscricoes.add(inscricao);
		nextId++;
	}

	@Override
	public Inscricao recuperar(long idInscricao) {
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
	public List<Inscricao> listarInscricoesEvento(long idEvento) {
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
	public List<Inscricao> listarInscricoesParticipante(long idParticipante) {
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
