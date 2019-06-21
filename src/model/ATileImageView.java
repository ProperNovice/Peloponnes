package model;

import controller.CredentialSingleton;
import interfaces.ITile;
import utils.ImageView;
import utils.ImageViewAble;

public abstract class ATileImageView implements ImageViewAble {

	private ITile iTile = null;

	public ATileImageView(ITile iTile) {

		this.iTile = iTile;
		createImageView();

	}

	private void createImageView() {

		ImageView imageView = new ImageView(getFilePath() + ".png", this);
		imageView.setWidth(CredentialSingleton.INSTANCE.DimensionsBuildIcon.x);
		relocateToFrontSetVisibleTrue();

	}

	public void relocateToFrontSetVisibleTrue() {

		ImageViewAble imageViewAbleITile = (ImageViewAble) this.iTile;

		double x = imageViewAbleITile.getImageView().getLayoutX();
		x += getX();

		double y = imageViewAbleITile.getImageView().getLayoutY();
		y += getY();

		this.getImageView().relocate(x, y);
		this.getImageView().toFront();
		this.getImageView().setVisible(true);

	}

	protected abstract String getFilePath();

	protected abstract double getX();

	protected abstract double getY();

}
