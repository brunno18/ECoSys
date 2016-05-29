package br.ufrn.imd.util.dao;

import br.ufrn.imd.util.model.Credential;

public interface IDAOCredential {
	public void createCredential(Credential credential);
	public Credential retrieveCredential(String service);
	public void updateCredential(Credential credential);
}
