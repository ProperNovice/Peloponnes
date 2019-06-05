package enums;

import gameState.*;

public enum EGameState {

	START_GAME(new StartGame()),
	GAME_WON(new GameWon()),
	GAME_LOST(new GameLost()),
	FLOW_HANDLER(new FlowHandler()),
	CHOOSE_CIVILIZATION(new ChooseCivilization()),
	REVEAL_TILES(new RevealTiles()),

	;

	private AGameState gameState = null;

	private EGameState(AGameState gameState) {
		this.gameState = gameState;
	}

	public AGameState getGameState() {
		return this.gameState;
	}

}
