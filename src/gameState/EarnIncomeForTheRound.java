package gameState;

import enums.EText;

public class EarnIncomeForTheRound extends AGameState {

	@Override
	public void handleGameStateChange() {
		super.controllerSingleton.text.showText(EText.EARN_INCOME);
	}

	@Override
	protected void executeTextOption(EText eText) {

		super.controllerSingleton.resources.earnIncomeForTheRound();
		super.controllerSingleton.flow.proceed();

	}

}
