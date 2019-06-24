package controller;

import enums.EResource;
import enums.ETileType;
import interfaces.ITile;
import interfaces.RestartAble;
import model.SeaTileImageViewBuild;
import model.SeaTileImageViewSelect;
import model.Tile;
import model.TileBuilder;
import utils.ArrayList;
import utils.ContainerImageViewAbles;
import utils.CoordinatesBuilder;
import utils.RearrangeTypeEnum;

public class TileSeaPile extends ContainerImageViewAbles<ITile> implements RestartAble {

	private SeaTileImageViewBuild seaTileImageViewBuild = null;
	private SeaTileImageViewSelect seaTileImageViewSelect = null;
	private boolean isBuilt = false;
	private ITile iTile = null;
	private ArrayList<ITile> listCurrent = new ArrayList<ITile>();

	public TileSeaPile() {

		createList();
//		restartList();
		relocateImageViews();

		createIconImageViews();
//		setNewSeaTile();

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

		// 08

		tileNumber++;
		super.arrayList.addLast(new TileBuilder().tileNumber(tileNumber).eTileType(ETileType.SEA).prestigePoints(3)
				.constructionCost(EResource.COIN, 3).constructionCost(EResource.FOOD, 3)
				.oneTimeIncome(EResource.POPULATION_GAIN).build());

	}

	private void createIconImageViews() {

		this.seaTileImageViewBuild = new SeaTileImageViewBuild(super.arrayList.getFirst());
		this.seaTileImageViewBuild.getImageView().setVisible(false);

		this.seaTileImageViewSelect = new SeaTileImageViewSelect(super.arrayList.getFirst());
		this.seaTileImageViewSelect.getImageView().setVisible(false);

	}

	public void tileSeaSetBuilt() {

		this.isBuilt = true;
		this.seaTileImageViewBuild.getImageView().setVisible(false);
		this.seaTileImageViewSelect.relocateToFrontSetVisibleTrue();

	}

	public boolean tileSeaIsBuilt() {
		return this.isBuilt;
	}

	private void setNewSeaTile() {

		this.iTile = this.listCurrent.removeFirst();

		Tile tile = (Tile) this.iTile;
		tile.getImageView().toFront();

		this.seaTileImageViewBuild.relocateToFrontSetVisibleTrue();
		this.seaTileImageViewSelect.getImageView().setVisible(false);

	}

	public ITile getSeaTile() {
		return this.iTile;
	}

	@Override
	public void restart() {
		setNewSeaTile();
	}

	public void restartList() {

		this.listCurrent.clear();
		this.listCurrent.addAll(super.arrayList);

		this.listCurrent.shuffle();
		toFront();

		setNewSeaTile();

	}

}
