package tarefa2.maucheiro;

public class Cliente1 {

	boolean isSenior;
	boolean isAleadySubsidized;
	boolean isPartTime;

	public Cliente1(boolean age, boolean sub, boolean notFtime) {
		this.isSenior = age;
		this.isAleadySubsidized = sub;
		this.isPartTime = notFtime;
	}
	
	/**
	 * 1- Consertando Nomes inapropriados
	 */
	
	// Qual é o desconto, se cliente já subsidiado ? (250)
	public int calculateDiscount() {
		int discount;
		if (!isSenior) {
			if (!isAleadySubsidized) {
				if (!isPartTime) {
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
