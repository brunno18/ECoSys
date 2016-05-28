package br.ufrn.imd.instanciaClinica;
import java.text.*;
import java.util.*;

import br.ufrn.imd.model.*;
import br.ufrn.imd.controller.*;
import br.ufrn.imd.dao.*;
import br.ufrn.imd.util.service.PublicationService;
import br.ufrn.imd.view.GerenciadorEventoGUI;

public class UIGerenciaConsulta implements GerenciadorEventoGUI{
	RegraParticipacaoPaciente regraP;
	RegraParticipacaoMedico regraM;
	EventoValidator validator;
	PublicationService service;
	IDAOInscricao daoInscricao;
	IDAOEvento daoEvento;
	IDAOParticipante daoParticipante;
	UIGerenciaParticipantesClinica managerPart;
	NotificacoesClinica notificadorFac;
	SMSNotificationService notificadorServ;
	NotificadorEvento notificador;
	
	
	
	private Scanner input; 
	private GerenciadorEvento gerenciadorEvento;
	private GerenciadorParticipante gerenciadorParticipante;
	
	public UIGerenciaConsulta(){
		daoInscricao = DAOInscricaoMemory.getInstancia();
		daoEvento = DAOEventoMemory.getInstancia();
		//daoParticipante = DAOParticipanteMemory.getInstancia();
		regraP = new RegraParticipacaoPaciente(daoInscricao);
		regraM = new RegraParticipacaoMedico(daoInscricao);
		validator = new ConsultaValidator(daoEvento);
		notificadorFac = new NotificacoesClinica();
		notificadorServ = new SMSNotificationService();
		notificador = new NotificadorEvento(notificadorFac, notificadorServ, daoInscricao);
		gerenciadorEvento = new GerenciadorEvento(regraP, regraM, validator, null, notificador);
		gerenciadorParticipante = new GerenciadorParticipante();
		input = new Scanner(System.in);
		
	}
	
	@Override
	public void criarEvento() throws ParseException{
		try{
			System.out.println("---Marcar Consulta---");
			
			System.out.print("ID: ");
			int id = input.nextInt();
			
			input.nextLine();
			System.out.print("Titulo: ");
			String titulo = input.nextLine();
			
			System.out.print("Descricao: ");
			String descricao = input.nextLine();
			
			Calendar c1 = Calendar.getInstance();
			Calendar c2 = Calendar.getInstance();
			
			System.out.print("Data (no formato dd/mm/aaaa): ");
			String data = input.nextLine();
			String dia = data.substring(0, 2);
			String mes = data.substring(3, 5);
			String ano = data.substring(6);
			
			System.out.print("\nHora (hh:mm): ");
			String horaCompleta = input.nextLine();
			String hora = horaCompleta.substring(0, 2);
			String minutos = horaCompleta.substring(3);
			
			System.out.print("\nLocal");
			System.out.print("\nNome: ");
			String nomeLocal = input.nextLine();
			System.out.print("\nEndereço: ");
			String enderecoLocal = input.nextLine();
			Local local = new Local();
			local.setNome(nomeLocal);
			local.setEndereco(enderecoLocal);
			local.setCapacidade(1);
			
			int diaInt = Integer.parseInt(dia);
			int mesInt = Integer.parseInt(mes) - 1;
			int anoInt = Integer.parseInt(ano);
			int horaInt = Integer.parseInt(hora);
			int minInt = Integer.parseInt(minutos);
			int minInt2 = minInt + 20;
			c1.set(anoInt, mesInt, diaInt, horaInt, minInt);
			c2.set(anoInt, mesInt, diaInt, horaInt, minInt2);
			
			Evento evento = new Evento();
			evento.setId(id);
			evento.setTitulo(titulo);
			evento.setDescricao(descricao);
			evento.setDataInicio(c1);
			evento.setDataFim(c2);
			evento.setLocal(local);
			
			gerenciadorEvento.criarEvento(evento);
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
			
			input.nextLine();
			System.out.print("Titulo: ");
			String titulo = input.nextLine();
			
			System.out.print("Descricao: ");
			String descricao = input.nextLine();
			
			Calendar c1 = Calendar.getInstance();
			Calendar c2 = Calendar.getInstance();
			
			System.out.print("Data (no formato dd/mm/aaaa): ");
			String data = input.nextLine();
			String dia = data.substring(0, 2);
			String mes = data.substring(3, 5);
			String ano = data.substring(6);
			
			System.out.print("\nHora (hh:mm): ");
			String horaCompleta = input.nextLine();
			String hora = horaCompleta.substring(0, 2);
			String minutos = horaCompleta.substring(3);
			
			System.out.print("\nLocal");
			System.out.print("\nNome: ");
			String nomeLocal = input.nextLine();
			System.out.print("\nEndereço: ");
			String enderecoLocal = input.nextLine();
			Local local = new Local();
			local.setNome(nomeLocal);
			local.setEndereco(enderecoLocal);
			local.setCapacidade(1);
			
			int diaInt = Integer.parseInt(dia);
			int mesInt = Integer.parseInt(mes) - 1;
			int anoInt = Integer.parseInt(ano);
			int horaInt = Integer.parseInt(hora);
			int minInt = Integer.parseInt(minutos);
			int minInt2 = minInt + 20;
			c1.set(anoInt, mesInt, diaInt, horaInt, minInt);
			c2.set(anoInt, mesInt, diaInt, horaInt, minInt2);
			
			Evento evento = new Evento();
			evento.setId(id);
			evento.setTitulo(titulo);
			evento.setDescricao(descricao);
			evento.setDataInicio(c1);
			evento.setDataFim(c2);
			evento.setLocal(local);
			
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
		
		//UIGerenciaParticipantesClinica managerPart = null;
		
		Paciente paciente = new Paciente(0, null, 0, null, null, null, 0, null);
		Medico medico = new Medico(0, null, 0, null);
		
		//System.out.println("");
		//System.out.println("---Recuperar Paciente---");
		//System.out.print("ID: ");
		
		paciente = (Paciente) gerenciadorParticipante.getParticipante(1);
		medico = (Medico) gerenciadorParticipante.getParticipante(2);
		
		//paciente = managerPart.recuperarPaciente();
		//medico = managerPart.recuperarMedico();
		
		Evento evento = gerenciadorEvento.getEvento(idEvento);
		
		gerenciadorEvento.inscreverParticipante(evento, paciente);	
		gerenciadorEvento.inscreverParticipante(evento, medico);
	
	}
}
