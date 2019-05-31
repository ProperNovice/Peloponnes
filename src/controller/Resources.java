package controller;

import enums.EResource;
import utils.ArrayList;
import utils.TextIndicator;

public class Resources {

	private ArrayList<Resource> resources = new ArrayList<Resource>();

	public Resources() {

		createResourceListCurrent();
		relocateResourceListCurrent();

	}

	public void addCurrentAmount(ArrayList<EResource> list) {

		for (EResource eResource : list) {

			for (Resource resource : this.resources) {

				if (resource.getEResource() != eResource)
					continue;

				if (resource.getCurrentAmount() != resource.capacity) {

					resource.addCurrentAmountSetText();
					continue;

				}

				for (Resource resourceTemp : this.resources)
					if (resourceTemp.getEResource() == EResource.LUXURY_GOODS)
						resourceTemp.addCurrentAmountSetText();

			}

		}

	}

	public void addIncome(ArrayList<EResource> list) {

		for (EResource eResource : list) {

			for (Resource resource : this.resources) {

				if (resource.getEResource() != eResource)
					continue;

				resource.addIncomeSetText();

			}

		}

	}

	private void createResourceListCurrent() {

		this.resources.addLast(new Resource(EResource.COIN));
		this.resources.addLast(new Resource(EResource.WOOD, 10));
		this.resources.addLast(new Resource(EResource.STONE, 10));
		this.resources.addLast(new Resource(EResource.FOOD, 13));
		this.resources.addLast(new Resource(EResource.LUXURY_GOODS));
		this.resources.addLast(new Resource(EResource.POPULATION_GAIN));

	}

	private void relocateResourceListCurrent() {

		double y = CredentialSingleton.INSTANCE.CoordinatesBoardCurrent.y;
		double gap = CredentialSingleton.INSTANCE.textBoardHeight;

		TextIndicator current = new TextIndicator("Current");
		current.setHeight(CredentialSingleton.INSTANCE.textBoardHeight);
		current.relocate(CredentialSingleton.INSTANCE.CoordinatesBoardCurrent.x, y);

		TextIndicator income = new TextIndicator("Income");
		income.setHeight(CredentialSingleton.INSTANCE.textBoardHeight);
		income.relocate(CredentialSingleton.INSTANCE.CoordinatesBoardIncome.x, y);

		y += gap;

		for (Resource resource : this.resources) {

			resource.relocate(y);
			resource.setText();

			y += gap;

		}

	}

	private class Resource {

		private EResource eResource = null;
		private int capacity = -1, currentAmount = 0, income = 0;
		private TextIndicator textIndicatorCurrentAmount = new TextIndicator();
		private TextIndicator textIndicatorIncome = new TextIndicator();

		public Resource(EResource eResource, int capacity) {

			this.eResource = eResource;
			this.capacity = capacity;
			this.textIndicatorCurrentAmount.setHeight(CredentialSingleton.INSTANCE.textBoardHeight);
			this.textIndicatorIncome.setHeight(CredentialSingleton.INSTANCE.textBoardHeight);

		}

		public Resource(EResource eResource) {

			this.eResource = eResource;
			this.textIndicatorCurrentAmount.setHeight(CredentialSingleton.INSTANCE.textBoardHeight);
			this.textIndicatorIncome.setHeight(CredentialSingleton.INSTANCE.textBoardHeight);

		}

		public void addIncomeSetText() {

			this.income++;
			setText();

		}

		public void addCurrentAmountSetText() {

			this.currentAmount++;
			setText();

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

		public void relocate(double y) {

			double xCurrentIndicator = CredentialSingleton.INSTANCE.CoordinatesBoardCurrent.x;
			double xIncome = CredentialSingleton.INSTANCE.CoordinatesBoardIncome.x;

			this.textIndicatorCurrentAmount.relocate(xCurrentIndicator, y);
			this.textIndicatorIncome.relocate(xIncome, y);

		}

		public void setText() {

			setTextTextIndicator(this.textIndicatorCurrentAmount);
			setTextTextIndicator(this.textIndicatorIncome);

		}

		private void setTextTextIndicator(TextIndicator textIndicator) {

			String text = "";
			text += this.eResource.getString();
			text += ": ";

			if (textIndicator == this.textIndicatorCurrentAmount)
				text += Integer.toString(this.currentAmount);
			else if (textIndicator == this.textIndicatorIncome)
				text += Integer.toString(this.income);

			if (textIndicator == this.textIndicatorCurrentAmount && this.capacity != -1) {

				text += "/";
				text += this.capacity;

			}

			textIndicator.setText(text);

		}

	}

}
