package model;

import controller.CredentialSingleton;
import enums.EResource;
import utils.TextIndicator;

public class Resource {

	private EResource eResource = null;
	private int capacity = -1, currentAmount = 0, income = 0;
	private TextIndicator textIndicatorCurrentAmount = new TextIndicator();
	private TextIndicator textIndicatorIncome = new TextIndicator();

	public Resource(EResource eResource, int capacity) {

		this.eResource = eResource;
		this.capacity = capacity;
		this.textIndicatorCurrentAmount.setHeight(CredentialSingleton.INSTANCE.textResourcesHeight);
		this.textIndicatorIncome.setHeight(CredentialSingleton.INSTANCE.textResourcesHeight);

	}

	public Resource(EResource eResource) {

		this.eResource = eResource;
		this.textIndicatorCurrentAmount.setHeight(CredentialSingleton.INSTANCE.textResourcesHeight);
		this.textIndicatorIncome.setHeight(CredentialSingleton.INSTANCE.textResourcesHeight);

	}

	public void addIncomeSetText() {

		this.income++;
		setText();

	}

	public void removeIncomeSetText() {

		this.income--;
		setText();

	}

	public void addCurrentAmountSetText() {

		this.currentAmount++;
		setText();

	}

	public void removeCurrentAmountSetText() {

		this.currentAmount--;
		setText();

	}

	public void setIncome(int income) {

		this.income = income;
		setText();

	}

	public int getIncome() {
		return this.income;
	}

	public EResource getEResource() {
		return this.eResource;
	}

	public int getCapacity() {
		return this.capacity;
	}

	public int getCurrentAmount() {
		return this.currentAmount;
	}

	public void relocate(double y) {

		double xCurrentIndicator = CredentialSingleton.INSTANCE.CoordinatesResources.x;
		double xIncome = CredentialSingleton.INSTANCE.CoordinatesIncome.x;

		this.textIndicatorCurrentAmount.relocate(xCurrentIndicator, y);
		this.textIndicatorIncome.relocate(xIncome, y);

	}

	public void setText() {

		setTextIndicator(this.textIndicatorCurrentAmount);
		setTextIndicator(this.textIndicatorIncome);

	}

	private void setTextIndicator(TextIndicator textIndicator) {

		String text = "";
		text += this.eResource.getString();
		text += ": ";

		if (textIndicator == this.textIndicatorCurrentAmount)
			text += Integer.toString(this.currentAmount);
		else if (textIndicator == this.textIndicatorIncome)
			text += Integer.toString(this.income);

		if (textIndicator == this.textIndicatorCurrentAmount && this.capacity != -1) {

			text += "/";
			text += this.capacity;

		}

		textIndicator.setText(text);

	}

}
