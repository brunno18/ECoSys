package br.ufrn.imd.util.service;

import br.ufrn.imd.model.Notificacao;
import br.ufrn.imd.model.Participante;

public abstract class NotificationService {
	public abstract void notificar(Notificacao notificacao, Participante participante);
}
