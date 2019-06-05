package gameState;

import enums.EGameState;
import utils.ArrayList;

public class FlowHandler extends AGameState {

	private ArrayList<EGameState> turn = new ArrayList<EGameState>();

	public FlowHandler() {

		new Thread(new Runnable() {

			@Override
			public void run() {

				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				createTurn();

			}

		}).start();

	}

	private void createTurn() {

		this.turn.addLast(EGameState.REVEAL_TILES);
		this.turn.addLast(EGameState.PURCHASE_TILE_OR_PASS);
		this.turn.addLast(EGameState.END_ROUND);

	}

	@Override
	public void handleGameStateChange() {

		while (this.turn.isEmpty())
			;

		EGameState eGameState = this.turn.removeFirst();
		this.turn.addLast(eGameState);

		super.controllerSingleton.flow.addFirstProceed(eGameState);

	}

}
