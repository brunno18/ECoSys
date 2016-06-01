package clinica;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.ufrn.imd.controller.GerenciadorParticipante;
import br.ufrn.imd.dao.DAOException;
import br.ufrn.imd.dao.IDAOInscricao;
import br.ufrn.imd.dao.IDAOParticipante;
import br.ufrn.imd.model.Evento;
import br.ufrn.imd.model.Inscricao;
import br.ufrn.imd.model.Participante;
import br.ufrn.imd.view.GerenciadorParticipanteGUI;

public class UIGerenciaPaciente implements GerenciadorParticipanteGUI {
	
	private static Scanner inputScanner = new Scanner(System.in);
	
	private enum MENU_ENTRY {
		CADASTRAR_PACIENTE, 
		LISTAR_PACIENTES, 
		CONSULTAS_AGENDADAS,
		BACK,
		START,
		INVALID
	}
	
	private MENU_ENTRY currentMenuEntry;
	
	private GerenciadorParticipante gerenciadorParticipante;
	
	public UIGerenciaPaciente(GerenciadorParticipante gerenciadorParticipante) {
		this.gerenciadorParticipante = gerenciadorParticipante;
		
		currentMenuEntry = currentMenuEntry.START;
	}

	@Override
	public void cadastrarParticipante() {
		String nome, cpf, rg, telefone;
		long prontuario, numeroPlanoSaude;
		int idade, planoEmDia;
		
		System.out.println();
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("  Cadastro de Paciente  ");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println();
		
		System.out.println("Preencha os Dados...");
		
		System.out.print("Nome: ");
		nome = inputScanner.nextLine();
		
		System.out.print("Idade: ");
		idade = Integer.parseInt(inputScanner.nextLine());
		
		System.out.print("CPF: ");
		cpf = inputScanner.nextLine();
		
		System.out.print("RG: ");
		rg = inputScanner.nextLine();
		
		System.out.print("Telefone: ");
		telefone = inputScanner.nextLine();
		
		System.out.print("Prontuario: ");
		prontuario = Long.parseLong(inputScanner.nextLine());
		
		System.out.print("Plano de Saude: ");
		numeroPlanoSaude = Long.parseLong(inputScanner.nextLine());
		
		Paciente paciente = new Paciente(nome, idade, cpf, rg, telefone, numeroPlanoSaude, prontuario);
		
		try {
			gerenciadorParticipante.cadastrarParticipante(paciente);
			System.out.println("Paciente cadastrado com sucesso");
		} catch (DAOException e) {
			System.out.println("Erro ao cadastrar paciente");
		}
	}

	@Override
	public void listarParticipantes() {
		System.out.println();
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println(" Pacientes Cadastrados ");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println();
		
		List<Participante> participantes = gerenciadorParticipante.listarParticipantes();
		
		System.out.println("Há " + participantes.size() + " paciente(s) cadastrado(s).");
		System.out.println();
		
		int counter = 1;
		for (Participante participante : participantes) {
			Paciente paciente = (Paciente) participante;
			System.out.println("Paciente " + counter);
			System.out.println("ID: " + paciente.getId());
			System.out.println("Nome: " + paciente.getNome());
			System.out.println("Idade: " + paciente.getIdade());
			System.out.println("CPF: " + paciente.getCpf());
			System.out.println("RG: " + paciente.getRg());
			System.out.println("Prontuario: " + paciente.getProntuario());
			System.out.println("Plano de Saude: " + paciente.getNumeroPlanoSaude());
			System.out.println("Plano de Saude em Dia? " + paciente.isConvenioEmDia());
			System.out.println();
			
			if (counter < participantes.size()) {
				System.out.print("Proximo Paciente - Pressione Enter...");
				inputScanner.nextLine();
				System.out.println();
			}
			counter++;
		}
	}
	
	@Override
	public void listarInscricoesParticipantes() {
		long prontuario;
		
		System.out.println();
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println(" Consultas Agendadas ");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println();
		
		System.out.print("Informe o prontuario do paciente: ");
		prontuario = inputScanner.nextLong();
	
		Paciente paciente = getPacienteByProntuario(prontuario);
		
		if (paciente == null) {
			System.out.println();
			System.out.println("Paciente não encontrado!");
		}
		else {
			List<Inscricao> inscricoes = gerenciadorParticipante.listarInscricoes(paciente.getId(), TipoInscricao.PACIENTE);
			List<Evento> consultasPendentes = new ArrayList<Evento>();
			List<Evento> consultasCan = new ArrayList<Evento>();
			
			for (Inscricao inscricao : inscricoes) {
				inscricao.getEvento();
			}
		}
	}
	
	public Paciente getPacienteByProntuario(long prontuario) {
		List<Participante> pacientes = gerenciadorParticipante.listarParticipantes();
		
		for(Participante p : pacientes) {
			Paciente paciente = (Paciente) p;
			if (paciente.getProntuario() == prontuario) {
				return paciente;
			}
		}
		
		return null;
	}
	
	private void toWelcome() {
		System.out.println();
		System.out.println("---------------------------------");
		System.out.println("## -- Gerência de Pacientes -- ##");
		System.out.println("---------------------------------");
		System.out.println();
	}

	public void showMenu() {
		System.out.println();
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("+++  Menu Pacientes +++");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println();
		
		System.out.println("1 - Cadastrar Paciente");
		System.out.println("2 - Listar Pacientes");
		System.out.println("3 - Consultas Agendadas");
		System.out.println("4 - Voltar");
		System.out.println();
	}
	
	private void selectOption() {
		System.out.println("Informe uma opção: ");
		String option = inputScanner.nextLine();
		
		switch (option) {
			case "1":
				currentMenuEntry = MENU_ENTRY.CADASTRAR_PACIENTE;
				break;
			case "2":
				currentMenuEntry = MENU_ENTRY.LISTAR_PACIENTES;
				break;
			case "3":
				currentMenuEntry = MENU_ENTRY.CONSULTAS_AGENDADAS;
				break;
			case "4":
				currentMenuEntry = MENU_ENTRY.BACK;
				break;
			default:
				currentMenuEntry = MENU_ENTRY.INVALID;
				break;
		}
	}
	
	private void handleInput() {
		switch (currentMenuEntry) {
			case CADASTRAR_PACIENTE:
				cadastrarParticipante();
				break;
			case LISTAR_PACIENTES:
				listarParticipantes();
				break;
			case CONSULTAS_AGENDADAS:
				listarInscricoesParticipantes();
				break;
			case BACK:
				break;
			case INVALID:
				handleInvalidInput();
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
		
		while (currentMenuEntry != MENU_ENTRY.BACK) {
			showMenu();
			selectOption();
			handleInput();
		}
		
		currentMenuEntry = currentMenuEntry.START;
	}
}
