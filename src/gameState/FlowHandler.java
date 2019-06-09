package gameState;

import enums.EGameState;
import utils.ArrayList;
import utils.Logger;

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

				createTurnOriginal();

			}

		}).start();

	}

	private void createTurnOriginal() {

		this.turn.addLast(EGameState.REVEAL_TILES);
		this.turn.addLast(EGameState.PURCHASE_TILE_OR_PASS);
		this.turn.addLast(EGameState.BUILD_NOW_LATER_OR_DISCARD);
		this.turn.addLast(EGameState.SET_ONE_TIME_INCOME_PER_ROUND);
		this.turn.addLast(EGameState.EARN_INCOME_FOR_THE_ROUND);
		this.turn.addLast(EGameState.END_ROUND);

	}

	@Override
	public void handleGameStateChange() {

		while (this.turn.isEmpty())
			;

		EGameState eGameState = this.turn.removeFirst();
		this.turn.addLast(eGameState);

		if (eGameStateShallResolve(eGameState))
			super.controllerSingleton.flow.addFirst(eGameState);
		else {
			Logger.INSTANCE.log(eGameState);
			Logger.INSTANCE.logNewLine("skipped");
		}

		super.controllerSingleton.flow.proceed();

	}

	private boolean eGameStateShallResolve(EGameState eGameState) {

		switch (eGameState) {

		case BUILD_NOW_LATER_OR_DISCARD:
			return super.controllerSingleton.modifiers.tileToBuy != null;

		case SET_ONE_TIME_INCOME_PER_ROUND:
			return super.controllerSingleton.modifiers.tileToBuy != null;

		default:
			return true;

		}

	}

}
