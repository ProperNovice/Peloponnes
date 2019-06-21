package controller;

import interfaces.ITileConquest;
import model.Tile;
import utils.ImageView;
import utils.Logger;

public class TileConquest extends Tile implements ITileConquest {

	public TileConquest() {
		createImageView();
	}

	private void createImageView() {

		ImageView imageView = new ImageView("conquest.png", this);
		imageView.setWidth(CredentialSingleton.INSTANCE.DimensionsTileGame.x);
		imageView.setVisible(false);

	}

	@Override
	protected void printTile() {

		String seperator = "*****";

		Logger.INSTANCE.logNewLine("printing tile");
		Logger.INSTANCE.logNewLine(seperator);

		Logger.INSTANCE.log("conquest");

		Logger.INSTANCE.newLine();
		Logger.INSTANCE.logNewLine(seperator);

	}

}
