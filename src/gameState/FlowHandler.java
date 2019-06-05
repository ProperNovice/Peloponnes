package gameState;

import enums.EGameState;
import utils.ArrayList;

public class FlowHandler extends AGameState {

	private ArrayList<EGameState> list = new ArrayList<EGameState>();

	public FlowHandler() {

		new Thread(new Runnable() {

			@Override
			public void run() {

				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				list.addLast(EGameState.REVEAL_TILES);

			}

		}).start();

	}

	@Override
	public void handleGameStateChange() {

		do {

		} while (this.list.isEmpty());

		EGameState eGameState = this.list.removeFirst();
		this.list.addLast(eGameState);

		super.controllerSingleton.flow.addFirstProceed(eGameState);

	}

}
