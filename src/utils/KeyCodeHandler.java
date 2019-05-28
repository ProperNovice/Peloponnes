package utils;

import javafx.scene.input.KeyCode;

public enum KeyCodeHandler {

	INSTANCE;

	public int getTextOptionToHandle(KeyCode keyCode) {

		int textOptionToHandle = -1;

		switch (keyCode) {

		case Q:
			textOptionToHandle = 1;
			break;

		case W:
			textOptionToHandle = 2;
			break;

		case E:
			textOptionToHandle = 3;
			break;

		case R:
			textOptionToHandle = 4;
			break;

		default:
			break;

		}

		return textOptionToHandle;

	}

}
