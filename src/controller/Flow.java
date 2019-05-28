package controller;

import enums.EGameState;
import utils.ArrayList;
import utils.Logger;

public class Flow {

	private ArrayList<EGameState> gameStateResolving = new ArrayList<>();

	public Flow() {

	}

	public void proceed() {

		EGameState gameStateEnum = this.gameStateResolving.removeFirst();
		ControllerSingleton.INSTANCE.gameState.setGameState(gameStateEnum);

	}

	public void addFirst(EGameState eGameState) {
		this.gameStateResolving.addFirst(eGameState);
	}

	public void addFirstProceed(EGameState eGameState) {

		addFirst(eGameState);
		proceed();

	}

	public void print() {

		Logger.INSTANCE.log("flow");
		this.gameStateResolving.printList();
	}

}
