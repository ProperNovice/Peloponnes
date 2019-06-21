package enums;

import gameState.*;

public enum EGameState {

	START_GAME(new StartGame()),
	GAME_WON(new GameWon()),
	GAME_LOST(new GameLost()),
	CHOOSE_CIVILIZATION(new ChooseCivilization()),
	REVEAL_TILE(new RevealTile()),
	PURCHASE_TILE_OR_PASS(new PurchaseTileOrPass()),
	END_ROUND(new EndRound()),
	BUILD_NOW_LATER_OR_DISCARD(new BuildNowLaterOrDiscard()),
	SET_ONE_TIME_INCOME_PER_ROUND(new SetOneTimeIncomePerRound()),
	EARN_INCOME_FOR_THE_ROUND(new EarnIncomeForTheRound()),
	SUPPLY_ROUND_TILE(new SupplyRoundTile()),
	SUPPLY_ROUND_END_GAME(new SupplyRoundFinal()),
	REVEAL_DISASTER_CHIT(new RevealDisasterChit()),
	RESOLVE_DISASTER(new ResolveDisaster()),
	SCORE(new Score()),
	START_NEW_CAMPAIGN_LEVEL(new StartNewCampaignLevel()),
	END_CAMPAIGN_LEVEL(new EndCampaignLevel()),
	RESTART_GAME(new RestartGame()),
	ADD_CIVILIZATION_AND_STARTING_RESOURCES(new AddCivilizationAndStartingResources()),

	;

	private AGameState gameState = null;

	private EGameState(AGameState gameState) {
		this.gameState = gameState;
	}

	public AGameState getGameState() {
		return this.gameState;
	}

}
