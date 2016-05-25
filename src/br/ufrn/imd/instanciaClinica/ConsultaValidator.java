package br.ufrn.imd.instanciaClinica;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.ufrn.imd.controller.EventoValidator;
import br.ufrn.imd.controller.ValidateEventoException;
import br.ufrn.imd.dao.IDAOEvento;
import br.ufrn.imd.dao.IDAOInscricao;
import br.ufrn.imd.model.Evento;
import br.ufrn.imd.model.StatusEvento;

public class ConsultaValidator extends EventoValidator {
	
	public static final int HORARIO_MINIMA_PADRAO = 7;
	
	public static final int HORARIO_MAXIMA_PADRAO = 22;
	
	private IDAOEvento daoEvento;
	
	private int horarioMinimo;
	
	private int horarioMaximo;
	
	public ConsultaValidator(IDAOEvento daoConsulta) {
		this.daoEvento = daoConsulta;
		horarioMinimo = HORARIO_MINIMA_PADRAO;
		horarioMaximo = HORARIO_MAXIMA_PADRAO;
	}

	@Override
	public void customValidation(Evento consulta) throws ValidateEventoException {
		List<Evento> consultas = daoEvento.listarByStatus(StatusEvento.PEDENTE);
		
		verificarHorarioConsulta(consulta);
		verificarOutraConsultaMesmoHorario();
	}

	private void verificarHorarioConsulta(Evento consulta) throws ValidateEventoException {
		Calendar calendar = Calendar.getInstance();
		Date dataInicio = consulta.getDataInicio();
		Date dataFim = consulta.getDataFim();
		
		calendar.setTime(dataInicio);  
		int horaInicio = calendar.get(Calendar.HOUR_OF_DAY);
		calendar.setTime(dataFim);
		int horaFim = calendar.get(Calendar.HOUR_OF_DAY);
		
		if (horaInicio < horarioMinimo || horaInicio > horarioMaximo) {
			throw new ValidateEventoException("Um consulta deve ser agendada entre " + horarioMinimo + "h e " + horarioMaximo + "h");
		}
		
		if (horaFim < horarioMinimo || horaFim > horarioMaximo) {
			throw new ValidateEventoException("Um consulta deve ser agendada entre " + horarioMinimo + "h e " + horarioMaximo + "h");
		}
	}
	
	private void verificarOutraConsultaMesmoHorario() {
		/**
		 * Talvez seja melhor fazer essa validação no framework
		 * Tipo, não pode haver duas consultas no mesmo horário e local.
		 * Ainda tem que verificar se os participantes(médico e paciente) já estão em consultas no horário. 
		 */
	}

}
