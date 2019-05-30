package model;

import controller.CredentialSingleton;
import interfaces.ITile;
import utils.EventHandler.EventHandlerAble;
import utils.ImageView;
import utils.ImageViewAble;

public abstract class Tile implements ImageViewAble, EventHandlerAble, ITile {

	protected void createImageView(String fileName) {

		ImageView imageView = new ImageView(fileName, this);
		imageView.setWidth(CredentialSingleton.INSTANCE.DimensionsTilePile.x);

	}

	protected abstract void printTile();

	@Override
	public void handleMouseButtonPressedPrimary() {

		printTile();

	}

}
