package enums;

public enum EResource {

	WOOD("Wood"), STONE("Stone"), FOOD("Food"), LUXURY_GOODS("Lux"), COIN("Coins"), POPULATION_GAIN("Pop"),
	POPULATION_LOST(null), CHOOSE_INCOME(null);

	private String string = null;

	private EResource(String string) {
		this.string = string;
	}

	public String getString() {
		return this.string;
	}

}
