package model;

import enums.EDisaster;
import interfaces.ITileDisaster;
import utils.ContainerImageViewAbles;
import utils.Logger;

public class TileDisaster extends Tile implements ITileDisaster {

	private EDisaster eDisaster = null;
	private ContainerImageViewAbles<DisasterChit> list = null;

	public TileDisaster(EDisaster eDisaster) {

		this.eDisaster = eDisaster;

		String fileName = "";
		fileName += "tiles/disasters/";
		fileName += this.eDisaster.getString();
		fileName += ".png";

		super.createImageView(fileName);

		this.list = new ContainerImageViewAbles<DisasterChit>() {

			@Override
			protected void createCoordinates() {

			}

		};

	}

	@Override
	protected void printTile() {

		String seperator = "*****";

		Logger.INSTANCE.logNewLine("printing tile");
		Logger.INSTANCE.logNewLine(seperator);

		Logger.INSTANCE.log("disaster");
		Logger.INSTANCE.log(this.eDisaster.getString());

		Logger.INSTANCE.newLine();
		Logger.INSTANCE.logNewLine(seperator);

	}

	public ContainerImageViewAbles<DisasterChit> getList() {
		return this.list;
	}

}
