package gameState;

import enums.EGameState;
import enums.EText;

public class EndCampaignLevel extends AGameState {

	@Override
	public void handleGameStateChange() {

		boolean gameWon = super.controllerSingleton.scoringIndicators.gameWon();

		if (gameWon)
			executeGameWon();
		else
			executeGameLost();

	}

	@Override
	protected void executeTextOption(EText eText) {
		super.controllerSingleton.flow.proceed();
	}

	private void executeGameWon() {

		if (super.controllerSingleton.modifiers.level == 5) {

			super.controllerSingleton.text.showText(EText.CONGRATULATIONS);
			handleRestartGame();
			super.controllerSingleton.text.showText(EText.RESTART);

		} else {

			super.controllerSingleton.text.showText(EText.YOU_WON);
			super.controllerSingleton.text.showText(EText.CONTINUE);

		}

	}

	private void executeGameLost() {

		handleRestartGame();

		super.controllerSingleton.text.showText(EText.YOU_LOST);
		super.controllerSingleton.text.showText(EText.RESTART);

	}

	private void handleRestartGame() {

		super.controllerSingleton.modifiers.resetExtraIncome();
		super.controllerSingleton.modifiers.level = 0;
		super.controllerSingleton.flow.createTurns();
		super.controllerSingleton.flow.addFirst(EGameState.RESTART_GAME);

	}

}
