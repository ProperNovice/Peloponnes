package controller;

import enums.EResource;
import enums.ETileType;
import interfaces.ITile;
import model.TileBuilder;
import utils.ArrayList;

public class Civilizations {

	private ArrayList<ITile> list = new ArrayList<ITile>();

	public Civilizations() {

		createCivilizations();

	}

	private void createCivilizations() {

		ETileType eTileType = ETileType.CIVILIZATION;
		int tileNumber = 0;

		// 01

		tileNumber++;
		this.list.addLast(new TileBuilder().eTileType(eTileType).tileNumber(tileNumber).oneTimeIncome(EResource.COIN, 6)
				.oneTimeIncome(EResource.POPULATION_GAIN, 2).incomePerRound(EResource.FOOD, 2).build());

	}

	public ITile getRandomCivilization() {
		return this.list.getRandom();
	}

}
