package tiles;

import controller.CredentialSingleton;
import enums.EPhase;
import enums.EResource;
import enums.ETileAbility;
import enums.ETileType;
import interfaces.ITile;
import utils.ArrayList;
import utils.EventHandler.EventHandlerAble;
import utils.ImageView;
import utils.ImageViewAble;
import utils.Logger;

public class Tile implements ImageViewAble, EventHandlerAble, ITile {

	private EPhase ePhase = null;
	private ETileType eTileType = null;
	private int prestigePoints = -1, buyCost = -1;
	private ArrayList<EResource> oneTimeIncome = new ArrayList<EResource>();
	private ArrayList<EResource> constructionCost = new ArrayList<EResource>();
	private ArrayList<EResource> incomePerRound = new ArrayList<EResource>();
	private ArrayList<ETileAbility> eTileAbility = new ArrayList<ETileAbility>();

	public Tile(EPhase ePhase, int tileNumber, ETileType eTileType, int prestigePoints, int buyCost,
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

		createImageView(fileName);

	}

	private void createImageView(String fileName) {

		ImageView imageView = new ImageView(fileName, this);
		imageView.setWidth(CredentialSingleton.INSTANCE.DimensionsTilePile.x);

		mapImageViews.put(this, imageView);

	}

	@Override
	public void handleMouseButtonPressedPrimary() {

		printTile();

	}

	private void printTile() {

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

}
