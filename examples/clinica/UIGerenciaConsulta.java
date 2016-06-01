package clinica;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import br.ufrn.imd.controller.EventoValidator;
import br.ufrn.imd.controller.GerenciadorEvento;
import br.ufrn.imd.controller.GerenciadorLocal;
import br.ufrn.imd.controller.GerenciadorParticipante;
import br.ufrn.imd.controller.NotificadorEvento;
import br.ufrn.imd.controller.RegraParticipacao;
import br.ufrn.imd.controller.ValidateEventoException;
import br.ufrn.imd.dao.IDAOEvento;
import br.ufrn.imd.dao.IDAOInscricao;
import br.ufrn.imd.dao.IDAOLocal;
import br.ufrn.imd.dao.IDAOParticipante;
import br.ufrn.imd.model.Evento;
import br.ufrn.imd.model.FabricaNotificacao;
import br.ufrn.imd.model.Inscricao;
import br.ufrn.imd.model.Local;
import br.ufrn.imd.model.ValidatePartipationException;
import br.ufrn.imd.util.service.NotificationService;
import br.ufrn.imd.util.service.PublicationService;
import br.ufrn.imd.view.GerenciadorEventoGUI;

public class UIGerenciaConsulta implements GerenciadorEventoGUI {
	
	private static Scanner inputScanner = new Scanner(System.in);
	
	private enum MENU_ENTRY {
		AGENDAR_CONSULTA,
		LISTAR_CONSULTAS,
		ATUALIZAR_CONSULTA,
		CANCELAR_CONSULTA,
		GERAR_RELATORIO_CONSULTA,
		NOTIFICAR_CONSULTAS_PROXIMAS,
		BACK,
		START,
		INVALID
	}
	
	private MENU_ENTRY currentMenuEntry;
	
	private GerenciadorEvento gerenciadorEvento;
	
	private GerenciadorParticipante gerenciadorPaciente;
	
	private GerenciadorParticipante gerenciadorMedico;
	
	private GerenciadorLocal gerenciadorLocal;
	
	private RegraParticipacao regraParticipacaoPaciente;
	
	private RegraParticipacao regraParticipacaoMedico;
	
	private Consulta currentConsulta;
	
	
	public UIGerenciaConsulta (
				GerenciadorEvento gerenciadorEvento, 
				GerenciadorParticipante gerenciadorPaciente, 
				GerenciadorParticipante gerenciadorMedico,
				GerenciadorLocal gerenciadorLocal,
				RegraParticipacao regraParticipacaoPaciente,
				RegraParticipacao regraParticipacaoMedico
			) 
	{
		this.gerenciadorEvento = gerenciadorEvento;
		this.gerenciadorPaciente = gerenciadorPaciente;
		this.gerenciadorMedico = gerenciadorMedico;
		this.regraParticipacaoPaciente = regraParticipacaoPaciente;
		this.regraParticipacaoMedico = regraParticipacaoMedico;
		this.gerenciadorLocal = gerenciadorLocal;
		
		currentMenuEntry = currentMenuEntry.START;
	}
	
