package gameState;

import interfaces.ITile;
import interfaces.IncomeAble;

public class SetOneTimeIncomePerRound extends AGameState {

	@Override
	public void handleGameStateChange() {

		ITile iTile = super.controllerSingleton.modifiers.tileToBuy;
		IncomeAble incomeAble = (IncomeAble) iTile;

		super.controllerSingleton.resources.addCurrentAmount(incomeAble.getOneTimeIncome());
		super.controllerSingleton.resources.addIncome(incomeAble.getIncomePerRound());

		super.controllerSingleton.flow.proceed();

	}

}
