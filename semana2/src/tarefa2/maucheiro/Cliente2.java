package tarefa2.maucheiro;

public class Cliente2 {

	boolean isSenior;
	boolean isAleadySubsidized;
	boolean isPartTime;

	public Cliente2(boolean age, boolean sub, boolean notFtime) {
		this.isSenior = age;
		this.isAleadySubsidized = sub;
		this.isPartTime = notFtime;
	}

	/**
	 * 2- Consertando Ifs com expressoes booleanas negativas
	 */

	// Qual é o desconto, se cliente já subsidiado ? (250)
	public int calculateDiscount() {
		int discount;
		if (isSenior) discount = 0;
		else {
			if (isAleadySubsidized) discount = 250;
			else {
				if (isPartTime) discount = 250;
				else discount = 500;
			}
		}
		return discount;
	}
}
