package br.com.avantews.services.exception;

public class IntegridadeDeDadosException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IntegridadeDeDadosException(String mensagem) {
		super(mensagem);
	}
	public IntegridadeDeDadosException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	

}
