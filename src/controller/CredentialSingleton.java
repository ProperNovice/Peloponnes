package controller;

import utils.NumbersPair;

public enum CredentialSingleton {

	INSTANCE;

	public NumbersPair DimensionsFrame, DimensionsInsets, DimensionsGapBetweenBorders, DimensionsNumberImageView;
	public NumbersPair CoordinatesTextPanel;
	public double textHeight;

	public void calculateCredentials() {

		double x = 0, y = 0;

		DimensionsFrame = new NumbersPair(1366, 788);
		DimensionsInsets = new NumbersPair(7, 29);
		DimensionsGapBetweenBorders = new NumbersPair(20, 20);
		DimensionsNumberImageView = new NumbersPair(100, 100);

		CoordinatesTextPanel = new NumbersPair(x, y);

		textHeight = 50;

	}

}
