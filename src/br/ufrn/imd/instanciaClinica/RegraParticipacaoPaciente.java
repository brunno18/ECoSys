package br.ufrn.imd.instanciaClinica;

import java.text.SimpleDateFormat;
import java.util.*;
import br.ufrn.imd.controller.*;
import br.ufrn.imd.dao.*;
import br.ufrn.imd.model.*;

public class RegraParticipacaoPaciente implements RegraParticipacao {
	
	public static final int NUMERO_MAXIMO_CONSULTAS_PENDENTES_PADRAO = 3;
	
	public static final int NUMERO_MAXIMO_CONSULTAS_DIA_PADRAO = 1;
	
	private IDAOInscricao daoInscricao;
	
	private int numeroMaximoConsultasPendentes;
	
	private int numeroMaximoConsultasDia;
	
	
	public RegraParticipacaoPaciente(IDAOInscricao daoInscricao) {
		this.daoInscricao = daoInscricao;
		this.numeroMaximoConsultasPendentes = NUMERO_MAXIMO_CONSULTAS_PENDENTES_PADRAO;
		this.numeroMaximoConsultasDia = NUMERO_MAXIMO_CONSULTAS_DIA_PADRAO;
	}
	
	public void setNumeroMaximoConsultaDia(int num) {
		numeroMaximoConsultasDia = num;
	}
	
	public int getNumeroMaximoConsultaDia() {
		return numeroMaximoConsultasDia;
	}
	
	public void setNumeroMaximoConsultasPendentes(int numeroMaximoConsultasPendentes) {
		this.numeroMaximoConsultasPendentes = numeroMaximoConsultasPendentes;
	}
	
	public int getNumeroMaximoConsultasPendentes() {
		return numeroMaximoConsultasPendentes;
	}

	@Override
	public void validarParticipacao(Participante paciente, Evento consulta) throws ValidatePartipationException {
		List<Inscricao> inscricoes = daoInscricao.listarInscricoesParticipante(paciente.getId());
		
		verificarConsultasAtivas(paciente, inscricoes);
		verificarConsultasNoDia(paciente, inscricoes, consulta);
		verificarConvenio(paciente);
	}
	
	private void verificarConsultasAtivas(Participante paciente, List<Inscricao> agendamentos) throws ValidatePartipationException {
		int numeroConsultasPendentes = 0;
		for(Inscricao inscricao : agendamentos) {
			if (inscricao.getEvento().getStatus() == StatusEvento.PENDENTE) {
				numeroConsultasPendentes++;
			}
		}
		
		if (numeroConsultasPendentes >= 3) {
			throw new ValidatePartipationException("O paciente " + paciente.getNome() + " possui " + numeroConsultasPendentes + " consultas agendadas. " + " O número máximo de agendamentos por vez por paciente é " +  numeroMaximoConsultasPendentes);
		}
	}

	private void verificarConsultasNoDia(Participante paciente, List<Inscricao> agendamentos, Evento consulta) throws ValidatePartipationException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String dataEventoAtual = simpleDateFormat.format(consulta.getDataInicio().getTime());
		
		int numeroConsultas = 0;
		for(Inscricao inscricao : agendamentos) {
			Calendar dataEvento = inscricao.getEvento().getDataInicio();
			if (dataEventoAtual.equals(simpleDateFormat.format(dataEvento)) && inscricao.getEvento().getStatus() == StatusEvento.PENDENTE) {
				numeroConsultas++;
			}
		}
		
		if (numeroConsultas >= numeroMaximoConsultasDia) {
			throw new ValidatePartipationException("Já há " + numeroConsultas + " consultas agendadas para o dia " + dataEventoAtual + ". O número máximo de consultas permitidas por dia para um paciente é " + numeroMaximoConsultasDia);
		}
	}
	
	private void verificarConvenio(Participante paciente) throws ValidatePartipationException {
		Paciente p = (Paciente) paciente;
		if (!p.isConvenioEmDia()) {
			throw new ValidatePartipationException("O convênio " + p.getNumeroPlanoSaude() + " do paciente " + p.getNome() +  " não está em dia.");
		}
	}
}
