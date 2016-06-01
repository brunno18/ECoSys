package clinica;

import java.util.Scanner;

import br.ufrn.imd.controller.GerenciadorEvento;
import br.ufrn.imd.controller.GerenciadorLocal;
import br.ufrn.imd.controller.GerenciadorParticipante;
import br.ufrn.imd.controller.NotificadorEvento;
import br.ufrn.imd.dao.IDAOEvento;
import br.ufrn.imd.dao.IDAOInscricao;
import br.ufrn.imd.dao.IDAOLocal;
import br.ufrn.imd.dao.IDAOParticipante;

public class Starter {
	
	private Scanner inputScanner = new Scanner(System.in);
	
	private enum MENU_ENTRY {
		GERENCIAR_MEDICOS, 
		GERENCIAR_PACIENTES, 
		GERENCIAR_CONSULTAS, 
		GERENCIAR_CONSULTORIOS, 
		GERENCIAR_MATERIAIS, 
		EXIT,
		START,
		INVALID
	}
	
	private MENU_ENTRY currentMenuEntry;
	
	private UIGerenciaPaciente gerenciaPaciente;
	
	private UIGerenciaMedico gerenciaMedico;
	
	private UIGerenciaConsultorio gerenciaConsultorio;
	
	private UIGerenciaConsulta gerenciaConsulta;
	
	public Starter () {
		init();
	}
	
	private void init() {
		currentMenuEntry = currentMenuEntry.START;
		
		IDAOInscricao daoInscricao = DAOInscricaoMemory.getInstancia();
		
		IDAOLocal daoConsultorio = DAOLocalMemory.getInstancia();
		GerenciadorLocal gerenciadorLocal = new GerenciadorLocal(daoConsultorio);
		gerenciaConsultorio = new UIGerenciaConsultorio(gerenciadorLocal);
		
		IDAOParticipante daoPaciente = DAOPacienteMemory.getInstancia();
		GerenciadorParticipante gerenciadorPaciente = new GerenciadorParticipante(daoPaciente, daoInscricao);
		gerenciaPaciente = new UIGerenciaPaciente(gerenciadorPaciente);
		
		IDAOParticipante daoMedico = DAOMedicoMemory.getInstancia();
		GerenciadorParticipante gerenciadorMedico = new GerenciadorParticipante(daoMedico, daoInscricao);
		gerenciaMedico = new UIGerenciaMedico(gerenciadorMedico);
		
		IDAOEvento daoConsulta = DAOConsultaMemory.getInstancia();
		
		NotificadorPacienteClinica notificadorPacienteClinica = new NotificadorPacienteClinica();
		SMSNotificationService smsNotificationService = new SMSNotificationService();
		ConsultaValidator consultaValidator = new ConsultaValidator(daoConsulta);
		RegraParticipacaoPaciente regraParticipacaoPaciente = new RegraParticipacaoPaciente(daoInscricao);
		RegraParticipacaoMedico regraParticipacaoMedico = new RegraParticipacaoMedico(daoInscricao);
		
		NotificadorEvento notificadorEvento = new NotificadorEvento(notificadorPacienteClinica, smsNotificationService, daoInscricao);
		GerenciadorEvento gerenciadorEvento = new GerenciadorEvento(consultaValidator, notificadorEvento, null, daoConsulta, daoInscricao);
		
		gerenciaConsulta = new UIGerenciaConsulta(
					gerenciadorEvento, 
					gerenciadorPaciente, 
					gerenciadorMedico, 
					gerenciadorLocal,
					regraParticipacaoPaciente, 
					regraParticipacaoMedico
				);
		
		Dados.cadastrarConsultórios(daoConsultorio);
		Dados.cadastrarPacientes(daoPaciente);
		Dados.cadastrarMedicos(daoMedico);
		Dados.agendarConsultas(daoConsulta, daoInscricao, daoConsultorio, daoPaciente, daoMedico);
	}
	
	private void toWelcome() {
		System.out.println();
		System.out.println("#################################");
		System.out.println("#################################");
		System.out.println("##  ---- Consultas Médicas --- ##");
		System.out.println("#################################");
		System.out.println("#################################");
		System.out.println();
	}
	
	private void showMenu() {
		System.out.println();
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("+++  Menu Principal +++");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println();
		
		System.out.println("1 - Gerenciar Médicos");
		System.out.println("2 - Gerenciar Pacientes");
		System.out.println("3 - Gerenciar Consultas");
		System.out.println("4 - Gerenciar Consultorios");
		System.out.println("5 - Gerenciar Materiais");
		System.out.println("6 - Sair");
		
		System.out.println();
	}
	
	private void selectOption() {
		System.out.println("Informe uma opção: ");
		String option = inputScanner.nextLine();
		
		switch (option) {
			case "1":
				currentMenuEntry = MENU_ENTRY.GERENCIAR_MEDICOS;
				break;
			case "2":
				currentMenuEntry = MENU_ENTRY.GERENCIAR_PACIENTES;
				break;
			case "3":
				currentMenuEntry = MENU_ENTRY.GERENCIAR_CONSULTAS;
				break;
			case "4":
				currentMenuEntry = MENU_ENTRY.GERENCIAR_CONSULTORIOS;
				break;
			case "5":
				currentMenuEntry = MENU_ENTRY.GERENCIAR_MATERIAIS;
				break;
			case "6":
				currentMenuEntry = MENU_ENTRY.EXIT;
				break;
			default:
				currentMenuEntry = MENU_ENTRY.INVALID;
				break;
		}
	}
	
	private void handleInput() {
		switch (currentMenuEntry) {
			case GERENCIAR_MEDICOS:
				gerenciaMedico.start();
				break;
			case GERENCIAR_PACIENTES:
				gerenciaPaciente.start();
				break;
			case GERENCIAR_CONSULTAS:
				gerenciaConsulta.start();
				break;			
			case GERENCIAR_CONSULTORIOS:
				gerenciaConsultorio.start();
				break;
			case GERENCIAR_MATERIAIS:
				break;
			case INVALID:
				handleInvalidInput();
				break;
			case START:
				break;
			default:
				break;
		}
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void handleInvalidInput() {
		System.out.println("Opção Inválida!");
	}
	
	public void start() {
		toWelcome();
		
		while (currentMenuEntry != MENU_ENTRY.EXIT) {
			showMenu();
			selectOption();
			handleInput();
		}
	}
}
