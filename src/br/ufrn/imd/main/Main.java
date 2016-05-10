package br.ufrn.imd.main;
import java.util.*;

import br.ufrn.imd.controller.*;
import br.ufrn.imd.model.*;

public class Main {
	
	
	
	@SuppressWarnings("resource")
	public static void main (String[] args) {
		
		Scanner in = new Scanner(System.in);
		GerenciadorEvento gerenciaEvento = new GerenciadorEvento();
		
		String titulo;
		String desc;
		String locale;
		String participantes;
		
		
		System.out.println("OO Framework to Event Control Systems");
	
		
		System.out.println("Criar evento ");
		
		System.out.print("titulo: ");
		titulo = in.nextLine();
		System.out.print("descricao: ");
		desc = in.nextLine();
		System.out.print("local: ");
		locale = in.nextLine();
		System.out.print("participante: ");
		participantes = in.nextLine();
		
		Evento evento = new Evento();
		//gerenciaEvento.criarEvento(locale, participantes, desc, titulo);
		
		
	}
}