	@Override
	public void criarEvento() {
		Local local;
		String titulo, descricao, data, horario;
		String[] dataPartes, horaPartes;
		long idLocal;
		Calendar dataInicio;
		
		System.out.println();
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("Agendamento de Consulta");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println();
		
		System.out.println("Preencha os Dados...");
		
		System.out.print("Titulo: ");
		titulo = inputScanner.nextLine();
		
		System.out.print("Descricao: ");
		descricao = inputScanner.nextLine();
		
		System.out.print("Local(id): ");
		idLocal = Long.parseLong(inputScanner.nextLine());
		
		System.out.print("Data(dd/mm/aaaa): ");
		data = inputScanner.nextLine();
		
		System.out.print("Hora(hh:mm): ");
		horario = inputScanner.nextLine();
		
		dataPartes = data.split("/");
		horaPartes = horario.split(":");
		
		dataInicio = Calendar.getInstance();
		dataInicio.set(Integer.parseInt(dataPartes[2]), Integer.parseInt(dataPartes[1])-1, Integer.parseInt(dataPartes[0]), Integer.parseInt(horaPartes[0]), Integer.parseInt(horaPartes[1]));
		
		local = gerenciadorLocal.recuperarLocal(idLocal);
		if (local != null) {
			try {
				currentConsulta = new Consulta(titulo, descricao, local, dataInicio);
				gerenciadorEvento.criarEvento(currentConsulta);
				inscreverParticipante();
			} catch (ValidateEventoException e) {
				System.out.println("Erro ao agendar consulta");
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("Error - Local não encontrado!");
		}
	}
	
	@Override
	public void listarEventos() {
		System.out.println();
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("  Consultas Agendadas  ");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println();
		
		List<Evento> consultas = gerenciadorEvento.listarEventos();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		System.out.println("Há " + consultas.size() + " consulta(s) agendada(s).");
		System.out.println();
		
		int counter = 1;
		for (Evento consulta : consultas) {
			System.out.println("Consulta " + counter);
			System.out.println("Titulo: " + consulta.getTitulo());
			System.out.println("Descrição: " + consulta.getDescicao());
			System.out.println("Data: " + sdf.format(consulta.getDataInicio().getTime()));
			
			System.out.println("Local: " + consulta.getLocal().getNome() + " - " + consulta.getLocal().getEndereco());
			
			List<Inscricao> inscricoes = gerenciadorEvento.listarInscricoesEvento(consulta.getId());
			for (Inscricao inscricao : inscricoes) {
				if (inscricao.getParticipante() instanceof Paciente) {
					System.out.println("Paciente: " + inscricao.getParticipante().getNome());
				}
				else {
					System.out.println("Médico: Dr. " + inscricao.getParticipante().getNome());
				}
			}
			
			System.out.println();
			if (counter < consultas.size()) {
				System.out.print("Próxima Consulta - Pressione Enter...");
				inputScanner.nextLine();
				System.out.println();
			}
			counter++;
		}
	}

	@Override
	public void atualizarEvento() {
		
	}

	@Override
	public void cancelarEvento() {

	}

	@Override
	public void publicarEvento(){
		/* Publicacao nao disponivel na instancia Clinica */
	}

	@Override
	public void gerarRelatorioCustoEvento() {

	}

	@Override
	public void notificarEventosProximos() {
		String dias;
		
		System.out.println();
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("  Notificar Pacientes  ");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println();
		
		System.out.print("Numero de dias: ");
		dias = inputScanner.nextLine();
		
		gerenciadorEvento.notificarEventosProximos(Integer.parseInt(dias));
	}

	@Override
	public void inscreverParticipante() {
		Paciente paciente;
		Medico medico;
		long idPaciente, idMedico;
		
		System.out.print("Paciente(id): ");
		idPaciente = Long.parseLong(inputScanner.nextLine());
		paciente = (Paciente) gerenciadorPaciente.getParticipante(idPaciente);
		
		if (paciente != null) {
			System.out.print("Medico(id): ");
			idMedico = Long.parseLong(inputScanner.nextLine());
			medico = (Medico) gerenciadorMedico.getParticipante(idMedico);
			
			if (medico != null) {
				try {
					gerenciadorEvento.inscreverParticipante(currentConsulta, paciente, TipoInscricao.PACIENTE, regraParticipacaoPaciente);
					gerenciadorEvento.inscreverParticipante(currentConsulta, medico, TipoInscricao.MEDICO, regraParticipacaoMedico);
					
					System.out.println("Consulta agendada com sucesso");
				} catch (ValidatePartipationException e) {
					System.out.println("Erro ao agendar consulta");
					System.out.println(e.getMessage());
				}
			}
			else {
				System.out.println("Erro - Médico não encontrado");
			}
		}
		else {
			System.out.println("Erro - Paciente não encontrado");
		}
	}
	
	private void toWelcome() {
		System.out.println();
		System.out.println("---------------------------------");
		System.out.println("## -- Gerência de Consultas -- ##");
		System.out.println("---------------------------------");
		System.out.println();
	}

	public void showMenu() {
		System.out.println();
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("+++  Menu Consultas +++");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println();
		
		System.out.println("1 - Agendar Consulta");
		System.out.println("2 - Consultas Agendadas");
		System.out.println("3 - Notificar Consultas Próximas");
		System.out.println("4 - Atualizar Consulta");
		System.out.println("5 - Cancelar Consulta");
		System.out.println("6 - Gerar Relatório Consulta");
		System.out.println("7 - Voltar");
		System.out.println();
	}
	
	private void selectOption() {
		System.out.println("Informe uma opção: ");
		String option = inputScanner.nextLine();
		
		switch (option) {
			case "1":
				currentMenuEntry = MENU_ENTRY.AGENDAR_CONSULTA;
				break;
			case "2":
				currentMenuEntry = MENU_ENTRY.LISTAR_CONSULTAS;
				break;
			case "3":
				currentMenuEntry = MENU_ENTRY.NOTIFICAR_CONSULTAS_PROXIMAS;
				break;
			case "4":
				currentMenuEntry = MENU_ENTRY.ATUALIZAR_CONSULTA;
				break;
			case "5":
				currentMenuEntry = MENU_ENTRY.CANCELAR_CONSULTA;
				break;
			case "6":
				currentMenuEntry = MENU_ENTRY.GERAR_RELATORIO_CONSULTA;
				break;
			case "7":
				currentMenuEntry = MENU_ENTRY.BACK;
				break;
			default:
				currentMenuEntry = MENU_ENTRY.INVALID;
				break;
		}
	}
	
	private void handleInput() {
		switch (currentMenuEntry) {
			case AGENDAR_CONSULTA:
				criarEvento();
				break;
			case LISTAR_CONSULTAS:
				listarEventos();
				break;
			case NOTIFICAR_CONSULTAS_PROXIMAS:
				notificarEventosProximos();
				break;
			case ATUALIZAR_CONSULTA:
				atualizarEvento();
				break;
			case CANCELAR_CONSULTA:
				cancelarEvento();
				break;
			case GERAR_RELATORIO_CONSULTA:
				gerarRelatorioCustoEvento();
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
