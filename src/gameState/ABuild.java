package gameState;

import enums.EResource;
import interfaces.ITile;
import interfaces.ITileBuilding;
import utils.ArrayList;
import utils.Logger;

public abstract class ABuild extends AGameState {

	protected int woodCurrent, stoneCurrent, luxuryGoodsCurrent, coinsCurrent;
	protected int woodCost, stoneCost;

	protected boolean tileBuildingCanBeBuiltNow(ITile iTile) {

		ITileBuilding iTileBuilding = (ITileBuilding) iTile;

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

		this.woodCost = 0;
		this.stoneCost = 0;

		ArrayList<EResource> resourcesNeed = iTileBuilding.getCostructionCost();

		for (EResource eResource : resourcesNeed)
			if (eResource == EResource.WOOD)
				this.woodCost++;
			else if (eResource == EResource.STONE)
				this.stoneCost++;

		Logger.INSTANCE.log("wood cost -> " + this.woodCost);
		Logger.INSTANCE.logNewLine("stone cost -> " + this.stoneCost);

	}

	private boolean checkIfTileCanBeBuiltNow() {

		int luxuryGoodsCost = 0;
		luxuryGoodsCost += 2 * Math.max(0, this.woodCost - this.woodCurrent);
		luxuryGoodsCost += 2 * Math.max(0, this.stoneCost - this.stoneCurrent);

		return luxuryGoodsCost <= this.luxuryGoodsCurrent;

	}

	protected void executeBuildResources() {

		int woodUsed = Math.min(this.woodCurrent, this.woodCost);
		int woodRemaining = this.woodCost - woodUsed;

		int stoneUsed = Math.min(this.stoneCurrent, this.stoneCost);
		int stoneRemaining = this.stoneCost - stoneUsed;

		int luxuryGoodsUsed = (woodRemaining + stoneRemaining) * 2;

		super.controllerSingleton.resources.removeCurrentAmount(EResource.WOOD, woodUsed);
		super.controllerSingleton.resources.removeCurrentAmount(EResource.STONE, stoneUsed);
		super.controllerSingleton.resources.removeCurrentAmount(EResource.LUXURY_GOODS, luxuryGoodsUsed);

		Logger.INSTANCE.log("wood used -> " + woodUsed);
		Logger.INSTANCE.log("stone used -> " + stoneUsed);
		Logger.INSTANCE.log("luxury goods used -> " + luxuryGoodsUsed);
		Logger.INSTANCE.newLine();

	}

}
