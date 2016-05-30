package clinica;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import br.ufrn.imd.dao.IDAOEvento;
import br.ufrn.imd.model.Evento;
import br.ufrn.imd.model.StatusEvento;

public class DAOConsultaMemory implements IDAOEvento {
	
	private static long nextId = 0;
	
	static DAOConsultaMemory daoConsultaMemory = null;
	
	private Set<Evento> consultas;
	
	private DAOConsultaMemory() {
		consultas = new HashSet<Evento>();
	}
	
	public static DAOConsultaMemory getInstancia() {
		if(daoConsultaMemory == null){
			daoConsultaMemory = new DAOConsultaMemory();
		}	
		return daoConsultaMemory;
	}

	@Override
	public void cadastrar(Evento consulta) {
		consulta.setId(nextId);
		consultas.add(consulta);
		nextId++;
	}

	@Override
	public Evento recuperar(int idConsuta) {
		Iterator<Evento> iter = consultas.iterator();
		
		while(iter.hasNext()) {
			Evento evento = iter.next();
			
			if(evento.getId() == idConsuta) {
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
		Iterator<Evento> iter = consultas.iterator();
		
		while(iter.hasNext()) {
			Evento evento = iter.next();
			resultList.add(evento);
		}
		
		return resultList;
	}

	@Override
	public List<Evento> listarByStatus(StatusEvento statusEvento) {
		List<Evento> resultList = new ArrayList<Evento>();
		Iterator<Evento> iter = consultas.iterator();
		
		while(iter.hasNext()) {
			Evento evento = iter.next();
			if (evento.getStatus() == statusEvento) {
				resultList.add(evento);
			}
		}
		
		return resultList;
	}

}
