package gameState;

import enums.EResource;
import interfaces.ConstructAble;
import interfaces.ITile;
import utils.ArrayList;
import utils.Logger;

public abstract class ABuildTile extends AGameState {

	protected int woodCurrent, stoneCurrent, luxuryGoodsCurrent, coinsCurrent, foodCurrent;
	protected int woodCost, stoneCost, luxuryGoodsCost, coinsCost, foodCost;

	protected boolean tileBuildingCanBeBuiltNow(ITile iTile) {

		ConstructAble constructAble = (ConstructAble) iTile;

		setResourcedNeedToConstruct(constructAble);
		setCurrentResources();

		return checkIfTileCanBeBuiltNow();

	}

	private void setResourcedNeedToConstruct(ConstructAble constructAble) {

		this.woodCost = 0;
		this.stoneCost = 0;
		this.coinsCost = 0;
		this.luxuryGoodsCost = 0;
		this.foodCost = 0;

		ArrayList<EResource> resourcesNeed = constructAble.getCostructionCost();

		for (EResource eResource : resourcesNeed)
			if (eResource == EResource.WOOD)
				this.woodCost++;
			else if (eResource == EResource.STONE)
				this.stoneCost++;
			else if (eResource == EResource.COIN)
				this.coinsCost++;
			else if (eResource == EResource.LUXURY_GOODS)
				this.luxuryGoodsCost++;
			else if (eResource == EResource.FOOD)
				this.foodCost++;

		logIfGreaterThanZero("wood cost -> ", this.woodCost);
		logIfGreaterThanZero("stone cost -> ", this.stoneCost);
		logIfGreaterThanZero("luxury goods cost -> ", this.luxuryGoodsCost);
		logIfGreaterThanZero("coin cost -> ", this.coinsCost);
		logIfGreaterThanZero("food cost -> ", this.foodCost);
		Logger.INSTANCE.newLine();

	}

	private void setCurrentResources() {

		this.woodCurrent = super.controllerSingleton.resources.getCurrentAmount(EResource.WOOD);
		this.stoneCurrent = super.controllerSingleton.resources.getCurrentAmount(EResource.STONE);
		this.luxuryGoodsCurrent = super.controllerSingleton.resources.getCurrentAmount(EResource.LUXURY_GOODS);
		this.coinsCurrent = super.controllerSingleton.resources.getCurrentAmount(EResource.COIN);
		this.foodCurrent = super.controllerSingleton.resources.getCurrentAmount(EResource.FOOD);

		logIfCostGreaterThanZero("current wood -> ", this.woodCurrent, this.woodCost);
		logIfCostGreaterThanZero("current stone -> ", this.stoneCurrent, this.stoneCost);
		logIfCostGreaterThanZero("current luxury goods -> ", this.luxuryGoodsCurrent, this.luxuryGoodsCost);
		logIfCostGreaterThanZero("current coins -> ", this.coinsCurrent, this.coinsCost);
		logIfCostGreaterThanZero("current food -> ", this.foodCurrent, this.foodCost);
		Logger.INSTANCE.newLine();

	}

	private boolean checkIfTileCanBeBuiltNow() {

		int luxuryGoodsCost = this.luxuryGoodsCost;
		luxuryGoodsCost += 2 * Math.max(0, this.woodCost - this.woodCurrent);
		luxuryGoodsCost += 2 * Math.max(0, this.stoneCost - this.stoneCurrent);
		luxuryGoodsCost += 2 * Math.max(0, this.coinsCost - this.coinsCurrent);
		luxuryGoodsCost += 2 * Math.max(0, this.foodCost - this.foodCurrent);

		return luxuryGoodsCost <= this.luxuryGoodsCurrent;

	}

	protected void executeBuildResources() {

		int woodUsed = Math.min(this.woodCurrent, this.woodCost);
		int woodRemaining = this.woodCost - woodUsed;

		int stoneUsed = Math.min(this.stoneCurrent, this.stoneCost);
		int stoneRemaining = this.stoneCost - stoneUsed;

		int coinsUsed = Math.min(this.coinsCurrent, this.coinsCost);
		int coinsRemaining = this.coinsCost - coinsUsed;

		int foodUsed = Math.min(this.foodCurrent, this.foodCost);
		int foodRemaining = this.foodCost - foodUsed;

		int luxuryGoodsUsed = this.luxuryGoodsCost
				+ (woodRemaining + stoneRemaining + coinsRemaining + foodRemaining) * 2;

		super.controllerSingleton.resources.removeCurrentAmount(EResource.WOOD, woodUsed);
		super.controllerSingleton.resources.removeCurrentAmount(EResource.STONE, stoneUsed);
		super.controllerSingleton.resources.removeCurrentAmount(EResource.LUXURY_GOODS, luxuryGoodsUsed);
		super.controllerSingleton.resources.removeCurrentAmount(EResource.COIN, coinsUsed);
		super.controllerSingleton.resources.removeCurrentAmount(EResource.FOOD, foodUsed);

		logIfGreaterThanZero("wood used -> ", woodUsed);
		logIfGreaterThanZero("stone used -> ", stoneUsed);
		logIfGreaterThanZero("luxury goods used -> ", luxuryGoodsUsed);
		logIfGreaterThanZero("coin used -> ", coinsUsed);
		logIfGreaterThanZero("food used -> ", foodUsed);
		Logger.INSTANCE.newLine();

	}

	private void logIfGreaterThanZero(String string, int amount) {

		if (amount == 0)
			return;

		Logger.INSTANCE.log(string + amount);

	}

	private void logIfCostGreaterThanZero(String string, int amount, int cost) {

		if (cost == 0)
			return;

		Logger.INSTANCE.log(string + amount);

	}

}
