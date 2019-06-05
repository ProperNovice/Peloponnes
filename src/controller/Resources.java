package controller;

import enums.EResource;
import utils.ArrayList;
import utils.HashMap;
import utils.TextIndicator;

public class Resources {

	private ArrayList<Resource> resources = new ArrayList<Resource>();
	private HashMap<Integer, PopulationCoinsLuxuryGoods> populationCoins = new HashMap<Integer, PopulationCoinsLuxuryGoods>();

	public Resources() {

		createResourceList();
		relocateResourceList();
		createPopulationCoinsList();

	}

	public void addCurrentAmount(ArrayList<EResource> list) {

		for (EResource eResource : list) {

			switch (eResource) {

			case POPULATION_LOST:

				for (Resource resource : this.resources)
					if (resource.getEResource() == EResource.POPULATION_GAIN)
						resource.removeCurrentAmountSetText();

				break;

			default:

				for (Resource resource : this.resources) {

					if (resource.getEResource() != eResource)
						continue;

					if (resource.getCurrentAmount() != resource.getCapacity())
						resource.addCurrentAmountSetText();

					else
						for (Resource resourceTemp : this.resources)
							if (resourceTemp.getEResource() == EResource.LUXURY_GOODS)
								resourceTemp.addCurrentAmountSetText();

				}

				break;

			}

		}

	}

	public void addIncome(ArrayList<EResource> list) {

		// handling income

		for (EResource eResource : list) {

			for (Resource resource : this.resources) {

				if (resource.getEResource() != eResource)
					continue;

				resource.addIncomeSetText();
				break;

			}

		}

		// handling coins - luxury goods

		int currentPopulation = -1;

		for (Resource resource : this.resources)
			if (resource.getEResource() == EResource.POPULATION_GAIN)
				currentPopulation = resource.getIncome().size();

		currentPopulation = Math.min(currentPopulation, 20);

		int coinIncome = this.populationCoins.get(currentPopulation).getCoins();
		int luxuryGoodsIncome = this.populationCoins.get(currentPopulation).getLuxuryGoods();

		for (Resource resource : this.resources)
			if (resource.getEResource() == EResource.COIN)
				resource.setIncome(coinIncome);
			else if (resource.getEResource() == EResource.LUXURY_GOODS)
				resource.setIncome(luxuryGoodsIncome);

	}

	public void earnIncomeForTheRound() {

		ArrayList<EResource> list = new ArrayList<EResource>();

		for (Resource resource : this.resources)
			list.addAll(resource.getIncome());

		addCurrentAmount(list);

	}

	private void createResourceList() {

		this.resources.addLast(new Resource(EResource.COIN));
		this.resources.addLast(new Resource(EResource.WOOD, 10));
		this.resources.addLast(new Resource(EResource.STONE, 10));
		this.resources.addLast(new Resource(EResource.FOOD, 13));
		this.resources.addLast(new Resource(EResource.LUXURY_GOODS));
		this.resources.addLast(new Resource(EResource.POPULATION_GAIN));

	}

	private void relocateResourceList() {

		double y = CredentialSingleton.INSTANCE.CoordinatesCurrentResources.y;
		double gap = CredentialSingleton.INSTANCE.textBoardHeight;

		TextIndicator current = new TextIndicator("Current");
		current.setHeight(CredentialSingleton.INSTANCE.textBoardHeight);
		current.relocate(CredentialSingleton.INSTANCE.CoordinatesCurrentResources.x, y);

		TextIndicator income = new TextIndicator("Income");
		income.setHeight(CredentialSingleton.INSTANCE.textBoardHeight);
		income.relocate(CredentialSingleton.INSTANCE.CoordinatesIncome.x, y);

		y += gap;

		for (Resource resource : this.resources) {

			resource.relocate(y);
			resource.setText();

			y += gap;

		}

	}

	private void createPopulationCoinsList() {

		addPopulationCoinsToList(0, 2, 1, 0);
		addPopulationCoinsToList(3, 4, 2, 0);
		addPopulationCoinsToList(5, 7, 3, 0);
		addPopulationCoinsToList(8, 10, 4, 0);
		addPopulationCoinsToList(11, 13, 5, 0);
		addPopulationCoinsToList(14, 16, 5, 2);
		addPopulationCoinsToList(17, 19, 5, 4);
		addPopulationCoinsToList(20, 20, 5, 6);

	}

	private void addPopulationCoinsToList(int populationFrom, int populationTo, int coins, int luxuryGoods) {

		for (int counter = populationFrom; counter <= populationTo; counter++)
			this.populationCoins.put(counter, new PopulationCoinsLuxuryGoods(coins, luxuryGoods));

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

		public void removeIncomeSetText() {

			this.income--;
			setText();

		}

		public void addCurrentAmountSetText() {

			this.currentAmount++;
			setText();

		}

		public void removeCurrentAmountSetText() {

			this.currentAmount--;
			setText();

		}

		public void setIncome(int income) {

			this.income = income;
			setText();

		}

		public ArrayList<EResource> getIncome() {

			ArrayList<EResource> list = new ArrayList<EResource>();

			for (int counter = 1; counter <= this.income; counter++)
				list.addLast(this.eResource);

			return list;

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

			double xCurrentIndicator = CredentialSingleton.INSTANCE.CoordinatesCurrentResources.x;
			double xIncome = CredentialSingleton.INSTANCE.CoordinatesIncome.x;

			this.textIndicatorCurrentAmount.relocate(xCurrentIndicator, y);
			this.textIndicatorIncome.relocate(xIncome, y);

		}

		public void setText() {

			setTextIndicator(this.textIndicatorCurrentAmount);
			setTextIndicator(this.textIndicatorIncome);

		}

		private void setTextIndicator(TextIndicator textIndicator) {

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

	private class PopulationCoinsLuxuryGoods {

		private int coins = -1, luxuryGoods = -1;

		public PopulationCoinsLuxuryGoods(int coins, int luxuryGoods) {

			this.coins = coins;
			this.luxuryGoods = luxuryGoods;

		}

		public int getCoins() {
			return this.coins;
		}

		public int getLuxuryGoods() {
			return this.luxuryGoods;
		}

	}

}
