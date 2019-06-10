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
	SUPPLY_ROUND(new SupplyRound()),

	;

	private AGameState gameState = null;

	private EGameState(AGameState gameState) {
		this.gameState = gameState;
	}

	public AGameState getGameState() {
		return this.gameState;
	}

}
