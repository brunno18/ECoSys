package eventosAcademicos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import br.ufrn.imd.dao.IDAOEvento;
import br.ufrn.imd.model.Evento;
import br.ufrn.imd.model.StatusEvento;

public class DAOEventoAMemory implements IDAOEvento{

	private static long nextId = 0;
	
	static DAOEventoAMemory daoEventoAMemory = null;
	
	private Set<Evento> eventosA;
	
	private DAOEventoAMemory() {
		eventosA = new HashSet<Evento>();
	}
	
	public static DAOEventoAMemory getInstancia() {
		if(daoEventoAMemory == null){
			daoEventoAMemory = new DAOEventoAMemory();
		}	
		return daoEventoAMemory;
	}
	
	@Override
	public void cadastrar(Evento eventoA) {
		eventoA.setId(nextId);
		eventosA.add(eventoA);
		nextId++;
	}

	@Override
	public Evento recuperar(int idEventoA) {
		Iterator<Evento> iter = eventosA.iterator();
		
		while(iter.hasNext()) {
			Evento evento = iter.next();
			
			if(evento.getId() == idEventoA) {
				return evento;
			}
		}
		return null;
	}

	@Override
	public void atualizar(Evento evento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Evento> listar() {
		List<Evento> resultList = new ArrayList<Evento>();
		Iterator<Evento> iter = eventosA.iterator();
		
		while(iter.hasNext()) {
			Evento evento = iter.next();
			resultList.add(evento);
		}
		
		return resultList;
	}

	@Override
	public List<Evento> listarByStatus(StatusEvento statusEvento) {
		List<Evento> resultList = new ArrayList<Evento>();
		Iterator<Evento> iter = eventosA.iterator();
		
		while(iter.hasNext()) {
			Evento evento = iter.next();
			if (evento.getStatus() == statusEvento) {
				resultList.add(evento);
			}
		}
		
		return resultList;
	}
}
