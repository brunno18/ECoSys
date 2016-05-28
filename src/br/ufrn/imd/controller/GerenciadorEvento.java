package br.ufrn.imd.controller;

import java.util.*;
import br.ufrn.imd.dao.*;
import br.ufrn.imd.instanciaClinica.Medico;
import br.ufrn.imd.instanciaClinica.Paciente;
import br.ufrn.imd.model.*;
import br.ufrn.imd.util.service.*;


public class GerenciadorEvento {
	
	private NotificadorEvento notificadorEvento;
	
	private IDAOEvento daoEvento;
	
	private IDAOInscricao daoInscricao;
	
	//private IDAOParticipante daoParticipante;
	
	private RegraParticipacao regraParticipacao;
	
	private RegraParticipacao regraParticipacao2;
	
	private EventoValidator validatorEvento;
	
	private PublicationService publicationService;
	
	
	public GerenciadorEvento (RegraParticipacao regraParticipacao, RegraParticipacao regraparticipacao2, EventoValidator validatorEvento, PublicationService publicationService, NotificadorEvento notificadorEvento) {
		this.daoEvento = DAOEventoMemory.getInstancia();
		this.daoInscricao = DAOInscricaoMemory.getInstancia();
		//this.daoParticipante = DAOParticipanteMemory.getInstancia();
		this.regraParticipacao = regraParticipacao;
		this.regraParticipacao2 = regraparticipacao2;
		this.validatorEvento = validatorEvento;
		this.publicationService = publicationService;
		this.notificadorEvento = notificadorEvento;
	}
	
	public void criarEvento(Evento evento) throws ValidateEventoException {
		validatorEvento.validar(evento);
		daoEvento.cadastrar(evento);
		evento.setStatus(StatusEvento.PENDENTE);
	}
	
	public void notificarEventosProximos(int dias){
		Calendar today = Calendar.getInstance();
		List<Evento> eventos = daoEvento.listar();
		//today.getTime();
		
		for(Evento evento: eventos){
			int diff = evento.getDataInicio().compareTo(today);
			//sys
			int diffInDays = (int)(diff / (1000 * 60 * 60 * 24));
			
			if(diffInDays >= 0 && diffInDays <=  dias){
				notificadorEvento.notificarProximidade(evento);
			}	
		}
	}
	
	public void inscreverParticipante(Evento evento, Participante participante) throws ValidatePartipationException {
		if(participante instanceof Paciente)
			regraParticipacao.validarParticipacao(participante, evento);
		else if(participante instanceof Medico)
			regraParticipacao2.validarParticipacao(participante, evento);
		
		Inscricao inscricao = new Inscricao(evento, participante);
		
		daoInscricao.cadastrar(inscricao);
		
		if(participante instanceof Paciente) 
			notificadorEvento.notificarInscricao(evento, participante);
	
	}
	
	public void publicarEvento(Publicacao publicacao) throws PublicationException {
		publicationService.publicarEvento(publicacao);
	}
	
	public void atualizarEvento(Evento evento) {
		daoEvento.atualizar(evento);
		
		notificadorEvento.notificarMudanca(evento);
		evento.setStatus(StatusEvento.PENDENTE);
	}
	
	public void cancelarEvento(int idEvento) {
		Evento evento = daoEvento.recuperar(idEvento);
		evento.setStatus(StatusEvento.CANCELADO);
		daoEvento.atualizar(evento);
		
		notificadorEvento.notificarCancelamento(evento);
	}
	
	public Evento getEvento(int idEvento){
		Evento evento = new Evento();
		evento = daoEvento.recuperar(idEvento);
		
		return evento;
	}
	
}
