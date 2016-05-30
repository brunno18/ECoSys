package clinica;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import br.ufrn.imd.dao.DAOException;
import br.ufrn.imd.dao.IDAOParticipante;
import br.ufrn.imd.model.Participante;

public class DAOMedicoMemory implements IDAOParticipante{
	
	private static long nextId = 0;
	
	static DAOMedicoMemory daoMedicoMemory = null;
	
	private Set<Participante> medicos;
	
	private DAOMedicoMemory() {
		medicos = new HashSet<Participante>();
	}
	
	public static DAOMedicoMemory getInstancia() {
		if(daoMedicoMemory == null){
			daoMedicoMemory = new DAOMedicoMemory();
		}	
		return daoMedicoMemory;
	}
	
	@Override
	public void cadastrar(Participante medico) throws DAOException {
		medico.setId(nextId);
		medicos.add(medico);
		nextId++;
	}

	@Override
	public List<Participante> listarParticipantes() {
		List<Participante> resultList = new ArrayList<Participante>();
		Iterator<Participante> iter = medicos.iterator();
		
		while(iter.hasNext()) {
			resultList.add(iter.next());
		}
		
		return resultList;
	}

	@Override
	public Participante recuperar(long idMedico) {
		Iterator<Participante> iter = medicos.iterator();
		
		while(iter.hasNext()) {
			Participante part = iter.next();
			
			if(part.getId() == idMedico) {
				return part;
			}
		}
		
		return null;
	}
}
