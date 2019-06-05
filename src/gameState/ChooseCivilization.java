package gameState;

import controller.CredentialSingleton;
import interfaces.ITile;
import model.Tile;

public class ChooseCivilization extends AGameState {

	@Override
	public void handleGameStateChange() {

		ITile iTile = super.controllerSingleton.civilizations.getRandomCivilization();

		Tile tile = (Tile) iTile;
		tile.getImageView().setWidth(CredentialSingleton.INSTANCE.DimensionsTileGame.x);

		super.controllerSingleton.board.getArrayList().addLast(iTile);
		super.controllerSingleton.board.relocateList();
		super.controllerSingleton.board.relocateImageViews();

	}

}
