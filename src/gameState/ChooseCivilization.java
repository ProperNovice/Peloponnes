package gameState;

import enums.EText;
import interfaces.ITile;
import model.Tile;
import model.TileCivilization;

public class ChooseCivilization extends AGameState {

	@Override
	public void handleGameStateChange() {

		super.controllerSingleton.tileConquest.getImageView().setVisible(false);

		civilizationTilesSetVisible(true);

		super.controllerSingleton.text.showText(EText.CHOOSE_CIVILIZATION);
		super.controllerSingleton.text.showText(EText.RANDOM);

		super.controllerSingleton.civilizations.relocateImageViews();

	}

	@Override
	protected void executeTextOption(EText eText) {

		ITile iTile = super.controllerSingleton.civilizations.getRandomCivilization();
		setCivilizationTileProceed(iTile);

	}

	@Override
	protected void handleCivilizationTilePressed(ITile iTile) {

		super.controllerSingleton.text.concealText();
		setCivilizationTileProceed(iTile);

	}

	private void civilizationTilesSetVisible(boolean value) {

		for (ITile iTile : super.controllerSingleton.civilizations.getArrayList()) {

			Tile tile = (Tile) iTile;
			tile.getImageView().setVisible(value);

		}

	}

	private void setCivilizationTileProceed(ITile iTile) {

		civilizationTilesSetVisible(false);
		super.controllerSingleton.modifiers.tileCivilization = (TileCivilization) iTile;
		super.controllerSingleton.flow.proceed();

	}

}
