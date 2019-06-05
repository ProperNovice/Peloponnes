package gameState;

import controller.CredentialSingleton;
import enums.EResource;
import interfaces.ITile;
import interfaces.ITileBuilding;
import interfaces.ITileLand;
import model.Tile;
import model.TileCivilization;
import model.TilePile;
import utils.ArrayList;

public class StartGame extends AGameState {

	@Override
	public void handleGameStateChange() {

		civilization();
//		board();
//		tileRows();
		addCurrentResources();

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

		int tiles = 10;

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

		super.controllerSingleton.board.relocateList();
		super.controllerSingleton.board.relocateImageViews();

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

		ArrayList<EResource> list = new ArrayList<EResource>();

		for (int counter = 1; counter <= 5; counter++)
			list.addLast(EResource.WOOD);
		for (int counter = 1; counter <= 5; counter++)
			list.addLast(EResource.LUXURY_GOODS);

		super.controllerSingleton.resources.addCurrentAmount(list);

	}

}
