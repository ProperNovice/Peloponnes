package gameState;

import interfaces.ITile;
import model.TileCivilization;

public class ChooseCivilization extends AGameState {

	@Override
	public void handleGameStateChange() {

		ITile iTile = super.controllerSingleton.civilizations.getRandomCivilization();

		super.controllerSingleton.modifiers.tileCivilization = (TileCivilization) iTile;

		super.controllerSingleton.flow.proceed();

	}

}
