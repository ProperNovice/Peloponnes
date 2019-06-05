package controller;

import enums.EDisaster;
import model.DisasterChit;
import utils.ArrayList;
import utils.ContainerImageViewAbles;
import utils.CoordinatesBuilder;

public class DisasterChits extends ContainerImageViewAbles<DisasterChit> {

	public DisasterChits() {

		createDisasterChits();
		super.relocateImageViews();

	}

	@Override
	protected void createCoordinates() {

		super.coordinates = new CoordinatesBuilder()
				.dimensionsNumbersPair(CredentialSingleton.INSTANCE.DimensionsDisasterChit)
				.coordinatesNumbersPair(CredentialSingleton.INSTANCE.CoordinatesDisasterChits)
				.gapNumbersPair(CredentialSingleton.INSTANCE.DimensionsGapBetweenComponents).build();

	}

	private void createDisasterChits() {

		ArrayList<EDisaster> list = new ArrayList<EDisaster>(EDisaster.values());
		list.shuffle();
		list.remove(EDisaster.BLANK);

		for (EDisaster eDisaster : list)
			for (int counter = 1; counter <= 3; counter++)
				super.arrayList.addLast(new DisasterChit(eDisaster));

		super.arrayList.addLast(new DisasterChit(EDisaster.BLANK));

	}

}
