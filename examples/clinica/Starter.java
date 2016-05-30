package clinica;

import java.util.Scanner;

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
		Dados.cadastrarConsultórios(daoConsultorio);
		gerenciaConsultorio = new UIGerenciaConsultorio(daoConsultorio);
		
		IDAOParticipante daoPaciente = DAOPacienteMemory.getInstancia();
		Dados.cadastrarPacientes(daoPaciente);
		gerenciaPaciente = new UIGerenciaPaciente(daoPaciente, daoInscricao);
		
		IDAOParticipante daoMedico = DAOMedicoMemory.getInstancia();
		Dados.cadastrarMedicos(daoMedico);
		gerenciaMedico = new UIGerenciaMedico(daoMedico, daoInscricao);
		
		
		IDAOEvento daoConsulta = DAOConsultaMemory.getInstancia();
		Dados.agendarConsultas(daoConsulta, daoInscricao, daoConsultorio, daoPaciente, daoMedico);
		
		NotificadorPacienteClinica notificacaoPacienteClinica = new NotificadorPacienteClinica();
		SMSNotificationService smsNotificationService = new SMSNotificationService();
		ConsultaValidator consultaValidator = new ConsultaValidator(daoConsulta);
		RegraParticipacaoPaciente regraParticipacaoPaciente = new RegraParticipacaoPaciente(daoInscricao);
		RegraParticipacaoMedico regraParticipacaoMedico = new RegraParticipacaoMedico(daoInscricao);
		
		
		gerenciaConsulta = new UIGerenciaConsulta(
				notificacaoPacienteClinica,
				smsNotificationService,
				consultaValidator,
				regraParticipacaoPaciente,
				regraParticipacaoMedico,
				daoConsulta,
				daoInscricao,
				daoPaciente,
				daoMedico,
				daoConsultorio
			);
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
