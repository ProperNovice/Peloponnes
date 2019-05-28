package utils;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import utils.EventHandler.EventHandlerAble;

public class TextOption extends TextIndicator {

	private Rectangle rectangle = null;

	public TextOption(String text, EventHandlerAble eventHandlerAble) {

		super(text);
		createBorder();

		this.rectangle.toFront();
		super.text.toFront();

		super.text.setOnMousePressed(new EventHandler(eventHandlerAble));
		this.rectangle.setOnMousePressed(new EventHandler(eventHandlerAble));

		setEventHandlers();

	}

	private void createBorder() {

		this.rectangle = new Rectangle(super.getWidth(), super.getHeight());
		this.rectangle.setStroke(null);
		this.rectangle.setFill(Color.WHEAT);

	}

	@Override
	public void toFront() {

		PlatformFX.runLater(() -> {

			this.rectangle.toFront();
			super.text.toFront();

		});

	}

	@Override
	public void setWidth(final double pixels) {

		PlatformFX.runLater(() -> {

			int font = 1;
			setFont(font);

			while (getWidth() <= pixels)
				setFont(++font);

			font--;
			setFont(font);

			resizePolyLine();

		});

	}

	@Override
	public void setHeight(final double pixels) {

		PlatformFX.runLater(() -> {

			int font = 1;
			setFont(font);

			while (getHeight() <= pixels)
				setFont(++font);

			font--;
			setFont(font);

			resizePolyLine();

		});

	}

	private void resizePolyLine() {

		PlatformFX.runLater(() -> {
			this.rectangle.getPoints().clear();
			this.rectangle.getPoints().addAll(0.0, 0.0, getWidth(), 0.0,
					getWidth(), getHeight(), 0.0, getHeight(), 0.0, 0.0);
		});

	}

	@Override
	public void relocate(final double x, double y) {

		PlatformFX.runLater(() -> {

			this.text.relocate(x, y);
			this.rectangle.relocate(x, y);

		});

	}

	@Override
	public final void setVisible(final boolean value) {

		PlatformFX.runLater(() -> {

			this.text.setVisible(value);
			this.rectangle.setVisible(value);

		});

	}

	protected class OnMouseEntered implements
			javafx.event.EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {

			rectangle.setFill(Color.BLACK);
			text.setFill(Color.WHITE);

		}

	}

	protected class OnMouseExited implements
			javafx.event.EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {

			rectangle.setFill(Color.WHEAT);
			text.setFill(Color.BLACK);

		}

	}

	private void setEventHandlers() {

		super.text.setOnMouseEntered(new OnMouseEntered());
		this.rectangle.setOnMouseEntered(new OnMouseEntered());
		super.text.setOnMouseExited(new OnMouseExited());
		this.rectangle.setOnMouseExited(new OnMouseExited());

	}

}
