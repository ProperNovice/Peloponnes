package controller;

import utils.NumbersPair;

public enum CredentialSingleton {

	INSTANCE;

	public NumbersPair DimensionsFrame, DimensionsInsets, DimensionsGapBetweenBorders, DimensionsNumberImageView,
			DimensionsTilePile, DimensionsGapBetweenComponents, DimensionsDisasterChit, DimensionsTileGame;
	public NumbersPair CoordinatesTextPanel, CoordinatesTilePiles, CoordinatesDisasterChits,
			CoordinatesCurrentResources, CoordinatesIncome, CoordinatesBoardPopulationCoin, CoordinatesBoard,
			CoordinatesTileRowConquest, CoordinatesTileRowNormal;
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
		this.DimensionsTilePile = new NumbersPair(x, x);

		x = 118;
		this.DimensionsTileGame = new NumbersPair(x, x);

		x = DimensionsGapBetweenBorders.x;
		y = DimensionsGapBetweenBorders.y;
		this.CoordinatesTilePiles = new NumbersPair(x, y);

		x = this.DimensionsTilePile.x / 2;
		this.DimensionsDisasterChit = new NumbersPair(x, x);

		x = this.CoordinatesTilePiles.x;
		y = this.CoordinatesTilePiles.y + 3 * (this.DimensionsTilePile.x + this.DimensionsGapBetweenComponents.y);
		this.CoordinatesDisasterChits = new NumbersPair(x, y);

		x = 2 * this.DimensionsGapBetweenBorders.x + 15 * this.DimensionsTilePile.x
				+ 14 * this.DimensionsGapBetweenComponents.x;
		y = 788;
		this.DimensionsFrame = new NumbersPair(x, y);

		x = DimensionsGapBetweenBorders.x;
		y = CoordinatesDisasterChits.y + DimensionsDisasterChit.y + DimensionsGapBetweenComponents.y;
		this.CoordinatesCurrentResources = new NumbersPair(x, y);

		x = this.CoordinatesCurrentResources.x + 200;
		y = this.CoordinatesCurrentResources.y;
		this.CoordinatesIncome = new NumbersPair(x, y);

		x = this.CoordinatesCurrentResources.x + 200;
		y = this.CoordinatesCurrentResources.y;
		this.CoordinatesBoardPopulationCoin = new NumbersPair(x, y);

		x = this.DimensionsFrame.x - this.DimensionsGapBetweenBorders.x - this.DimensionsTileGame.x;
		y = this.DimensionsFrame.y - this.DimensionsGapBetweenBorders.y - this.DimensionsTileGame.y;
		this.CoordinatesBoard = new NumbersPair(x, y);

		x = this.CoordinatesBoard.x;
		y = this.CoordinatesDisasterChits.y + DimensionsDisasterChit.y + this.DimensionsGapBetweenComponents.y;
		this.CoordinatesTileRowConquest = new NumbersPair(x, y);

		x = this.CoordinatesTileRowConquest.x;
		y = this.CoordinatesTileRowConquest.y + DimensionsTileGame.y + this.DimensionsGapBetweenComponents.y;
		this.CoordinatesTileRowNormal = new NumbersPair(x, y);

	}

}
