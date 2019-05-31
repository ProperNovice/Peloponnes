package controller;

import utils.NumbersPair;

public enum CredentialSingleton {

	INSTANCE;

	public NumbersPair DimensionsFrame, DimensionsInsets, DimensionsGapBetweenBorders, DimensionsNumberImageView,
			DimensionsTilePile, DimensionsGapBetweenComponents, DimensionsDisasterChitPile;
	public NumbersPair CoordinatesTextPanel, CoordinatesTilePiles, CoordinatesDisasterChits, CoordinatesBoardCurrent,
			CoordinatesBoardIncome;
	public double textHeight, textBoardHeight;

	public void calculateCredentials() {

		double x = 0, y = 0;

		this.DimensionsFrame = new NumbersPair(1366, 788);
		this.DimensionsInsets = new NumbersPair(7, 29);
		this.DimensionsGapBetweenBorders = new NumbersPair(20, 20);
		this.DimensionsGapBetweenComponents = new NumbersPair(2, 2);
		this.DimensionsNumberImageView = new NumbersPair(100, 100);

		this.CoordinatesTextPanel = new NumbersPair(x, y);

		this.textHeight = 50;
		this.textBoardHeight = 40;

		x = 90;
//		x = 118;
		this.DimensionsTilePile = new NumbersPair(x, x);

		x = DimensionsGapBetweenBorders.x;
		y = DimensionsGapBetweenBorders.y;
		this.CoordinatesTilePiles = new NumbersPair(x, y);

		x = this.DimensionsTilePile.x / 2;
		this.DimensionsDisasterChitPile = new NumbersPair(x, x);

		x = this.CoordinatesTilePiles.x;
		y = this.CoordinatesTilePiles.y + 3 * (this.DimensionsTilePile.x + this.DimensionsGapBetweenComponents.y);
		this.CoordinatesDisasterChits = new NumbersPair(x, y);

		x = 2 * this.DimensionsGapBetweenBorders.x + 15 * this.DimensionsTilePile.x
				+ 14 * this.DimensionsGapBetweenComponents.x;
		y = 788;
		this.DimensionsFrame = new NumbersPair(x, y);

		x = DimensionsGapBetweenBorders.x;
		y = CoordinatesDisasterChits.y + DimensionsDisasterChitPile.y + DimensionsGapBetweenComponents.y;
		this.CoordinatesBoardCurrent = new NumbersPair(x, y);

		x = this.CoordinatesBoardCurrent.x + 200;
		y = this.CoordinatesBoardCurrent.y;
		this.CoordinatesBoardIncome = new NumbersPair(x, y);

	}

}
