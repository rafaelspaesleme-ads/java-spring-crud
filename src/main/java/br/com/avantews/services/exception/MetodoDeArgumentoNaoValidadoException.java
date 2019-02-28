package br.com.avantews.services.exception;

public class MetodoDeArgumentoNaoValidadoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MetodoDeArgumentoNaoValidadoException(String mensagem) {
		super(mensagem);
	}
	public MetodoDeArgumentoNaoValidadoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	

}
