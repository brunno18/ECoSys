package clinica;

import java.util.List;
import java.util.Scanner;

import br.ufrn.imd.controller.GerenciadorParticipante;
import br.ufrn.imd.dao.DAOException;
import br.ufrn.imd.dao.IDAOInscricao;
import br.ufrn.imd.dao.IDAOParticipante;
import br.ufrn.imd.model.Participante;
import br.ufrn.imd.view.GerenciadorParticipanteGUI;

public class UIGerenciaMedico implements GerenciadorParticipanteGUI {
	
	private Scanner inputScanner = new Scanner(System.in);
	
	private enum MENU_ENTRY {
		CADASTRAR_MÉDICO, 
		LISTAR_MÉDICOS, 
		CONSULTAS_AGENDADAS,
		BACK,
		START,
		INVALID
	}
	
	private MENU_ENTRY currentMenuEntry;
	
	private GerenciadorParticipante gerenciadorParticipante;
	
	public UIGerenciaMedico(IDAOParticipante daoMedico, IDAOInscricao daoInscricao) {
		gerenciadorParticipante = new GerenciadorParticipante(daoMedico, daoInscricao);
		
		currentMenuEntry = currentMenuEntry.START;
	}

	@Override
	public void cadastrarParticipante() {
		String nome, especialidade, crm, telefone;
		
		System.out.println();
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("  Cadastro de Médico  ");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println();
		
		System.out.println("Preencha os Dados...");
		
		System.out.print("Nome: ");
		nome = inputScanner.nextLine();
		
		System.out.print("Telefone: ");
		telefone = inputScanner.nextLine();
		
		System.out.print("CRM: ");
		crm = inputScanner.nextLine();
		
		System.out.print("Especialidade: ");
		especialidade = inputScanner.nextLine();
		
		
		Medico medico = new Medico(nome, telefone, crm, especialidade);
		
		try {
			gerenciadorParticipante.cadastrarParticipante(medico);
			System.out.println("Médico cadastrado com sucesso");
		} catch (DAOException e) {
			System.out.println("Erro ao cadastrar médico");
		}
	}

	@Override
	public void listarParticipantes() {
		System.out.println();
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println(" Médicos Cadastrados ");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println();
		
		List<Participante> participantes = gerenciadorParticipante.listarParticipantes();
		
		System.out.println("Há " + participantes.size() + " médico(s) cadastrado(s).");
		System.out.println();
		
		int counter = 1;
		for (Participante participante : participantes) {
			Medico medico = (Medico) participante;
			System.out.println("Médico " + counter);
			System.out.println("ID: " + medico.getId());
			System.out.println("Nome: " + medico.getNome());
			System.out.println("Telefone: " + medico.getTelefone());
			System.out.println("CRM: " + medico.getCrm());
			System.out.println("Especialidade: " + medico.getEspecialidade());
			System.out.println();
			if (counter < participantes.size()) {
				System.out.print("Proximo Médico - Pressione Enter...");
				inputScanner.nextLine();
				System.out.println();
			}
			counter++;
		}
	}

	@Override
	public void listarInscricoesParticipantes() {
		System.out.println();
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println(" Consultas Agendadas ");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println();
	}
	
	private void toWelcome() {
		System.out.println();
		System.out.println("---------------------------------");
		System.out.println("## --- Gerência de Médicos --- ##");
		System.out.println("---------------------------------");
		System.out.println();
	}

	public void showMenu() {
		System.out.println();
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("+++   Menu Médicos  +++");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println();
		
		System.out.println("1 - Cadastrar Médico");
		System.out.println("2 - Listar Médicos");
		System.out.println("3 - Consultas Agendadas");
		System.out.println("4 - Voltar");
		System.out.println();
	}
	
	private void selectOption() {
		System.out.println("Informe uma opção: ");
		String option = inputScanner.nextLine();
		
		switch (option) {
			case "1":
				currentMenuEntry = MENU_ENTRY.CADASTRAR_MÉDICO;
				break;
			case "2":
				currentMenuEntry = MENU_ENTRY.LISTAR_MÉDICOS;
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
			case CADASTRAR_MÉDICO:
				cadastrarParticipante();
				break;
			case LISTAR_MÉDICOS:
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
