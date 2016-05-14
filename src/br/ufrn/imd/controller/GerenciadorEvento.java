package br.ufrn.imd.controller;

import java.util.*;
//import java.util.List;
import br.ufrn.imd.model.*;
//import br.ufrn.imd.model.Publicacao;
//import br.ufrn.imd.util.service.PublicationService;
//import br.ufrn.imd.view.GerenciadorEventoGUI;

public class GerenciadorEvento {
	
	List<Evento> eventos = new ArrayList<Evento>();
	
	NotificadorEvento notificador;
	
	public void criarEvento(Local local, List<Participante> participantes, String descricao, String title, Date data){
		//Evento evento;
		Evento evento = new Evento();
		
		evento.setLocal(local);
		evento.setParticipantes(participantes);
		evento.setDescricao(descricao);
		evento.setTitulo(title);
		evento.setDataEvento(data);
		
		eventos.add(evento);
		//return evento;
	}
	
	public void notificarEventosProximos(Date dias){
		for(Evento evento: eventos){
			if(evento.getDataEvento() != dias){
				notificador.notificarProximidade(evento);
			}	
		}
	}
}
