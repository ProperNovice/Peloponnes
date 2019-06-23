package controller;

import utils.NumbersPair;

public enum CredentialSingleton {

	INSTANCE;

	public NumbersPair DimensionsFrame, DimensionsInsets, DimensionsGapBetweenBorders, DimensionsNumberImageView,
			DimensionsTilePile, DimensionsGapBetweenComponents, DimensionsDisasterChit, DimensionsTileGame,
			DimensionsBuildIcon;
	public NumbersPair CoordinatesTextPanel, CoordinatesTilePiles, CoordinatesDisasterChits, CoordinatesResources,
			CoordinatesIncome, CoordinatesBoard, CoordinatesTileRowConquest, CoordinatesTileRowNormal,
			CoordinatesTileConquest, CoordinatesDisasterTiles, CoordinatesScoring;
	public double textHeight, textResourcesHeight, textScoringHeight, frameX, frameY;

	public void calculateCredentials() {

		double x = 0, y = 0;

		this.DimensionsFrame = new NumbersPair(1366, 788);
		this.DimensionsInsets = new NumbersPair(7, 29);
		this.DimensionsGapBetweenBorders = new NumbersPair(20, 20);
		this.DimensionsGapBetweenComponents = new NumbersPair(2, 2);
		this.DimensionsNumberImageView = new NumbersPair(100, 100);

		this.textHeight = 40;

		x = 90;
//		x = 118;
		this.DimensionsTilePile = new NumbersPair(x, x);

		x = 118;
		this.DimensionsTileGame = new NumbersPair(x, x);

		this.textResourcesHeight = (2 * DimensionsTileGame.y + DimensionsGapBetweenComponents.y) / 7;

		this.textScoringHeight = this.DimensionsTilePile.y / 3;

		x = DimensionsGapBetweenBorders.x;
		y = DimensionsGapBetweenBorders.y;
		this.CoordinatesTilePiles = new NumbersPair(x, y);

		x = this.DimensionsTilePile.x / 2;
		this.DimensionsDisasterChit = new NumbersPair(x, x);

		x = DimensionsGapBetweenBorders.x;
		y = DimensionsGapBetweenBorders.y + 4 * (DimensionsTilePile.x + DimensionsGapBetweenComponents.y);
		this.CoordinatesDisasterTiles = new NumbersPair(x, y);

		x = this.CoordinatesTilePiles.x;
		y = this.CoordinatesDisasterTiles.y + this.DimensionsTilePile.x + this.DimensionsGapBetweenComponents.y;
		this.CoordinatesDisasterChits = new NumbersPair(x, y);

		x = DimensionsGapBetweenBorders.x;
		y = CoordinatesDisasterChits.y + DimensionsDisasterChit.y + DimensionsGapBetweenComponents.y;
		this.CoordinatesResources = new NumbersPair(x, y);

		x = this.CoordinatesResources.x + 180;
		y = this.CoordinatesResources.y;
		this.CoordinatesIncome = new NumbersPair(x, y);

		this.frameX = 2 * this.DimensionsGapBetweenBorders.x + 15 * this.DimensionsTilePile.x
				+ 14 * this.DimensionsGapBetweenComponents.x;

		x = this.frameX - this.DimensionsGapBetweenBorders.x - this.DimensionsTileGame.x;
		y = this.CoordinatesDisasterChits.y + DimensionsDisasterChit.y + 2 * this.DimensionsGapBetweenComponents.y;
		this.CoordinatesTileRowConquest = new NumbersPair(x, y);

		x = this.CoordinatesTileRowConquest.x;
		y = this.CoordinatesTileRowConquest.y + DimensionsTileGame.y + this.DimensionsGapBetweenComponents.y;
		this.CoordinatesTileRowNormal = new NumbersPair(x, y);

		x = CoordinatesTileRowConquest.x;
		y = this.CoordinatesTileRowNormal.y + DimensionsTileGame.y + 5 * this.DimensionsGapBetweenComponents.y;
		this.CoordinatesBoard = new NumbersPair(x, y);

		this.frameY = this.CoordinatesBoard.y + this.DimensionsTileGame.y + DimensionsGapBetweenBorders.y;
		this.DimensionsFrame = new NumbersPair(this.frameX, this.frameY);

		x = DimensionsTileGame.x * 0.4;
		y = x;
		this.DimensionsBuildIcon = new NumbersPair(x, y);

		x = 350;
		y = this.CoordinatesResources.y + 7 * this.textHeight / 2 - this.textHeight / 2;
		this.CoordinatesTextPanel = new NumbersPair(x, y);

		x = this.CoordinatesDisasterTiles.x + 5 * (this.DimensionsTilePile.x + this.DimensionsGapBetweenComponents.x);
		y = this.CoordinatesDisasterTiles.y;
		this.CoordinatesScoring = new NumbersPair(x, y);

		x = this.CoordinatesTileRowConquest.x;
		y = this.CoordinatesTileRowConquest.y;
		this.CoordinatesTileConquest = new NumbersPair(x, y);

	}

}
