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

			int amount = 3;

			if (eDisaster == EDisaster.BLANK)
				amount = 1;

			for (int counter = 1; counter <= amount; counter++)
				this.list.get(eDisaster).addLast(new DisasterChit(eDisaster));

		}

	}

	@Override
	public void restart() {

		super.arrayList.clear();

		ArrayList<EDisaster> listEDisaster = new ArrayList<EDisaster>(EDisaster.values());
		listEDisaster.shuffle();
		listEDisaster.remove(EDisaster.BLANK);

		for (EDisaster eDisaster : listEDisaster)
			super.arrayList.addAll(this.list.get(eDisaster).clone());

		super.arrayList.addAll(this.list.get(EDisaster.BLANK));

		for (DisasterChit disasterChit : this.list.get(EDisaster.BLANK))
			disasterChit.getImageView().setVisible(true);

		super.relocateImageViews();

	}

}
