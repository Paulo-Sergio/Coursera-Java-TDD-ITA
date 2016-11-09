package tarefa2.maucheiro;

public class Cliente {

	boolean age;
	boolean sub;
	boolean notFtime;

	public Cliente(boolean age, boolean sub, boolean notFtime) {
		this.age = age;
		this.sub = sub;
		this.notFtime = notFtime;
	}
	
	/**
	 * 5 PROBLEMAS DESSE METODO
	 * 1- Nomes inapropriados
	 * 2- Expressoes Booleanas negativas
	 * 3- Comentarios (j� tirei)
	 * 4- C�digo Duplicado
	 * 5- Ifs Aninhados
	 */

	// Qual � o desconto, se cliente j� subsidiado ? (250)
	public int calculateDiscount() {
		int discount;
		if (!age) {
			if (!sub) {
				if (!notFtime) {
					discount = 500;
				} else {
					discount = 250;
				}
			} else {
				discount = 250;
			}
		} else {
			discount = 0;
		}
		return discount;
	}
}
