package br.ufrn.imd.instanciaClinica;
import java.text.*;
import java.util.*;

import br.ufrn.imd.model.*;
import br.ufrn.imd.controller.*;
import br.ufrn.imd.util.service.PublicationService;
import br.ufrn.imd.view.GerenciadorEventoGUI;

public class UIGerenciaClinica implements GerenciadorEventoGUI{
	RegraClinica regra;
	EventoValidator validator;
	PublicationService service;
	
	private Scanner input = new Scanner(System.in); 
	private GerenciadorEvento gerenciadorEvento = new GerenciadorEvento(regra, validator, service);
	
	@Override
	public void criarEvento() throws ParseException{
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
			//publicarEvento(evento);
		}
		catch(ValidateEventoException e){
			System.out.println("ERRO : " + e.getMessage());
		}
	}

	@Override
	public void atualizarEvento() throws ParseException{
			System.out.println("---Atualizar Consulta---");
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
			
			gerenciadorEvento.atualizarEvento(evento);
	}

	@Override
	public void cancelarEvento() {
		System.out.println("---Cancelar Consulta---");
		System.out.print("ID: ");
		int id = input.nextInt();
		System.out.println("");
		
		gerenciadorEvento.cancelarEvento(id);	
	}

	@Override
	public void publicarEvento(){
		/* Publicacao nao disponivel na instancia Clinica */
	}

	@Override
	public void gerarRelatorioCustoEvento() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notificarEventosProximos() {
		System.out.println("---Notificar Proximas Consultas---");
		System.out.print("Numero de dias: ");
		int dias = input.nextInt(); 
		gerenciadorEvento.notificarEventosProximos(dias);
		
	}

	@Override
	public void inscreverParticipante(int idEvento) throws ValidatePartipationException {
		System.out.println("---Inscrever Paciente---");
		System.out.print("ID: ");
		int id = input.nextInt();
		System.out.println("");
		
		System.out.print("Nome: ");
		String nome = input.nextLine();
		System.out.println("");
		
		System.out.print("Idade: ");
		int idade = input.nextInt();
		System.out.println("");
		
		Participante participante = new Participante(id, nome, idade);
		participante.setId(id);
		participante.setNome(nome);
		participante.setIdade(idade);
		
		
		Evento evento = gerenciadorEvento.getEvento(idEvento);
		gerenciadorEvento.inscreverParticipante(evento, participante);
		
		System.out.print("CPF: ");
		String cpf = input.nextLine();
		System.out.println("");
		
		System.out.print("RG: ");
		String rg = input.nextLine();
		System.out.println("");
		
		System.out.print("Num. Plano de Saude: ");
		Long numPlano = input.nextLong();
		System.out.println("");
		
		Paciente paciente = new Paciente(id, nome, idade, cpf, rg, numPlano);
		
		//regra.getDiasParticipante(id);
		regra.validarParticipacao(participante, evento);
	
	}
	
}
