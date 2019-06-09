package model;

import controller.CredentialSingleton;
import interfaces.ITile;
import utils.ImageView;
import utils.ImageViewAble;

public class BuildImageView implements ImageViewAble {

	private ITile iTile = null;

	public BuildImageView(ITile iTile) {
		this.iTile = iTile;
		createImageView();
	}

	private void createImageView() {

		ImageView imageView = new ImageView("build.png", this);
		imageView.setWidth(CredentialSingleton.INSTANCE.DimensionsBuildIcon.x);

		ImageViewAble imageViewAbleITile = (ImageViewAble) iTile;

		double x = imageViewAbleITile.getImageView().getLayoutX();
		x += CredentialSingleton.INSTANCE.DimensionsTileGame.x;
		x -= CredentialSingleton.INSTANCE.DimensionsBuildIcon.x;

		double y = imageViewAbleITile.getImageView().getLayoutY();

		imageView.relocate(x, y);

	}

}
