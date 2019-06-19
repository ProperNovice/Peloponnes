package gameState;

import enums.EResource;
import interfaces.ITile;
import interfaces.PrestigePointsAble;
import utils.Logger;

public class Score extends AGameState {

	private int prestigeScore, populationScore, totalScore;

	@Override
	public void handleGameStateChange() {

		Logger.INSTANCE.log("scoring");

		setPrestigeScore();
		setPopulationScore();
		setTotalScore();

		Logger.INSTANCE.newLine();

	}

	private void setPrestigeScore() {

		int coinScore = super.controllerSingleton.resources.getCurrentAmount(EResource.COIN) / 3;
		int tileScore = 0;

		for (ITile iTile : super.controllerSingleton.board.getArrayList()) {

			if (!(iTile instanceof PrestigePointsAble))
				continue;

			PrestigePointsAble prestigePointsAble = (PrestigePointsAble) iTile;
			tileScore += prestigePointsAble.getPrestigePoints();

		}

		this.prestigeScore = coinScore + tileScore;

		Logger.INSTANCE.log("coins -> " + coinScore);
		Logger.INSTANCE.log("tiles -> " + tileScore);
		Logger.INSTANCE.log("prestige -> " + this.prestigeScore);

		super.controllerSingleton.scoringIndicators.setPrestige(this.prestigeScore);

	}

	private void setPopulationScore() {

		this.populationScore = 3 * super.controllerSingleton.resources.getCurrentAmount(EResource.POPULATION_GAIN);
		Logger.INSTANCE.log("population -> " + this.populationScore);

		super.controllerSingleton.scoringIndicators.setPopulation(this.populationScore);

	}

	private void setTotalScore() {

		this.totalScore = Math.min(this.prestigeScore, this.populationScore);
		Logger.INSTANCE.log("total -> " + this.totalScore);

		super.controllerSingleton.scoringIndicators.setTotal();

	}

}
