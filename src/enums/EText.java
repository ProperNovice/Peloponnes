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
	SUPPLY_ROUND_FINAL("Final supply round", TextTypeEnum.INDICATOR),
	FEED_POPULATION("Feed population", TextTypeEnum.OPTION),
	CHOOSE_BUILDING_TO_BUILD("Choose building to build", TextTypeEnum.INDICATOR),
	DON_T_BUILD_THE_REST("Don't build the rest", TextTypeEnum.OPTION),
	DRAW_DISASTER_CHIT("Draw disaster chit", TextTypeEnum.OPTION),
	RESOLVE_PLAGUE("Resolve plague", TextTypeEnum.OPTION),
	RESOLVE_EARTHQUAKE("Resolve earthquake", TextTypeEnum.OPTION),
	RESOLVE_DRAUGHT("Resolve draught", TextTypeEnum.OPTION),
	RESOLVE_TEMPEST("Resolve tempest", TextTypeEnum.OPTION),
	RESOLVE_DECLINE("Resolve decline", TextTypeEnum.OPTION),
	CHOOSE_TILE_TO_PROTECT("Choose tile to protect", TextTypeEnum.INDICATOR),
	PROTECT_ALL_TILES("Protect all tiles", TextTypeEnum.OPTION),
	PROTECTION_FROM_PLAGUE("Protection from plague", TextTypeEnum.INDICATOR),
	PROTECTION_FROM_EARTHQUAKE("Protection from earthquake", TextTypeEnum.INDICATOR),
	PROTECTION_FROM_DRAUGHT("Protection from draught", TextTypeEnum.INDICATOR),
	PROTECTION_FROM_TEMPEST("Protection from tempest", TextTypeEnum.INDICATOR),
	PROTECTION_FROM_DECLINE("Protection from decline", TextTypeEnum.INDICATOR),
	CONTINUE_PROTECTION("Continue", TextTypeEnum.OPTION),
	CHOOSE_INCOME("Choose income", TextTypeEnum.INDICATOR),
	COIN("Coin", TextTypeEnum.OPTION),
	STONE("Stone", TextTypeEnum.OPTION),
	WOOD("Wood", TextTypeEnum.OPTION),
	FOOD("Food", TextTypeEnum.OPTION),
	START_CAMPAIGN_LEVEL_1("Start campaign level 1", TextTypeEnum.OPTION),
	START_CAMPAIGN_LEVEL_2("Start campaign level 2", TextTypeEnum.OPTION),
	START_CAMPAIGN_LEVEL_3("Start campaign level 3", TextTypeEnum.OPTION),
	START_CAMPAIGN_LEVEL_4("Start campaign level 4", TextTypeEnum.OPTION),
	START_CAMPAIGN_LEVEL_5("Start campaign level 5", TextTypeEnum.OPTION),
	YOU_WON("You won", TextTypeEnum.INDICATOR),
	YOU_LOST("You lost", TextTypeEnum.INDICATOR),
	DRAW_SACRUM_CHITS("Draw sacrum chits", TextTypeEnum.OPTION),
	SACRIFICE_INHABITANT("Sacrifice inhabitant", TextTypeEnum.OPTION),
	
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
