package br.ufrn.imd.model;

public interface FabricaNotificacao {
	public Notificacao gerarNotificacaoProximidade(Evento evento);
	
	public Notificacao gerarNotificacaoMudanca(Evento evento);
	
	public Notificacao gerarNotificacaoCancelamento(Evento evento);
	
	public Notificacao gerarNotificacaoConvite(Evento evento, Participante participante);
}
