package clinica;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.ufrn.imd.controller.RegraParticipacao;
import br.ufrn.imd.dao.IDAOInscricao;
import br.ufrn.imd.model.Evento;
import br.ufrn.imd.model.Inscricao;
import br.ufrn.imd.model.Participante;
import br.ufrn.imd.model.StatusEvento;
import br.ufrn.imd.model.ValidatePartipationException;

public class RegraParticipacaoMedico implements RegraParticipacao {
	
	public static final int NUMERO_MAXIMO_CONSULTAS_DIA_PADRAO = 8;
	
	private IDAOInscricao daoInscricao;
	
	private int numeroMaximoConsultasDia;
	
	public RegraParticipacaoMedico(IDAOInscricao daoInscricao) {
		this.daoInscricao = daoInscricao;
		this.numeroMaximoConsultasDia = NUMERO_MAXIMO_CONSULTAS_DIA_PADRAO;
	}
	
	public void setNumeroMaximoConsultaDia(int num) {
		numeroMaximoConsultasDia = num;
	}
	
	public int getNumeroMaximoConsultaDia() {
		return numeroMaximoConsultasDia;
	}

	@Override
	public void validarParticipacao(Participante medico, Evento consulta) throws ValidatePartipationException {		
		List<Inscricao> inscricoes = daoInscricao.listarInscricoesParticipante(medico.getId(), TipoInscricao.MEDICO);
		
		verificarConsultasNoDia(medico, inscricoes, consulta);
	}
	
	private void verificarConsultasNoDia(Participante medico, List<Inscricao> agendamentos, Evento consulta) throws ValidatePartipationException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String dataEventoAtual = simpleDateFormat.format(consulta.getDataInicio().getTime());
		
		int numeroConsultas = 0;
		for(Inscricao inscricao : agendamentos) {
			Calendar dataEvento = inscricao.getEvento().getDataInicio();
			if (dataEventoAtual.equals(simpleDateFormat.format(dataEvento.getTime())) && inscricao.getEvento().getStatus() == StatusEvento.PENDENTE) {
				numeroConsultas++;
			}
		}
		
		if (numeroConsultas >= numeroMaximoConsultasDia) {
			throw new ValidatePartipationException("Já há " + numeroConsultas + " consultas agendadas para o dia " + dataEventoAtual + ". O número máximo de consultas permitidas por dia para um médico é " + numeroMaximoConsultasDia);
		}
	}

}
