package br.ufrn.imd.instanciaClinica;

import br.ufrn.imd.model.*;

public class Paciente extends Participante{
	
	private Long numeroPlanoSaude;
	
	private int prontuario;
	
	private String telefone;
	
	private String cpf;
	
	private String rg;
	
	private boolean convenioEmDia;
		
	
	public Paciente(int id, String nome, int idade, String cpf, String rg, Long numeroPlanoSaude, int prontuario, String telefone) {
		super(id, nome, idade);
		this.setCpf(cpf);
		this.setRg(rg);
		this.setNumeroPlanoSaude(numeroPlanoSaude);
		this.setProntuario(prontuario);
		this.setTelefone(telefone);
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
	
	public boolean isConvenioEmDia() {
		return convenioEmDia;
	}

	public void setConvenioEmDia(boolean convenioEmDia) {
		this.convenioEmDia = convenioEmDia;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public int getProntuario() {
		return prontuario;
	}

	public void setProntuario(int prontuario) {
		this.prontuario = prontuario;
	}
}
