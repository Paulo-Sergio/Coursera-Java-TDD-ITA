package tarefaprincipal;

public interface ServicoRemoto {

	public ContaCorrente recuperarConta(int numeroConta);

	public void persistirConta();

}