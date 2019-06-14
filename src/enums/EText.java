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
	DON_T_BUILD_THE_REST("Don't build the rest", TextTypeEnum.OPTION),
	DRAW_DISASTER_CHIT("Draw disaster chit", TextTypeEnum.OPTION),
	RESOLVE_PLAGUE("Resolve plague", TextTypeEnum.OPTION),
	RESOLVE_EARTHQUAKE("Resolve earthquake", TextTypeEnum.OPTION),
	RESOLVE_DRAUGHT("Resolve draught", TextTypeEnum.OPTION),
	RESOLVE_TEMPEST("Resolve tempest", TextTypeEnum.OPTION),
	RESOLVE_DECLINE("Resolve decline", TextTypeEnum.OPTION),
	
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
