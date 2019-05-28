package utils;

import controller.ControllerSingleton;
import controller.CredentialSingleton;
import enums.EText;
import utils.EventHandler.EventHandlerAble;

public class TextGame implements EventHandlerAble {

	private EText textEnum = null;
	private TextIndicator text = null;

	public TextGame(EText eText) {

		this.textEnum = eText;
		createText();

	}

	private void createText() {

		String text = this.textEnum.getString();

		switch (this.textEnum.getTextTypeEnum()) {

		case INDICATOR:
			this.text = new TextIndicator(text);
			break;

		case OPTION:
			this.text = new TextOption(text, this);
			break;

		}

		if (this.textEnum.getString().contains("\n"))
			this.text.setHeight(2 * CredentialSingleton.INSTANCE.textHeight);
		else
			this.text.setHeight(CredentialSingleton.INSTANCE.textHeight);

		this.text.setVisible(false);

	}

	@Override
	public void handleMouseButtonPressedPrimary() {
		ControllerSingleton.INSTANCE.gameState.getCurrentGameState().handleTextOptionPressed(this.textEnum);
	}

	public void relocate(double x, double y) {
		this.text.relocate(x, y);
	}

	public void relocate(NumbersPair numbersPair) {
		relocate(numbersPair.x, numbersPair.y);
	}

	public void toFront() {
		this.text.toFront();
	}

	public void setVisible(boolean value) {
		this.text.setVisible(value);
	}

	public EText getTextEnum() {
		return this.textEnum;
	}

}
