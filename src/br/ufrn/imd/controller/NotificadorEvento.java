package br.ufrn.imd.controller;

import java.util.*;
import br.ufrn.imd.model.*;

public class NotificadorEvento {
	
	private FabricaNotificacao noticefactory;
	
	public void notificarProximidade(Evento evento) {
		Notificacao notice = noticefactory.gerarNotificacaoProximidade(evento);
		List<Participante> participantes = evento.getParticipantes();
		
		for(Participante participante : participantes) {
			notice.notificar(participante);
		}
	}
	
	public void notificarMudanca() {
		
	}
	
	public void notificarCancelamento() {
		
	}
}
