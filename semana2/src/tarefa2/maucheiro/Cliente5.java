package tarefa2.maucheiro;

public class Cliente5 {

	boolean isSenior;
	boolean isAleadySubsidized;
	boolean isPartTime;

	public Cliente5(boolean age, boolean sub, boolean notFtime) {
		this.isSenior = age;
		this.isAleadySubsidized = sub;
		this.isPartTime = notFtime;
	}

	/**
	 * 5- Consertando Ifs aninhados
	 */

	// Qual é o desconto, se cliente já subsidiado ? (250)
	public int calculateDiscount() {
		if (isSenior) return 0;
		if (isAleadySubsidized) return 250;
		if (isPartTime) return 250;
		return 500;
	}
}
