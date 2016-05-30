package clinica;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import br.ufrn.imd.dao.DAOException;
import br.ufrn.imd.dao.IDAOLocal;
import br.ufrn.imd.model.Local;

public class DAOLocalMemory implements IDAOLocal {
	
	private static long nextId = 0;
	
	static DAOLocalMemory daoLocalMemory = null;
	
	private Set<Local> locais;
	
	private DAOLocalMemory() {
		locais = new HashSet<Local>();
	}
	
	public static DAOLocalMemory getInstancia() {
		if(daoLocalMemory == null){
			daoLocalMemory = new DAOLocalMemory();
		}	
		return daoLocalMemory;
	}

	@Override
	public void cadastrar(Local consultorio) throws DAOException {
		consultorio.setId(nextId);
		locais.add(consultorio);
		nextId++;
	}

	@Override
	public Local recuperar(long idConsultorio) {
		Iterator<Local> iter = locais.iterator();
		
		while(iter.hasNext()) {
			Local local = iter.next();
			
			if(local.getId() == idConsultorio) {
				return local;
			}
		}
		
		return null;
	}

	@Override
	public List<Local> listar() {
		List<Local> resultList = new ArrayList<Local>();
		Iterator<Local> iter = locais.iterator();
		
		while(iter.hasNext()) {
			resultList.add(iter.next());
		}
		
		return resultList;
	}

	@Override
	public void atualizar(Local local) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remover(long idLocal) {
		// TODO Auto-generated method stub
		
	}

}
