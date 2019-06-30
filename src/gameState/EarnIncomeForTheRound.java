package gameState;

import enums.EResource;
import enums.EText;
import interfaces.ITile;
import interfaces.IncomeAble;

public class EarnIncomeForTheRound extends AGameState {

	private int chooseIncomeTiles = 0;

	@Override
	public void handleGameStateChange() {
		super.controllerSingleton.text.showText(EText.EARN_INCOME);
	}

	@Override
	protected void executeTextOption(EText eText) {

		switch (eText) {

		case EARN_INCOME:

			handleEarnIncome();
			setChooseIncomeTilesAmount();
			handleContinue();
			break;

		case COIN:
			executeChooseIncomeTile(EResource.COIN);
			break;

		case STONE:
			executeChooseIncomeTile(EResource.STONE);
			break;

		case WOOD:
			executeChooseIncomeTile(EResource.WOOD);
			break;

		case FOOD:
			executeChooseIncomeTile(EResource.FOOD);
			break;

		default:
			break;

		}

	}

	private void handleEarnIncome() {
		super.controllerSingleton.resources.earnIncomeForTheRound();
	}

	private void setChooseIncomeTilesAmount() {

		for (ITile iTile : super.controllerSingleton.board.getArrayList()) {

			IncomeAble incomeAble = (IncomeAble) iTile;

			for (EResource eResource : incomeAble.getIncomePerRound())
				if (eResource == EResource.CHOOSE_INCOME)
					this.chooseIncomeTiles++;

		}

	}

	private void handleContinue() {

		if (this.chooseIncomeTiles == 0) {
			super.controllerSingleton.flow.proceed();
			return;
		}

		super.controllerSingleton.text.showText(EText.CHOOSE_INCOME);
		super.controllerSingleton.text.showText(EText.COIN);
		super.controllerSingleton.text.showText(EText.STONE);
		super.controllerSingleton.text.showText(EText.WOOD);
		super.controllerSingleton.text.showText(EText.FOOD);

	}

	private void executeChooseIncomeTile(EResource eResource) {

		this.chooseIncomeTiles--;
		super.controllerSingleton.resources.addCurrentAmount(eResource, 1);
		handleContinue();

	}

}
