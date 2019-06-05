package gameState;

import enums.EGameState;
import utils.ArrayList;

public class FlowHandler extends AGameState {

	private ArrayList<EGameState> list = new ArrayList<EGameState>();

	public FlowHandler() {

		this.list.addLast(EGameState.CHOOSE_CIVILIZATION);
		this.list.addLast(EGameState.GAME_WON);
		this.list.addLast(EGameState.REVEAL_TILES);
		this.list.addLast(EGameState.START_GAME);

		System.out.println("l");
		this.list.printList();

	}

	@Override
	public void handleGameStateChange() {

		EGameState eGameState = this.list.removeFirst();
		this.list.addLast(eGameState);

		System.out.println(eGameState + " fh");

		super.controllerSingleton.flow.addFirstProceed(eGameState);

	}

}
