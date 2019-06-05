package model;

import controller.CredentialSingleton;
import interfaces.ITile;
import utils.ContainerImageViewAbles;
import utils.CoordinatesBuilder;
import utils.DirectionEnum;

public class TileRowNormal extends ContainerImageViewAbles<ITile> {

	@Override
	protected void createCoordinates() {

		super.coordinates = new CoordinatesBuilder()
				.dimensionsNumbersPair(CredentialSingleton.INSTANCE.DimensionsTileGame)
				.gapNumbersPair(CredentialSingleton.INSTANCE.DimensionsGapBetweenComponents)
				.directionEnumHorizontal(DirectionEnum.LEFT)
				.coordinatesNumbersPair(CredentialSingleton.INSTANCE.CoordinatesTileRowNormal).build();

	}

}
