package clinica;

import br.ufrn.imd.model.Notificacao;
import br.ufrn.imd.model.Participante;
import br.ufrn.imd.util.service.NotificationService;

public class SMSNotificationService extends NotificationService{

	@Override
	public void notificar(Notificacao notificacao, Participante paciente) {
		System.out.println(notificacao.getTitulo());
		System.out.println(notificacao.getMensagem());
	}

}

