package controller;

import interfaces.ITile;
import interfaces.RestartAble;
import utils.ContainerImageViewAbles;
import utils.CoordinatesBuilder;

public class Board extends ContainerImageViewAbles<ITile> implements RestartAble {

	@Override
	protected void createCoordinates() {

		super.coordinates = new CoordinatesBuilder()
				.dimensionsNumbersPair(CredentialSingleton.INSTANCE.DimensionsTileGame)
				.gapNumbersPair(CredentialSingleton.INSTANCE.DimensionsGapBetweenComponents)
				.coordinatesNumbersPair(CredentialSingleton.INSTANCE.CoordinatesBoard).build();

	}

	public void relocateList() {

		double totalWidth = (super.arrayList.size() - 1) * (CredentialSingleton.INSTANCE.DimensionsTileGame.x
				+ CredentialSingleton.INSTANCE.DimensionsGapBetweenComponents.x);

		super.relocateList(CredentialSingleton.INSTANCE.CoordinatesBoard.x - totalWidth,
				CredentialSingleton.INSTANCE.CoordinatesBoard.y);

	}

	@Override
	public void restart() {

		super.arrayList.clear();

	}

}
