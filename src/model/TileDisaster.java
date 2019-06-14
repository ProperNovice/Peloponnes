package model;

import controller.CredentialSingleton;
import enums.EDisaster;
import interfaces.ITileDisaster;
import utils.ContainerImageViewAbles;
import utils.CoordinatesBuilder;
import utils.Logger;
import utils.RearrangeTypeEnum;

public class TileDisaster extends Tile implements ITileDisaster {

	private EDisaster eDisaster = null;
	private List list = null;

	public TileDisaster(EDisaster eDisaster) {

		this.eDisaster = eDisaster;

		this.list = new List();

		String fileName = "";
		fileName += "tiles/disasters/";
		fileName += this.eDisaster.getString();
		fileName += ".png";

		super.createImageView(fileName);

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

	public List getList() {
		return this.list;
	}

	public EDisaster getEDisaster() {
		return this.eDisaster;
	}

	public class List extends ContainerImageViewAbles<DisasterChit> {

		@Override
		protected void createCoordinates() {

			super.arrayList.setCapacity(3);

			super.coordinates = new CoordinatesBuilder()
					.dimensionsNumbersPair(CredentialSingleton.INSTANCE.DimensionsDisasterChit)
					.rearrangeTypeEnum(RearrangeTypeEnum.PIVOT)
					.gapX(-CredentialSingleton.INSTANCE.DimensionsDisasterChit.x / 2).listSizeAble(this).build();

		}

		public void relocateList() {

			double x = getImageView().getLayoutX() + CredentialSingleton.INSTANCE.DimensionsTilePile.x / 2;
			double y = getImageView().getLayoutY() + CredentialSingleton.INSTANCE.DimensionsTilePile.y / 2;
			super.coordinates.relocateList(x, y);

		}

	}

}
