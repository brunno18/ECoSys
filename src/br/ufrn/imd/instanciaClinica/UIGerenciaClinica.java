package br.ufrn.imd.instanciaClinica;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;

import br.ufrn.imd.controller.EventoValidator;
import br.ufrn.imd.controller.GerenciadorEvento;
import br.ufrn.imd.controller.RegraParticipacao;
import br.ufrn.imd.controller.ValidateEventoException;
import br.ufrn.imd.model.Evento;
import br.ufrn.imd.util.service.PublicationService;
import br.ufrn.imd.view.GerenciadorEventoGUI;

public class UIGerenciaClinica implements GerenciadorEventoGUI{
	RegraParticipacao regra;
	EventoValidator validator;
	PublicationService service;
	
	private Scanner input = new Scanner(System.in); 
	private GerenciadorEvento gerenciadorEvento = new GerenciadorEvento(regra, validator, service);
	
	@Override
	public void criarEvento() throws ParseException {
		try{
			System.out.println("---Realizar Consulta---");
			System.out.print("ID: ");
			int id = input.nextInt();
			System.out.println("");
			
			System.out.print("Titulo: ");
			String titulo = input.nextLine();
			System.out.println("");
			
			System.out.print("Descricao: ");
			String descricao = input.nextLine();
			System.out.println("");
			
			DateFormat formataData = DateFormat.getInstance();
			System.out.print("Data (dd/MM/yyyy): ");
			String dataString = input.nextLine();
			System.out.println("");
			Date data = formataData.parse(dataString);
			
			Evento evento = new Evento();
			evento.setId(id);
			evento.setTitulo(titulo);
			evento.setDescricao(descricao);
			evento.setDataInicio(data);
			
			gerenciadorEvento.criarEvento(evento);
		}
		catch(ValidateEventoException e){
			System.out.println("ERRO : " + e.getMessage());
		}
	
	
	}

	@Override
	public void atualizarEvento() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelarEvento() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void publicarEvento() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void gerarRelatorioCustoEvento() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notificarEventosProximos() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inscreverParticipante() {
		// TODO Auto-generated method stub
		
	}
	
}
