package tiles;

import controller.CredentialSingleton;
import interfaces.ITile;
import utils.ContainerImageViewAbles;
import utils.CoordinatesBuilder;

public class TilePile extends ContainerImageViewAbles<ITile> {

	@Override
	protected void createCoordinates() {

		super.coordinates = new CoordinatesBuilder().dimensionsNumbersPair(CredentialSingleton.INSTANCE.DimensionsTilePile)
				.gapNumbersPair(CredentialSingleton.INSTANCE.DimensionsGapBetweenTiles).build();

	}

}
