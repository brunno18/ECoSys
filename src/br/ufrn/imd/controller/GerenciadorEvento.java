package br.ufrn.imd.controller;

import java.util.*;
//import java.util.List;
import br.ufrn.imd.model.*;
//import br.ufrn.imd.model.Publicacao;
//import br.ufrn.imd.util.service.PublicationService;
//import br.ufrn.imd.view.GerenciadorEventoGUI;

public class GerenciadorEvento{
	private Publicacao publicacao;
	
	public Publicacao criarEvento(Local local, Participante participante, String descricao, String title){
		//Evento evento;
		Evento evento = new Evento();
		
		evento.setLocal(local);
		evento.setParticipante(participante);
		evento.setDescricao(descricao);
		evento.setTitulo(title);
		
		publicacao.setEvento(evento);
		
		return publicacao;
		//return evento;
	}
	
}
