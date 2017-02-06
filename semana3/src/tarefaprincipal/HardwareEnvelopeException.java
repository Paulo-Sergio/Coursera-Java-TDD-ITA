package tarefaprincipal;

public class HardwareEnvelopeException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HardwareEnvelopeException() {
		super("Caixa falhou ao realizar a leitura do envelope");
	}
}
