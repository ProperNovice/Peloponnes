package model;

import controller.CredentialSingleton;
import interfaces.ITile;

public class SelectImageView extends ATileImageView {

	public SelectImageView(ITile iTile) {
		super(iTile);
	}

	@Override
	protected String getFilePath() {
		return "select";
	}

	@Override
	protected double getX() {
		return 0;
	}

	@Override
	protected double getY() {
		return CredentialSingleton.INSTANCE.DimensionsTileGame.y
				- CredentialSingleton.INSTANCE.DimensionsImageViewIcon.y;
	}

}
