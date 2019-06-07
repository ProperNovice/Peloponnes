package gameState;

import enums.EResource;
import enums.EText;
import interfaces.ITile;
import interfaces.ITileBuilding;
import interfaces.ITileLand;
import utils.ArrayList;
import utils.Logger;

public class BuildNowLaterOrDiscard extends AGameState {

	private int woodCurrent, stoneCurrent, luxuryGoodsCurrent, coinsCurrent;
	private int woodNeed, stoneNeed;

	@Override
	public void handleGameStateChange() {

		if (super.controllerSingleton.modifiers.tileToBuy instanceof ITileLand) {

			handleBuildNow();
			return;

		}

		if (tileBuildingBoughtCanBeBuiltNow()) {

			Logger.INSTANCE.logNewLine("can be built now");
			super.controllerSingleton.text.showText(EText.BUILD_NOW);

		}

		if (tileBoughtCanBeBuildLater())
			super.controllerSingleton.text.showText(EText.BUILD_LATER);

		super.controllerSingleton.text.showText(EText.DISCARD);

	}

	@Override
	protected void executeTextOption(EText eText) {

		switch (eText) {

		case BUILD_NOW:
			handleBuildNow();
			break;

		case BUILD_LATER:
			handleBuildLater();
			break;

		case DISCARD:
			handleDiscard();
			break;

		default:
			break;

		}

	}

	private boolean tileBuildingBoughtCanBeBuiltNow() {

		ITile tileBought = super.controllerSingleton.modifiers.tileToBuy;
		ITileBuilding iTileBuilding = (ITileBuilding) tileBought;

		setCurrentResources();
		setResourcedNeedToConstruct(iTileBuilding);

		return checkIfTileCanBeBuiltNow();

	}

	private void setCurrentResources() {

		this.woodCurrent = super.controllerSingleton.resources.getCurrentAmount(EResource.WOOD);
		this.stoneCurrent = super.controllerSingleton.resources.getCurrentAmount(EResource.STONE);
		this.luxuryGoodsCurrent = super.controllerSingleton.resources.getCurrentAmount(EResource.LUXURY_GOODS);
		this.coinsCurrent = super.controllerSingleton.resources.getCurrentAmount(EResource.COIN);

		Logger.INSTANCE.log("current wood -> " + this.woodCurrent);
		Logger.INSTANCE.log("current stone -> " + this.stoneCurrent);
		Logger.INSTANCE.log("current luxury goods -> " + this.luxuryGoodsCurrent);

	}

	private void setResourcedNeedToConstruct(ITileBuilding iTileBuilding) {

		this.woodNeed = 0;
		this.stoneNeed = 0;

		ArrayList<EResource> resourcesNeed = iTileBuilding.getCostructionCost();

		for (EResource eResource : resourcesNeed)
			if (eResource == EResource.WOOD)
				this.woodNeed++;
			else if (eResource == EResource.STONE)
				this.stoneNeed++;

		Logger.INSTANCE.log("wood need -> " + this.woodNeed);
		Logger.INSTANCE.logNewLine("stone need -> " + this.stoneNeed);

	}

	private boolean checkIfTileCanBeBuiltNow() {

		int luxuryGoodsNeed = 0;
		luxuryGoodsNeed += 2 * Math.max(0, this.woodNeed - this.woodCurrent);
		luxuryGoodsNeed += 2 * Math.max(0, this.stoneNeed - this.stoneCurrent);

		return luxuryGoodsNeed <= this.luxuryGoodsCurrent;

	}

	private boolean tileBoughtCanBeBuildLater() {

		boolean canBeBuiltLater;

		if (this.coinsCurrent >= 1)
			canBeBuiltLater = true;
		else if (this.luxuryGoodsCurrent >= 2)
			canBeBuiltLater = true;
		else
			canBeBuiltLater = false;

		if (canBeBuiltLater)
			Logger.INSTANCE.logNewLine("can be build later");
		else
			Logger.INSTANCE.logNewLine("can't be build later");

		return canBeBuiltLater;

	}

	private void handleBuildNow() {

		int woodUsed = Math.min(this.woodCurrent, this.woodNeed);
		int woodRemaining = this.woodNeed - woodUsed;

		int stoneUsed = Math.min(this.stoneCurrent, this.woodNeed);
		int stoneRemaining = this.stoneNeed - stoneUsed;

		int luxuryGoodsUsed = (woodRemaining - stoneRemaining) * 2;

		super.controllerSingleton.resources.removeCurrentAmount(EResource.WOOD, woodUsed);
		super.controllerSingleton.resources.removeCurrentAmount(EResource.STONE, stoneUsed);
		super.controllerSingleton.resources.removeCurrentAmount(EResource.LUXURY_GOODS, luxuryGoodsUsed);

		Logger.INSTANCE.log("wood used -> " + woodUsed);
		Logger.INSTANCE.log("stone used -> " + stoneUsed);
		Logger.INSTANCE.log("luxury goods used -> " + luxuryGoodsUsed);
		Logger.INSTANCE.newLine();

		ITile iTile = super.controllerSingleton.modifiers.tileToBuy;

		if (iTile instanceof ITileLand)
			super.controllerSingleton.board.getArrayList().addLast(iTile);
		else if (iTile instanceof ITileBuilding)
			super.controllerSingleton.board.getArrayList().addFirst(iTile);

		super.controllerSingleton.board.relocateList();
		super.controllerSingleton.board.animateSynchronousLock();

		super.controllerSingleton.flow.proceed();

	}

	private void handleBuildLater() {

	}

	private void handleDiscard() {

	}

}
