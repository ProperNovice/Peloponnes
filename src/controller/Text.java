package controller;

import enums.EText;
import enums.EText.TextTypeEnum;
import utils.ArrayList;
import utils.Coordinates;
import utils.CoordinatesBuilder;
import utils.IListSize;
import utils.RearrangeTypeEnum;
import utils.TextGame;

public class Text implements IListSize {

	private ArrayList<TextGame> textGame = new ArrayList<>();
	private ArrayList<TextGame> textGameShowing = new ArrayList<>();
	private Coordinates coordinates = null;

	public Text() {

		createCoordinates();
		createTexts();

	}

	private void createCoordinates() {

		this.coordinates = new CoordinatesBuilder().height(CredentialSingleton.INSTANCE.textHeight)
				.coordinatesNumbersPair(CredentialSingleton.INSTANCE.CoordinatesTextPanel).objectsPerRow(1)
				.rearrangeTypeEnum(RearrangeTypeEnum.PIVOT).listSizeAble(this).build();

	}

	private void createTexts() {

		for (EText eText : EText.values())
			this.textGame.addLast(new TextGame(eText));

	}

	public void showText(EText eText) {

		for (TextGame textGame : this.textGame) {

			if (!textGame.getTextEnum().equals(eText))
				continue;

			this.textGameShowing.addLast(textGame);
			break;

		}

		showText();

	}

	public void showText(EText... eText) {

		for (EText eTextTemp : eText)
			for (TextGame textGame : this.textGame) {

				if (!textGame.getTextEnum().equals(eTextTemp))
					continue;

				this.textGameShowing.addLast(textGame);
				break;

			}

		showText();

	}

	private void showText() {

		for (TextGame textGame : this.textGameShowing) {

			textGame.toFront();

			textGame.setVisible(true);
			textGame.relocate(this.coordinates.getCoordinate(this.textGameShowing.indexOf(textGame)));

		}

	}

	public void concealText() {

		for (TextGame textGame : this.textGameShowing)
			textGame.setVisible(false);

		this.textGameShowing.clear();

	}

	public EText getTextEnumOptionShowing(int textOptionListOrder) {

		int textOptionId = 0;
		EText eText = null;

		for (TextGame textGame : this.textGameShowing) {

			EText eTextTemp = textGame.getTextEnum();

			if (eTextTemp.getTextTypeEnum() == TextTypeEnum.INDICATOR)
				continue;

			textOptionId++;

			if (textOptionListOrder != textOptionId)
				continue;

			eText = eTextTemp;
			break;

		}

		return eText;

	}

	@Override
	public int getSize() {
		return this.textGameShowing.size();
	}

}
