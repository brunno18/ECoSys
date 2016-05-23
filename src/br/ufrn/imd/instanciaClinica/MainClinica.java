package br.ufrn.imd.instanciaClinica;

import java.text.ParseException;
//import java.util.*;

import br.ufrn.imd.model.ValidatePartipationException;

public class MainClinica {
	//private static Scanner input = new Scanner(System.in);
	
	private static UIGerenciaClinica manager = new UIGerenciaClinica();
	
	public static void main(String [] args) throws ParseException, ValidatePartipationException{
		
		manager.criarEvento();
		//manager.atualizarEvento();
		//manager.cancelarEvento();
		manager.inscreverParticipante(1);
		//manager.notificarEventosProximos();
		
	}
}
