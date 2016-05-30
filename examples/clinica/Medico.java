package clinica;

import br.ufrn.imd.model.Participante;

public class Medico extends Participante {
	
	private String crm;
	
	private String especialidade;
	
	private String telefone;
	
	public Medico(String name, String telefone, String crm, String especialidade) {
		super(name);
		this.setCrm(crm);
		this.setEspecialidade(especialidade);
		this.setTelefone(telefone);
	}

	public String getCrm(){
		return crm;
	}
	
	public void setCrm(String crm){
		this.crm = crm;
	}
	
	public String getEspecialidade(){
		return especialidade;
	}
	
	public void setEspecialidade(String especialidade){
		this.especialidade = especialidade;
	}
	
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}
