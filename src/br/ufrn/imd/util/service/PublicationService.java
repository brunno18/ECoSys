package br.ufrn.imd.util.service;

import br.ufrn.imd.model.Publicacao;
import br.ufrn.imd.util.dao.IDAOCredential;
import br.ufrn.imd.util.model.Credential;

public abstract class PublicationService {
	
	protected IDAOCredential daoCredential;
	
	protected Credential credential;
	
	public abstract void publicarEvento(Publicacao publicacao) throws PublicationException;
	public abstract void setCredential(Credential credential);
	protected abstract void validateCredential() throws ValidateCredentialException;
}
