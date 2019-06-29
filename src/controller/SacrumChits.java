package controller;

import model.ASacrumChit;
import model.SacrumChitNonSkull;
import model.SacrumChitSkull;
import utils.ContainerImageViewAbles;
import utils.CoordinatesBuilder;
import utils.DirectionEnum;

public class SacrumChits extends ContainerImageViewAbles<ASacrumChit> {

	public SacrumChits() {
		createList();
	}

	@Override
	protected void createCoordinates() {

		super.coordinates = new CoordinatesBuilder().dimensionsNumbersPair(CredentialSingleton.INSTANCE.DimensionsChit)
				.coordinatesNumbersPair(CredentialSingleton.INSTANCE.CoordinatesSacrumChits)
				.gapNumbersPair(CredentialSingleton.INSTANCE.DimensionsGapBetweenComponents)
				.directionEnumHorizontal(DirectionEnum.LEFT).build();

	}

	private void createList() {

		for (int counter = 1; counter <= 4; counter++)
			super.arrayList.addLast(new SacrumChitNonSkull());

		super.arrayList.addFirst(new SacrumChitSkull());
		super.arrayList.shuffle();

		relocateImageViews();

	}

}
