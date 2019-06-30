package controller;

import enums.EResource;
import enums.ETileAbility;
import enums.ETileType;
import interfaces.ITile;
import interfaces.RestartAble;
import model.Tile;
import model.TileBuilder;
import utils.ContainerImageViewAbles;
import utils.CoordinatesBuilder;
import utils.DirectionEnum;

public class Civilizations extends ContainerImageViewAbles<ITile> implements RestartAble {

	public Civilizations() {

		createCivilizations();
		setVisibleFalse();

	}

	@Override
	protected void createCoordinates() {

		super.coordinates = new CoordinatesBuilder()
				.dimensionsNumbersPair(CredentialSingleton.INSTANCE.DimensionsTileGame)
				.coordinatesNumbersPair(CredentialSingleton.INSTANCE.CoordinatesBoard)
				.gapNumbersPair(CredentialSingleton.INSTANCE.DimensionsGapBetweenComponents)
				.directionEnumHorizontal(DirectionEnum.LEFT).directionEnumVertical(DirectionEnum.UP).objectsPerRow(5)
				.build();

	}

	private void createCivilizations() {

		ETileType eTileType = ETileType.CIVILIZATION;
		int tileNumber = -1;

		// 00

		tileNumber++;
		super.arrayList.addLast(new TileBuilder().eTileType(eTileType).tileNumber(tileNumber)
				.oneTimeIncome(EResource.COIN, 5).oneTimeIncome(EResource.POPULATION_GAIN, 4)
				.incomePerRound(EResource.STONE).eTileAbility(ETileAbility.PROTECTION_FROM_PLAGUE).build());

		// 01

		tileNumber++;
		super.arrayList
				.addLast(new TileBuilder().eTileType(eTileType).tileNumber(tileNumber).oneTimeIncome(EResource.COIN, 6)
						.oneTimeIncome(EResource.POPULATION_GAIN, 2).incomePerRound(EResource.FOOD, 2).build());

		// 02

		tileNumber++;
		super.arrayList.addLast(new TileBuilder().eTileType(eTileType).tileNumber(tileNumber)
				.oneTimeIncome(EResource.COIN, 7).oneTimeIncome(EResource.POPULATION_GAIN, 1)
				.incomePerRound(EResource.WOOD, EResource.FOOD).build());

		// 03

		tileNumber++;
		super.arrayList
				.addLast(new TileBuilder().eTileType(eTileType).tileNumber(tileNumber).oneTimeIncome(EResource.COIN, 8)
						.oneTimeIncome(EResource.POPULATION_GAIN, 2).incomePerRound(EResource.POPULATION_GAIN).build());

		// 04

		tileNumber++;
		super.arrayList.addLast(new TileBuilder().eTileType(eTileType).tileNumber(tileNumber)
				.oneTimeIncome(EResource.COIN, 7).oneTimeIncome(EResource.FOOD, 3)
				.oneTimeIncome(EResource.POPULATION_GAIN, 3).incomePerRound(EResource.FOOD).build());

		// 05

		tileNumber++;
		super.arrayList.addLast(new TileBuilder().eTileType(eTileType).tileNumber(tileNumber)
				.oneTimeIncome(EResource.COIN, 7).oneTimeIncome(EResource.WOOD, 4)
				.oneTimeIncome(EResource.POPULATION_GAIN, 3).incomePerRound(EResource.WOOD).build());

		// 06

		tileNumber++;
		super.arrayList.addLast(new TileBuilder().eTileType(eTileType).tileNumber(tileNumber)
				.oneTimeIncome(EResource.COIN, 8).oneTimeIncome(EResource.STONE, 3)
				.oneTimeIncome(EResource.POPULATION_GAIN, 2).incomePerRound(EResource.STONE).build());

		// 07

		tileNumber++;
		super.arrayList.addLast(new TileBuilder().eTileType(eTileType).tileNumber(tileNumber)
				.oneTimeIncome(EResource.COIN, 5).oneTimeIncome(EResource.POPULATION_GAIN, 2)
				.incomePerRound(EResource.WOOD, EResource.STONE).build());

		// 08

		tileNumber++;
		super.arrayList.addLast(new TileBuilder().eTileType(eTileType).tileNumber(tileNumber)
				.oneTimeIncome(EResource.COIN, 6).oneTimeIncome(EResource.POPULATION_GAIN, 2)
				.eTileAbility(ETileAbility.PROTECTION_FROM_EARTHQUAKE, ETileAbility.PROTECTION_FROM_DROUGHT).build());

		// 09

		tileNumber++;
		super.arrayList
				.addLast(new TileBuilder().eTileType(eTileType).tileNumber(tileNumber).oneTimeIncome(EResource.COIN, 6)
						.oneTimeIncome(EResource.POPULATION_GAIN, 1).incomePerRound(EResource.LUXURY_GOODS, 2)
						.eTileAbility(ETileAbility.IGNORE_PLACEMENT_RULES_WHEN_PLACING_LAND_TILES).build());

		// 10

		tileNumber++;
		super.arrayList.addLast(new TileBuilder().eTileType(eTileType).tileNumber(tileNumber)
				.oneTimeIncome(EResource.COIN, 4).oneTimeIncome(EResource.POPULATION_GAIN, 2)
				.incomePerRound(EResource.POPULATION_GAIN, 2).build());

		// 11
		tileNumber++;
		super.arrayList
				.addLast(new TileBuilder().eTileType(eTileType).tileNumber(tileNumber).oneTimeIncome(EResource.COIN, 9)
						.oneTimeIncome(EResource.POPULATION_GAIN, 3).incomePerRound(EResource.POPULATION_LOST)
						.eTileAbility(ETileAbility.PROTECTION_FROM_DISASTERS).build());

		// 12
		tileNumber++;
		super.arrayList
				.addLast(new TileBuilder().eTileType(eTileType).tileNumber(tileNumber).oneTimeIncome(EResource.COIN, 8)
						.oneTimeIncome(EResource.POPULATION_GAIN, 2).incomePerRound(EResource.COIN, EResource.WOOD)
						.eTileAbility(ETileAbility.SACRIFICE_INHABITANTS_EACH_ROUND).build());

		// 13
		tileNumber++;
		super.arrayList
				.addLast(new TileBuilder().eTileType(eTileType).tileNumber(tileNumber).oneTimeIncome(EResource.COIN, 3)
						.oneTimeIncome(EResource.POPULATION_GAIN).incomePerRound(EResource.CHOOSE_INCOME, 3).build());

		// 14
		tileNumber++;
		super.arrayList
				.addLast(new TileBuilder().eTileType(eTileType).tileNumber(tileNumber).oneTimeIncome(EResource.COIN, 9)
						.oneTimeIncome(EResource.FOOD, 9).oneTimeIncome(EResource.POPULATION_GAIN, 3)
						.incomePerRound(EResource.WOOD_LOST, EResource.STONE_LOST, EResource.FOOD)
						.eTileAbility(ETileAbility.PROTECTION_FROM_TEMPEST).build());

	}

	private void setVisibleFalse() {

		for (ITile iTile : super.arrayList) {

			Tile tile = (Tile) iTile;
			tile.getImageView().setVisible(false);

		}

	}

	public ITile getRandomCivilization() {
		return super.arrayList.getRandom();
	}

	public ITile getSpecificCivilization(int number) {
		return super.arrayList.get(number);
	}

	public ITile getLast() {
		return super.arrayList.getLast();
	}

	@Override
	public void restart() {

		for (ITile iTile : super.arrayList) {

			Tile tile = (Tile) iTile;
			tile.getImageView().setVisible(false);

		}

	}

}
