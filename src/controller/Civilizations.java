package controller;

import enums.EResource;
import enums.ETileType;
import interfaces.ITile;
import model.Tile;
import model.TileBuilder;
import utils.ArrayList;

public class Civilizations {

	private ArrayList<ITile> list = new ArrayList<ITile>();

	public Civilizations() {

		createCivilizations();
		setVisibleFalse();

	}

	private void createCivilizations() {

		ETileType eTileType = ETileType.CIVILIZATION;
		int tileNumber = 0;

		// 01

		tileNumber++;
		this.list.addLast(new TileBuilder().eTileType(eTileType).tileNumber(tileNumber).oneTimeIncome(EResource.COIN, 6)
				.oneTimeIncome(EResource.POPULATION_GAIN, 2).incomePerRound(EResource.FOOD, 2).build());

		// 02

		tileNumber++;
		this.list.addLast(new TileBuilder().eTileType(eTileType).tileNumber(tileNumber).oneTimeIncome(EResource.COIN, 7)
				.oneTimeIncome(EResource.POPULATION_GAIN, 1).incomePerRound(EResource.WOOD, EResource.FOOD).build());

		// 03

		tileNumber++;
		this.list.addLast(new TileBuilder().eTileType(eTileType).tileNumber(tileNumber).oneTimeIncome(EResource.COIN, 8)
				.oneTimeIncome(EResource.POPULATION_GAIN, 2).incomePerRound(EResource.POPULATION_GAIN).build());

		// 04

		tileNumber++;
		this.list.addLast(new TileBuilder().eTileType(eTileType).tileNumber(tileNumber).oneTimeIncome(EResource.COIN, 7)
				.oneTimeIncome(EResource.FOOD, 3).oneTimeIncome(EResource.POPULATION_GAIN, 3)
				.incomePerRound(EResource.FOOD).build());

		// 05

		tileNumber++;
		this.list.addLast(new TileBuilder().eTileType(eTileType).tileNumber(tileNumber).oneTimeIncome(EResource.COIN, 7)
				.oneTimeIncome(EResource.WOOD, 4).oneTimeIncome(EResource.POPULATION_GAIN, 3)
				.incomePerRound(EResource.WOOD).build());

		// 06

		tileNumber++;
		this.list.addLast(new TileBuilder().eTileType(eTileType).tileNumber(tileNumber).oneTimeIncome(EResource.COIN, 8)
				.oneTimeIncome(EResource.STONE, 3).oneTimeIncome(EResource.POPULATION_GAIN, 2)
				.incomePerRound(EResource.STONE).build());

		// 07

		tileNumber++;
		this.list.addLast(new TileBuilder().eTileType(eTileType).tileNumber(tileNumber).oneTimeIncome(EResource.COIN, 5)
				.oneTimeIncome(EResource.POPULATION_GAIN, 2).incomePerRound(EResource.WOOD, EResource.STONE).build());

	}

	private void setVisibleFalse() {

		for (ITile iTile : this.list) {

			Tile tile = (Tile) iTile;
			tile.getImageView().setVisible(false);

		}

	}

	public ITile getRandomCivilization() {
		return this.list.getRandom();
	}

	public ITile getSpecificCivilization(int number) {
		return this.list.get(number);
	}

	public ITile getLast() {
		return this.list.getLast();
	}

}
