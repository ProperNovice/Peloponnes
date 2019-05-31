package gameState;

import enums.EResource;
import utils.ArrayList;

public class StartGame extends AGameState {

	@Override
	public void handleGameStateChange() {

		ArrayList<EResource> list = new ArrayList<EResource>();

		for (int counter = 1; counter <= 12; counter++)
			list.addLast(EResource.WOOD);

		for (int counter = 1; counter <= 16; counter++)
			list.addLast(EResource.STONE);
		
		for (int counter = 1; counter <= 24; counter++)
			list.addLast(EResource.FOOD);
		
		for (int counter = 1; counter <= 3; counter++)
			list.addLast(EResource.POPULATION_GAIN);
		
		for (int counter = 1; counter <= 4; counter++)
			list.addLast(EResource.LUXURY_GOODS);

		super.controllerSingleton.resources.addCurrentAmount(list);
		super.controllerSingleton.resources.addIncome(list);

	}

}
