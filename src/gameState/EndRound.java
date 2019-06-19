package gameState;

public class EndRound extends AGameState {

	@Override
	public void handleGameStateChange() {

		super.controllerSingleton.modifiers.tileToBuy = null;

		super.controllerSingleton.flow.proceed();

	}

}
