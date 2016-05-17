package br.ufrn.imd.dao;
import java.util.*;
import br.ufrn.imd.model.*;

public class DAOEventoMemory implements IDAOEvento {

	static IDAOEvento daoEvento = null;
	private Set<Evento> eventos;
	 
	public static IDAOEvento getInstancia() {
		if(daoEvento == null){
			daoEvento = new DAOEventoMemory();
		}	
		return daoEvento;
	}
		
	private DAOEventoMemory() {
		eventos = new HashSet<Evento>();
	}
	 
	@Override
	public void cadastrar(Evento evento) {
		eventos.add(evento);
		
	}
	
	@Override
	public Evento recuperar(int idEvento) {
		Iterator<Evento> iter = eventos.iterator();
		while(iter.hasNext()) {
			Evento even = iter.next();
			
			if(even.getId() == idEvento) {
				return even;
			}
		}
		return null;
	}
	
	@Override
	public List<Evento> listar() {
		List<Evento> resultList = new ArrayList<Evento>();
		Iterator<Evento> iter = eventos.iterator();
		
		while(iter.hasNext()) {
			resultList.add(iter.next());
		}
		return resultList;
	}
	
}
