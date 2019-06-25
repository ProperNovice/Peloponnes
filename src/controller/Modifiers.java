package controller;

import enums.EDisaster;
import enums.EResource;
import interfaces.ITile;
import model.TileCivilization;
import utils.ArrayList;
import utils.HashMap;

public class Modifiers {

	public int tileRowNormalCapacity = 5;
	public int tilesRevealed = 5;
	public ITile tileToBuy = null;
	public boolean supplyRound = false;
	public EDisaster eDisasterDrawn = null;
	public int totalTurns = 9;
	public int level = 0;
	public HashMap<EResource, Integer> extraIncome = new HashMap<EResource, Integer>();
	public TileCivilization tileCivilization = null;
	public ArrayList<EDisaster> eDisasters = new ArrayList<EDisaster>();

	public Modifiers() {

		resetExtraIncome();
		createEDisasters();

	}

	public void resetExtraIncome() {

		this.extraIncome.put(EResource.STONE, 0);
		this.extraIncome.put(EResource.WOOD, 0);
		this.extraIncome.put(EResource.FOOD, 0);

	}

	public void createEDisasters() {

		this.eDisasters.clear();
		this.eDisasters.addAll(EDisaster.values());

		this.eDisasters.remove(EDisaster.BLANK);
		this.eDisasters.shuffle();
		this.eDisasters.addLast(EDisaster.BLANK);

	}

}
