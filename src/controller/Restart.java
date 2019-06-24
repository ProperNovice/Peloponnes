package controller;

import interfaces.RestartAble;
import utils.ArrayList;

public class Restart implements RestartAble {

	private ArrayList<RestartAble> list = new ArrayList<RestartAble>();

	public Restart() {
		createRestartAbles();
	}

	private void createRestartAbles() {

		this.list.addLast(ControllerSingleton.INSTANCE.tilePiles);
		this.list.addLast(ControllerSingleton.INSTANCE.disasterTiles);
		this.list.addLast(ControllerSingleton.INSTANCE.disasterChits);
		this.list.addLast(ControllerSingleton.INSTANCE.board);
		this.list.addLast(ControllerSingleton.INSTANCE.civilizations);
		this.list.addLast(ControllerSingleton.INSTANCE.resources);
		this.list.addLast(ControllerSingleton.INSTANCE.tileSeaPile);

	}

	@Override
	public void restart() {

		for (RestartAble restartAble : this.list) {

			if (restartAble == null)
				System.out.println("null yo");
			else
				System.out.println("not null");

			restartAble.restart();

		}

	}

}
