package controller;

import enums.EPhase;
import enums.EResource;
import enums.ETileAbility;
import enums.ETileType;
import tiles.TileBuilder;
import tiles.TilePile;
import utils.HashMap;

public class TilePiles {

	private HashMap<EPhase, TilePile> list = new HashMap<EPhase, TilePile>();

	public TilePiles() {

		createTiles();
		relocateTiles();

	}

	private void createTiles() {

		EPhase ePhase = null;
		int tileNumber = 0;

		for (EPhase ePhaseTemp : EPhase.values())
			this.list.put(ePhaseTemp, new TilePile());

		// A

		ePhase = EPhase.A;
		tileNumber = 0;

		// 01

		tileNumber++;
		this.list.get(ePhase).getArrayList().addLast(new TileBuilder().ePhase(ePhase).tileNumber(tileNumber)
				.eTileType(ETileType.LAND).prestigePoints(3).buyCost(3).incomePerRound(EResource.STONE, 3).build());

		// 02

		tileNumber++;
		this.list.get(ePhase).getArrayList()
				.addLast(new TileBuilder().ePhase(ePhase).tileNumber(tileNumber).eTileType(ETileType.LAND)
						.prestigePoints(2).buyCost(3).incomePerRound(EResource.WOOD, EResource.STONE)
						.oneTimeIncome(EResource.POPULATION_GAIN).build());

		// 03

		tileNumber++;
		this.list.get(ePhase).getArrayList()
				.addLast(new TileBuilder().ePhase(ePhase).tileNumber(tileNumber).eTileType(ETileType.LAND)
						.prestigePoints(2).buyCost(4).incomePerRound(EResource.STONE).oneTimeIncome(EResource.COIN, 5)
						.oneTimeIncome(EResource.POPULATION_GAIN).build());

		// 04

		tileNumber++;
		this.list.get(ePhase).getArrayList()
				.addLast(new TileBuilder().ePhase(ePhase).tileNumber(tileNumber).eTileType(ETileType.LAND)
						.prestigePoints(2).buyCost(4).incomePerRound(EResource.WOOD, EResource.STONE)
						.oneTimeIncome(EResource.COIN, 2).oneTimeIncome(EResource.POPULATION_GAIN).build());

		// 05

		tileNumber++;
		this.list.get(ePhase).getArrayList().addLast(new TileBuilder().ePhase(ePhase).tileNumber(tileNumber)
				.eTileType(ETileType.LAND).prestigePoints(1).buyCost(3).incomePerRound(EResource.FOOD, 3).build());

		// 06

		tileNumber++;
		this.list.get(ePhase).getArrayList()
				.addLast(new TileBuilder().ePhase(ePhase).tileNumber(tileNumber).eTileType(ETileType.LAND)
						.prestigePoints(1).buyCost(3).incomePerRound(EResource.FOOD, 2).incomePerRound(EResource.WOOD)
						.oneTimeIncome(EResource.POPULATION_GAIN, 2).build());

		// 07

		tileNumber++;
		this.list.get(ePhase).getArrayList()
				.addLast(new TileBuilder().ePhase(ePhase).tileNumber(tileNumber).eTileType(ETileType.LAND)
						.prestigePoints(2).buyCost(2).incomePerRound(EResource.WOOD, 3)
						.oneTimeIncome(EResource.POPULATION_GAIN, 2).build());

		// 08

		tileNumber++;
		this.list.get(ePhase).getArrayList()
				.addLast(new TileBuilder().ePhase(ePhase).tileNumber(tileNumber).eTileType(ETileType.LAND)
						.prestigePoints(2).buyCost(3).incomePerRound(EResource.FOOD).incomePerRound(EResource.WOOD, 2)
						.oneTimeIncome(EResource.POPULATION_GAIN, 2).build());

		// 09

		tileNumber++;
		this.list.get(ePhase).getArrayList()
				.addLast(new TileBuilder().ePhase(ePhase).tileNumber(tileNumber).eTileType(ETileType.LAND)
						.prestigePoints(1).buyCost(2).incomePerRound(EResource.FOOD).incomePerRound(EResource.WOOD, 2)
						.oneTimeIncome(EResource.POPULATION_GAIN, 2).build());

		// 10

		tileNumber++;
		this.list.get(ePhase).getArrayList()
				.addLast(new TileBuilder().ePhase(ePhase).tileNumber(tileNumber).eTileType(ETileType.LAND)
						.prestigePoints(2).buyCost(2).incomePerRound(EResource.WOOD, 2).oneTimeIncome(EResource.COIN, 2)
						.oneTimeIncome(EResource.POPULATION_GAIN).build());

		// 11

		tileNumber++;
		this.list.get(ePhase).getArrayList()
				.addLast(new TileBuilder().ePhase(ePhase).tileNumber(tileNumber).eTileType(ETileType.BUILDING)
						.prestigePoints(4).buyCost(2).oneTimeIncome(EResource.COIN, 4)
						.oneTimeIncome(EResource.POPULATION_LOST, 2).constructionCost(EResource.STONE, 2)
						.constructionCost(EResource.WOOD)
						.eTileAbility(ETileAbility.IGNORE_PLACEMENT_RULES_WHEN_PLACING_LAND_TILES).build());

		// 12

		tileNumber++;
		this.list.get(ePhase).getArrayList()
				.addLast(new TileBuilder().ePhase(ePhase).tileNumber(tileNumber).eTileType(ETileType.BUILDING)
						.prestigePoints(4).buyCost(1).oneTimeIncome(EResource.POPULATION_GAIN, 2)
						.constructionCost(EResource.WOOD, 2).incomePerRound(EResource.FOOD).build());

		// 13

		tileNumber++;
		this.list.get(ePhase).getArrayList()
				.addLast(new TileBuilder().ePhase(ePhase).tileNumber(tileNumber).eTileType(ETileType.BUILDING)
						.prestigePoints(3).buyCost(3).oneTimeIncome(EResource.POPULATION_GAIN, 3)
						.constructionCost(EResource.WOOD, 3).incomePerRound(EResource.FOOD).build());

		// 14

		tileNumber++;
		this.list.get(ePhase).getArrayList()
				.addLast(new TileBuilder().ePhase(ePhase).tileNumber(tileNumber).eTileType(ETileType.BUILDING)
						.prestigePoints(4).buyCost(1).oneTimeIncome(EResource.POPULATION_GAIN)
						.constructionCost(EResource.STONE).incomePerRound(EResource.FOOD).build());

		// 15

		tileNumber++;
		this.list.get(ePhase).getArrayList().addLast(new TileBuilder().ePhase(ePhase).tileNumber(tileNumber)
				.eTileType(ETileType.BUILDING).prestigePoints(3).buyCost(2).oneTimeIncome(EResource.POPULATION_GAIN, 2)
				.constructionCost(EResource.STONE, 3).constructionCost(EResource.WOOD)
				.incomePerRound(EResource.CHOOSE_INCOME).eTileAbility(ETileAbility.PROTECTION_FROM_DECLINE).build());

		// B

		ePhase = EPhase.B;
		tileNumber = 0;

		// 01

		tileNumber++;
		this.list.get(ePhase).getArrayList()
				.addLast(new TileBuilder().ePhase(ePhase).tileNumber(tileNumber).eTileType(ETileType.BUILDING)
						.prestigePoints(4).buyCost(4).incomePerRound(EResource.FOOD)
						.oneTimeIncome(EResource.POPULATION_GAIN, 2).constructionCost(EResource.STONE, 3)
						.constructionCost(EResource.WOOD).eTileAbility(ETileAbility.PROTECTION_FROM_PLAGUE).build());

		// 02

		tileNumber++;
		this.list.get(ePhase).getArrayList()
				.addLast(new TileBuilder().ePhase(ePhase).tileNumber(tileNumber).eTileType(ETileType.BUILDING)
						.prestigePoints(4).buyCost(4).incomePerRound(EResource.FOOD)
						.oneTimeIncome(EResource.POPULATION_GAIN, 2).constructionCost(EResource.STONE, 4).build());

		// 03

		tileNumber++;
		this.list.get(ePhase).getArrayList()
				.addLast(new TileBuilder().ePhase(ePhase).tileNumber(tileNumber).eTileType(ETileType.BUILDING)
						.prestigePoints(7).buyCost(6).oneTimeIncome(EResource.LUXURY_GOODS, 3)
						.oneTimeIncome(EResource.POPULATION_GAIN, 3).constructionCost(EResource.STONE, 3)
						.constructionCost(EResource.WOOD, 2).build());

		// 04

		tileNumber++;
		this.list.get(ePhase).getArrayList()
				.addLast(new TileBuilder().ePhase(ePhase).tileNumber(tileNumber).eTileType(ETileType.LAND)
						.prestigePoints(2).buyCost(4).oneTimeIncome(EResource.POPULATION_GAIN, 2)
						.incomePerRound(EResource.FOOD, 2).incomePerRound(EResource.WOOD).build());

		// 05

		tileNumber++;
		this.list.get(ePhase).getArrayList()
				.addLast(new TileBuilder().ePhase(ePhase).tileNumber(tileNumber).eTileType(ETileType.LAND)
						.prestigePoints(2).buyCost(5).oneTimeIncome(EResource.POPULATION_GAIN)
						.incomePerRound(EResource.FOOD, 3).eTileAbility(ETileAbility.SUPPLY_ROUND).build());

		// 06

		tileNumber++;
		this.list.get(ePhase).getArrayList()
				.addLast(new TileBuilder().ePhase(ePhase).tileNumber(tileNumber).eTileType(ETileType.LAND)
						.prestigePoints(4).buyCost(3).oneTimeIncome(EResource.POPULATION_GAIN, 3)
						.incomePerRound(EResource.WOOD, 2).build());

		// 07

		tileNumber++;
		this.list.get(ePhase).getArrayList()
				.addLast(new TileBuilder().ePhase(ePhase).tileNumber(tileNumber).eTileType(ETileType.LAND)
						.prestigePoints(4).buyCost(4).oneTimeIncome(EResource.COIN)
						.oneTimeIncome(EResource.POPULATION_GAIN).incomePerRound(EResource.WOOD)
						.incomePerRound(EResource.STONE).build());

		// 08

		tileNumber++;
		this.list.get(ePhase).getArrayList()
				.addLast(new TileBuilder().ePhase(ePhase).tileNumber(tileNumber).eTileType(ETileType.BUILDING)
						.prestigePoints(5).buyCost(2).oneTimeIncome(EResource.LUXURY_GOODS, 5)
						.oneTimeIncome(EResource.POPULATION_GAIN).constructionCost(EResource.STONE, 2)
						.constructionCost(EResource.WOOD, 3).build());

		// 09

		tileNumber++;
		this.list.get(ePhase).getArrayList()
				.addLast(new TileBuilder().ePhase(ePhase).tileNumber(tileNumber).eTileType(ETileType.LAND)
						.prestigePoints(3).buyCost(5).oneTimeIncome(EResource.POPULATION_GAIN)
						.incomePerRound(EResource.STONE, 3).build());

		// 10

		tileNumber++;
		this.list.get(ePhase).getArrayList()
				.addLast(new TileBuilder().ePhase(ePhase).tileNumber(tileNumber).eTileType(ETileType.LAND)
						.prestigePoints(4).buyCost(4).oneTimeIncome(EResource.COIN, 3)
						.oneTimeIncome(EResource.POPULATION_GAIN).incomePerRound(EResource.STONE).build());

		// 11

		tileNumber++;
		this.list.get(ePhase).getArrayList()
				.addLast(new TileBuilder().ePhase(ePhase).tileNumber(tileNumber).eTileType(ETileType.LAND)
						.prestigePoints(4).buyCost(3).oneTimeIncome(EResource.COIN, 3)
						.oneTimeIncome(EResource.POPULATION_GAIN, 2).incomePerRound(EResource.WOOD).build());

		// 12

		tileNumber++;
		this.list.get(ePhase).getArrayList()
				.addLast(new TileBuilder().ePhase(ePhase).tileNumber(tileNumber).eTileType(ETileType.BUILDING)
						.prestigePoints(5).buyCost(2).constructionCost(EResource.WOOD, 3)
						.oneTimeIncome(EResource.FOOD, 3).build());

		// 13

		tileNumber++;
		this.list.get(ePhase).getArrayList()
				.addLast(new TileBuilder().ePhase(ePhase).tileNumber(tileNumber).eTileType(ETileType.BUILDING)
						.prestigePoints(3).buyCost(2).constructionCost(EResource.STONE, 2)
						.constructionCost(EResource.WOOD, 2).incomePerRound(EResource.STONE, 2).build());

		// 14

		tileNumber++;
		this.list.get(ePhase).getArrayList()
				.addLast(new TileBuilder().ePhase(ePhase).tileNumber(tileNumber).eTileType(ETileType.BUILDING)
						.prestigePoints(3).buyCost(4).constructionCost(EResource.STONE, 2)
						.constructionCost(EResource.WOOD).oneTimeIncome(EResource.POPULATION_GAIN)
						.incomePerRound(EResource.FOOD).eTileAbility(ETileAbility.PROTECTION_FROM_PLAGUE).build());

		// 15

		tileNumber++;
		this.list.get(ePhase).getArrayList()
				.addLast(new TileBuilder().ePhase(ePhase).tileNumber(tileNumber).eTileType(ETileType.BUILDING)
						.prestigePoints(5).buyCost(4).constructionCost(EResource.STONE, 5)
						.oneTimeIncome(EResource.POPULATION_GAIN, 2).incomePerRound(EResource.CHOOSE_INCOME)
						.eTileAbility(ETileAbility.PROTECTION_FROM_DROUGHT).build());

		// C

		ePhase = EPhase.C;
		tileNumber = 0;

		// 01

		tileNumber++;
		this.list.get(ePhase).getArrayList().addLast(new TileBuilder().ePhase(ePhase).tileNumber(tileNumber)
				.eTileType(ETileType.BUILDING).prestigePoints(7).buyCost(6).oneTimeIncome(EResource.POPULATION_GAIN)
				.constructionCost(EResource.STONE, 6).eTileAbility(ETileAbility.PROTECTION_FROM_EARTHQUAKE).build());

		// 02

		tileNumber++;
		this.list.get(ePhase).getArrayList()
				.addLast(new TileBuilder().ePhase(ePhase).tileNumber(tileNumber).eTileType(ETileType.BUILDING)
						.prestigePoints(5).buyCost(4).constructionCost(EResource.WOOD, 5)
						.incomePerRound(EResource.FOOD, 2).build());

		// 03

		tileNumber++;
		this.list.get(ePhase).getArrayList()
				.addLast(new TileBuilder().ePhase(ePhase).tileNumber(tileNumber).eTileType(ETileType.LAND)
						.prestigePoints(4).buyCost(5).oneTimeIncome(EResource.POPULATION_GAIN, 3)
						.incomePerRound(EResource.WOOD, 2).build());

		// 04

		tileNumber++;
		this.list.get(ePhase).getArrayList()
				.addLast(new TileBuilder().ePhase(ePhase).tileNumber(tileNumber).eTileType(ETileType.LAND)
						.prestigePoints(4).buyCost(4).oneTimeIncome(EResource.COIN)
						.oneTimeIncome(EResource.POPULATION_GAIN, 2).incomePerRound(EResource.WOOD, EResource.STONE)
						.eTileAbility(ETileAbility.SUPPLY_ROUND).build());

		// 05

		tileNumber++;
		this.list.get(ePhase).getArrayList()
				.addLast(new TileBuilder().ePhase(ePhase).tileNumber(tileNumber).eTileType(ETileType.LAND)
						.prestigePoints(5).buyCost(5).oneTimeIncome(EResource.COIN, 2)
						.oneTimeIncome(EResource.POPULATION_GAIN).incomePerRound(EResource.STONE, 2).build());

		// 06

		tileNumber++;
		this.list.get(ePhase).getArrayList().addLast(new TileBuilder().ePhase(ePhase).tileNumber(tileNumber)
				.eTileType(ETileType.BUILDING).prestigePoints(5).buyCost(5).oneTimeIncome(EResource.POPULATION_GAIN)
				.incomePerRound(EResource.STONE).constructionCost(EResource.STONE, 3)
				.constructionCost(EResource.WOOD, 3).eTileAbility(ETileAbility.PROTECTION_FROM_TEMPEST).build());

		// 07

		tileNumber++;
		this.list.get(ePhase).getArrayList().addLast(new TileBuilder().ePhase(ePhase).tileNumber(tileNumber)
				.eTileType(ETileType.BUILDING).prestigePoints(6).buyCost(7).oneTimeIncome(EResource.POPULATION_GAIN, 2)
				.incomePerRound(EResource.FOOD).constructionCost(EResource.STONE, 4).constructionCost(EResource.WOOD, 2)
				.eTileAbility(ETileAbility.PROTECTION_FROM_DECLINE).build());

		// 08

		tileNumber++;
		this.list.get(ePhase).getArrayList()
				.addLast(new TileBuilder().ePhase(ePhase).tileNumber(tileNumber).eTileType(ETileType.BUILDING)
						.prestigePoints(8).buyCost(8).oneTimeIncome(EResource.POPULATION_GAIN, 2)
						.incomePerRound(EResource.FOOD).constructionCost(EResource.STONE, 8).build());

		// 09

		tileNumber++;
		this.list.get(ePhase).getArrayList()
				.addLast(new TileBuilder().ePhase(ePhase).tileNumber(tileNumber).eTileType(ETileType.BUILDING)
						.prestigePoints(4).buyCost(5).oneTimeIncome(EResource.POPULATION_GAIN)
						.incomePerRound(EResource.STONE, EResource.FOOD).constructionCost(EResource.STONE, 2)
						.constructionCost(EResource.WOOD).eTileAbility(ETileAbility.PROTECTION_FROM_DROUGHT).build());

		// 10

		tileNumber++;
		this.list.get(ePhase).getArrayList()
				.addLast(new TileBuilder().ePhase(ePhase).tileNumber(tileNumber).eTileType(ETileType.BUILDING)
						.prestigePoints(7).buyCost(6).oneTimeIncome(EResource.POPULATION_LOST)
						.incomePerRound(EResource.WOOD, EResource.FOOD).constructionCost(EResource.STONE, 3)
						.constructionCost(EResource.WOOD).build());

	}

	private void relocateTiles() {

		double x = CredentialSingleton.INSTANCE.CoordinatesTilePiles.x;
		double y = CredentialSingleton.INSTANCE.CoordinatesTilePiles.y;
		double gap = CredentialSingleton.INSTANCE.DimensionsTilePile.y
				+ CredentialSingleton.INSTANCE.DimensionsGapBetweenComponents.y;

		for (EPhase ePhaseTemp : EPhase.values()) {

			this.list.get(ePhaseTemp).relocateList(x, y);
			this.list.get(ePhaseTemp).relocateImageViews();
			y += gap;

		}

	}

}
