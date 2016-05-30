//package br.ufrn.imd.instanciaClinica;
//
//import java.util.*;
//import br.ufrn.imd.dao.*;
//import br.ufrn.imd.model.*;
//
//public class Teste {
//	public void popularDAO() {
//		IDAOEvento daoEvento = DAOEventoMemory.getInstancia();
//		IDAOInscricao daoInscricao = DAOInscricaoMemory.getInstancia();
//		IDAOParticipante daoParticipante = DAOParticipanteMemory.getInstancia();
//		
//		Calendar c1 = Calendar.getInstance();
//		Calendar c2 = Calendar.getInstance(); 
//		Calendar c3 = Calendar.getInstance(); 
//		Calendar c4 = Calendar.getInstance();
//		
//		c1.set(2016, Calendar.JULY, 15, 15, 00);
//		c2.set(2016, Calendar.JULY, 15, 15, 40);
//		c3.set(2016, Calendar.MAY, 30, 16, 00);
//		c4.set(2016, Calendar.MAY, 15, 16, 40);
//		
//		Local local1 = new Local();
//		Local local2 = new Local();
//		local1.setNome("Clinica X");
//		local1.setEndereco("Rua Inconfidentes, nº 100");
//		local1.setCapacidade(100);
//		local2.setNome("Clinica Y");
//		local2.setEndereco("Rua Furnas, nº 100");
//		local2.setCapacidade(100);
//		
//		Paciente paciente1 = new Paciente(1, "Rubens Viana Araújo", 22, "103.961.714-00", "2.595.811", 3658372549621501L, 1, "9 9674-1760");
//		Paciente paciente2 = new Paciente(2, "Kauê Rocha Martins", 82, "953.307.474-49", "9.862.578", 3613048989754137L, 2, "9 8851-9743");
//		Paciente paciente3 = new Paciente(3, "Brenda Oliveira Rodrigues", 57, "933.982.386-96", "2.354.734", 5732972121482467L, 3, "9 9669-3844");
//		paciente1.setConvenioEmDia(true);
//		paciente2.setConvenioEmDia(true);
//		paciente3.setConvenioEmDia(true);
//		
//		Medico medico1 = new Medico(4, "Nicole Araujo Almeida", 29, "3149-RN");
//		Medico medico2 = new Medico(5, "Thiago Santos Correia", 34, "5469-RN");
//		
//		daoParticipante.cadastrar(paciente1);
//		daoParticipante.cadastrar(paciente2);
//		daoParticipante.cadastrar(paciente3);
//		daoParticipante.cadastrar(medico1);
//		daoParticipante.cadastrar(medico2);
//		
//		Evento consulta1 = new Evento();
//		Evento consulta2 = new Evento();
//		
//		consulta1.setId(1);
//		consulta1.setTitulo("Consulta - Dra. Nicole");
//		consulta1.setDescricao("Consulta com a Dra. Nicole, para realização de exames gerais.");
//		consulta1.setDataInicio(c1);
//		consulta1.setDataFim(c2);
//		consulta1.setLocal(local1);
//		consulta1.setStatus(StatusEvento.PENDENTE);
//		
//		consulta2.setId(2);
//		consulta2.setTitulo("Consulta - Dr. Thiago");
//		consulta2.setDescricao("Consulta com a Dr. Thiago, para realização de exame cardiólogico.");
//		consulta2.setDataInicio(c3);
//		consulta2.setDataFim(c4);
//		consulta2.setLocal(local2);
//		consulta2.setStatus(StatusEvento.PENDENTE);
//	
//	
//		daoEvento.cadastrar(consulta1);
//		daoEvento.cadastrar(consulta2);
//		
//		Inscricao inscricao1 = new Inscricao(consulta1, paciente1);
//		Inscricao inscricao2 = new Inscricao(consulta1, medico1);
//		Inscricao inscricao3 = new Inscricao(consulta2, paciente2);
//		Inscricao inscricao4 = new Inscricao(consulta2, medico2);
//		
//		daoInscricao.cadastrar(inscricao1);
//		daoInscricao.cadastrar(inscricao2);
//		daoInscricao.cadastrar(inscricao3);
//		daoInscricao.cadastrar(inscricao4);
//	}	
//	
//}
