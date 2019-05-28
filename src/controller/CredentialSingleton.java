package controller;

import utils.NumbersPair;

public enum CredentialSingleton {

	INSTANCE;

	public NumbersPair DimensionsFrame, DimensionsInsets, DimensionsGapBetweenBorders, DimensionsNumberImageView,
			DimensionsTile, DimensionsGapBetweenTiles;
	public NumbersPair CoordinatesTextPanel, CoordinatesTilePiles;
	public double textHeight;

	public void calculateCredentials() {

		double x = 0, y = 0;

		this.DimensionsFrame = new NumbersPair(1366, 788);
		this.DimensionsInsets = new NumbersPair(7, 29);
		this.DimensionsGapBetweenBorders = new NumbersPair(20, 20);
		this.DimensionsGapBetweenTiles = new NumbersPair(4, 4);
		this.DimensionsNumberImageView = new NumbersPair(100, 100);

		this.CoordinatesTextPanel = new NumbersPair(x, y);

		this.textHeight = 50;

		x = 100;
		this.DimensionsTile = new NumbersPair(x, x);

		x = DimensionsGapBetweenBorders.x;
		y = DimensionsGapBetweenBorders.y;
		this.CoordinatesTilePiles = new NumbersPair(x, y);

	}

}
