package controller;

import enums.EResource;
import utils.ArrayList;
import utils.ImageViewAble;
import utils.TextIndicator;

public class Board implements ImageViewAble {

	private ArrayList<Resource> currentResources = new ArrayList<Resource>();
	private ArrayList<Resource> income = new ArrayList<Resource>();

	public Board() {

		createResourceListCurrent();
		relocateResourceListCurrent();
		createResourceListIncome();
		relocateResourceListIncome();

	}

	private void createResourceListCurrent() {

		this.currentResources.addLast(new Resource(EResource.WOOD, 10));
		this.currentResources.addLast(new Resource(EResource.STONE, 10));
		this.currentResources.addLast(new Resource(EResource.FOOD, 13));
		this.currentResources.addLast(new Resource(EResource.LUXURY_GOODS, -1));
		this.currentResources.addLast(new Resource(EResource.POPULATION_GAIN, -1));
		this.currentResources.addLast(new Resource(EResource.COIN, -1));

	}

	private void relocateResourceListCurrent() {

		double x = CredentialSingleton.INSTANCE.CoordinatesBoardCurrent.x;
		double y = CredentialSingleton.INSTANCE.CoordinatesBoardCurrent.y;
		double gap = CredentialSingleton.INSTANCE.textBoardHeight;

		TextIndicator textIndicator = new TextIndicator("Current");
		textIndicator.setHeight(CredentialSingleton.INSTANCE.textBoardHeight);
		textIndicator.relocate(x, y);
		y += gap;

		for (Resource resource : this.currentResources) {

			resource.relocate(x, y);
			y += gap;
			resource.setText();

		}

	}

	private void createResourceListIncome() {

		this.income.addLast(new Resource(EResource.WOOD, -1));
		this.income.addLast(new Resource(EResource.STONE, -1));
		this.income.addLast(new Resource(EResource.FOOD, -1));
		this.income.addLast(new Resource(EResource.LUXURY_GOODS, -1));
		this.income.addLast(new Resource(EResource.POPULATION_GAIN, -1));
		this.income.addLast(new Resource(EResource.COIN, -1));

	}

	private void relocateResourceListIncome() {

		double x = CredentialSingleton.INSTANCE.CoordinatesBoardIncome.x;
		double y = CredentialSingleton.INSTANCE.CoordinatesBoardIncome.y;
		double gap = CredentialSingleton.INSTANCE.textBoardHeight;

		TextIndicator textIndicator = new TextIndicator("Income");
		textIndicator.setHeight(CredentialSingleton.INSTANCE.textBoardHeight);
		textIndicator.relocate(x, y);
		y += gap;

		for (Resource resource : this.income) {

			resource.relocate(x, y);
			y += gap;
			resource.setText();

		}

	}

	private class Resource {

		private EResource eResource = null;
		private int capacity = -1, currentAmount = 0, income = 0;
		private TextIndicator textIndicator = new TextIndicator();

		public Resource(EResource eResource, int capacity) {

			this.eResource = eResource;
			this.capacity = capacity;
			this.textIndicator.setHeight(CredentialSingleton.INSTANCE.textBoardHeight);

		}

		public EResource getEResource() {
			return this.eResource;
		}

		public int getCapacity() {
			return this.capacity;
		}

		public int getCurrentAmount() {
			return this.currentAmount;
		}

		public int getIncome() {
			return this.income;
		}

		public void relocate(double x, double y) {
			this.textIndicator.relocate(x, y);
		}

		public void setText() {

			String text = "";
			text += this.eResource.getString();
			text += ": ";
			text += Integer.toString(this.currentAmount);

			if (this.capacity != -1) {

				text += "/";
				text += this.capacity;

			}

			this.textIndicator.setText(text);

		}

	}

}
