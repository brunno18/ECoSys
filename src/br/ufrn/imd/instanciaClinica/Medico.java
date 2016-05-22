package br.ufrn.imd.instanciaClinica;

import br.ufrn.imd.model.*;

public class Medico extends Participante{
	String crm;
	
	public Medico(int idNum, String name, int idadeNum, String crm) {
		super(idNum, name, idadeNum);
		this.setCrm(crm);
	}

	public String getCrm(){
		return crm;
	}
	
	public void setCrm(String crm){
		this.crm = crm;
	}
}
