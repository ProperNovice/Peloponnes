package gameState;

import controller.CredentialSingleton;
import enums.EResource;
import model.TileCivilization;
import utils.ArrayList;
import utils.Logger;

public class AddCivilizationAndStartingResources extends AGameState {

	@Override
	public void handleGameStateChange() {

		addCivilization();
		addCivilizationResources();
		addAdditionalIncome();
		restartSeaTileList();

		super.controllerSingleton.flow.proceed();

	}

	private void addCivilization() {

		TileCivilization tile = super.controllerSingleton.modifiers.tileCivilization;

		tile.getImageView().setVisible(true);
		tile.getImageView().setWidth(CredentialSingleton.INSTANCE.DimensionsTileGame.x);

		super.controllerSingleton.board.getArrayList().addLast(tile);
		super.controllerSingleton.board.relocateList();
		super.controllerSingleton.board.relocateImageViews();

	}

	private void addCivilizationResources() {

		TileCivilization tile = super.controllerSingleton.modifiers.tileCivilization;

		ArrayList<EResource> oneTimeIncome = tile.getOneTimeIncome();
		ArrayList<EResource> incomePerRound = tile.getIncomePerRound();

		super.controllerSingleton.resources.addCurrentAmount(oneTimeIncome);
		super.controllerSingleton.resources.addIncome(incomePerRound);

	}

	private void addAdditionalIncome() {

		ArrayList<EResource> income = new ArrayList<EResource>();

		for (EResource eResourceTemp : super.controllerSingleton.modifiers.extraIncome)
			for (int counter = 1; counter <= super.controllerSingleton.modifiers.extraIncome
					.get(eResourceTemp); counter++)
				income.addLast(eResourceTemp);

		Logger.INSTANCE.log("additional income");
		income.printList();

		super.controllerSingleton.resources.addIncome(income);

	}

	private void restartSeaTileList() {
		super.controllerSingleton.tileSeaPile.restartList();
	}

}
