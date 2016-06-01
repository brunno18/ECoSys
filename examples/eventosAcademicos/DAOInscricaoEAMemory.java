package eventosAcademicos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import br.ufrn.imd.dao.IDAOInscricao;
import br.ufrn.imd.model.Inscricao;

public class DAOInscricaoEAMemory implements IDAOInscricao{

	private static long nextId = 0;
	
	static DAOInscricaoEAMemory daoInscricaoEAMemory = null;
	
	private Set<Inscricao> inscricoesEA;
	
	private DAOInscricaoEAMemory() {
		inscricoesEA = new HashSet<Inscricao>();
	}
	
	public static DAOInscricaoEAMemory getInstancia() {
		if(daoInscricaoEAMemory == null){
			daoInscricaoEAMemory = new DAOInscricaoEAMemory();
		}	
		return daoInscricaoEAMemory;
	}
	
	@Override
	public void cadastrar(Inscricao inscricao) {
		inscricao.setId(nextId);
		inscricoesEA.add(inscricao);
		nextId++;
	}

	@Override
	public Inscricao recuperar(long idInscricaoEA) {
		Iterator<Inscricao> iter = inscricoesEA.iterator();
		while(iter.hasNext()) {
			Inscricao inscri = iter.next();
			
			if(inscri.getId() == idInscricaoEA) {
				return inscri;
			}
		}
		return null;
	}

	@Override
	public List<Inscricao> listarInscricoesEvento(long idEventoEA) {
		List<Inscricao> resultList = new ArrayList<Inscricao>();
		Iterator<Inscricao> iter = inscricoesEA.iterator();
		
		while(iter.hasNext()) {
			Inscricao insc = iter.next();
			if(insc.getEvento().getId() == idEventoEA) {
				resultList.add(insc);
			}
		}
		
		return resultList;
	}

	@Override
	public List<Inscricao> listarInscricoesParticipante(long idParticipanteEA) {
		List<Inscricao> resultList = new ArrayList<Inscricao>();
		Iterator<Inscricao> iter = inscricoesEA.iterator();
		
		while(iter.hasNext()) {
			Inscricao insc = iter.next();
			if(insc.getParticipante().getId() == idParticipanteEA) {
				resultList.add(insc);
			}
		}
		
		return resultList;
	}
	
}
