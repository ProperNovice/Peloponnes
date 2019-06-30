package model;

import controller.CredentialSingleton;
import enums.EResource;
import enums.ETileAbility;
import enums.ETileType;
import interfaces.AbilityAble;
import interfaces.ITileCivilization;
import interfaces.IncomeAble;
import utils.ArrayList;
import utils.Logger;

public class TileCivilization extends Tile implements ITileCivilization, IncomeAble, AbilityAble {

	private ETileType eTileType = null;
	private ArrayList<EResource> oneTimeIncome = new ArrayList<EResource>();
	private ArrayList<EResource> incomePerRound = new ArrayList<EResource>();
	private ArrayList<ETileAbility> eTileAbility = new ArrayList<ETileAbility>();

	public TileCivilization(int tileNumber, ETileType eTileType, ArrayList<EResource> oneTimeIncome,
			ArrayList<EResource> incomePerRound, ArrayList<ETileAbility> eTileAbility) {

		this.eTileType = eTileType;
		this.oneTimeIncome = oneTimeIncome;
		this.incomePerRound = incomePerRound;
		this.eTileAbility = eTileAbility;

		String fileName = "";
		fileName += this.eTileType.string();
		fileName += "/";

		String tileNumberString = Integer.toString(tileNumber);
		if (tileNumberString.length() == 1)
			tileNumberString = "0" + tileNumberString;

		fileName += tileNumberString;
		fileName += ".png";

		super.createImageView(fileName);
		getImageView().setWidth(CredentialSingleton.INSTANCE.DimensionsTileGame.x);

	}

	@Override
	public ETileType getETileType() {
		return this.eTileType;
	}

	@Override
	public ArrayList<EResource> getOneTimeIncome() {
		return this.oneTimeIncome;
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
	protected void printTile() {

		String seperator = "*****";

		Logger.INSTANCE.logNewLine("printing tile");
		Logger.INSTANCE.logNewLine(seperator);
		Logger.INSTANCE.log("tile type - " + this.eTileType);

		printResourceList(this.oneTimeIncome, "one time income");
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

}
