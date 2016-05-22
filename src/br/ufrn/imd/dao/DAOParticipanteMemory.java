package br.ufrn.imd.dao;

import java.util.*;
import br.ufrn.imd.model.*;

public class DAOParticipanteMemory implements IDAOParticipante {
	static IDAOParticipante daoParticipante = null;
	private Set<Participante> participantes;
	
	
	public static IDAOParticipante getInstancia() {
		if(daoParticipante == null){
			daoParticipante = new DAOParticipanteMemory();
		}	
		return daoParticipante;
	}
		
	private DAOParticipanteMemory() {
		participantes = new HashSet<Participante>();
	}
	
	@Override
	public void cadastrar(Participante participante) {
		participantes.add(participante);
	}

	@Override
	public Participante recuperar(int idParticipante) {
		Iterator<Participante> iter = participantes.iterator();
		while(iter.hasNext()) {
			Participante part = iter.next();
			
			if(part.getId() == idParticipante) {
				return part;
			}
		}
		return null;
	}

	@Override
	public List<Participante> listarParticipantes() {
		List<Participante> resultList = new ArrayList<Participante>();
		Iterator<Participante> iter = participantes.iterator();
		
		while(iter.hasNext()) {
			resultList.add(iter.next());
		}
		return resultList;
	}
}
