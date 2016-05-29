package br.ufrn.imd.controller;

import br.ufrn.imd.model.Publicacao;
import br.ufrn.imd.util.service.PublicationException;
import br.ufrn.imd.util.service.PublicationService;

public class GerenciadorPublicacao {
	
	private PublicationService publicationService;
	
	public GerenciadorPublicacao (PublicationService publicationService) {
		this.publicationService = publicationService;
	}
	
	public void publicar(Publicacao publicacao) throws PublicationException {
		publicationService.publicarEvento(publicacao);
	}
}
