package model;

import controller.CredentialSingleton;
import interfaces.ITile;

public class SeaTileImageViewBuild extends ATileImageView {

	public SeaTileImageViewBuild(ITile iTile) {
		super(iTile);
	}

	@Override
	protected String getFilePath() {
		return "build";
	}

	@Override
	protected double getX() {
		return (CredentialSingleton.INSTANCE.DimensionsTilePile.x
				- CredentialSingleton.INSTANCE.DimensionsImageViewIcon.x) / 2;
	}

	@Override
	protected double getY() {
		return (CredentialSingleton.INSTANCE.DimensionsTilePile.y
				- CredentialSingleton.INSTANCE.DimensionsImageViewIcon.y) / 2;
	}

}
