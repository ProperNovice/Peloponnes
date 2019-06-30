package gameState;

import controller.Resources;
import enums.EGameState;
import enums.EResource;
import enums.EText;

public class ResolveSacrificeInhabitants extends AGameState {

	@Override
	public void handleGameStateChange() {
		handleAbilityToSacrificeInhabitant();
	}

	@Override
	protected void executeTextOption(EText eText) {

		switch (eText) {

		case SACRIFICE_INHABITANT:
			sacrificeInhabitant();
			break;

		case CONTINUE:
			proceed();
			break;

		default:
			break;

		}

	}

	private void sacrificeInhabitant() {

		Resources resources = super.controllerSingleton.resources;

		if (resources.getCurrentAmount(EResource.COIN) >= 1)
			resources.removeCurrentAmount(EResource.COIN, 1);
		else if (resources.getCurrentAmount(EResource.LUXURY_GOODS) >= 2)
			resources.removeCurrentAmount(EResource.LUXURY_GOODS, 2);

		resources.removeCurrentAmount(EResource.POPULATION_GAIN, 1);
		resources.addCurrentAmount(EResource.POPULATION_SACRUM, 1);

		EGameState.SCORE.getGameState().handleGameStateChange();
		handleAbilityToSacrificeInhabitant();

	}

	private void proceed() {
		super.controllerSingleton.flow.proceed();
	}

	private void handleAbilityToSacrificeInhabitant() {

		if (canSacrificeInhabitant()) {

			super.controllerSingleton.text.showText(EText.CONTINUE);
			super.controllerSingleton.text.showText(EText.SACRIFICE_INHABITANT);

		} else
			proceed();

	}

	private boolean canSacrificeInhabitant() {

		Resources resources = super.controllerSingleton.resources;

		if (resources.getCurrentAmount(EResource.POPULATION_GAIN) == 0)
			return false;

		if (resources.getCurrentAmount(EResource.COIN) >= 1)
			return true;
		else if (resources.getCurrentAmount(EResource.LUXURY_GOODS) >= 2)
			return true;
		else
			return false;

	}

}
