package tarefa2.maucheiro;

public class Cliente4 {

	boolean isSenior;
	boolean isAleadySubsidized;
	boolean isPartTime;

	public Cliente4(boolean age, boolean sub, boolean notFtime) {
		this.isSenior = age;
		this.isAleadySubsidized = sub;
		this.isPartTime = notFtime;
	}

	/**
	 * 4- Codigo duplicado (discount)
	 */

	// Qual � o desconto, se cliente j� subsidiado ? (250)
	public int calculateDiscount() {
		if (isSenior) return 0;
		else {
			if (isAleadySubsidized) return 250;
			else {
				if (isPartTime) return 250;
				else return 500;
			}
		}
	}
}
