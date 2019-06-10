package gameState;

import enums.EResource;
import enums.EText;
import interfaces.BuildAble;
import interfaces.ITile;
import utils.Logger;

public class SupplyRound extends AGameState {

	private boolean feedPopulationPhase = true;

	@Override
	public void handleGameStateChange() {

		this.feedPopulationPhase = true;

		super.controllerSingleton.text.showText(EText.SUPPLY_ROUND);
		super.controllerSingleton.text.showText(EText.FEED_POPULATION);

	}

	@Override
	protected void executeTextOption(EText eText) {

		switch (eText) {

		case FEED_POPULATION:
			feedPopulation();
			this.feedPopulationPhase = false;
			handleUnbuiltBuildings();
			break;

		case CONTINUE:
			break;

		default:
			break;

		}

	}

	@Override
	protected void handleTilePressedBoard(ITile iTile) {

		if (this.feedPopulationPhase)
			return;

		if (!(iTile instanceof BuildAble))
			return;

		BuildAble tileBuildable = (BuildAble) iTile;

		if (tileBuildable.isBuilt())
			return;

	}

	private void feedPopulation() {

		int foodCurrent = super.controllerSingleton.resources.getCurrentAmount(EResource.FOOD);
		int luxuryGoodsCurrent = super.controllerSingleton.resources.getCurrentAmount(EResource.LUXURY_GOODS);
		int populationCurrent = super.controllerSingleton.resources.getCurrentAmount(EResource.POPULATION_GAIN);

		Logger.INSTANCE.log("food -> " + foodCurrent);
		Logger.INSTANCE.log("luxury goods -> " + luxuryGoodsCurrent);
		Logger.INSTANCE.log("population -> " + populationCurrent);

		int populationCanBeFed = foodCurrent + luxuryGoodsCurrent / 2;

		Logger.INSTANCE.log("pop can be fed -> " + populationCanBeFed);
		Logger.INSTANCE.log("population -> " + populationCurrent);

		int populationFinal = Math.min(populationCanBeFed, populationCurrent);
		int populationLost = populationCurrent - populationFinal;
		Logger.INSTANCE.log("population lost -> " + populationLost);
		Logger.INSTANCE.log("population final  -> " + populationFinal);
		int foodNeeded = populationFinal;

		int foodSpent = Math.min(foodNeeded, foodCurrent);
		Logger.INSTANCE.log("food spent -> " + foodSpent);

		foodNeeded -= foodSpent;
		int luxuryGoodsSpent = 2 * foodNeeded;
		Logger.INSTANCE.log("luxury goods spent -> " + luxuryGoodsSpent);

		super.controllerSingleton.resources.removeCurrentAmount(EResource.FOOD, foodSpent);
		super.controllerSingleton.resources.removeCurrentAmount(EResource.LUXURY_GOODS, luxuryGoodsSpent);
		super.controllerSingleton.resources.removeCurrentAmount(EResource.POPULATION_GAIN, populationLost);

		Logger.INSTANCE.newLine();

	}

	private void handleUnbuiltBuildings() {

		int unbuiltBuildings = 0;

		for (ITile iTile : super.controllerSingleton.board.getArrayList()) {

			if (!(iTile instanceof BuildAble))
				continue;

			BuildAble buildAble = (BuildAble) iTile;

			if (!buildAble.isBuilt())
				unbuiltBuildings++;

		}

		Logger.INSTANCE.logNewLine("unbuild buildings -> " + unbuiltBuildings);

		if (unbuiltBuildings > 0)
			setUpUnbuiltBuildingsText();
		else
			super.controllerSingleton.flow.proceed();

	}

	private void setUpUnbuiltBuildingsText() {

		super.controllerSingleton.text.showText(EText.SUPPLY_ROUND);
		super.controllerSingleton.text.showText(EText.CHOOSE_BUILDING_TO_BUILD);
		super.controllerSingleton.text.showText(EText.CONTINUE);

	}

}
