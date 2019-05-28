package utils;

import gui.PanelGame;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import utils.EventHandler.EventHandlerAble;

public class TextIndicator implements INode {

	protected javafx.scene.text.Text text = null;

	public TextIndicator() {
		createText(null);
	}

	public TextIndicator(String text) {
		createText(text);
	}

	public TextIndicator(int text) {
		createText(Integer.toString(text));
	}

	private void createText(String text) {

		if (text == null)
			this.text = new javafx.scene.text.Text();

		else
			this.text = new javafx.scene.text.Text(text);

		PanelGame panelGame = PanelGameInstance.INSTANCE.getPanelGameInstance();
		panelGame.addNode(this.text);

	}

	public void setVisible(final boolean value) {
		PlatformFX.runLater(() -> this.text.setVisible(value));
	}

	public boolean isVisible() {
		return this.text.isVisible();
	}

	public void toBack() {
		PlatformFX.runLater(() -> this.text.toBack());
	}

	@Override
	public void toFront() {
		PlatformFX.runLater(() -> this.text.toFront());
	}

	@Override
	public final double getLayoutX() {
		return this.text.getLayoutX();
	}

	@Override
	public final double getLayoutY() {
		return this.text.getLayoutY();
	}

	public double getWidth() {
		return text.minWidth(0);
	}

	public double getHeight() {
		return text.minHeight(0);
	}

	public void setWidth(final double pixels) {

		PlatformFX.runLater(() -> {

			int font = 1;
			setFont(font);

			while (getWidth() <= pixels)
				setFont(++font);

			font--;
			setFont(font);

		});

	}

	public void setHeight(final double pixels) {

		PlatformFX.runLater(() -> {

			int font = 1;
			setFont(font);

			while (getHeight() <= pixels)
				setFont(++font);

			setFont(font);

		});

	}

	@Override
	public void relocate(final double x, final double y) {
		PlatformFX.runLater(() -> this.text.relocate(x, y));
	}

	@Override
	public void relocate(final NumbersPair numbersPair) {
		relocate(numbersPair.x, numbersPair.y);
	}

	public void setEventHandler(EventHandlerAble eventHandlerAble) {

		PlatformFX.runLater(() -> {

			EventHandler eventHandler = new EventHandler(eventHandlerAble);

			this.text.setOnMouseEntered(eventHandler);
			this.text.setOnMouseExited(eventHandler);
			this.text.setOnMousePressed(eventHandler);

		});

	}

	public final void setText(final String text) {
		PlatformFX.runLater(() -> this.text.setText(text));
	}

	public final void setText(final int text) {
		PlatformFX.runLater(() -> this.text.setText(Integer.toString(text)));
	}

	protected final void setFont(final int value) {
		PlatformFX.runLater(() -> this.text.setFont(new Font(value)));
	}

	public final void setFill(Paint value) {
		PlatformFX.runLater(() -> this.text.setFill(value));
	}

}
