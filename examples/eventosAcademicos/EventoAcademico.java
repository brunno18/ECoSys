package eventosAcademicos;

import java.util.Calendar;

import br.ufrn.imd.model.Evento;
import br.ufrn.imd.model.Local;
import br.ufrn.imd.model.StatusEvento;

public class EventoAcademico extends Evento{
	
	String tipoEvento;
	boolean isGratuito;
	float precoIngressoEvento;
	
	public EventoAcademico(String titulo, String descricao, Local local, Calendar dataInicio, Calendar dataFim, 
			String tipoEvento, boolean isGratuito, float precoEvento){
		
		this.setTitulo(titulo);
		this.setDescricao(descricao);
		this.setLocal(local);
		this.setDataInicio(dataInicio);
		this.setDataFim(dataFim);
		this.tipoEvento = tipoEvento;
		this.isGratuito = isGratuito;
		this.precoIngressoEvento = precoEvento;
		
		this.setStatus(StatusEvento.PENDENTE);
		
	}
}
