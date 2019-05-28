package controller;

import utils.NumbersPair;

public enum CredentialSingleton {

	INSTANCE;

	public NumbersPair DimensionsFrame, DimensionsInsets, DimensionsGapBetweenBorders, DimensionsNumberImageView,
			DimensionsTilePile, DimensionsGapBetweenTiles;
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

		x = 90;
		this.DimensionsTilePile = new NumbersPair(x, x);

		x = 2 * this.DimensionsGapBetweenBorders.x + 15 * this.DimensionsTilePile.x + 14 * this.DimensionsGapBetweenTiles.x;
		this.DimensionsFrame = new NumbersPair(x, 788);

		x = DimensionsGapBetweenBorders.x;
		y = DimensionsGapBetweenBorders.y;
		this.CoordinatesTilePiles = new NumbersPair(x, y);

	}

}
