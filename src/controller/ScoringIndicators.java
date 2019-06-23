package controller;

import utils.HashMap;
import utils.TextIndicator;

public class ScoringIndicators {

	private TextIndicator prestige, population, total;
	private int tilesInt, coinsInt, prestigeInt, populationInt, totalInt;
	private HashMap<Integer, Integer> campaignLevelScore = new HashMap<Integer, Integer>();
	private int campaignLevel = 1;

	public ScoringIndicators() {
		createScoringTarget();
		createIndicators();
	}

	private void createIndicators() {

		this.prestige = new TextIndicator();
		this.population = new TextIndicator();
		this.total = new TextIndicator();

		this.prestige.setHeight(CredentialSingleton.INSTANCE.textScoringHeight);
		this.population.setHeight(CredentialSingleton.INSTANCE.textScoringHeight);
		this.total.setHeight(CredentialSingleton.INSTANCE.textScoringHeight);

		double x = CredentialSingleton.INSTANCE.CoordinatesScoring.x;
		double y = CredentialSingleton.INSTANCE.CoordinatesScoring.y;

		this.prestige.relocate(x, y);
		y += CredentialSingleton.INSTANCE.textScoringHeight;
		this.population.relocate(x, y);
		y += CredentialSingleton.INSTANCE.textScoringHeight;
		this.total.relocate(x, y);

		setPrestige(0, 0);
		setPopulation(0, 3);
		setTotal();

	}

	private void createScoringTarget() {

		this.campaignLevelScore.put(1, 24);
		this.campaignLevelScore.put(2, 28);
		this.campaignLevelScore.put(3, 32);
		this.campaignLevelScore.put(4, 35);
		this.campaignLevelScore.put(5, 35);

	}

	public void setPrestige(int tilesInt, int coinsInt) {

		this.tilesInt = tilesInt;
		this.coinsInt = coinsInt;
		this.prestigeInt = this.tilesInt + this.coinsInt;

		String text = "tiles: ";

		String tilesString = Integer.toString(this.tilesInt);
		if (tilesString.length() == 1)
			tilesString = "0" + tilesString;

		text += tilesString;
		text += " + coins: ";
		text += Integer.toString(this.coinsInt);

		text += " -> ";

		String prestigeString = Integer.toString(this.prestigeInt);
		if (prestigeString.length() == 1)
			prestigeString = "0" + prestigeString;

		text += prestigeString;

		this.prestige.setText(text);

	}

	public void setPopulation(int points, int devidedBy) {

		this.populationInt = points;

		String populationString = Integer.toString(this.populationInt);
		if (populationString.length() == 1)
			populationString = "0" + populationString;

		String text = "population: ";
		text += points / devidedBy;

		text += "*" + devidedBy + " -> ";
		text += populationString;
		this.population.setText(text);

	}

	public void setTotal() {

		this.totalInt = Math.min(this.prestigeInt, this.populationInt);

		String pointsString = Integer.toString(this.totalInt);
		if (pointsString.length() == 1)
			pointsString = "0" + pointsString;

		String text = "total: ";

		text += pointsString;

		int targetPoints = this.campaignLevelScore.get(this.campaignLevel);

		text += "/";
		text += targetPoints;

		this.total.setText(text);

	}

	public int getScoringTarget() {
		return this.campaignLevelScore.get(this.campaignLevel);
	}

	public void setCampaignLevelText(int campaignLevel) {
		this.campaignLevel = campaignLevel;
		setTotal();
	}

	public boolean gameWon() {
		return this.totalInt >= this.campaignLevelScore.get(this.campaignLevel);
	}

}
