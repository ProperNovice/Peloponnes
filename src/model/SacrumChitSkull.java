package model;

import utils.Image;

public class SacrumChitSkull extends ASacrumChit {

	private Image skull = new Image("sacrum skull.png");

	@Override
	public boolean isSkull() {
		return true;
	}

	@Override
	public void flip() {

		if (getImageView().getImage() == super.nonSkull)
			getImageView().setImage(this.skull);
		else
			getImageView().setImage(super.nonSkull);

	}

}
