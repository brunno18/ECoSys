//package br.ufrn.imd.instanciaClinica;
//
//import java.text.*;
//import java.util.*;
//import br.ufrn.imd.model.*;
//
//public class MainClinica {
//	
//	private static UIGerenciaConsulta managerConsulta = new UIGerenciaConsulta();
//	private static UIGerenciaParticipantesClinica managerParticipantes = new UIGerenciaParticipantesClinica();
//	private static Scanner input = new Scanner(System.in);
//	
//	public static void main(String [] args) throws ParseException, ValidatePartipationException{
//		
//		//Teste teste = new Teste();
//		//teste.popularDAO();
//		
//		int op;
//				
//		do{
//			System.out.println("Gerenciamento de Clinicas - ECoSys v0.1");
//			System.out.println("1 - Cadastrar Pessoa");
//			System.out.println("2 - Listar Pacientes");
//			System.out.println("3 - Listar Médicos");
//			System.out.println("4 - Marcar Consulta");
//			System.out.println("5 - Atualizar Consulta");
//			System.out.println("6 - Cancelar Consulta");
//			System.out.println("7 - Inscrever Pessoas para uma Consulta");
//			System.out.println("0 - Sair");
//			
//			System.out.print("Selecione um comando: ");
//			op = input.nextInt();
//			System.out.println("");
//			//System.out.println("-----------------------------------------------------------------------------");
//			
//			
//			switch (op) {
//				case 1:
//					managerParticipantes.cadastrarParticipante();
//					break;
//				case 2:
//					System.out.println("-----------------------------------------------------------------------------");
//					managerParticipantes.listarParticipantes(1);
//					System.out.println("-----------------------------------------------------------------------------");
//					break;
//				case 3:
//					System.out.println("-----------------------------------------------------------------------------");
//					managerParticipantes.listarParticipantes(2);
//					System.out.println("-----------------------------------------------------------------------------");
//					break;
//				case 4:
//					managerConsulta.criarEvento();
//					break;
//				case 5:
//					managerConsulta.atualizarEvento();
//					break;
//				case 6:
//					managerConsulta.cancelarEvento();
//					break;
//				case 7:
//					managerConsulta.listarConsultas();
//					System.out.print("Selecione o ID da Consulta: ");
//					int idConsulta = input.nextInt();
//					
//					managerConsulta.inscreverParticipante(idConsulta);
//					break;
//				case 0:
//					break;
//				default:
//					System.out.println("Comando não existe!");
//					System.out.println("");
//					break;
//			}
//		} while(op > 0);	
//	}
//}
