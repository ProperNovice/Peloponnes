package controller;

import enums.EResource;
import enums.ETileAbility;
import enums.ETileType;
import interfaces.ITile;
import interfaces.RestartAble;
import model.Tile;
import model.TileBuilder;
import utils.ArrayList;

public class Civilizations implements RestartAble {

	private ArrayList<ITile> list = new ArrayList<ITile>();

	public Civilizations() {

		createCivilizations();
		setVisibleFalse();

	}

	private void createCivilizations() {

		ETileType eTileType = ETileType.CIVILIZATION;
		int tileNumber = -1;

		// 00

		tileNumber++;
		this.list.addLast(new TileBuilder().eTileType(eTileType).tileNumber(tileNumber).oneTimeIncome(EResource.COIN, 5)
				.oneTimeIncome(EResource.POPULATION_GAIN, 4).incomePerRound(EResource.STONE)
				.eTileAbility(ETileAbility.PROTECTION_FROM_PLAGUE).build());

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

		// 08

		tileNumber++;
		this.list.addLast(new TileBuilder().eTileType(eTileType).tileNumber(tileNumber).oneTimeIncome(EResource.COIN, 6)
				.oneTimeIncome(EResource.POPULATION_GAIN, 2)
				.eTileAbility(ETileAbility.PROTECTION_FROM_EARTHQUAKE, ETileAbility.PROTECTION_FROM_DROUGHT).build());

		// 09

		tileNumber++;
		this.list.addLast(new TileBuilder().eTileType(eTileType).tileNumber(tileNumber).oneTimeIncome(EResource.COIN, 6)
				.oneTimeIncome(EResource.POPULATION_GAIN, 1).incomePerRound(EResource.LUXURY_GOODS, 2)
				.eTileAbility(ETileAbility.IGNORE_PLACEMENT_RULES_WHEN_PLACING_LAND_TILES).build());

		// 10

		tileNumber++;
		this.list.addLast(new TileBuilder().eTileType(eTileType).tileNumber(tileNumber).oneTimeIncome(EResource.COIN, 4)
				.oneTimeIncome(EResource.POPULATION_GAIN, 2).incomePerRound(EResource.POPULATION_GAIN, 2).build());

		// 11
		tileNumber++;
		this.list.addLast(new TileBuilder().eTileType(eTileType).tileNumber(tileNumber).oneTimeIncome(EResource.COIN, 9)
				.oneTimeIncome(EResource.POPULATION_GAIN, 3).incomePerRound(EResource.POPULATION_LOST)
				.eTileAbility(ETileAbility.PROTECTION_FROM_DISASTERS).build());

		// 12
		tileNumber++;
		this.list.addLast(new TileBuilder().eTileType(eTileType).tileNumber(tileNumber).oneTimeIncome(EResource.COIN, 8)
				.oneTimeIncome(EResource.POPULATION_GAIN, 2).incomePerRound(EResource.COIN, EResource.WOOD)
				.eTileAbility(ETileAbility.SACRIFICE_INHABITANTS_EACH_ROUND).build());

		// 13
		tileNumber++;
		this.list.addLast(new TileBuilder().eTileType(eTileType).tileNumber(tileNumber).oneTimeIncome(EResource.COIN, 3)
				.oneTimeIncome(EResource.POPULATION_GAIN).incomePerRound(EResource.CHOOSE_INCOME, 3).build());

		// 14
		tileNumber++;
		this.list.addLast(new TileBuilder().eTileType(eTileType).tileNumber(tileNumber).oneTimeIncome(EResource.COIN, 9)
				.oneTimeIncome(EResource.FOOD, 9).oneTimeIncome(EResource.POPULATION_GAIN, 3)
				.incomePerRound(EResource.WOOD_LOST, EResource.STONE_LOST, EResource.FOOD)
				.eTileAbility(ETileAbility.PROTECTION_FROM_TEMPEST).build());

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

	@Override
	public void restart() {

		for (ITile iTile : this.list) {

			Tile tile = (Tile) iTile;
			tile.getImageView().setVisible(false);

		}

	}

}
