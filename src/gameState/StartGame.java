package gameState;

import controller.CredentialSingleton;
import enums.EResource;
import interfaces.ITile;
import interfaces.ITileBuilding;
import interfaces.ITileCivilization;
import interfaces.ITileLand;
import interfaces.IncomeAble;
import model.Tile;
import model.TileCivilization;
import model.TilePile;
import utils.ArrayList;

public class StartGame extends AGameState {

	@Override
	public void handleGameStateChange() {

		civilization();
		board();
//		tileRows();
		addCurrentResources();
		setSupplyRound();

//		super.controllerSingleton.modifiers.eDisasterDrawn = EDisaster.TEMPEST;
//		super.controllerSingleton.flow.addFirst(EGameState.RESOLVE_DISASTER);

		super.controllerSingleton.flow.proceed();

	}

	public void civilization() {

		ITile iTile = super.controllerSingleton.civilizations.getRandomCivilization();

		TileCivilization tile = (TileCivilization) iTile;
		tile.getImageView().setWidth(CredentialSingleton.INSTANCE.DimensionsTileGame.x);

		super.controllerSingleton.board.getArrayList().addLast(iTile);
		super.controllerSingleton.board.relocateList();
		super.controllerSingleton.board.relocateImageViews();

		ArrayList<EResource> oneTimeIncome = tile.getOneTimeIncome();
		ArrayList<EResource> incomePerRound = tile.getIncomePerRound();

		super.controllerSingleton.resources.addCurrentAmount(oneTimeIncome);
		super.controllerSingleton.resources.addIncome(incomePerRound);

	}

	public void board() {

		// add tiles

		int tiles = 6;

		for (int counter = 1; counter <= tiles; counter++) {

			TilePile tilePile = super.controllerSingleton.tilePiles.getFirstNonEmptyTilePile();

			ITile iTile = tilePile.getArrayList().removeRandom();
			Tile tile = (Tile) iTile;
			tile.getImageView().setWidth(CredentialSingleton.INSTANCE.DimensionsTileGame.x);

			if (tile instanceof ITileLand)
				super.controllerSingleton.board.getArrayList().addLast(iTile);
			else if (tile instanceof ITileBuilding)
				super.controllerSingleton.board.getArrayList().addFirst(iTile);

			tilePile.relocateImageViews();

		}

		// add income

		for (ITile iTile : super.controllerSingleton.board.getArrayList()) {

			if (iTile instanceof ITileCivilization)
				continue;

			IncomeAble incomeAble = (IncomeAble) iTile;
			super.controllerSingleton.resources.addIncome(incomeAble.getIncomePerRound());

		}

		super.controllerSingleton.board.relocateList();
		super.controllerSingleton.board.relocateImageViews();

		// set unbuild

//		for (ITile iTile : super.controllerSingleton.board.getArrayList()) {
//
//			if (!(iTile instanceof BuildAble))
//				continue;
//
//			BuildAble buildAble = (BuildAble) iTile;
//			buildAble.setUnbuilt();
//
//		}

	}

	public void tileRows() {

		int tiles = 15;

		for (int counter = 1; counter <= tiles; counter++) {

			TilePile tilePile = super.controllerSingleton.tilePiles.getFirstNonEmptyTilePile();

			ITile iTile = tilePile.getArrayList().removeRandom();
			Tile tile = (Tile) iTile;
			tile.getImageView().setWidth(CredentialSingleton.INSTANCE.DimensionsTileGame.x);

			if (!super.controllerSingleton.tileRows.getTileRowNormal().getArrayList().isMaxedCapacity())
				super.controllerSingleton.tileRows.getTileRowNormal().getArrayList().addLast(iTile);
			else
				super.controllerSingleton.tileRows.getTileRowConquest().getArrayList().addLast(iTile);

			tilePile.relocateImageViews();

		}

		super.controllerSingleton.tileRows.getTileRowNormal().relocateImageViews();
		super.controllerSingleton.tileRows.getTileRowConquest().relocateImageViews();

	}

	public void addCurrentResources() {

		super.controllerSingleton.resources.addCurrentAmount(EResource.POPULATION_GAIN, 8);
		super.controllerSingleton.resources.addCurrentAmount(EResource.WOOD, 6);
		super.controllerSingleton.resources.addCurrentAmount(EResource.FOOD, 1);
		super.controllerSingleton.resources.addCurrentAmount(EResource.LUXURY_GOODS, 30);
		super.controllerSingleton.resources.addCurrentAmount(EResource.STONE, 4);
		super.controllerSingleton.resources.removeCurrentAmount(EResource.COIN, 4);

	}

	public void setSupplyRound() {
		super.controllerSingleton.modifiers.supplyRound = true;
	}

}
