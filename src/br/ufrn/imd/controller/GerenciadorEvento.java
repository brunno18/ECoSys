package br.ufrn.imd.controller;

import java.text.SimpleDateFormat;
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
	
	private EventoValidator validatorEvento;
	
	private PublicationService publicationService;
	
	
	public GerenciadorEvento (EventoValidator validatorEvento, NotificadorEvento notificadorEvento, PublicationService publicationService, IDAOEvento daoEvento, IDAOInscricao daoInscricao) {
		this.validatorEvento = validatorEvento;
		this.notificadorEvento = notificadorEvento;
		this.publicationService = publicationService;
		this.daoEvento = daoEvento;
		this.daoInscricao = daoInscricao;
	}
	
	public void criarEvento(Evento evento) throws ValidateEventoException {
		validatorEvento.validar(evento);
		daoEvento.cadastrar(evento);
		evento.setStatus(StatusEvento.PENDENTE);
	}
	
	public List<Evento> listarEventos() {
		return daoEvento.listar();
	}
	
	public void notificarEventosProximos(int dias){
		Calendar today = Calendar.getInstance();
		List<Evento> eventos = daoEvento.listar();
		
		for(Evento evento: eventos){
			long difference =  (evento.getDataInicio().getTime().getTime() - today.getTime().getTime())/86400000;
			long diffInDays = Math.abs(difference);
			
			if(diffInDays >= 0 && diffInDays <=  dias){
				notificadorEvento.notificarProximidade(evento);
			}	
		}
	}
	
	public void inscreverParticipante(Evento evento, Participante participante, String tipoInscricao, RegraParticipacao regraParticipacao) throws ValidatePartipationException {
		regraParticipacao.validarParticipacao(participante, evento);
		
		Inscricao inscricao = new Inscricao(evento, participante, tipoInscricao);
		
		daoInscricao.cadastrar(inscricao);
		
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
	
	public List<Inscricao> listarInscricoesEvento(long idEvento) {
		return daoInscricao.listarInscricoesEvento(idEvento);
	}
	
	public List<Inscricao> listarInscricoesParticipante(long idParticipante, String tipoInscricao) {
		return daoInscricao.listarInscricoesParticipante(idParticipante, tipoInscricao);
	}
}
