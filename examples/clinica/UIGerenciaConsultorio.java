package clinica;

import java.util.List;
import java.util.Scanner;

import br.ufrn.imd.controller.GerenciadorLocal;
import br.ufrn.imd.dao.DAOException;
import br.ufrn.imd.dao.IDAOLocal;
import br.ufrn.imd.model.Local;
import br.ufrn.imd.view.GerenciadorLocalGUI;

public class UIGerenciaConsultorio implements GerenciadorLocalGUI {
	
	private Scanner inputScanner = new Scanner(System.in);
	
	private enum MENU_ENTRY {
		CADASTRAR_CONSULTÓRIO, 
		LISTAR_CONSULTÓRIO, 
		BACK,
		START,
		INVALID
	}

	private MENU_ENTRY currentMenuEntry;
	
	private GerenciadorLocal gerenciadorLocal;
	
	public UIGerenciaConsultorio(GerenciadorLocal gerenciadorLocal) {
		this.gerenciadorLocal = gerenciadorLocal;
		currentMenuEntry = MENU_ENTRY.START;
	}
	
	@Override
	public void cadastrarLocal() {
		String nome, endereco;
		
		System.out.println();
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("Cadastro de Consultório");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println();
		
		System.out.println("Preencha os Dados...");
		
		System.out.print("Nome: ");
		nome = inputScanner.nextLine();
		
		System.out.print("Endereço: ");
		endereco = inputScanner.nextLine();
		
		Local local = new Local(nome, endereco);
		
		try {
			gerenciadorLocal.cadastrarLocal(local);
			System.out.println("Consultório cadastrado com sucesso");
		} catch (DAOException e) {
			System.out.println("Erro ao cadastrar consultório");
		}
		
	}

	@Override
	public void listarLocais() {
		System.out.println();
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("Consultórios Cadastrados");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println();
		
		List<Local> locais = gerenciadorLocal.listarLocais();
		
		System.out.println("Há " + locais.size() + " consultório(s) cadastrado(s).");
		System.out.println();
		
		int counter = 1;
		for (Local local : locais) {
			System.out.println("Consultório " + counter);
			System.out.println("ID: " + local.getId());
			System.out.println("Nome: " + local.getNome());
			System.out.println("Endereço: " + local.getEndereco());
			System.out.println();
			if (counter < locais.size()) {
				System.out.print("Proximo Consultório - Pressione Enter...");
				inputScanner.nextLine();
				System.out.println();
			}
			counter++;
		}
	}

	private void toWelcome() {
		System.out.println();
		System.out.println("---------------------------------");
		System.out.println("##- Gerência de Consultórios - ##");
		System.out.println("---------------------------------");
		System.out.println();
	}

	public void showMenu() {
		System.out.println();
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("++ Menu Consultórios ++");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println();
		
		System.out.println("1 - Cadastrar Consultório");
		System.out.println("2 - Listar Consultórios");
		System.out.println("3 - Voltar");
		System.out.println();
	}
	
	private void selectOption() {
		System.out.println("Informe uma opção: ");
		String option = inputScanner.nextLine();
		
		switch (option) {
			case "1":
				currentMenuEntry = MENU_ENTRY.CADASTRAR_CONSULTÓRIO;
				break;
			case "2":
				currentMenuEntry = MENU_ENTRY.LISTAR_CONSULTÓRIO;
				break;
			case "3":
				currentMenuEntry = MENU_ENTRY.BACK;
				break;
			default:
				currentMenuEntry = MENU_ENTRY.INVALID;
				break;
		}
	}
	
	private void handleInput() {
		switch (currentMenuEntry) {
			case CADASTRAR_CONSULTÓRIO:
				cadastrarLocal();
				break;
			case LISTAR_CONSULTÓRIO:
				listarLocais();
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
