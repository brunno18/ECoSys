package clinica;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import br.ufrn.imd.controller.EventoValidator;
import br.ufrn.imd.controller.GerenciadorEvento;
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
import br.ufrn.imd.model.Participante;
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
	
	private RegraParticipacao regraParticipacaoPaciente;
	
	private RegraParticipacao regraParticipacaoMedico;
	
	private EventoValidator consultaValidator;
	
	private PublicationService publicationService;
	
	private IDAOInscricao daoInscricao;
	
	private IDAOEvento daoConsulta;
	
	private IDAOParticipante daoPaciente;
	
	private IDAOParticipante daoMedico;
	
	private IDAOLocal daoLocal;
	
	private FabricaNotificacao notificadorPacienteClinica;
	
	private NotificationService smsNotificationService;
	
	private NotificadorEvento notificadorEvento;
	
	private Consulta currentConsulta;
	
	
	public UIGerenciaConsulta(
								FabricaNotificacao notificadorPacienteClinica, 
								NotificationService smsNotificationService, 
								EventoValidator consultaValidator, 
								RegraParticipacao regraParticipacaoPaciente,
								RegraParticipacao regraParticipacaoMedico,
								IDAOEvento daoConsulta, 
								IDAOInscricao daoInscricao, 
								IDAOParticipante daoPaciente, 
								IDAOParticipante daoMedico,
								IDAOLocal daoLocal
							  ) 
	{
		
		this.daoConsulta = daoConsulta;
		this.daoInscricao = daoInscricao;
		this.daoMedico = daoMedico;
		this.daoPaciente = daoPaciente;
		this.daoLocal = daoLocal;
		
		this.consultaValidator = consultaValidator;
		this.regraParticipacaoPaciente = regraParticipacaoPaciente;
		this.regraParticipacaoMedico = regraParticipacaoMedico;
		
		this.notificadorPacienteClinica = notificadorPacienteClinica;
		this.smsNotificationService = smsNotificationService;
		
		notificadorEvento = new NotificadorEvento(this.notificadorPacienteClinica, this.smsNotificationService, this.daoInscricao);
		
		gerenciadorEvento = new GerenciadorEvento(this.consultaValidator, this.notificadorEvento, null, this.daoConsulta, this.daoInscricao);
		
		currentMenuEntry = currentMenuEntry.START;
	}
	
	@Override
	public void criarEvento() {
		Local local;
		String titulo, descricao, data, horario;
		String dia, mes, ano, hora, minutos;
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
		local = daoLocal.recuperar(idLocal);
		
		System.out.print("Data(dd/mm/aaaa): ");
		data = inputScanner.nextLine();
		
		dia = data.substring(0, 2);
		mes = data.substring(3, 5);
		ano = data.substring(6);
		
		System.out.print("Hora(hh:mm): ");
		horario = inputScanner.nextLine();
		
		hora = horario.substring(0, 2);
		minutos = horario.substring(3);
		
		dataInicio = Calendar.getInstance();
		dataInicio.set(Integer.parseInt(ano), Integer.parseInt(mes), Integer.parseInt(dia), Integer.parseInt(hora), Integer.parseInt(minutos));
		
		if (local != null) {
			currentConsulta = new Consulta(titulo, descricao, local, dataInicio);
		
			inscreverParticipante();
		} else {
			System.out.println("Error - Local n�o encontrado!");
		}
	}
	
	@Override
	public void listarEventos() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		System.out.println();
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("  Consultas Agendadas  ");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println();
		
		List<Evento> consultas = gerenciadorEvento.listarEventos();
		
		System.out.println("H� " + consultas.size() + " consulta(s) agendada(s).");
		System.out.println();
		
		int counter = 1;
		for (Evento consulta : consultas) {
			System.out.println("Consulta " + counter);
			System.out.println("Titulo: " + consulta.getTitulo());
			System.out.println("Descri��o: " + consulta.getDescicao());
			System.out.println("Data: " + simpleDateFormat.format(consulta.getDataInicio().getTime()));
			System.out.println("Local: " + consulta.getLocal().getNome() + " - " + consulta.getLocal().getEndereco());
			
			List<Inscricao> inscricoes = daoInscricao.listarInscricoesEvento(consulta.getId());
			for (Inscricao inscricao : inscricoes) {
				if (inscricao.getParticipante() instanceof Paciente) {
					System.out.println("Paciente: " + inscricao.getParticipante().getNome());
				}
				else {
					System.out.println("M�dico: Dr. " + inscricao.getParticipante().getNome());
				}
			}
			
			System.out.println();
			if (counter < consultas.size()) {
				System.out.print("Proximo M�dico - Pressione Enter...");
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notificarEventosProximos() {
		
	}

	@Override
	public void inscreverParticipante() {
		Paciente paciente;
		Medico medico;
		long idPaciente, idMedico;
		
		System.out.print("Paciente(id): ");
		idPaciente = Long.parseLong(inputScanner.nextLine());
		paciente = (Paciente) daoPaciente.recuperar(idPaciente);
		
		if (paciente != null) {
			System.out.print("Medico(id): ");
			idMedico = Long.parseLong(inputScanner.nextLine());
			medico = (Medico) daoMedico.recuperar(idMedico);
			
			if (medico != null) {
				try {
					consultaValidator.validar(currentConsulta);
					regraParticipacaoPaciente.validarParticipacao(paciente, currentConsulta);
					regraParticipacaoMedico.validarParticipacao(medico, currentConsulta);
					
					Inscricao inscricaoPaciente = new Inscricao(currentConsulta, paciente);
					Inscricao inscricaoMedico = new Inscricao(currentConsulta, medico);
					
					daoConsulta.cadastrar(currentConsulta);
					daoInscricao.cadastrar(inscricaoPaciente);
					daoInscricao.cadastrar(inscricaoMedico);
					currentConsulta = null;
				} catch (ValidateEventoException|ValidatePartipationException e) {
					System.out.println("Erro ao agendar consulta");
					System.out.println(e.getMessage());
				}
			}
			else {
				System.out.println("Erro - M�dico n�o encontrado");
			}
		}
		else {
			System.out.println("Erro - Paciente n�o encontrado");
		}
	}
	
	private void toWelcome() {
		System.out.println();
		System.out.println("---------------------------------");
		System.out.println("## -- Ger�ncia de Consultas -- ##");
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
		System.out.println("3 - Notificar Consultas Pr�ximas");
		System.out.println("4 - Atualizar Consulta");
		System.out.println("5 - Cancelar Consulta");
		System.out.println("6 - Gerar Relat�rio Consulta");
		System.out.println("7 - Voltar");
		System.out.println();
	}
	
	private void selectOption() {
		System.out.println("Informe uma op��o: ");
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
		System.out.println("Op��o Inv�lida!");
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
