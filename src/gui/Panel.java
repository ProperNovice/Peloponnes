package gui;

import controller.ControllerSingleton;
import enums.EGameState;
import javafx.scene.image.ImageView;
import utils.EventHandler;
import utils.EventHandler.EventHandlerAble;
import utils.Executor;
import utils.Parent;
import utils.ShutDown;

public class Panel extends Parent implements EventHandlerAble {

	private ImageView background = new ImageView("/images/misc/background.png");
	private PanelGame panelGame = null;

	public Panel() {

		this.background.toBack();
		this.background.setOnMousePressed(new EventHandler(this));

		this.getChildren().add(this.background);

		createNewPanelGame();

	}

	public void removeCurrentPanelGame() {

		this.panelGame.setVisible(false);
		this.getChildren().remove(this.panelGame);

	}

	public void createNewPanelGame() {

		this.panelGame = new PanelGame(this);
		this.getChildren().add(this.panelGame);

	}

	public void startGame() {

		Executor.INSTANCE.runLater(() -> ControllerSingleton.INSTANCE.gameState.setGameState(EGameState.START_GAME));

	}

	@Override
	public void handleMouseButtonPressedSecondary() {
		ShutDown.execute();
	}

}
