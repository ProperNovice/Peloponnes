package controller;

import enums.EGameState;
import utils.ArrayList;
import utils.Logger;

public class Flow {

	private ArrayList<EGameState> gameStateResolving = new ArrayList<>();

	public Flow() {

		createTurn();

	}

	private void createTurn() {

		for (int counter = 1; counter <= 5; counter++) {
			this.gameStateResolving.addLast(EGameState.REVEAL_TILE);
			this.gameStateResolving.addLast(EGameState.SUPPLY_ROUND);
		}

		this.gameStateResolving.addLast(EGameState.PURCHASE_TILE_OR_PASS);
		this.gameStateResolving.addLast(EGameState.BUILD_NOW_LATER_OR_DISCARD);
		this.gameStateResolving.addLast(EGameState.SET_ONE_TIME_INCOME_PER_ROUND);
		this.gameStateResolving.addLast(EGameState.EARN_INCOME_FOR_THE_ROUND);

		for (int counter = 1; counter <= 2; counter++)
			this.gameStateResolving.addLast(EGameState.REVEAL_DISASTER_CHIT);

		this.gameStateResolving.addLast(EGameState.END_ROUND);

	}

	public void proceed() {

		EGameState eGameState = this.gameStateResolving.removeFirst();
		this.gameStateResolving.addLast(eGameState);

		if (!eGameStateShallResolve(eGameState)) {

			Logger.INSTANCE.log(eGameState);
			Logger.INSTANCE.logNewLine("skipped");
			proceed();
			return;

		} else
			ControllerSingleton.INSTANCE.gameState.setGameState(eGameState);

	}

	private boolean eGameStateShallResolve(EGameState eGameState) {

		switch (eGameState) {

		case BUILD_NOW_LATER_OR_DISCARD:
			return ControllerSingleton.INSTANCE.modifiers.tileToBuy != null;

		case SET_ONE_TIME_INCOME_PER_ROUND:
			return ControllerSingleton.INSTANCE.modifiers.tileToBuy != null;

		case SUPPLY_ROUND:
			return ControllerSingleton.INSTANCE.modifiers.supplyRound;

		default:
			return true;

		}

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
