package br.ufrn.imd.controller;

import java.util.*;
import br.ufrn.imd.model.*;

public class NotificadorEvento {
	private FabricaNotificacao noticefactory;
	
	public void notificarProximidade(Evento evento) {
		Notificacao notice = noticefactory.gerarNotificacaoProximidade(evento);
	}
	
	public void notificarMudanca() {
		
	}
	
	public void notificarCancelamento() {
		
	}
}
