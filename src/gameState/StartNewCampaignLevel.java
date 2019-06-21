package gameState;

import enums.EResource;
import enums.EText;
import utils.ArrayList;

public class StartNewCampaignLevel extends AGameState {

	@Override
	public void handleGameStateChange() {

		super.controllerSingleton.modifiers.level++;

		super.controllerSingleton.scoringIndicators.setCampaignLevelText(super.controllerSingleton.modifiers.level);

		handleTileRowNormalCapacity();

		switch (super.controllerSingleton.modifiers.level) {

		case 1:
			super.controllerSingleton.text.showText(EText.START_CAMPAIGN_LEVEL_1);
			break;

		case 2:
			super.controllerSingleton.text.showText(EText.START_CAMPAIGN_LEVEL_2);
			break;

		case 3:
			super.controllerSingleton.text.showText(EText.START_CAMPAIGN_LEVEL_3);
			break;

		case 4:
			super.controllerSingleton.text.showText(EText.START_CAMPAIGN_LEVEL_4);
			break;

		case 5:
			super.controllerSingleton.text.showText(EText.START_CAMPAIGN_LEVEL_5);
			break;

		}

	}

	@Override
	protected void executeTextOption(EText eText) {

		switch (eText) {

		case START_CAMPAIGN_LEVEL_1:
			resetAdditionalIncome();
			break;

		case START_CAMPAIGN_LEVEL_2:
			handleChooseIncome();
			break;

		case START_CAMPAIGN_LEVEL_3:
			handleChooseIncome();
			break;

		case START_CAMPAIGN_LEVEL_4:
			handleChooseIncome();
			break;

		case START_CAMPAIGN_LEVEL_5:
			handleChooseIncome();
			break;

		case STONE:
			addIncomeProceed(EResource.STONE);
			break;

		case WOOD:
			addIncomeProceed(EResource.WOOD);
			break;

		case FOOD:
			addIncomeProceed(EResource.FOOD);
			break;

		default:
			break;

		}

	}

	private void handleTileRowNormalCapacity() {
		super.controllerSingleton.modifiers.tileRowNormalCapacity = 5 + 1 - super.controllerSingleton.modifiers.level;
	}

	private void handleChooseIncome() {

		super.controllerSingleton.text.showText(EText.CHOOSE_INCOME);
		super.controllerSingleton.text.showText(EText.STONE);
		super.controllerSingleton.text.showText(EText.WOOD);
		super.controllerSingleton.text.showText(EText.FOOD);

	}

	private void addIncomeProceed(EResource eResource) {

		int currentIncome = super.controllerSingleton.modifiers.extraIncome.get(eResource);
		currentIncome++;

		super.controllerSingleton.modifiers.extraIncome.put(eResource, currentIncome);

		ArrayList<EResource> income = new ArrayList<EResource>();
//
//		for (EResource eResourceTemp : super.controllerSingleton.modifiers.extraIncome)
//			for (int counter = 1; counter <= super.controllerSingleton.modifiers.extraIncome
//					.get(eResourceTemp); counter++)
//				income.addLast(eResourceTemp);

		income.addLast(eResource);

		super.controllerSingleton.resources.addIncome(income);

		super.controllerSingleton.flow.proceed();

	}

	private void resetAdditionalIncome() {

		super.controllerSingleton.modifiers.extraIncome.put(EResource.STONE, 0);
		super.controllerSingleton.modifiers.extraIncome.put(EResource.WOOD, 0);
		super.controllerSingleton.modifiers.extraIncome.put(EResource.FOOD, 0);

		super.controllerSingleton.flow.proceed();

	}

}
