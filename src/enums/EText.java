package enums;

public enum EText {

	CONTINUE("Continue", TextTypeEnum.OPTION),
	RESTART("Restart", TextTypeEnum.OPTION),
	REVEAL_TILES("Reveal tiles", TextTypeEnum.OPTION),
	PURCHASE_TILE("Choose tile to purchase", TextTypeEnum.INDICATOR),
	PASS("Pass", TextTypeEnum.OPTION),
	BUILD_NOW("Build now", TextTypeEnum.OPTION),
	BUILD_LATER("Build later", TextTypeEnum.OPTION),
	DISCARD("Discard", TextTypeEnum.OPTION),
	EARN_INCOME("Earn income", TextTypeEnum.OPTION),
	SUPPLY_ROUND("Supply round", TextTypeEnum.INDICATOR),
	FEED_POPULATION("Feed population", TextTypeEnum.OPTION),
	CHOOSE_BUILDING_TO_BUILD("Choose building to build", TextTypeEnum.INDICATOR),
	
	;

	private String string = null;
	private TextTypeEnum textTypeEnum = null;

	public enum TextTypeEnum {
		INDICATOR, OPTION
	}

	private EText(String string, TextTypeEnum textTypeEnum) {
		this.string = string;
		this.textTypeEnum = textTypeEnum;
	}

	public String getString() {
		return this.string;
	}

	public TextTypeEnum getTextTypeEnum() {
		return this.textTypeEnum;
	}

}
