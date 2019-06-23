package gameState;

import enums.EResource;
import interfaces.ITile;
import interfaces.PrestigePointsAble;
import utils.Logger;

public class Score extends AGameState {

	private int coinScoreNew, tileScoreNew, populationScoreNew;
	private int coinScoreOld, tileScoreOld, populationScoreOld;

	@Override
	public void handleGameStateChange() {

		setPrestigeScore();
		setPopulationScore();

		updateScoringIndicators();

	}

	private void setPrestigeScore() {

		this.coinScoreNew = super.controllerSingleton.resources.getCurrentAmount(EResource.COIN) / 3;
		this.tileScoreNew = 0;

		for (ITile iTile : super.controllerSingleton.board.getArrayList()) {

			if (!(iTile instanceof PrestigePointsAble))
				continue;

			PrestigePointsAble prestigePointsAble = (PrestigePointsAble) iTile;
			this.tileScoreNew += prestigePointsAble.getPrestigePoints();

		}

	}

	private void setPopulationScore() {
		this.populationScoreNew = 3 * super.controllerSingleton.resources.getCurrentAmount(EResource.POPULATION_GAIN);
	}

	private void updateScoringIndicators() {

		if (this.tileScoreNew == this.tileScoreOld)
			if (this.coinScoreNew == this.coinScoreOld)
				if (this.populationScoreNew == this.populationScoreOld) {
					Logger.INSTANCE.logNewLine("skipped updating");
					return;
				}

		Logger.INSTANCE.log("scoring");
		Logger.INSTANCE.log("tiles -> " + this.tileScoreNew);
		Logger.INSTANCE.log("coins -> " + this.coinScoreNew);
		Logger.INSTANCE.log("population -> " + this.populationScoreNew);
		Logger.INSTANCE.log("total -> " + Math.min(this.tileScoreNew + this.coinScoreNew, this.populationScoreNew));
		Logger.INSTANCE.newLine();

		this.tileScoreOld = this.tileScoreNew;
		this.coinScoreOld = this.coinScoreNew;
		this.populationScoreOld = this.populationScoreNew;

		super.controllerSingleton.scoringIndicators.setPrestige(this.tileScoreNew, this.coinScoreNew);
		super.controllerSingleton.scoringIndicators.setPopulation(this.populationScoreNew, 3);
		super.controllerSingleton.scoringIndicators.setTotal();

	}

}
