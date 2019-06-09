package model;

import enums.EPhase;
import enums.EResource;
import enums.ETileAbility;
import enums.ETileType;
import interfaces.AbilityAble;
import interfaces.BuildAble;
import interfaces.BuyCostAble;
import interfaces.ITileBuilding;
import interfaces.IncomeAble;
import interfaces.PrestigePointsAble;
import utils.ArrayList;
import utils.Logger;

public class TileBuilding extends Tile
		implements ITileBuilding, IncomeAble, PrestigePointsAble, BuyCostAble, AbilityAble, BuildAble {

	private EPhase ePhase = null;
	private ETileType eTileType = null;
	private int prestigePoints = -1, buyCost = -1;
	private ArrayList<EResource> oneTimeIncome = new ArrayList<EResource>();
	private ArrayList<EResource> constructionCost = new ArrayList<EResource>();
	private ArrayList<EResource> incomePerRound = new ArrayList<EResource>();
	private ArrayList<ETileAbility> eTileAbility = new ArrayList<ETileAbility>();
	private boolean isBuilt = true;
	private BuildImageView buildImageView = null;

	public TileBuilding(EPhase ePhase, int tileNumber, ETileType eTileType, int prestigePoints, int buyCost,
			ArrayList<EResource> oneTimeIncome, ArrayList<EResource> constructionCost,
			ArrayList<EResource> incomePerRound, ArrayList<ETileAbility> eTileAbility) {

		this.ePhase = ePhase;
		this.eTileType = eTileType;
		this.prestigePoints = prestigePoints;
		this.buyCost = buyCost;
		this.oneTimeIncome = oneTimeIncome;
		this.constructionCost = constructionCost;
		this.incomePerRound = incomePerRound;
		this.eTileAbility = eTileAbility;

		String fileName = "";
		fileName += this.eTileType.string();
		fileName += "/";

		fileName += this.ePhase.string();

		String tileNumberString = Integer.toString(tileNumber);
		if (tileNumberString.length() == 1)
			tileNumberString = "0" + tileNumberString;

		fileName += tileNumberString;
		fileName += ".png";

		super.createImageView(fileName);

	}

	@Override
	protected void printTile() {

		String seperator = "*****";

		Logger.INSTANCE.logNewLine("printing tile");
		Logger.INSTANCE.logNewLine(seperator);
		Logger.INSTANCE.log("phase - " + this.ePhase);
		Logger.INSTANCE.log("tile type - " + this.eTileType);
		Logger.INSTANCE.log("prestige points - " + this.prestigePoints);
		Logger.INSTANCE.log("buy cost - " + this.buyCost);

		printResourceList(this.oneTimeIncome, "one time income");
		printResourceList(this.constructionCost, "construction cost");
		printResourceList(this.incomePerRound, "income per round");

		if (!this.eTileAbility.isEmpty()) {

			Logger.INSTANCE.newLine();
			Logger.INSTANCE.log("tile ability");

			for (ETileAbility eTileAbility : this.eTileAbility)
				Logger.INSTANCE.log(eTileAbility);

		}

		Logger.INSTANCE.newLine();
		Logger.INSTANCE.logNewLine(seperator);

	}

	private void printResourceList(ArrayList<EResource> resourceList, String string) {

		if (resourceList.isEmpty())
			return;

		Logger.INSTANCE.newLine();
		Logger.INSTANCE.log(string);

		for (EResource eResource : resourceList)
			Logger.INSTANCE.log(eResource);

	}

	@Override
	public ETileType getETileType() {
		return this.eTileType;
	}

	@Override
	public int getPrestigePoints() {
		return this.prestigePoints;
	}

	@Override
	public int getBuyCost() {
		return this.buyCost;
	}

	@Override
	public ArrayList<EResource> getOneTimeIncome() {
		return this.oneTimeIncome;
	}

	@Override
	public ArrayList<EResource> getCostructionCost() {
		return this.constructionCost;
	}

	@Override
	public ArrayList<EResource> getIncomePerRound() {
		return this.incomePerRound;
	}

	@Override
	public ArrayList<ETileAbility> getTileAbility() {
		return this.eTileAbility;
	}

	@Override
	public void setBuilt() {

		this.isBuilt = true;

		if (this.buildImageView == null)
			return;

		this.buildImageView.getImageView().setVisible(false);
		this.buildImageView = null;

	}

	@Override
	public void setUnbuilt() {

		this.isBuilt = false;

		if (this.buildImageView != null)
			return;

		this.buildImageView = new BuildImageView(this);

	}

	@Override
	public boolean isBuilt() {
		return this.isBuilt;
	}

	@Override
	public BuildImageView getBuildImageView() {
		return this.buildImageView;
	}

}
