package clinica;

import java.text.SimpleDateFormat;

import br.ufrn.imd.model.*;

public class NotificadorPacienteClinica implements FabricaNotificacao {
	
	
	@Override
	public Notificacao gerarNotificacaoProximidade(Evento evento) {
		Notificacao notification = new Notificacao();
		notification.setTitulo("\n ####### Notificação - Consulta Proxima #######");
		notification.setMensagem(" Titulo: " + evento.getTitulo() + 
							  "\n Descricao: " + evento.getDescicao() + 
							  "\n Local: " + evento.getLocal().getNome() + ", " + evento.getLocal().getEndereco() +
							  "\n Data: " + new SimpleDateFormat("dd/MM/yyyy").format(evento.getDataInicio().getTime()) +
							  "\n ####### FIM DA NOTIFICAÇÃO #######" +
							  "\n");
		
		return notification;
	}

	@Override
	public Notificacao gerarNotificacaoMudanca(Evento evento) {
		Notificacao notification = new Notificacao();
		notification.setTitulo("\n ####### Notificação - Consulta Modificada #######");
		notification.setMensagem("Titulo: " + evento.getTitulo() + 
							  "\n Descricao: " + evento.getDescicao() + 
							  "\n Local: " + evento.getLocal().getNome() + ", " + evento.getLocal().getEndereco() +
							  "\n Data: " + new SimpleDateFormat("dd/MM/yyyy").format(evento.getDataInicio().getTime()) +
							  "\n ####### FIM DA NOTIFICAÇÃO #######" +
							  "\n");
		
		return notification;
	}

	@Override
	public Notificacao gerarNotificacaoCancelamento(Evento evento) {
		Notificacao notification = new Notificacao();
		notification.setTitulo("\n####### Notificação - Consulta CANCELADA #######");
		notification.setMensagem("\n A seguinte consulta foi cancelada: " +
							  "\n Titulo: " + evento.getTitulo() + 
							  "\n Descricao: " + evento.getDescicao() + 
							  "\n Local: " + evento.getLocal().getNome() + ", " + evento.getLocal().getEndereco() +
							  "\n Data: " + new SimpleDateFormat("dd/MM/yyyy").format(evento.getDataInicio().getTime()) +
							  "\n ####### FIM DA NOTIFICAÇÃO #######" +
							  "\n");
		
		return notification;
	}

	@Override
	public Notificacao gerarNotificacaoConvite(Evento evento, Participante participante) {
		Notificacao notification = new Notificacao();
		notification.setTitulo("\n ####### Notificação - Consulta Agendada #######");
		notification.setMensagem( "\n Olá " + participante.getNome() + ", voce está marcado para a seguinte consulta: \n" +
							  "\n Titulo: " + evento.getTitulo() + 
							  "\n Descricao: " + evento.getDescicao() + 
							  "\n Local: " + evento.getLocal().getNome() + ", " + evento.getLocal().getEndereco() +
							  "\n Data e hora: " + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(evento.getDataInicio().getTime()) +
							  "\n ####### FIM DA NOTIFICAÇÃO #######" +
							  "\n");
		
		return notification;
	}

}
