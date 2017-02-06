package tarefaprincipal;

public class HardwareCartaoException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HardwareCartaoException() {
		super("Falhou ao fazer leitura do cartão");
	}
}
