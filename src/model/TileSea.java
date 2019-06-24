package model;

import enums.EResource;
import enums.ETileType;
import interfaces.ITileSea;
import interfaces.IncomeAble;
import interfaces.PrestigePointsAble;
import utils.ArrayList;
import utils.Logger;

public class TileSea extends Tile implements ITileSea, IncomeAble, PrestigePointsAble {

	private ETileType eTileType = null;
	private int prestigePoints = -1;
	private ArrayList<EResource> oneTimeIncome = new ArrayList<EResource>();
	private ArrayList<EResource> constructionCost = new ArrayList<EResource>();
	private ArrayList<EResource> incomePerRound = new ArrayList<EResource>();

	public TileSea(int tileNumber, ETileType eTileType, int prestigePoints, ArrayList<EResource> oneTimeIncome,
			ArrayList<EResource> constructionCost) {

		this.eTileType = eTileType;
		this.prestigePoints = prestigePoints;
		this.oneTimeIncome = oneTimeIncome;
		this.constructionCost = constructionCost;

		String fileName = "";
		fileName += this.eTileType.string();
		fileName += "/";

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
		Logger.INSTANCE.log("tile type - " + this.eTileType);
		Logger.INSTANCE.log("prestige points - " + this.prestigePoints);

		printResourceList(this.oneTimeIncome, "one time income");
		printResourceList(this.constructionCost, "construction cost");

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

}
