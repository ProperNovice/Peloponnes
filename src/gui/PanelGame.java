package gui;

import controller.ControllerSingleton;
import utils.PanelGameInstance;
import utils.Parent;
import utils.PlatformFX;

public class PanelGame extends Parent {

	private Panel panel = null;

	public PanelGame(Panel panel) {

		this.panel = panel;
		PanelGameInstance.INSTANCE.setPanelGameInstance(this);
		ControllerSingleton.INSTANCE.createInstances();

	}

	public void restartGame() {

		PlatformFX.runLater(() -> {

			this.panel.removeCurrentPanelGame();
			this.panel.createNewPanelGame();
			this.panel.startGame();

		});

	}

}
