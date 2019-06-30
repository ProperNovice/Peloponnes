package controller;

import enums.EResource;
import interfaces.IncomeAble;
import interfaces.RestartAble;
import model.PopulationCoinsLuxuryGoods;
import model.Resource;
import utils.ArrayList;
import utils.HashMap;
import utils.ShutDown;
import utils.TextIndicator;

public class Resources implements RestartAble {

	private ArrayList<Resource> resources = new ArrayList<Resource>();
	private HashMap<Integer, PopulationCoinsLuxuryGoods> populationCoins = new HashMap<Integer, PopulationCoinsLuxuryGoods>();
	private int populationLostPerRound = 0, coinsPerRound = 0, woodLostPerRound = 0, stoneLostPerRound = 0;

	public Resources() {

		createResourceList();
		relocateResourceList();
		createPopulationCoinsList();

	}

	public void addCurrentAmount(ArrayList<EResource> list) {

		// handling current amount

		for (EResource eResource : list) {

			switch (eResource) {

			case POPULATION_LOST:

				for (Resource resource : this.resources)
					if (resource.getEResource() == EResource.POPULATION_GAIN)
						resource.removeOneSetText();

				break;

			default:

				for (Resource resource : this.resources) {

					if (resource.getEResource() != eResource)
						continue;

					if (resource.getCurrentAmount() != resource.getCapacity())
						resource.addOneSetText();

					else
						for (Resource resourceTemp : this.resources)
							if (resourceTemp.getEResource() == EResource.LUXURY_GOODS)
								resourceTemp.addOneSetText();

				}

				break;

			}

		}

		handlePopulationToCoinsAndLuxury();

	}

	public void addIncome(ArrayList<EResource> list) {

		for (EResource eResource : list) {

			if (eResource == EResource.POPULATION_LOST) {

				this.populationLostPerRound++;
				continue;

			} else if (eResource == EResource.COIN) {

				this.coinsPerRound++;
				continue;

			} else if (eResource == EResource.WOOD_LOST) {

				this.woodLostPerRound++;
				continue;

			} else if (eResource == EResource.STONE_LOST) {

				this.stoneLostPerRound++;
				continue;

			}

			for (Resource resource : this.resources) {

				if (resource.getEResource() != eResource)
					continue;

				resource.addIncomeSetText();
				break;

			}

		}

		handlePopulationToCoinsAndLuxury();

	}

	private void handlePopulationToCoinsAndLuxury() {

		// set luxury income default

		int luxuryGoodsIncomeDefault = 0;

		IncomeAble incomeAble = (IncomeAble) ControllerSingleton.INSTANCE.modifiers.tileCivilization;

		for (EResource eResource : incomeAble.getIncomePerRound())
			if (eResource == EResource.LUXURY_GOODS)
				luxuryGoodsIncomeDefault++;

		//

		int currentPopulation = -1;

		for (Resource resource : this.resources)
			if (resource.getEResource() == EResource.POPULATION_GAIN)
				currentPopulation = resource.getCurrentAmount();

		currentPopulation = Math.min(currentPopulation, 20);

		int coinIncome = this.populationCoins.get(currentPopulation).getCoins();
		coinIncome += this.coinsPerRound;
		int luxuryGoodsIncome = this.populationCoins.get(currentPopulation).getLuxuryGoods();

		luxuryGoodsIncome += luxuryGoodsIncomeDefault;

		for (Resource resource : this.resources)
			if (resource.getEResource() == EResource.COIN)
				resource.setIncome(coinIncome);
			else if (resource.getEResource() == EResource.LUXURY_GOODS)
				resource.setIncome(luxuryGoodsIncome);

	}

	public void removeIncome(ArrayList<EResource> list) {

		for (EResource eResource : list) {

			for (Resource resource : this.resources) {

				if (resource.getEResource() != eResource)
					continue;

				resource.removeIncomeSetText();
				break;

			}

		}

		handlePopulationToCoinsAndLuxury();

	}

	public void earnIncomeForTheRound() {

		for (Resource resource : this.resources)
			addCurrentAmount(resource.getEResource(), resource.getIncome());

		if (this.populationLostPerRound > 0)
			for (int counter = 1; counter <= this.populationLostPerRound; counter++)
				if (getCurrentAmount(EResource.POPULATION_GAIN) > 0)
					removeCurrentAmount(EResource.POPULATION_GAIN, this.populationLostPerRound);

		if (this.woodLostPerRound > 0)
			for (int counter = 1; counter <= this.woodLostPerRound; counter++)
				if (getCurrentAmount(EResource.WOOD) > 0)
					removeCurrentAmount(EResource.WOOD, this.woodLostPerRound);

		if (this.stoneLostPerRound > 0)
			for (int counter = 1; counter <= this.stoneLostPerRound; counter++)
				if (getCurrentAmount(EResource.STONE) > 0)
					removeCurrentAmount(EResource.STONE, this.stoneLostPerRound);

		handlePopulationToCoinsAndLuxury();

	}

	public int getCurrentAmount(EResource eResource) {

		for (Resource resource : this.resources)
			if (resource.getEResource() == eResource)
				return resource.getCurrentAmount();

		ShutDown.INSTANCE.execute("getCurrentAmount didn't find eResource");
		return -1;

	}

	public void removeCurrentAmount(EResource eResource, int amount) {

		for (Resource resource : this.resources)
			if (resource.getEResource() == eResource)
				for (int counter = 1; counter <= amount; counter++)
					resource.removeOneSetText();

		handlePopulationToCoinsAndLuxury();

	}

	public void addCurrentAmount(EResource eResource, int amount) {

		ArrayList<EResource> list = new ArrayList<EResource>();

		for (int counter = 1; counter <= amount; counter++)
			list.addLast(eResource);

		addCurrentAmount(list);

	}

	private void createResourceList() {

		this.resources.addLast(new Resource(EResource.COIN, true));
		this.resources.addLast(new Resource(EResource.STONE, 10, true));
		this.resources.addLast(new Resource(EResource.WOOD, 10, true));
		this.resources.addLast(new Resource(EResource.FOOD, 13, true));
		this.resources.addLast(new Resource(EResource.LUXURY_GOODS, true));
		this.resources.addLast(new Resource(EResource.POPULATION_GAIN, true));
		this.resources.addLast(new Resource(EResource.POPULATION_SACRUM, false));

	}

	private void relocateResourceList() {

		double y = CredentialSingleton.INSTANCE.CoordinatesResources.y;
		double gap = CredentialSingleton.INSTANCE.textResourcesHeight;

		TextIndicator current = new TextIndicator("Current");
		current.setHeight(CredentialSingleton.INSTANCE.textResourcesHeight);
		current.relocate(CredentialSingleton.INSTANCE.CoordinatesResources.x, y);

		TextIndicator income = new TextIndicator("Income");
		income.setHeight(CredentialSingleton.INSTANCE.textResourcesHeight);
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

	@Override
	public void restart() {

		for (Resource resource : this.resources)
			resource.resetSetText();

		this.populationLostPerRound = 0;
		this.coinsPerRound = 0;
		this.woodLostPerRound = 0;
		this.stoneLostPerRound = 0;

	}

}
