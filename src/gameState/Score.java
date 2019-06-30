package gameState;

import enums.EResource;
import enums.ETileAbility;
import interfaces.AbilityAble;
import interfaces.ITile;
import interfaces.PrestigePointsAble;
import utils.ArrayList;
import utils.Logger;

public class Score extends AGameState {

	private int coinScoreNew, tileScoreNew, populationTotalScoreNew, populationNormalScoreNew, populationSacrumScoreNew;
	private int coinScoreOld, tileScoreOld, populationTotalScoreOld;
	private int coinDivider = 3, populationNormalMultiplier = 3, populationSacrumMultiplier = 2;
	private ScoreType scoreType = ScoreType.MIN;

	@Override
	public void handleGameStateChange() {

		this.coinDivider = 3;
		this.populationNormalMultiplier = 3;
		this.populationSacrumMultiplier = 2;
		this.scoreType = ScoreType.MIN;

		setCredentials();
		setPrestigeScore();
		setPopulationScore();

		updateScoringIndicators();

	}

	private void setCredentials() {

		for (ITile iTile : super.controllerSingleton.board.getArrayList()) {

			AbilityAble abilityAble = (AbilityAble) iTile;
			ArrayList<ETileAbility> list = abilityAble.getTileAbility();

			if (list.contains(ETileAbility.ONE_POINT_FOR_EVERY_TWO_COINS))
				this.coinDivider = 2;

			if (list.contains(ETileAbility.FOUR_POINTS_FOR_EACH_POPULATION))
				this.populationNormalMultiplier = 4;

			if (list.contains(ETileAbility.SACRIFICED_INHABITANTS_WORTH_3_POINTS))
				this.populationSacrumMultiplier = 3;

			if (list.contains(ETileAbility.IF_TWENTY_LUXURY_GOODS_THEN_HIGHER_NUMBER_OF_THE_TWO_SCORES))
				if (super.controllerSingleton.resources.getCurrentAmount(EResource.LUXURY_GOODS) >= 20)
					this.scoreType = ScoreType.MAX;

		}

	}

	private void setPrestigeScore() {

		this.coinScoreNew = super.controllerSingleton.resources.getCurrentAmount(EResource.COIN) / this.coinDivider;
		this.tileScoreNew = 0;

		ArrayList<ITile> tilesToScore = new ArrayList<ITile>();
		tilesToScore.addAll(super.controllerSingleton.board.getArrayList().clone());
		if (super.controllerSingleton.tileSeaPile.tileSeaIsBuilt())
			tilesToScore.addLast(super.controllerSingleton.tileSeaPile.getSeaTile());

		for (ITile iTile : tilesToScore) {

			if (!(iTile instanceof PrestigePointsAble))
				continue;

			PrestigePointsAble prestigePointsAble = (PrestigePointsAble) iTile;
			this.tileScoreNew += prestigePointsAble.getPrestigePoints();

		}

	}

	private void setPopulationScore() {

		this.populationNormalScoreNew = this.populationNormalMultiplier
				* super.controllerSingleton.resources.getCurrentAmount(EResource.POPULATION_GAIN);

		this.populationSacrumScoreNew = this.populationSacrumMultiplier
				* super.controllerSingleton.resources.getCurrentAmount(EResource.POPULATION_SACRUM);

		this.populationTotalScoreNew = this.populationNormalScoreNew + this.populationSacrumScoreNew;

	}

	private void updateScoringIndicators() {

		if (this.tileScoreNew == this.tileScoreOld)
			if (this.coinScoreNew == this.coinScoreOld)
				if (this.populationTotalScoreNew == this.populationTotalScoreOld) {
					Logger.INSTANCE.logNewLine("skipped updating");
					return;
				}

		int totalScore = -1;

		switch (this.scoreType) {

		case MIN:
			totalScore = Math.min(this.tileScoreNew + this.coinScoreNew, this.populationTotalScoreNew);
			break;

		case MAX:
			totalScore = Math.max(this.tileScoreNew + this.coinScoreNew, this.populationTotalScoreNew);
			break;

		}

		Logger.INSTANCE.log("scoring");
		Logger.INSTANCE.log("tiles -> " + this.tileScoreNew);
		Logger.INSTANCE.log("coins -> " + this.coinScoreNew);
		Logger.INSTANCE.log("population -> " + this.populationTotalScoreNew);
		Logger.INSTANCE.log("total -> " + totalScore);
		Logger.INSTANCE.newLine();

		this.tileScoreOld = this.tileScoreNew;
		this.coinScoreOld = this.coinScoreNew;
		this.populationTotalScoreOld = this.populationTotalScoreNew;

		super.controllerSingleton.scoringIndicators.setPrestige(this.tileScoreNew, this.coinScoreNew);
		super.controllerSingleton.scoringIndicators.setPopulation(this.populationTotalScoreNew,
				this.populationNormalScoreNew, this.populationNormalMultiplier, this.populationSacrumScoreNew,
				this.populationSacrumMultiplier);
		super.controllerSingleton.scoringIndicators.setTotal(totalScore);

	}

	private enum ScoreType {
		MIN, MAX
	}

}
