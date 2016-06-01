package br.ufrn.imd.controller;

import java.util.*;

import br.ufrn.imd.dao.IDAOInscricao;
import br.ufrn.imd.model.*;
import br.ufrn.imd.util.service.NotificationService;

public class NotificadorEvento {
	
	private FabricaNotificacao noticefactory;
	
	private NotificationService notificationService;
	
	private IDAOInscricao daoInscricao;
	
	
	public NotificadorEvento(FabricaNotificacao noticefactory, NotificationService notificationService, IDAOInscricao daoInscricao) {
		this.noticefactory = noticefactory;
		this.notificationService = notificationService;
		this.daoInscricao = daoInscricao;
	}
	
	public void notificarProximidade(Evento evento) {
		Notificacao notice = noticefactory.gerarNotificacaoProximidade(evento);
		List<Inscricao> inscricoes = daoInscricao.listarInscricoesEvento(evento.getId());
		
		for(Inscricao inscricao : inscricoes) {
			notificationService.notificar(notice, inscricao.getParticipante());
		}
	}
	
	public void notificarMudanca(Evento evento) {
		Notificacao notice = noticefactory.gerarNotificacaoMudanca(evento);
		List<Inscricao> inscricoes = daoInscricao.listarInscricoesEvento(evento.getId());
		
		for(Inscricao inscricao : inscricoes) {
			notificationService.notificar(notice, inscricao.getParticipante());
		}
	}
	
	public void notificarCancelamento(Evento evento) {
		Notificacao notice = noticefactory.gerarNotificacaoCancelamento(evento);
		List<Inscricao> inscricoes = daoInscricao.listarInscricoesEvento(evento.getId());
		
		for(Inscricao inscricao : inscricoes) {
			notificationService.notificar(notice, inscricao.getParticipante());
		}
	}
	
	public void notificarInscricao(Evento evento, Participante participante) {
		Notificacao notice = noticefactory.gerarNotificacaoConvite(evento, participante);
		
		notificationService.notificar(notice, participante);
	}
}
