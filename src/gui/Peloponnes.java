package gui;

import controller.ControllerSingleton;
import controller.CredentialSingleton;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import utils.Animation;
import utils.Executor;
import utils.Logger;
import utils.ShutDown;

public class Peloponnes extends Application {

	private Panel panel = null;
	private double pixesOnTheLeft = 180;

	@Override
	public void start(Stage primaryStage) throws Exception {

		CredentialSingleton.INSTANCE.calculateCredentials();

		this.panel = new Panel();

		double width = CredentialSingleton.INSTANCE.DimensionsFrame.x + CredentialSingleton.INSTANCE.DimensionsInsets.x;
		double height = CredentialSingleton.INSTANCE.DimensionsFrame.y
				+ CredentialSingleton.INSTANCE.DimensionsInsets.y;

		Scene scene = new Scene(this.panel);

		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {

				KeyCode keyCode = event.getCode();

				Logger.INSTANCE.logNewLine(keyCode + " key code pressed");

				Executor.INSTANCE.runLater(() -> {

					if (keyCode == KeyCode.ESCAPE)
						ShutDown.INSTANCE.execute();

					else if (Animation.INSTANCE.isAnimatingSynchronous())
						return;

					ControllerSingleton.INSTANCE.gameState.getCurrentGameState().executeKeyPressed(keyCode);

				});

			}
		});

		primaryStage.setScene(scene);
		primaryStage.setWidth(width);
		primaryStage.setHeight(height);
		primaryStage.setResizable(false);

		primaryStage.setTitle("Peloponnes");

		primaryStage.setX((Screen.getPrimary().getBounds().getWidth() - width) / 2 - this.pixesOnTheLeft);
		primaryStage.setY((Screen.getPrimary().getBounds().getHeight() - height) / 2);

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				ShutDown.INSTANCE.execute();
			}

		});

		primaryStage.show();

		this.panel.startGame();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
