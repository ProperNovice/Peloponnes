package model;

import controller.CredentialSingleton;
import enums.EDisaster;
import utils.ImageView;
import utils.ImageViewAble;

public class DisasterChit implements ImageViewAble {

	private EDisaster eDisaster = null;

	public DisasterChit(EDisaster eDisaster) {

		this.eDisaster = eDisaster;
		createImageView();

	}

	private void createImageView() {

		String filePath = "disasters/";
		filePath += this.eDisaster.getString();
		filePath += ".png";

		ImageView imageView = new ImageView(filePath, this);
		imageView.setWidth(CredentialSingleton.INSTANCE.DimensionsDisasterChit.x);

	}

}
