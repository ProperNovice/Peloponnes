package controller;

import enums.EDisaster;
import enums.EResource;
import interfaces.ITile;
import model.TileCivilization;
import utils.HashMap;

public class Modifiers {

	public int tileRowNormalCapacity = 5;
	public ITile tileToBuy = null;
	public boolean supplyRound = false;
	public EDisaster eDisasterDrawn = null;
	public int totalTurns = 8;
	public int level = 0;
	public HashMap<EResource, Integer> extraIncome = new HashMap<EResource, Integer>();
	public TileCivilization tileCivilization = null;

	public Modifiers() {

		resetExtraIncome();

	}

	public void resetExtraIncome() {

		this.extraIncome.put(EResource.STONE, 0);
		this.extraIncome.put(EResource.WOOD, 0);
		this.extraIncome.put(EResource.FOOD, 0);

	}

}
