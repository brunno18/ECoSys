package br.ufrn.imd.instanciaClinica;

import java.text.ParseException;
//import java.util.*;

import br.ufrn.imd.controller.GerenciadorEvento;
import br.ufrn.imd.dao.DAOEventoMemory;
import br.ufrn.imd.dao.IDAOEvento;
import br.ufrn.imd.model.ValidatePartipationException;

public class MainClinica {
	//private static Scanner input = new Scanner(System.in);
	
	private static UIGerenciaConsulta managerConsulta = new UIGerenciaConsulta();
	private static UIGerenciaParticipantesClinica managerParticipantes = new UIGerenciaParticipantesClinica();
	//public static DAOEventoMemory daoEvento = new DAOEventoMemory();
	
	public static void main(String [] args) throws ParseException, ValidatePartipationException{
		
		int i = 0;
		while(i <= 1){
			managerParticipantes.cadastrarParticipante();
			i++;
		}
		
		
		//managerParticipantes.cadastrarParticipante();
		
		managerConsulta.criarEvento();
		
		//daoEvento.listar();
		//manager.
		//manager.atualizarEvento();
		//manager.cancelarEvento();
		managerConsulta.inscreverParticipante(12);
		//manager.notificarEventosProximos();
		
	}
}
