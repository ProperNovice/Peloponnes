package controller;

import enums.EDisaster;
import interfaces.RestartAble;
import model.TileDisaster;
import utils.ContainerImageViewAbles;
import utils.CoordinatesBuilder;

public class DisasterTiles extends ContainerImageViewAbles<TileDisaster> implements RestartAble {

	public DisasterTiles() {

		createTileDisasters();

	}

	@Override
	protected void createCoordinates() {

		super.coordinates = new CoordinatesBuilder()
				.dimensionsNumbersPair(CredentialSingleton.INSTANCE.DimensionsTilePile)
				.coordinatesNumbersPair(CredentialSingleton.INSTANCE.CoordinatesDisasterTiles)
				.gapNumbersPair(CredentialSingleton.INSTANCE.DimensionsGapBetweenComponents).build();

	}

	private void createTileDisasters() {

		for (EDisaster eDisaster : EDisaster.values()) {

			if (eDisaster == EDisaster.BLANK)
				continue;

			super.arrayList.addLast(new TileDisaster(eDisaster));

		}

		super.arrayList.shuffle();
		relocateImageViews();

		for (TileDisaster tileDisaster : super.arrayList)
			tileDisaster.getList().relocateList();

	}

	@Override
	public void restart() {

		for (TileDisaster tileDisaster : super.arrayList)
			tileDisaster.getList().getArrayList().clear();

	}

}
