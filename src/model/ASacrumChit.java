package model;

import controller.CredentialSingleton;
import utils.Image;
import utils.ImageView;
import utils.ImageViewAble;

public abstract class ASacrumChit implements ImageViewAble {

	protected Image nonSkull = null;

	public ASacrumChit() {
		createChit();
	}

	private void createChit() {

		String fileName = "";
		fileName += "sacrum non ";
		fileName += "skull.png";

		this.nonSkull = new Image(fileName);

		ImageView imageView = new ImageView(this.nonSkull, this);
		imageView.setWidth(CredentialSingleton.INSTANCE.DimensionsChit.x);

	}

	public abstract boolean isSkull();

	public abstract void flip();

}
