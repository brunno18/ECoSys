package clinica;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import br.ufrn.imd.dao.DAOException;
import br.ufrn.imd.dao.IDAOParticipante;
import br.ufrn.imd.model.Participante;

public class DAOPacienteMemory implements IDAOParticipante{
	
	private static long nextId = 0;
	
	static DAOPacienteMemory daoPacienteMemory = null;
	
	private Set<Participante> pacientes;
	
	private DAOPacienteMemory() {
		pacientes = new HashSet<Participante>();
	}
	
	public static DAOPacienteMemory getInstancia() {
		if(daoPacienteMemory == null){
			daoPacienteMemory = new DAOPacienteMemory();
		}	
		return daoPacienteMemory;
	}
	
	@Override
	public void cadastrar(Participante paciente) throws DAOException {
		paciente.setId(nextId);
		pacientes.add(paciente);
		nextId++;
	}

	@Override
	public List<Participante> listarParticipantes() {
		List<Participante> resultList = new ArrayList<Participante>();
		Iterator<Participante> iter = pacientes.iterator();
		
		while(iter.hasNext()) {
			resultList.add(iter.next());
		}
		
		return resultList;
	}

	@Override
	public Participante recuperar(long idPaciente) {
		Iterator<Participante> iter = pacientes.iterator();
		
		while(iter.hasNext()) {
			Participante part = iter.next();
			
			if(part.getId() == idPaciente) {
				return part;
			}
		}
		
		return null;
	}

}
