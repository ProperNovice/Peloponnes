package gameState;

import enums.EGameState;
import utils.ArrayList;

public class FlowHandler extends AGameState {

	private ArrayList<EGameState> turnCurrent = new ArrayList<EGameState>();
	private ArrayList<EGameState> turnOriginal = new ArrayList<EGameState>();
	private ArrayList<EGameState> buildTileGameStateSequence = new ArrayList<EGameState>();
	private EGameState lastGameStateResolved = null;

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

		// turn

		this.turnOriginal.addLast(EGameState.REVEAL_TILES);
		this.turnOriginal.addLast(EGameState.PURCHASE_TILE_OR_PASS);
		this.turnOriginal.addLast(EGameState.END_ROUND);

		// build tile

		this.buildTileGameStateSequence.addLast(EGameState.BUILD_NOW_LATER_OR_DISCARD);

	}

	@Override
	public void handleGameStateChange() {

		while (this.turnOriginal.isEmpty())
			;

		if (this.turnCurrent.isEmpty())
			this.turnCurrent.addAll(this.turnOriginal);

		checkToSeeIfBuildTilePhase();

		this.lastGameStateResolved = this.turnCurrent.removeFirst();
		this.turnCurrent.addLast(this.lastGameStateResolved);
		super.controllerSingleton.flow.addFirst(this.lastGameStateResolved);

		super.controllerSingleton.flow.proceed();

	}

	private void checkToSeeIfBuildTilePhase() {

		if (this.lastGameStateResolved != EGameState.PURCHASE_TILE_OR_PASS)
			return;

		if (super.controllerSingleton.modifiers.tileToBuy == null)
			return;

		ArrayList<EGameState> turnTemp = this.turnCurrent.clone();
		this.turnCurrent.clear();
		this.turnCurrent.addAll(this.buildTileGameStateSequence);
		this.turnCurrent.addAll(turnTemp);

	}

}
