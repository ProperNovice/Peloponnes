package utils;

import gui.PanelGame;
import javafx.scene.paint.Paint;

public class Line implements INode {

	private javafx.scene.shape.Line line = new javafx.scene.shape.Line();

	public Line(double startX, double startY, double endX, double endY) {

		this.line = new javafx.scene.shape.Line(startX, startY, endX, endY);

		final PanelGame panelGame = PanelGameInstance.INSTANCE.getPanelGameInstance();
		PlatformFX.runLater(() -> panelGame.addNode(this.line));

	}

	public final void setStroke(Paint value) {
		PlatformFX.runLater(() -> this.line.setStroke(value));
	}

	public void toBack() {
		PlatformFX.runLater(() -> this.line.toBack());
	}

	@Override
	public void toFront() {
		PlatformFX.runLater(() -> this.line.toFront());
	}

	public final void setVisible(boolean visibility) {
		PlatformFX.runLater(() -> this.line.setVisible(visibility));
	}

	@Override
	public void relocate(double x, double y) {
		PlatformFX.runLater(() -> this.line.relocate(x, y));
	}

	@Override
	public void relocate(final NumbersPair numbersPair) {
		relocate(numbersPair.x, numbersPair.y);
	}

	@Override
	public double getLayoutX() {
		return this.line.getStartX();
	}

	@Override
	public double getLayoutY() {
		return this.line.getStartY();
	}

}
