package controller;

import enums.EDisaster;
import interfaces.RestartAble;
import model.DisasterChit;
import utils.ArrayList;
import utils.ContainerImageViewAbles;
import utils.CoordinatesBuilder;
import utils.HashMap;

public class DisasterChits extends ContainerImageViewAbles<DisasterChit> implements RestartAble {

	private HashMap<EDisaster, ArrayList<DisasterChit>> list = new HashMap<EDisaster, ArrayList<DisasterChit>>();

	public DisasterChits() {

		createDisasterChits();
		restart();
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

		for (EDisaster eDisaster : EDisaster.values()) {

			this.list.put(eDisaster, new ArrayList<DisasterChit>());

			for (int counter = 1; counter <= 3; counter++)
				this.list.get(eDisaster).addLast(new DisasterChit(eDisaster));

		}

	}

	@Override
	public void restart() {

		super.arrayList.clear();

		ArrayList<EDisaster> listEDisaster = ControllerSingleton.INSTANCE.modifiers.eDisasters;

		for (EDisaster eDisaster : listEDisaster)
			super.arrayList.addAll(this.list.get(eDisaster).clone());

		for (DisasterChit disasterChit : this.list.get(EDisaster.BLANK))
			disasterChit.getImageView().setVisible(true);

		super.relocateImageViews();

	}

}
