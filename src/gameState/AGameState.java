package gameState;

import controller.ControllerSingleton;
import enums.EText;
import interfaces.ITile;
import javafx.scene.input.KeyCode;
import utils.KeyCodeHandler;
import utils.Logger;

public abstract class AGameState {

	protected final ControllerSingleton controllerSingleton = ControllerSingleton.INSTANCE;

	public abstract void handleGameStateChange();

	public final void handleTextOptionPressed(EText textEnum) {

		Logger.INSTANCE.log("text executing");
		Logger.INSTANCE.logNewLine(textEnum);

		ControllerSingleton.INSTANCE.text.concealText();
		executeTextOption(textEnum);

	}

	public final void executeKeyPressed(KeyCode keyCode) {

		int textOptionToHandle = KeyCodeHandler.INSTANCE.getTextOptionToHandle(keyCode);

		EText textEnumPressed = ControllerSingleton.INSTANCE.text.getTextEnumOptionShowing(textOptionToHandle);

		if (textEnumPressed == null)
			return;

		Logger.INSTANCE.log("executing key pressed -> " + keyCode);
		handleTextOptionPressed(textEnumPressed);

	}

	protected void executeTextOption(EText eText) {

	}

	public final void handleTilePressed(ITile iTile) {

		if (this.controllerSingleton.tileRows.getTileRowNormal().getArrayList().contains(iTile))
			handleTilePressedRowNormal(iTile);
		else if (this.controllerSingleton.tileRows.getTileRowConquest().getArrayList().contains(iTile))
			handleTilePressedRowCoquest(iTile);
		else if (this.controllerSingleton.board.getArrayList().contains(iTile))
			handleTilePressedBoard(iTile);

	}

	protected void handleTilePressedRowNormal(ITile iTile) {

	}

	protected void handleTilePressedRowCoquest(ITile iTile) {

	}

	protected void handleTilePressedBoard(ITile iTile) {

	}

}
