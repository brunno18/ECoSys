package br.ufrn.imd.util.service;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.exception.FacebookException;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.types.FacebookType;

import br.ufrn.imd.model.Publicacao;
import br.ufrn.imd.util.dao.IDAOCredential;
import br.ufrn.imd.util.model.Credential;

public class FacebookPublicationService extends PublicationService {
	
	public FacebookPublicationService (IDAOCredential daoCredential) {
		this.daoCredential = daoCredential;
	}

	@Override
	public void publicarEvento(Publicacao publicacao) throws PublicationException {
		
		try {
			validateCredential();
			FacebookClient facebookClient = new DefaultFacebookClient(credential.getAccessToken(), Version.LATEST);
			
			AccessToken accessToken = facebookClient.obtainExtendedAccessToken(credential.getAppId(), credential.getAppSecret());
			
			FacebookType publishMessageResponse =
					 facebookClient.publish("me/feed", FacebookType.class,
					   Parameter.with("message", publicacao.getMensagem()));
		} catch (ValidateCredentialException ex) {
			
		} catch (FacebookException ex) {
			
		}
	}

	@Override
	protected void validateCredential() throws ValidateCredentialException {
		
	}

	@Override
	public void setCredential(Credential credential) {
		this.credential = credential;
	}

}
