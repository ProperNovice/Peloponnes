package gameState;

import controller.CredentialSingleton;
import enums.EResource;
import interfaces.ITile;
import model.TileCivilization;
import utils.ArrayList;

public class ChooseCivilization extends AGameState {

	@Override
	public void handleGameStateChange() {

		ITile iTile = super.controllerSingleton.civilizations.getRandomCivilization();
		iTile = super.controllerSingleton.civilizations.getLast();

		TileCivilization tile = (TileCivilization) iTile;
		tile.getImageView().setVisible(true);
		tile.getImageView().setWidth(CredentialSingleton.INSTANCE.DimensionsTileGame.x);

		super.controllerSingleton.board.getArrayList().addLast(iTile);
		super.controllerSingleton.board.relocateList();
		super.controllerSingleton.board.relocateImageViews();

		ArrayList<EResource> oneTimeIncome = tile.getOneTimeIncome();
		ArrayList<EResource> incomePerRound = tile.getIncomePerRound();

		super.controllerSingleton.resources.addCurrentAmount(oneTimeIncome);
		super.controllerSingleton.resources.addIncome(incomePerRound);

		super.controllerSingleton.flow.proceed();

	}

}
