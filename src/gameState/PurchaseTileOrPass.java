package gameState;

import enums.EResource;
import enums.EText;
import enums.ETileAbility;
import interfaces.AbilityAble;
import interfaces.BuyCostAble;
import interfaces.ITile;
import interfaces.ITileCivilization;
import interfaces.ITileLand;
import interfaces.IncomeAble;
import model.ATileRow;
import model.Tile;
import utils.ImageViewAble;
import utils.Logger;

public class PurchaseTileOrPass extends AGameState {

	@Override
	public void handleGameStateChange() {

		super.controllerSingleton.text.showText(EText.PURCHASE_TILE);
		super.controllerSingleton.text.showText(EText.PASS);

	}

	@Override
	protected void handleTilePressedRowNormal(ITile iTile) {
		handleTilePressed(iTile, 0);
	}

	@Override
	protected void handleTilePressedRowCoquest(ITile iTile) {
		handleTilePressed(iTile, 3);
	}

	@Override
	protected void executeTextOption(EText textEnum) {

		super.controllerSingleton.resources.addCurrentAmount(EResource.COIN, 3);

		clearTileRowsSetVisibleFalse();

		super.controllerSingleton.flow.proceed();

	}

	private void handleTilePressed(ITile iTile, int additionalCost) {

		if (iTile instanceof ITileLand)
			if (!canPlaceLandTile(iTile))
				return;

		BuyCostAble buyCostAble = (BuyCostAble) iTile;

		int buyCost = buyCostAble.getBuyCost();
		buyCost += additionalCost;

		int currentCoins = super.controllerSingleton.resources.getCurrentAmount(EResource.COIN);
		int luxuryGoods = super.controllerSingleton.resources.getCurrentAmount(EResource.LUXURY_GOODS);
		int totalCoins = currentCoins + luxuryGoods / 2;

		Logger.INSTANCE.log("tile cost - " + buyCostAble.getBuyCost());
		Logger.INSTANCE.log("additional cost - " + additionalCost);
		Logger.INSTANCE.log("current coins - " + currentCoins);
		Logger.INSTANCE.log("current luxury goods - " + luxuryGoods);
		Logger.INSTANCE.log("total cost - " + buyCost);
		Logger.INSTANCE.log("coins available - " + (currentCoins + luxuryGoods / 2));
		Logger.INSTANCE.newLine();

		if (totalCoins < buyCost) {
			Logger.INSTANCE.logNewLine("cannot buy the tile");
			return;
		}

		int coinsToRemove = Math.min(currentCoins, buyCost);
		int luxuryGoodsToRemove = 0;

		buyCost -= coinsToRemove;

		if (buyCost > 0)
			luxuryGoodsToRemove = buyCost * 2;

		clearTileRowsSetVisibleFalse();
		ImageViewAble imageViewAble = (ImageViewAble) iTile;
		imageViewAble.getImageView().setVisible(true);

		super.controllerSingleton.resources.removeCurrentAmount(EResource.COIN, coinsToRemove);
		super.controllerSingleton.resources.removeCurrentAmount(EResource.LUXURY_GOODS, luxuryGoodsToRemove);
		super.controllerSingleton.modifiers.tileToBuy = iTile;

		super.controllerSingleton.flow.proceed();

	}

	private boolean canPlaceLandTile(ITile iTile) {

		Logger.INSTANCE.log("checking land tile");

		for (ITile iTileTemp : super.controllerSingleton.board.getArrayList()) {

			AbilityAble abilityAble = (AbilityAble) iTileTemp;

			if (abilityAble.getTileAbility().contains(ETileAbility.IGNORE_PLACEMENT_RULES_WHEN_PLACING_LAND_TILES)) {
				Logger.INSTANCE.logNewLine("ignore placement rules ability");
				return true;
			}

		}

		ITile iTileBoardLast = super.controllerSingleton.board.getArrayList().getLast();

		if (iTileBoardLast instanceof ITileCivilization) {
			Logger.INSTANCE.logNewLine("last tile civilization");
			return true;
		}

		IncomeAble iTileLandTileToBuyIncomeAble = (IncomeAble) iTile;
		IncomeAble iTileBoardLastIncomeAble = (IncomeAble) iTileBoardLast;

		for (EResource eResource : iTileLandTileToBuyIncomeAble.getIncomePerRound())
			if (iTileBoardLastIncomeAble.getIncomePerRound().contains(eResource)) {
				Logger.INSTANCE.logNewLine("at least an income resource matches");
				return true;
			}

		Logger.INSTANCE.logNewLine("cannot buy land tile");

		return false;

	}

	private void clearTileRowsSetVisibleFalse() {

		ATileRow tileRow = null;

		tileRow = super.controllerSingleton.tileRows.getTileRowNormal();

		for (ITile iTile : tileRow.getArrayList().clone()) {

			Tile tile = (Tile) iTile;
			tile.getImageView().setVisible(false);
			tileRow.getArrayList().remove(iTile);

		}

		tileRow = super.controllerSingleton.tileRows.getTileRowConquest();

		for (ITile iTile : tileRow.getArrayList().clone()) {

			Tile tile = (Tile) iTile;
			tile.getImageView().setVisible(false);
			tileRow.getArrayList().remove(iTile);

		}

	}

}
