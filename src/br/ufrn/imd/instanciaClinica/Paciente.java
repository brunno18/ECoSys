package br.ufrn.imd.instanciaClinica;

import br.ufrn.imd.model.*;

public class Paciente extends Participante{
	private Long numeroPlanoSaude;
	private String cpf;
	private String rg;
		
	
	public Paciente(int id, String nome, int idade, String cpf, String rg, Long numeroPlanoSaude) {
		super(id, nome, idade);
		this.setCpf(cpf);
		this.setRg(rg);
		this.setNumeroPlanoSaude(numeroPlanoSaude);
	}

	public Long getNumeroPlanoSaude() {
		return numeroPlanoSaude;
	}

	public void setNumeroPlanoSaude(Long numeroPlanoSaude) {
		this.numeroPlanoSaude = numeroPlanoSaude;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}
}
