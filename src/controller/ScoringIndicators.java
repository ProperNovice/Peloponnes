package controller;

import utils.TextIndicator;

public class ScoringIndicators {

	private TextIndicator prestige, population, total;
	private int tilesInt, coinsInt, prestigeInt, populationInt;

	public ScoringIndicators() {
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

	public void setPrestige(int tilesInt, int coinsInt) {

		this.tilesInt = tilesInt;
		this.coinsInt = coinsInt;
		this.prestigeInt = this.tilesInt + this.coinsInt;

		String text = "tiles: ";
		text += this.tilesInt;
		text += " - coins: ";
		text += Integer.toString(this.coinsInt);
		this.prestige.setText(text);

	}

	public void setPopulation(int points) {

		this.populationInt = points;

		String text = "population: ";
		text += Integer.toString(this.populationInt);
		this.population.setText(text);

	}

	public void setTotal() {

		int points = Math.min(this.prestigeInt, this.populationInt);

		String text = "total: ";
		text += Integer.toString(points);
		this.total.setText(text);

	}

}
