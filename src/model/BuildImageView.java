package model;

import controller.CredentialSingleton;
import interfaces.ITile;

public class BuildImageView extends ATileImageView {

	public BuildImageView(ITile iTile) {
		super(iTile);
	}

	@Override
	protected String getFilePath() {
		return "build";
	}

	@Override
	protected double getX() {
		return CredentialSingleton.INSTANCE.DimensionsTileGame.x - CredentialSingleton.INSTANCE.DimensionsImageViewIcon.x;
	}

	@Override
	protected double getY() {
		return 0;
	}

}
