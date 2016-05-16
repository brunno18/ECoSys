package br.ufrn.imd.controller;

import java.util.*;

import br.ufrn.imd.dao.IDAOInscricao;
import br.ufrn.imd.model.*;
import br.ufrn.imd.util.service.NotificationService;

public class NotificadorEvento {
	
	private FabricaNotificacao noticefactory;
	
	private NotificationService notificationService;
	
	private IDAOInscricao daoInscricao;
	
	public void notificarProximidade(Evento evento) {
		Notificacao notice = noticefactory.gerarNotificacaoProximidade(evento);
		List<Inscricao> inscricoes = daoInscricao.listarInscricoesEvento(evento.getId());
		
		for(Inscricao inscricao : inscricoes) {
			notificationService.notificar(notice, inscricao.getParticipante());
		}
	}
	
	public void notificarMudanca() {
		
	}
	
	public void notificarCancelamento() {
		
	}
}
