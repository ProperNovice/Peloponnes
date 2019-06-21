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

		super.controllerSingleton.text.showText(EText.CONTINUE);

	}

	@Override
	protected void executeTextOption(EText eText) {
		super.controllerSingleton.flow.proceed();
	}

	private void executeGameWon() {
		super.controllerSingleton.text.showText(EText.YOU_WON);
	}

	private void executeGameLost() {

		super.controllerSingleton.text.showText(EText.YOU_LOST);
		super.controllerSingleton.modifiers.resetExtraIncome();
		super.controllerSingleton.modifiers.level = 0;
		super.controllerSingleton.flow.createTurns();
		super.controllerSingleton.flow.addFirst(EGameState.RESTART_GAME);

	}

}
