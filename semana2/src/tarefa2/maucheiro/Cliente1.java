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
	
	// Qual � o desconto, se cliente j� subsidiado ? (250)
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
