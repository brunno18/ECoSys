package br.ufrn.imd.controller;

import java.util.*;
import br.ufrn.imd.model.*;
import br.ufrn.imd.util.service.NotificationService;

public class NotificadorEvento {
	
	private FabricaNotificacao noticefactory;
	
	private NotificationService notificationService;
	
	public void notificarProximidade(Evento evento) {
		Notificacao notice = noticefactory.gerarNotificacaoProximidade(evento);
		List<Participante> participantes = evento.getParticipantes();
		
		for(Participante participante : participantes) {
			notificationService.notificar(notice, participante);
		}
	}
	
	public void notificarMudanca() {
		
	}
	
	public void notificarCancelamento() {
		
	}
}
