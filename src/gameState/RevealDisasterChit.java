package gameState;

import enums.EDisaster;
import enums.EText;
import model.DisasterChit;
import model.TileDisaster;

public class RevealDisasterChit extends AGameState {

	@Override
	public void handleGameStateChange() {
		super.controllerSingleton.text.showText(EText.DRAW_DISASTER_CHIT);
	}

	@Override
	protected void executeTextOption(EText eText) {

		DisasterChit disasterChit = super.controllerSingleton.disasterChits.getArrayList().removeRandom();
		EDisaster eDisaster = disasterChit.getEDisaster();
		super.controllerSingleton.modifiers.eDisasterDrawn = eDisaster;
		super.controllerSingleton.disasterChits.animateAsynchronous();

		switch (eDisaster) {

		case BLANK:
			disasterChit.getImageView().setVisible(false);
			break;

		default:
			for (TileDisaster tileDisaster : super.controllerSingleton.disasterTiles.getArrayList()) {

				if (disasterChit.getEDisaster() != tileDisaster.getEDisaster())
					continue;

				tileDisaster.getList().getArrayList().addLast(disasterChit);
				tileDisaster.getList().animateSynchronousLock();

			}

			break;

		}

		super.controllerSingleton.flow.proceed();

	}

}
