package br.ufrn.imd.instanciaClinica;

import java.text.SimpleDateFormat;

import br.ufrn.imd.model.*;

public class NotificacoesClinica implements FabricaNotificacao{
	
	
	@Override
	public Notificacao gerarNotificacaoProximidade(Evento evento) {
		Notificacao notification = new Notificacao();
		notification.setTitulo("Consulta Proxima \n");
		notification.setMensagem("Titulo: " + evento.getTitulo() + 
							  "/n Descricao: " + evento.getDescicao() + 
							  "/n Local: " + evento.getLocal().getNome() + ", " + evento.getLocal().getEndereco() +
							  "/n Data: " + new SimpleDateFormat("dd/MM/yyyy").format(evento.getDataInicio()) +
							  "/n");
		
		return notification;
	}

	@Override
	public Notificacao gerarNotificacaoMudanca(Evento evento) {
		Notificacao notification = new Notificacao();
		notification.setTitulo("Consulta Modificada \n");
		notification.setMensagem("Titulo: " + evento.getTitulo() + 
							  "/n Descricao: " + evento.getDescicao() + 
							  "/n Local: " + evento.getLocal().getNome() + ", " + evento.getLocal().getEndereco() +
							  "/n Data: " + new SimpleDateFormat("dd/MM/yyyy").format(evento.getDataInicio()) +
							  "/n");
		
		return notification;
	}

	@Override
	public Notificacao gerarNotificacaoCancelamento(Evento evento) {
		Notificacao notification = new Notificacao();
		notification.setTitulo("####### Consulta CANCELADA #######");
		notification.setMensagem("/n A seguinte consulta foi cancelada: " +
							  "/n Titulo: " + evento.getTitulo() + 
							  "/n Descricao: " + evento.getDescicao() + 
							  "/n Local: " + evento.getLocal().getNome() + ", " + evento.getLocal().getEndereco() +
							  "/n Data: " + new SimpleDateFormat("dd/MM/yyyy").format(evento.getDataInicio()) +
							  "/n");
		
		return notification;
	}

	@Override
	public Notificacao gerarNotificacaoConvite(Evento evento, Participante participante) {
		Notificacao notification = new Notificacao();
		notification.setTitulo("Notificação para nova Consulta");
		notification.setMensagem( "/n Olá " + participante.getNome() + ", voce está marcado para a seguinte consulta: " +
							  "/n Titulo: " + evento.getTitulo() + 
							  "/n Descricao: " + evento.getDescicao() + 
							  "/n Local: " + evento.getLocal().getNome() + ", " + evento.getLocal().getEndereco() +
							  "/n Data: " + new SimpleDateFormat("dd/MM/yyyy").format(evento.getDataInicio()) +
							  "/n");
		
		return notification;
	}

}
