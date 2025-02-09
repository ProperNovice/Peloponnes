package controller;

import enums.EDisaster;
import enums.EGameState;
import enums.EResource;
import enums.ETileAbility;
import interfaces.AbilityAble;
import interfaces.ITile;
import model.TileDisaster;
import utils.ArrayList;
import utils.Logger;
import utils.ShutDown;

public class Flow {

	private ArrayList<EGameState> gameStateResolving = new ArrayList<>();

	public Flow() {
		createTurns();
	}

	public void createTurns() {

		this.gameStateResolving.clear();

		this.gameStateResolving.addLast(EGameState.CHOOSE_CIVILIZATION);

		for (int games = 1; games <= 5; games++) {

			this.gameStateResolving.addLast(EGameState.ADD_CIVILIZATION_AND_STARTING_RESOURCES);

			this.gameStateResolving.addLast(EGameState.START_NEW_CAMPAIGN_LEVEL);

			for (int turns = 1; turns <= ControllerSingleton.INSTANCE.modifiers.totalTurns; turns++) {

				for (int counter = 1; counter <= ControllerSingleton.INSTANCE.modifiers.tilesRevealed; counter++) {
					this.gameStateResolving.addLast(EGameState.REVEAL_TILE);
					this.gameStateResolving.addLast(EGameState.SUPPLY_ROUND_TILE);
				}

				this.gameStateResolving.addLast(EGameState.PURCHASE_TILE_OR_PASS);
				this.gameStateResolving.addLast(EGameState.BUILD_NOW_LATER_OR_DISCARD);
				this.gameStateResolving.addLast(EGameState.SET_ONE_TIME_INCOME_PER_ROUND);
				this.gameStateResolving.addLast(EGameState.EARN_INCOME_FOR_THE_ROUND);

				this.gameStateResolving.addLast(EGameState.REVEAL_SACRUM_CHITS);
				this.gameStateResolving.addLast(EGameState.RESOLVE_SACRIFICE_INHABITANTS);

				for (int counter = 1; counter <= 2; counter++) {
					this.gameStateResolving.addLast(EGameState.REVEAL_DISASTER_CHIT);
					this.gameStateResolving.addLast(EGameState.RESOLVE_DISASTER);
				}

				this.gameStateResolving.addLast(EGameState.END_ROUND);

			}

			this.gameStateResolving.addLast(EGameState.SUPPLY_ROUND_END_GAME);
			this.gameStateResolving.addLast(EGameState.END_CAMPAIGN_LEVEL);
			this.gameStateResolving.addLast(EGameState.RESTART_GAME);

		}

	}

	public void proceed() {

		ControllerSingleton.INSTANCE.gameState.setGameState(EGameState.SCORE);

		EGameState eGameState = this.gameStateResolving.removeFirst();

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

		case SUPPLY_ROUND_TILE:
			return ControllerSingleton.INSTANCE.modifiers.supplyRound;

		case REVEAL_SACRUM_CHITS:
			return revealSacrumChits();

		case RESOLVE_SACRIFICE_INHABITANTS:
			return sacrificeInhabitants();

		case RESOLVE_DISASTER:
			return resolveDisaster();

		case REVEAL_DISASTER_CHIT:
			return revealDisasterChit();

		default:
			return true;

		}

	}

	private boolean resolveDisaster() {

		EDisaster eDisaster = ControllerSingleton.INSTANCE.modifiers.eDisasterDrawn;

		if (eDisaster == null)
			return false;

		if (eDisaster == EDisaster.BLANK)
			return false;

		for (TileDisaster tileDisaster : ControllerSingleton.INSTANCE.disasterTiles.getArrayList())
			if (tileDisaster.getEDisaster() == eDisaster)
				return tileDisaster.getList().getArrayList().isMaxedCapacity();

		ShutDown.INSTANCE.execute("resolve disaster check didn't find disaster");

		return true;
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

	private boolean revealSacrumChits() {

		for (ITile iTile : ControllerSingleton.INSTANCE.board.getArrayList()) {

			AbilityAble abilityAble = (AbilityAble) iTile;

			if (!abilityAble.getTileAbility().contains(ETileAbility.SACRIFICE_INHABITANTS_EACH_ROUND))
				continue;

			ControllerSingleton.INSTANCE.modifiers.sacrificeInhabitants = true;
			return false;

		}

		return true;

	}

	private boolean sacrificeInhabitants() {

		if (ControllerSingleton.INSTANCE.resources.getCurrentAmount(EResource.POPULATION_GAIN) == 0)
			return false;
		else
			return ControllerSingleton.INSTANCE.modifiers.sacrificeInhabitants;

	}

	private boolean revealDisasterChit() {

		for (ITile iTile : ControllerSingleton.INSTANCE.board.getArrayList()) {

			AbilityAble abilityAble = (AbilityAble) iTile;

			if (abilityAble.getTileAbility().contains(ETileAbility.PROTECTION_FROM_DISASTERS))
				return false;

		}

		return true;

	}

}
