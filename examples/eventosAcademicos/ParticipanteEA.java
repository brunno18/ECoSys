package eventosAcademicos;

import br.ufrn.imd.model.Participante;

public class ParticipanteEA extends Participante{
	
	private int matricula;
	
	private String universidade;
	
	private String formacao;
	
	private String telefone;
	
	private String cpf;
	
	private String rg;
	
	
	
	public ParticipanteEA(String name, int matricula, String universidade, String formacao, String telefone, String cpf, String rg) {
		super(name);
		this.setMatricula(matricula);
		this.setUniversidade(universidade);
		this.setFormacao(formacao);
		this.setTelefone(telefone);
		this.setCpf(cpf);
		this.setRg(rg);
	}

	
	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}


	public String getUniversidade() {
		return universidade;
	}

	public void setUniversidade(String universidade) {
		this.universidade = universidade;
	}


	public String getFormacao() {
		return formacao;
	}

	public void setFormacao(String formacao) {
		this.formacao = formacao;
	}


	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
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
