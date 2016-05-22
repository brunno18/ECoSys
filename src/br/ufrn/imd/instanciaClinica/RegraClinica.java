package br.ufrn.imd.instanciaClinica;

import java.util.*;
import br.ufrn.imd.controller.*;
import br.ufrn.imd.dao.*;
import br.ufrn.imd.model.*;

public class RegraClinica implements RegraParticipacao{
	GerenciadorEvento gerenciadorEvento;
	IDAOInscricao daoInscricao;
	List<Date> listaDatas = new ArrayList<Date>();
	
	@Override
	public List<Date> getDiasParticipante(int idPart) {
		List<Inscricao> inscricoes = new ArrayList<Inscricao>();
		inscricoes = daoInscricao.listarInscricoesParticipante(idPart);
		Evento evento;
		
		Iterator<Inscricao> iter = inscricoes.iterator();
		while(iter.hasNext()){
			Inscricao insc = iter.next();
			evento = insc.getEvento();
			listaDatas.add(evento.getDataInicio());
		}
		return listaDatas;
	}
	
		
	
	
	@Override
	public boolean validarParticipacao(Participante participante) throws ValidatePartipationException {
		
		//########### FALTA CONCLUIR ##############
		
		/*if(participante instanceof Paciente){
			if(((Paciente) participante).getNumeroPlanoSaude() != null){
				if(listaDatas){
				return true;
			
				}
			}
		}	
		else if(participante instanceof Medico){
			if(((Medico) participante).getCrm() != null){
				return true;
			}
		}*/
		
		return false;
	}
}
