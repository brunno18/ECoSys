package br.ufrn.imd.instanciaClinica;

import java.text.ParseException;
//import java.util.*;

import br.ufrn.imd.controller.GerenciadorEvento;
import br.ufrn.imd.dao.DAOEventoMemory;
import br.ufrn.imd.dao.IDAOEvento;
import br.ufrn.imd.model.ValidatePartipationException;

public class MainClinica {
	//private static Scanner input = new Scanner(System.in);
	
	private static UIGerenciaClinica manager = new UIGerenciaClinica();
	//public static DAOEventoMemory daoEvento = new DAOEventoMemory();
	
	public static void main(String [] args) throws ParseException, ValidatePartipationException{
		
		manager.criarEvento();
		
		//daoEvento.listar();
		//manager.
		//manager.atualizarEvento();
		//manager.cancelarEvento();
		manager.inscreverParticipante(1);
		//manager.notificarEventosProximos();
		
	}
}
