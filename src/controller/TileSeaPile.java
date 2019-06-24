package controller;

import enums.EResource;
import enums.ETileType;
import interfaces.ITile;
import interfaces.SelectAble;
import model.SelectImageView;
import model.TileBuilder;
import utils.ContainerImageViewAbles;
import utils.CoordinatesBuilder;
import utils.RearrangeTypeEnum;

public class TileSeaPile extends ContainerImageViewAbles<ITile> implements SelectAble {

	public TileSeaPile() {

		createList();

		super.arrayList.shuffle();
		toFront();

		relocateImageViews();

	}

	@Override
	protected void createCoordinates() {

		super.coordinates = new CoordinatesBuilder()
				.dimensionsNumbersPair(CredentialSingleton.INSTANCE.DimensionsTilePile)
				.coordinatesNumbersPair(CredentialSingleton.INSTANCE.CoordinatesSeaTiles)
				.rearrangeTypeEnum(RearrangeTypeEnum.STATIC).build();

	}

	private void createList() {

		int tileNumber = 0;

		// 01

		tileNumber++;
		super.arrayList.addLast(new TileBuilder().tileNumber(tileNumber).eTileType(ETileType.SEA).prestigePoints(5)
				.constructionCost(EResource.COIN, 7).build());

		// 02

		tileNumber++;
		super.arrayList.addLast(new TileBuilder().tileNumber(tileNumber).eTileType(ETileType.SEA).prestigePoints(0)
				.constructionCost(EResource.STONE, 6).constructionCost(EResource.WOOD, 2)
				.oneTimeIncome(EResource.POPULATION_GAIN, 4).build());

		// 03

		tileNumber++;
		super.arrayList.addLast(new TileBuilder().tileNumber(tileNumber).eTileType(ETileType.SEA).prestigePoints(0)
				.constructionCost(EResource.WOOD, 6).oneTimeIncome(EResource.POPULATION_GAIN, 3).build());

		// 04

		tileNumber++;
		super.arrayList.addLast(new TileBuilder().tileNumber(tileNumber).eTileType(ETileType.SEA).prestigePoints(0)
				.constructionCost(EResource.STONE, 4).oneTimeIncome(EResource.POPULATION_GAIN, 2).build());

		// 05

		tileNumber++;
		super.arrayList.addLast(new TileBuilder().tileNumber(tileNumber).eTileType(ETileType.SEA).prestigePoints(3)
				.constructionCost(EResource.COIN, 5).build());

		// 06

		tileNumber++;
		super.arrayList.addLast(new TileBuilder().tileNumber(tileNumber).eTileType(ETileType.SEA).prestigePoints(4)
				.constructionCost(EResource.COIN, 6).build());

		// 07

		tileNumber++;
		super.arrayList.addLast(new TileBuilder().tileNumber(tileNumber).eTileType(ETileType.SEA).prestigePoints(4)
				.constructionCost(EResource.LUXURY_GOODS, 12).oneTimeIncome(EResource.POPULATION_GAIN, 2).build());

		// 07

		tileNumber++;
		super.arrayList.addLast(new TileBuilder().tileNumber(tileNumber).eTileType(ETileType.SEA).prestigePoints(3)
				.constructionCost(EResource.COIN, 3).constructionCost(EResource.FOOD, 3)
				.oneTimeIncome(EResource.POPULATION_GAIN).build());

	}

	@Override
	public void setBuilt() {

	}

	@Override
	public void setUnbuilt() {

	}

	@Override
	public boolean isBuilt() {
		return false;
	}

	@Override
	public SelectImageView getSelectImageView() {
		return null;
	}

}
