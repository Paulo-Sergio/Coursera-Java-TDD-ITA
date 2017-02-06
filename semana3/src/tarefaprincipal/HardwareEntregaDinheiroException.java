package tarefaprincipal;

public class HardwareEntregaDinheiroException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HardwareEntregaDinheiroException() {
		super("Caixa falhou ao fazer a entrega do dinheiro");
	}
}
