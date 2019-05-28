package controller;

import enums.EGameState;
import gameState.AGameState;
import utils.Logger;

public class GameStateManager {

	private AGameState currentGameState = null;

	public GameStateManager() {

	}

	public void setGameState(EGameState eGameState) {

		this.currentGameState = eGameState.getGameState();

		Logger.INSTANCE.log("changing gameState");
		Logger.INSTANCE.logNewLine(eGameState);

		this.currentGameState.handleGameStateChange();

	}

	public AGameState getCurrentGameState() {
		return this.currentGameState;
	}

}
