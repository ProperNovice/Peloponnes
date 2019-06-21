package gameState;

public class RestartGame extends AGameState {

	@Override
	public void handleGameStateChange() {

		super.controllerSingleton.restart.restart();
		super.controllerSingleton.scoringIndicators.setCampaignLevelText(1);

		super.controllerSingleton.flow.proceed();

	}

}
