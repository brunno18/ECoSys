package eventosAcademicos;

import java.util.List;
import java.util.Scanner;

import br.ufrn.imd.controller.GerenciadorParticipante;
import br.ufrn.imd.dao.DAOException;
import br.ufrn.imd.dao.IDAOInscricao;
import br.ufrn.imd.dao.IDAOParticipante;
import br.ufrn.imd.model.Inscricao;
import br.ufrn.imd.model.Participante;
import br.ufrn.imd.view.GerenciadorParticipanteGUI;


public class UIGerenciaParticipanteEA implements GerenciadorParticipanteGUI{

private static Scanner inputScanner = new Scanner(System.in);
	
	private enum MENU_ENTRY {
		CADASTRAR_PARTICIPANTE, 
		LISTAR_PARTICIPANTE, 
		EVENTOS_INSCRITOS,
		BACK,
		START,
		INVALID
	}
	
	private MENU_ENTRY currentMenuEntry;
	
	private GerenciadorParticipante gerenciadorParticipante;
	
	public UIGerenciaParticipanteEA(IDAOParticipante daoParticipanteEA, IDAOInscricao daoInscricao) {
		gerenciadorParticipante = new GerenciadorParticipante(daoParticipanteEA, daoInscricao);
		
		currentMenuEntry = currentMenuEntry.START;
	}
	
	@Override
	public void cadastrarParticipante() {
		String nome, cpf, rg, telefone, universidade, formacao;
		int matricula;
		
		System.out.println();
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("  Cadastro de Participante  ");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println();
		
		System.out.println("Preencha os Dados...");
		
		System.out.print("Nome: ");
		nome = inputScanner.nextLine();
		
		System.out.print("Matricula: ");
		matricula = Integer.parseInt(inputScanner.nextLine());
		
		System.out.print("CPF: ");
		cpf = inputScanner.nextLine();
		
		System.out.print("RG: ");
		rg = inputScanner.nextLine();
		
		System.out.print("Telefone: ");
		telefone = inputScanner.nextLine();
		
		System.out.print("Universidade: ");
		universidade = inputScanner.nextLine();
		
		System.out.print("Formação: ");
		formacao = inputScanner.nextLine();
		
		ParticipanteEA participante = new ParticipanteEA(nome, matricula, universidade, formacao, telefone, cpf, rg);
		
		try {
			gerenciadorParticipante.cadastrarParticipante(participante);
			System.out.println("Paciente cadastrado com sucesso");
		} catch (DAOException e) {
			System.out.println("Erro ao cadastrar paciente");
		}
	}

	@Override
	public void listarParticipantes() {
		System.out.println();
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println(" Participantes Cadastrados ");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println();
		
		List<Participante> participantes = gerenciadorParticipante.listarParticipantes();
		
		System.out.println("Há " + participantes.size() + " paciente(s) cadastrado(s).");
		System.out.println();
		
		int counter = 1;
		for (Participante participante : participantes) {
			ParticipanteEA paticipanteEA = (ParticipanteEA) participante;
			System.out.println("Participante " + counter);
			System.out.println("ID: " + paticipanteEA.getId());
			System.out.println("Nome: " + paticipanteEA.getNome());
			System.out.println("Matricula: " + paticipanteEA.getMatricula());
			System.out.println("CPF: " + paticipanteEA.getCpf());
			System.out.println("RG: " + paticipanteEA.getRg());
			System.out.println("Universidade: " + paticipanteEA.getUniversidade());
			System.out.println("Formação: " + paticipanteEA.getFormacao());
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
		
		System.out.println();
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println(" Inscrições Realizadas ");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println();
		
		System.out.print("Informe o ID do participante: ");
		int idPart = inputScanner.nextInt();
	
		ParticipanteEA participante = getParticipanteByID(idPart);
		
		if (participante == null) {
			System.out.println();
			System.out.println("Paciente não encontrado!");
		}
		else {
			List<Inscricao> inscricoes = gerenciadorParticipante.listarInscricoes(participante.getId());
			
			for (Inscricao inscricao : inscricoes) {
				inscricao.getEvento();
			}
		}
	}

	public ParticipanteEA getParticipanteByID(int idPart) {
		List<Participante> participantes = gerenciadorParticipante.listarParticipantes();
		
		for(Participante p : participantes) {
			ParticipanteEA participante = (ParticipanteEA) p;
			if (participante.getId() == idPart) {
				return participante;
			}
		}
		
		return null;
	}

	private void toWelcome() {
		System.out.println();
		System.out.println("---------------------------------");
		System.out.println("## -- Gerência de Participantes de Eventos Academicos -- ##");
		System.out.println("---------------------------------");
		System.out.println();
	}

	public void showMenu() {
		System.out.println();
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("+++  Menu Participantes +++");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println();
		
		System.out.println("1 - Cadastrar Participante");
		System.out.println("2 - Listar Participantes");
		System.out.println("3 - Listar Inscrições de um Participante");
		System.out.println("4 - Voltar");
		System.out.println();
	}
	
	private void selectOption() {
		System.out.println("Informe uma opção: ");
		String option = inputScanner.nextLine();
		
		switch (option) {
			case "1":
				currentMenuEntry = MENU_ENTRY.CADASTRAR_PARTICIPANTE;
				break;
			case "2":
				currentMenuEntry = MENU_ENTRY.LISTAR_PARTICIPANTE;
				break;
			case "3":
				currentMenuEntry = MENU_ENTRY.EVENTOS_INSCRITOS;
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
			case CADASTRAR_PARTICIPANTE:
				cadastrarParticipante();
				break;
			case LISTAR_PARTICIPANTE:
				listarParticipantes();
				break;
			case EVENTOS_INSCRITOS:
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
