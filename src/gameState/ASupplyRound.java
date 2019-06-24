package gameState;

import enums.EResource;
import enums.EText;
import interfaces.BuildAble;
import interfaces.ITile;
import interfaces.IncomeAble;
import model.Tile;
import utils.Logger;

public class ASupplyRound extends ABuildTile {

	private boolean feedPopulationPhase = true;
	protected EText eTextSupplyRound = null;

	@Override
	public void handleGameStateChange() {

		this.feedPopulationPhase = true;

		super.controllerSingleton.text.showText(this.eTextSupplyRound);
		super.controllerSingleton.text.showText(EText.FEED_POPULATION);

	}

	@Override
	protected void executeTextOption(EText eText) {

		switch (eText) {

		case FEED_POPULATION:
			executeFeedPopulation();
			break;

		case DON_T_BUILD_THE_REST:
			handleDontBuildTheRest();
			break;

		default:
			break;

		}

	}

	@Override
	protected void handleTilePressedBoard(ITile iTile) {

		if (this.feedPopulationPhase) {
			Logger.INSTANCE.logNewLine("we are in population phase");
			return;
		}

		if (!(iTile instanceof BuildAble)) {
			Logger.INSTANCE.logNewLine("tile is not build able");
			return;
		}

		BuildAble tileBuildable = (BuildAble) iTile;

		if (tileBuildable.isBuilt()) {
			Logger.INSTANCE.logNewLine("tile is built");
			return;
		}

		if (!super.tileBuildingCanBeBuiltNow(iTile)) {
			Logger.INSTANCE.logNewLine("tile cannot be built");
			return;
		}

		super.controllerSingleton.text.concealText();
		buildTile(tileBuildable);

	}

	private void executeFeedPopulation() {

		this.feedPopulationPhase = false;
		feedPopulation();
		handleUnbuiltBuildings();

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

		int populationFinal = Math.min(populationCanBeFed, populationCurrent);
		int populationLost = populationCurrent - populationFinal;
		Logger.INSTANCE.log("population fed  -> " + populationFinal);
		Logger.INSTANCE.log("population lost -> " + populationLost);

		int foodNeeded = populationFinal;

		if (super.controllerSingleton.tileSeaPile.tileSeaIsBuilt()) {
			foodNeeded--;
			Logger.INSTANCE.log("sea tile is build");
		}

		int foodSpent = Math.min(foodNeeded, foodCurrent);

		if (foodSpent > 0)
			Logger.INSTANCE.log("food spent -> " + foodSpent);

		foodNeeded -= foodSpent;
		int luxuryGoodsSpent = 2 * foodNeeded;

		if (luxuryGoodsSpent > 0)
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

		super.controllerSingleton.text.showText(EText.CHOOSE_BUILDING_TO_BUILD);
		super.controllerSingleton.text.showText(EText.DON_T_BUILD_THE_REST);

	}

	private void buildTile(BuildAble buildAble) {

		Logger.INSTANCE.logNewLine("building tile");
		super.executeBuildResources();
		super.controllerSingleton.resources.addCurrentAmount(EResource.COIN, 1);

		buildAble.setBuilt();
		handleUnbuiltBuildings();

	}

	private void handleDontBuildTheRest() {

		for (ITile iTile : super.controllerSingleton.board.getArrayList().clone()) {

			if (!(iTile instanceof BuildAble))
				continue;

			BuildAble buildAble = (BuildAble) iTile;

			if (buildAble.isBuilt())
				continue;

			buildAble.setBuilt();
			super.controllerSingleton.board.getArrayList().remove(iTile);
			super.controllerSingleton.resources.addCurrentAmount(EResource.COIN, 1);

			Tile tile = (Tile) iTile;
			tile.getImageView().setVisible(false);

			IncomeAble incomeAble = (IncomeAble) iTile;
			super.controllerSingleton.resources.removeIncome(incomeAble.getIncomePerRound());

		}

		super.controllerSingleton.board.relocateList();
		super.controllerSingleton.board.animateAsynchronous();

		super.controllerSingleton.flow.proceed();

	}

}
