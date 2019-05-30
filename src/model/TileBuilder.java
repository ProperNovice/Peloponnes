package model;

import enums.EPhase;
import enums.EResource;
import enums.ETileAbility;
import enums.ETileType;
import interfaces.ITile;
import utils.ArrayList;

public class TileBuilder {

	private EPhase ePhase = null;
	private ETileType eTileType = null;
	private int tileNumber = -1, prestigePoints = -1, buyCost = -1;
	private ArrayList<EResource> oneTimeIncome = new ArrayList<EResource>();
	private ArrayList<EResource> constructionCost = new ArrayList<EResource>();
	private ArrayList<EResource> incomePerRound = new ArrayList<EResource>();
	private ArrayList<ETileAbility> eTileAbility = new ArrayList<ETileAbility>();

	public TileBuilder ePhase(EPhase ePhase) {
		this.ePhase = ePhase;
		return this;
	}

	public TileBuilder tileNumber(int tileNumber) {
		this.tileNumber = tileNumber;
		return this;
	}

	public TileBuilder eTileType(ETileType eTileType) {
		this.eTileType = eTileType;
		return this;
	}

	public TileBuilder prestigePoints(int prestigePoints) {
		this.prestigePoints = prestigePoints;
		return this;
	}

	public TileBuilder buyCost(int buyCost) {
		this.buyCost = buyCost;
		return this;
	}

	public TileBuilder oneTimeIncome(EResource... eResource) {
		addResourcesToList(this.oneTimeIncome, eResource);
		return this;
	}

	public TileBuilder oneTimeIncome(EResource eResource, int amount) {
		addResourcesToList(this.oneTimeIncome, eResource, amount);
		return this;
	}

	public TileBuilder constructionCost(EResource... eResource) {
		addResourcesToList(this.constructionCost, eResource);
		return this;
	}

	public TileBuilder constructionCost(EResource eResource, int amount) {
		addResourcesToList(this.constructionCost, eResource, amount);
		return this;
	}

	public TileBuilder incomePerRound(EResource... eResource) {
		addResourcesToList(this.incomePerRound, eResource);
		return this;
	}

	public TileBuilder incomePerRound(EResource eResource, int amount) {
		addResourcesToList(this.incomePerRound, eResource, amount);
		return this;
	}

	public TileBuilder eTileAbility(ETileAbility... eTileAbilisty) {
		this.eTileAbility.addAll(eTileAbilisty);
		return this;
	}

	private void addResourcesToList(ArrayList<EResource> list, EResource... eResource) {
		list.addAll(eResource);
	}

	private void addResourcesToList(ArrayList<EResource> list, EResource eResource, int amount) {
		for (int counter = 1; counter <= amount; counter++)
			list.addLast(eResource);
	}

	public ITile build() {

		switch (this.eTileType) {

		case BUILDING:
			return new TileBuilding(this.ePhase, this.tileNumber, this.eTileType, this.prestigePoints, this.buyCost,
					this.oneTimeIncome, this.constructionCost, this.incomePerRound, this.eTileAbility);

		case CIVILIZATION:
			break;

		case LAND:
			return new TileLand(this.ePhase, this.tileNumber, this.eTileType, this.prestigePoints, this.buyCost,
					this.oneTimeIncome, this.incomePerRound, this.eTileAbility);

		}

		return null;

	}

}
