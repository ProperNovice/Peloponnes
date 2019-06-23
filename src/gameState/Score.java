package gameState;

import enums.EResource;
import enums.ETileAbility;
import interfaces.AbilityAble;
import interfaces.ITile;
import interfaces.PrestigePointsAble;
import utils.ArrayList;
import utils.Logger;

public class Score extends AGameState {

	private int coinScoreNew, tileScoreNew, populationScoreNew;
	private int coinScoreOld, tileScoreOld, populationScoreOld;
	private int coinDivider = 3, populationMultiplier = 3;
	private ScoreType scoreType = ScoreType.MIN;

	@Override
	public void handleGameStateChange() {

		this.coinDivider = 3;
		this.populationMultiplier = 3;
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

			else if (list.contains(ETileAbility.FOUR_POINTS_FOR_EACH_POPULATION))
				this.populationMultiplier = 4;

			else if (list.contains(ETileAbility.IF_TWENTY_LUXURY_GOODS_THEN_HIGHER_NUMBER_OF_THE_TWO_SCORES))
				this.scoreType = ScoreType.MAX;

		}

	}

	private void setPrestigeScore() {

		this.coinScoreNew = super.controllerSingleton.resources.getCurrentAmount(EResource.COIN) / this.coinDivider;
		this.tileScoreNew = 0;

		for (ITile iTile : super.controllerSingleton.board.getArrayList()) {

			if (!(iTile instanceof PrestigePointsAble))
				continue;

			PrestigePointsAble prestigePointsAble = (PrestigePointsAble) iTile;
			this.tileScoreNew += prestigePointsAble.getPrestigePoints();

		}

	}

	private void setPopulationScore() {
		this.populationScoreNew = this.populationMultiplier
				* super.controllerSingleton.resources.getCurrentAmount(EResource.POPULATION_GAIN);
	}

	private void updateScoringIndicators() {

		if (this.tileScoreNew == this.tileScoreOld)
			if (this.coinScoreNew == this.coinScoreOld)
				if (this.populationScoreNew == this.populationScoreOld) {
					Logger.INSTANCE.logNewLine("skipped updating");
					return;
				}

		int totalScore = -1;

		switch (this.scoreType) {

		case MIN:
			totalScore = Math.min(this.tileScoreNew + this.coinScoreNew, this.populationScoreNew);
			break;

		case MAX:
			totalScore = Math.max(this.tileScoreNew + this.coinScoreNew, this.populationScoreNew);
			break;

		}

		Logger.INSTANCE.log("scoring");
		Logger.INSTANCE.log("tiles -> " + this.tileScoreNew);
		Logger.INSTANCE.log("coins -> " + this.coinScoreNew);
		Logger.INSTANCE.log("population -> " + this.populationScoreNew);
		Logger.INSTANCE.log("total -> " + totalScore);
		Logger.INSTANCE.newLine();

		this.tileScoreOld = this.tileScoreNew;
		this.coinScoreOld = this.coinScoreNew;
		this.populationScoreOld = this.populationScoreNew;

		super.controllerSingleton.scoringIndicators.setPrestige(this.tileScoreNew, this.coinScoreNew);
		super.controllerSingleton.scoringIndicators.setPopulation(this.populationScoreNew, this.populationMultiplier);
		super.controllerSingleton.scoringIndicators.setTotal();

	}

	private enum ScoreType {
		MIN, MAX
	}

}
