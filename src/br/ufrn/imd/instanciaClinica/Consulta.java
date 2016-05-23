package br.ufrn.imd.instanciaClinica;

import br.ufrn.imd.model.Evento;

public class Consulta extends Evento {
	
	//Tempo padrão de duração de uma consulta em minutos
	public static final int TEMPO = 45;
	
	//Tempo de duração vigente para a consulta
	private int tempo;
	
	public Consulta () {
		super();
		this.tempo = TEMPO;
	}
	
	public int getTempo() {
		return tempo;
	}
	
	public void setTempo(int tempo) {
		this.tempo = tempo;
	}
}
