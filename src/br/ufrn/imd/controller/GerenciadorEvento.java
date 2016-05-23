package br.ufrn.imd.controller;

import java.util.*;
import br.ufrn.imd.dao.*;
import br.ufrn.imd.model.*;
import br.ufrn.imd.util.service.*;


public class GerenciadorEvento {
	
	private NotificadorEvento notificadorEvento;
	
	private IDAOEvento daoEvento;
	
	private IDAOInscricao daoInscricao;
	
	//private IDAOParticipante daoParticipante;
	
	private RegraParticipacao regraParticipacao;
	
	private EventoValidator validatorEvento;
	
	private PublicationService publicationService;
	
	
	public GerenciadorEvento (RegraParticipacao regraParticipacao, EventoValidator validatorEvento, PublicationService publicationService) {
		this.daoEvento = DAOEventoMemory.getInstancia();
		this.daoInscricao = DAOInscricaoMemory.getInstancia();
		//this.daoParticipante = DAOParticipanteMemory.getInstancia();
		this.regraParticipacao = regraParticipacao;
		this.validatorEvento = validatorEvento;
		this.publicationService = publicationService;
	}
	
	public void criarEvento(Evento evento) throws ValidateEventoException {
		validatorEvento.validar(evento);
		daoEvento.cadastrar(evento);
	}
	
	public void notificarEventosProximos(int dias){
		Date today = new Date();
		List<Evento> eventos = daoEvento.listar();
		
		for(Evento evento: eventos){
			long diff = evento.getDataInicio().getTime() - today.getTime();
			int diffInDays = (int)(diff / (1000 * 60 * 60 * 24));
			
			if(diffInDays >= 0 && diffInDays <=  dias){
				notificadorEvento.notificarProximidade(evento);
			}	
		}
	}
	
	public void inscreverParticipante(Evento evento, Participante participante) throws ValidatePartipationException {
		regraParticipacao.validarParticipacao(participante, evento);
		
		Inscricao inscricao = new Inscricao(evento, participante);
		
		daoInscricao.cadastrar(inscricao);
		
		notificadorEvento.notificarInscricao(evento, participante);
	}
	
	public void publicarEvento(Publicacao publicacao) throws PublicationException {
		publicationService.publicarEvento(publicacao);
	}
	
	public void atualizarEvento(Evento evento) {
		daoEvento.atualizar(evento);
		
		notificadorEvento.notificarMudanca(evento);
	}
	
	public void cancelarEvento(int idEvento) {
		Evento evento = daoEvento.recuperar(idEvento);
		evento.setStatus(StatusEvento.CANCELADO);
		daoEvento.atualizar(evento);
		
		notificadorEvento.notificarCancelamento(evento);
	}
	
	public Evento getEvento(int idEvento){
		Evento evento = daoEvento.recuperar(idEvento);
		
		return evento;
	}
	
}
