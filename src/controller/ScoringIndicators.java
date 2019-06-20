package controller;

import utils.HashMap;
import utils.TextIndicator;

public class ScoringIndicators {

	private TextIndicator prestige, population, total;
	private int tilesInt, coinsInt, prestigeInt, populationInt;
	private HashMap<Integer, Integer> scoringTarget = new HashMap<Integer, Integer>();

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
		setPopulation(0);
		setTotal();

	}

	private void createScoringTarget() {

		this.scoringTarget.put(1, 24);
		this.scoringTarget.put(2, 28);
		this.scoringTarget.put(3, 32);
		this.scoringTarget.put(4, 35);
		this.scoringTarget.put(5, 35);

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
		text += " - coins: ";
		text += Integer.toString(this.coinsInt);

		text += " -> ";
		text += this.prestigeInt;

		this.prestige.setText(text);

	}

	public void setPopulation(int points) {

		this.populationInt = points;

		String populationString = Integer.toString(this.populationInt);
		if (populationString.length() == 1)
			populationString = "0" + populationString;

		String text = "population: ";
		text += populationString;
		this.population.setText(text);

	}

	public void setTotal() {

		int points = Math.min(this.prestigeInt, this.populationInt);

		String pointsString = Integer.toString(points);
		if (pointsString.length() == 1)
			pointsString = "0" + pointsString;

		String text = "total: ";

		text += pointsString;

		int level = ControllerSingleton.INSTANCE.modifiers.level;

		int targetPoints = this.scoringTarget.get(level);

		text += "/";
		text += targetPoints;

		this.total.setText(text);

	}

	public int getScoringTarget(int level) {
		return this.scoringTarget.get(level);
	}

}
