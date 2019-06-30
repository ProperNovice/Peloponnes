package gameState;

import interfaces.ITile;
import model.TileCivilization;

public class ChooseCivilization extends AGameState {

	@Override
	public void handleGameStateChange() {

		ITile iTile = super.controllerSingleton.civilizations.getRandomCivilization();
		iTile = super.controllerSingleton.civilizations.getLast();

		super.controllerSingleton.modifiers.tileCivilization = (TileCivilization) iTile;

		super.controllerSingleton.flow.proceed();

	}

}
