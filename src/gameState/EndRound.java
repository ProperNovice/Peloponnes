package gameState;

public class EndRound extends AGameState {

	@Override
	public void handleGameStateChange() {

		super.controllerSingleton.modifiers.tileToBuy = null;
		super.controllerSingleton.modifiers.sacrificeInhabitants = false;

		super.controllerSingleton.flow.proceed();

	}

}
