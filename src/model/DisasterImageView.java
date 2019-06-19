package model;

import controller.CredentialSingleton;
import interfaces.ITile;

public class DisasterImageView extends ATileImageView {

	public DisasterImageView(ITile iTile) {
		super(iTile);
	}

	@Override
	protected String getFilePath() {
		return "disaster";
	}

	@Override
	protected double getX() {
		return 0;
	}

	@Override
	protected double getY() {
		return CredentialSingleton.INSTANCE.DimensionsTileGame.y - CredentialSingleton.INSTANCE.DimensionsBuildIcon.y;
	}

}
