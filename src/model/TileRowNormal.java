package model;

import controller.CredentialSingleton;
import utils.CoordinatesBuilder;
import utils.DirectionEnum;

public class TileRowNormal extends ATileRow {

	@Override
	protected void createCoordinates() {

		super.coordinates = new CoordinatesBuilder()
				.dimensionsNumbersPair(CredentialSingleton.INSTANCE.DimensionsTileGame)
				.gapNumbersPair(CredentialSingleton.INSTANCE.DimensionsGapBetweenComponents)
				.directionEnumHorizontal(DirectionEnum.LEFT)
				.coordinatesNumbersPair(CredentialSingleton.INSTANCE.CoordinatesTileRowNormal).build();

	}

}
