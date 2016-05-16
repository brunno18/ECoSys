package br.ufrn.imd.controller;

import java.util.*;

import br.ufrn.imd.dao.IDAOEvento;
import br.ufrn.imd.dao.IDAOInscricao;
import br.ufrn.imd.dao.IDAOParticipante;
//import java.util.List;
import br.ufrn.imd.model.*;
//import br.ufrn.imd.model.Publicacao;
//import br.ufrn.imd.util.service.PublicationService;
//import br.ufrn.imd.view.GerenciadorEventoGUI;

public class GerenciadorEvento {
	
	List<Evento> eventos = new ArrayList<Evento>();
	
	NotificadorEvento notificador;
	
	private IDAOEvento daoEvento;
	
	private IDAOInscricao daoInscricao;
	
	private IDAOParticipante daoParticipante;
	
	private RegraParticipacao regraParticipacao;
	
	private EventoValidator validatorEvento;
	
	public void criarEvento(Evento evento) throws ValidateEventoException{
		validatorEvento.validar(evento);
		eventos.add(evento);
	}
	
	public void notificarEventosProximos(int dias){
		Date today = new Date();
		
		for(Evento evento: eventos){
			long diff = evento.getDataInicio().getTime() - today.getTime();
			int diffInDays = (int)(diff / (1000 * 60 * 60 * 24));
			
			if(diffInDays >= 0 && diffInDays <=  dias){
				notificador.notificarProximidade(evento);
			}	
		}
	}
	
	public void inscreverParticipante(Evento evento, Participante participante) throws ValidatePartipationException {
		regraParticipacao.validarParticipacao(participante);
		
		Inscricao inscricao = new Inscricao(evento, participante);
		
		daoInscricao.cadastrar(inscricao);
	}
}
