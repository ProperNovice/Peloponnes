package gameState;

import enums.EText;

public class ResolveSacrificeInhabitants extends AGameState {

	@Override
	public void handleGameStateChange() {

		super.controllerSingleton.text.showText(EText.SACRIFICE_INHABITANT);
		super.controllerSingleton.text.showText(EText.CONTINUE);

	}

	@Override
	protected void executeTextOption(EText eText) {

		switch (eText) {

		case SACRIFICE_INHABITANT:
			break;

		case CONTINUE:
			break;

		default:
			break;

		}

	}

}
