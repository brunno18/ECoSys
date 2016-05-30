package clinica;

import java.util.Calendar;

import br.ufrn.imd.model.Evento;
import br.ufrn.imd.model.Local;
import br.ufrn.imd.model.StatusEvento;

public class Consulta extends Evento {
	
	//Duração padrão da consulta
	public static int DURACAO_PADRAO = 30;
	
	//Duração da consulta em minutos
	private int duracao;

	public Consulta(String titulo, String descricao, Local local, Calendar dataInicio) {
		this.setTitulo(titulo);
		this.setDescricao(descricao);
		this.setLocal(local);
		this.setDataInicio(dataInicio);
		
		this.setDataFim((Calendar)dataInicio.clone());
		this.getDataFim().add(Calendar.MINUTE, duracao);
		
		this.setStatus(StatusEvento.PENDENTE);
	}
}
