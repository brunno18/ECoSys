package clinica;

import java.util.Calendar;

import br.ufrn.imd.dao.DAOException;
import br.ufrn.imd.dao.IDAOEvento;
import br.ufrn.imd.dao.IDAOInscricao;
import br.ufrn.imd.dao.IDAOLocal;
import br.ufrn.imd.dao.IDAOParticipante;
import br.ufrn.imd.model.Evento;
import br.ufrn.imd.model.Inscricao;
import br.ufrn.imd.model.Local;

public class Dados {
	
	public static void cadastrarConsultórios(IDAOLocal daoConsultorio) {
		Local consultoio1 = new Local("Sala A10", "Rua Santos do Dumont, Prédio Salamandra - Segundo Andar");
		Local consultoio2 = new Local("Sala A20", "Rua Santos do Dumont, Prédio Salamandra - Segundo Andar");
		Local consultoio3 = new Local("Sala A30", "Rua Santos do Dumont, Prédio Salamandra - Segundo Andar");
		
		try {
			daoConsultorio.cadastrar(consultoio1);
			daoConsultorio.cadastrar(consultoio2);
			daoConsultorio.cadastrar(consultoio3);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	public static void cadastrarPacientes(IDAOParticipante daoPaciente) {
		Paciente paciente1 = new Paciente("Rubens Viana Araújo", 22, "103.961.714-00", "2.595.811", "8432328845", 3658372549621501L, 1014L);
		Paciente paciente2 = new Paciente("Kauê Rocha Martins", 82, "953.307.474-49", "9.862.578", "8416398442", 3613048989754137L, 1015L);
		Paciente paciente3 = new Paciente("Brenda Oliveira Rodrigues", 57, "933.982.386-96", "2.354.734", "8432228399", 5732972121482467L, 1016L);
		
		try {
			daoPaciente.cadastrar(paciente1);
			daoPaciente.cadastrar(paciente2);
			daoPaciente.cadastrar(paciente3);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	public static void cadastrarMedicos(IDAOParticipante daoMedico) {
		Medico medico1 = new Medico("Nicole Araujo Almeida", "8455555555", "3149-RN", "Clinico Geral");
		Medico medico2 = new Medico("Thiago Santos Correia", "8494358734", "5469-RN", "Cardiologista");
		
		try {
			daoMedico.cadastrar(medico1);
			daoMedico.cadastrar(medico2);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	public static void agendarConsultas(IDAOEvento daoConsulta, IDAOInscricao daoInscricao, IDAOLocal daoConsultorio, IDAOParticipante daoPaciente, IDAOParticipante daoMedico) {
		
		Calendar dataInicio1 = Calendar.getInstance();
		Calendar dataInicio2 = Calendar.getInstance(); 
		
		dataInicio1.set(2016, Calendar.JULY, 15, 15, 00);
		dataInicio2.set(2016, Calendar.JULY, 15, 15, 40);
		
		Evento consulta1 = new Consulta(
				"Consulta - Dra. Nicole",
				"Consulta com a Dra. Nicole, para realização de exames gerais.",
				daoConsultorio.recuperar(0L),
				dataInicio1
			);
		daoConsulta.cadastrar(consulta1);
		
		Evento consulta2 = new Consulta(
				"Consulta - Dr. Thiago",
				"Consulta com a Dr. Thiago, para realização de exame cardiólogico.",
				daoConsultorio.recuperar(1L),
				dataInicio2
			);
		
		daoConsulta.cadastrar(consulta2);
		
		
		Inscricao inscricaoConsulta1Paciente = new Inscricao(daoConsulta.recuperar(0), daoPaciente.recuperar(0));
		daoInscricao.cadastrar(inscricaoConsulta1Paciente);
		Inscricao inscricaoConsulta1Medico = new Inscricao(daoConsulta.recuperar(0), daoMedico.recuperar(0));
		daoInscricao.cadastrar(inscricaoConsulta1Medico);
		
		Inscricao inscricaoConsulta2Paciente = new Inscricao(daoConsulta.recuperar(1), daoPaciente.recuperar(1));
		daoInscricao.cadastrar(inscricaoConsulta2Paciente);
		Inscricao inscricaoConsulta2Medico = new Inscricao(daoConsulta.recuperar(1), daoMedico.recuperar(1));
		daoInscricao.cadastrar(inscricaoConsulta2Medico);
	}
}
