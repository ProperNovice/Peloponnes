package utils;

import gui.PanelGame;
import javafx.geometry.Rectangle2D;
import javafx.scene.shape.Rectangle;
import utils.EventHandler.EventHandlerAble;

public class ImageView implements INode {

	private javafx.scene.image.ImageView imageView = null;
	private Image imageTrue = null;
	private Image imageFalse = new Image("misc/imageVisibilityFalse.png");
	private Image imageActive = null;
	private double widthOriginal, heightOriginal, scale = 1;
	private EventHandler eventHandler = null;

	public ImageView(String path, Object object) {

		this.imageTrue = new Image(path);
		createAndAddNode((ImageViewAble) object);

		if (object instanceof EventHandlerAble)
			this.setEventHandler((EventHandlerAble) object);

	}

	public ImageView(Image image, Object object) {

		this.imageTrue = image;
		createAndAddNode((ImageViewAble) object);

		if (object instanceof EventHandlerAble)
			this.setEventHandler((EventHandlerAble) object);

	}

	public ImageView(Image image, ImageViewAble imageViewAble, EventHandlerAble eventHandlerAble) {

		this.imageTrue = image;
		createAndAddNode(imageViewAble);

		this.setEventHandler(eventHandlerAble);

	}

	private void createAndAddNode(ImageViewAble imageViewAble) {

		ImageViewsMap.INSTANCE.getImageViewsMap().put(imageViewAble, this);

		this.imageView = new javafx.scene.image.ImageView(this.imageTrue.getImage());

		PanelGame panelGame = PanelGameInstance.INSTANCE.getPanelGameInstance();
		PlatformFX.runLater(() -> panelGame.addNode(this.imageView));

		this.imageActive = this.imageTrue;

		this.widthOriginal = this.imageView.minWidth(0);
		this.heightOriginal = this.imageView.minHeight(0);

	}

	public final void setVisible(final boolean value) {

		if (value)
			this.imageActive = this.imageTrue;
		else if (!value)
			this.imageActive = this.imageFalse;

		PlatformFX.runLater(() -> this.imageView.setImage(imageActive.getImage()));

	}

	public final boolean isVisible() {
		return this.imageActive.equals(this.imageTrue);
	}

	public void toBack() {
		PlatformFX.runLater(() -> this.imageView.toBack());
	}

	@Override
	public void toFront() {
		PlatformFX.runLater(() -> this.imageView.toFront());
	}

	@Override
	public final double getLayoutX() {
		return this.imageView.getLayoutX();
	}

	@Override
	public final double getLayoutY() {
		return this.imageView.getLayoutY();
	}

	@Override
	public void relocate(final double x, final double y) {
		PlatformFX.runLater(() -> this.imageView.relocate(x, y));
	}

	@Override
	public void relocate(final NumbersPair numbersPair) {
		relocate(numbersPair.x, numbersPair.y);
	}

	public boolean contains(double x, double y) {

		double xCoord = this.getLayoutX();
		double yCoord = this.getLayoutY();
		double width = this.getWidth();
		double height = this.getHeight();

		if (x < xCoord)
			return false;

		else if (x > xCoord + width)
			return false;

		else if (y < yCoord)
			return false;

		else if (y > yCoord + height)
			return false;

		return true;

	}

	public boolean contains(NumbersPair numbersPair) {
		return contains(numbersPair.x, numbersPair.y);
	}

	public final void setViewport(double topLeftX, double topLeftY, double width, double height) {

		PlatformFX.runLater(() -> {

			Rectangle2D rectangle2d = new Rectangle2D(topLeftX, topLeftY, width, height);
			this.imageView.setViewport(rectangle2d);

		});

	}

	public final void setClip(double x, double y, double width, double height) {

		PlatformFX.runLater(() -> {

			Rectangle rectangle = new Rectangle(x, y, width, height);
			this.imageView.setClip(rectangle);

		});

	}

	public final void setRotate(double value) {
		PlatformFX.runLater(() -> this.imageView.setRotate(value));
	}

	private void setEventHandler(EventHandlerAble eventHandlerAble) {

		PlatformFX.runLater(() -> {

			eventHandler = new EventHandler(eventHandlerAble);

			this.imageView.setOnMouseEntered(eventHandler);
			this.imageView.setOnMouseExited(eventHandler);
			this.imageView.setOnMousePressed(eventHandler);

		});

	}

	public final void setImage(final Image image) {

		PlatformFX.runLater(() -> {

			this.imageView.setImage(image.getImage());
			this.imageTrue = image;

		});
	}

	public final Image getImage() {
		return this.imageTrue;
	}

	public final void setScale(double scale) {

		PlatformFX.runLater(() -> {

			this.scale = scale;

			this.imageView.setScaleX(this.scale);
			this.imageView.setScaleY(this.scale);

			double widthTotal = this.widthOriginal;
			double heightTotal = this.heightOriginal;

			double widthNew = this.scale * widthTotal;
			double heightNew = this.scale * heightTotal;

			double translateX = (widthNew - widthTotal) / 2;
			double translateY = (heightNew - heightTotal) / 2;

			this.imageView.setTranslateX(translateX);
			this.imageView.setTranslateY(translateY);

		});

	}

	public void setWidth(double width) {

		double scale = width / this.widthOriginal;
		setScale(scale);

	}

	public void setHeight(double height) {

		double scale = height / this.heightOriginal;
		setScale(scale);

	}

	public double getWidth() {
		return this.widthOriginal * this.scale;
	}

	public double getHeight() {
		return this.heightOriginal * this.scale;
	}

	public double getScale() {
		return this.scale;
	}

	public double getEventX() {
		return this.eventHandler.getMouseEvent().getX() * this.scale;
	}

	public double getEventY() {
		return this.eventHandler.getMouseEvent().getY() * this.scale;
	}

}
