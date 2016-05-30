//package br.ufrn.imd.instanciaClinica;
//
//import java.util.Iterator;
//import java.util.List;
//import java.util.Scanner;
//import br.ufrn.imd.controller.*;
//import br.ufrn.imd.model.Participante;
//import br.ufrn.imd.view.*;
//
//public class UIGerenciaParticipantesClinica implements GerenciadorParticipanteGUI{
//	
//	private GerenciadorParticipante gerenciadorParticipante = new GerenciadorParticipante();
//	private Scanner input = new Scanner(System.in);
//	
//	@Override
//	public void cadastrarParticipante() {
//		System.out.println("Cadastrar Participante");
//		System.out.println("1 - Cadastrar Paciente\n"
//						 + "2 - Cadastrar M�dico");
//		System.out.print("Selecione uma op��o: ");
//		int option = input.nextInt();
//		
//		switch(option){
//			case 1:
//				System.out.println("---Cadastrar Paciente---");
//				System.out.print("ID: ");
//				int id = input.nextInt();
//				
//				input.nextLine();
//				
//				System.out.print("\nNome: ");
//				String nome = input.nextLine();
//						
//				System.out.print("\nIdade: ");
//				int idade = input.nextInt();
//						
//				//Participante participante = new Participante(id, nome, idade);
//				//participante.setId(id);
//				//participante.setNome(nome);
//				//participante.setIdade(idade);
//				
//				input.nextLine();
//				
//				System.out.print("\nCPF: ");
//				String cpf = input.nextLine();
//						
//				System.out.print("\nRG: ");
//				String rg = input.nextLine();
//						
//				System.out.print("\nNum. Plano de Saude: ");
//				Long numPlano = input.nextLong();
//				
//				System.out.print("\nNum. do Prontuario: ");
//				int pront = input.nextInt();
//				
//				input.nextLine();
//				System.out.print("\nTelefone: ");
//				String tel = input.nextLine();
//				
//				Paciente paciente = new Paciente(id, nome, idade, cpf, rg, numPlano, pront, tel);
//				paciente.setConvenioEmDia(true);
//				
//				gerenciadorParticipante.cadastrarParticipante(paciente);
//				break;
//			case 2:
//				System.out.println("---Cadastrar M�dico---");
//				System.out.print("ID: ");
//				int id2 = input.nextInt();
//				
//				input.nextLine();
//				
//				System.out.print("\nNome: ");
//				String nome2 = input.nextLine();
//						
//				System.out.print("\nIdade: ");
//				int idade2 = input.nextInt();
//						
//				//Participante participante2 = new Participante(id2, nome2, idade2);
//				
//				input.nextLine();
//						
//				System.out.print("\nCRM: ");
//				String crm = input.nextLine();	
//				
//				Medico medico = new Medico(id2, nome2, idade2, crm);
//				
//				gerenciadorParticipante.cadastrarParticipante(medico);
//				break;
//			default:
//				System.out.println("Op��o n�o v�lida!");
//				break;
//		}
//	}
//
//	
//	/*public Paciente recuperarPaciente() {
//		System.out.println("---Recuperar Paciente---");
//		System.out.print("ID: ");
//		int idPart = input.nextInt();
//		Paciente paciente = (Paciente) gerenciadorParticipante.getParticipante(idPart);
//		
//		return paciente;
//	}
//	
//	
//	public Medico recuperarMedico() {
//		System.out.println("---Recuperar Medico---");
//		System.out.print("ID: ");
//		int idPart = input.nextInt();
//		Medico medico = (Medico) gerenciadorParticipante.getParticipante(idPart);
//	
//		return medico;
//	}*/
//
//	@Override
//	public void listarParticipantes(int op) {
//		List <Participante> participantes = gerenciadorParticipante.listarParticipantes();;
//		Iterator<Participante> iter = participantes.iterator();
//		Participante participante;
//		
//		while(iter.hasNext()) {
//			participante = iter.next();
//			//System.out.println("ID: " + participante.getId());
//			//System.out.println("Nome: " + participante.getNome());
//			//System.out.println("Idade: " + participante.getIdade());
//			if(op == 1 && participante instanceof Paciente){
//				System.out.println("ID: " + participante.getId());
//				System.out.println("Nome: " + participante.getNome());
//				System.out.println("Idade: " + participante.getIdade());
//				System.out.println("CPF: " + ((Paciente) participante).getCpf());
//				System.out.println("RG: " + ((Paciente) participante).getRg());
//				System.out.println("Num. Plano de Sa�de: " + ((Paciente) participante).getNumeroPlanoSaude());
//				System.out.println("Telefone: " + ((Paciente) participante).getTelefone());
//				System.out.println("");
//			}
//			else if(op == 2 && participante instanceof Medico){ 
//				System.out.println("ID: " + participante.getId());
//				System.out.println("Nome: " + participante.getNome());
//				System.out.println("Idade: " + participante.getIdade());
//				System.out.println("CRM: " + ((Medico) participante).getCrm());
//				System.out.println("");
//			}
//		}
//		//gerenciadorParticipante.listarParticipantes();
//	}
//}
