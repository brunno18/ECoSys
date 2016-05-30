package clinica;

import br.ufrn.imd.model.Participante;

public class Paciente extends Participante{
	
	private long numeroPlanoSaude;
	
	private long prontuario;
	
	private int idade;
	
	private String telefone;
	
	private String cpf;
	
	private String rg;
	
	private boolean convenioEmDia;
		
	
	public Paciente(String nome, int idade, String cpf, String rg, String telefone, long numeroPlanoSaude, long prontuario) {
		super(nome);
		this.setCpf(cpf);
		this.setRg(rg);
		this.setNumeroPlanoSaude(numeroPlanoSaude);
		this.setProntuario(prontuario);
		this.setTelefone(telefone);
		this.setConvenioEmDia(true);
	}

	public long getNumeroPlanoSaude() {
		return numeroPlanoSaude;
	}

	public void setNumeroPlanoSaude(long numeroPlanoSaude) {
		this.numeroPlanoSaude = numeroPlanoSaude;
	}
	
	public int getIdade() {
		return idade;
	}
	
	public void setIdade(int idade) {
		this.idade = idade;
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

	public long getProntuario() {
		return prontuario;
	}

	public void setProntuario(long prontuario) {
		this.prontuario = prontuario;
	}
}
