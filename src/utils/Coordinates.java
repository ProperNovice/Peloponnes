package utils;

public class Coordinates {

	private double x, y, width, height, gapX, gapY;
	private int objectsPerRow;
	private double firstObjectX, firstObjectY;
	private RearrangeTypeEnum rearrangeTypeEnum;
	private DirectionEnum directionEnumHorizontal, directionEnumVertical;
	private IListSize listSizable = null;

	public Coordinates(double x, double y, double width, double height, double gapX, double gapY, int objectsPerRow,
			RearrangeTypeEnum rearrangeTypeEnum, DirectionEnum directionEnumHorizontal,
			DirectionEnum directionEnumVertical, IListSize listSizable) {

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.gapX = gapX;
		this.gapY = gapY;
		this.objectsPerRow = objectsPerRow;
		this.rearrangeTypeEnum = rearrangeTypeEnum;
		this.directionEnumHorizontal = directionEnumHorizontal;
		this.directionEnumVertical = directionEnumVertical;
		this.listSizable = listSizable;

		if (this.rearrangeTypeEnum != RearrangeTypeEnum.PIVOT)
			return;

		if (this.listSizable != null)
			return;

		Logger.INSTANCE.log("pivot rearrange type");
		Logger.INSTANCE.log("no list sizable object");
		ShutDown.INSTANCE.execute();

	}

	public NumbersPair getCoordinate(int index) {

		switch (this.rearrangeTypeEnum) {

		case LINEAR:
			this.firstObjectX = this.x;
			this.firstObjectY = this.y;
			break;

		case PIVOT:
			calculateFirstObjectCoordinatesPivot();
			break;

		case STATIC:
			return new NumbersPair(this.x, this.y);

		default:
			break;

		}

		double coordinateX = 0, coordinateY = 0;

		int row, column;

		if (this.objectsPerRow == -1) {

			row = 0;
			column = index;

		} else {

			row = index / this.objectsPerRow;
			column = index - row * this.objectsPerRow;

		}

		double x = column * (this.width + this.gapX);
		double y = row * (this.height + this.gapY);

		switch (this.directionEnumHorizontal) {

		case RIGHT:
			coordinateX = this.firstObjectX + x;
			break;

		case LEFT:
			coordinateX = this.firstObjectX - x;
			break;

		default:
			logErrorShutDown(this.directionEnumHorizontal);
			break;

		}

		switch (this.directionEnumVertical) {

		case DOWN:
			coordinateY = this.firstObjectY + y;
			break;

		case UP:
			coordinateY = this.firstObjectY - y;
			break;

		default:
			logErrorShutDown(this.directionEnumVertical);
			break;

		}

		return new NumbersPair(coordinateX, coordinateY);

	}

	public void relocateList(double x, double y) {

		this.x = x;
		this.y = y;

	}

	public void relocateList(NumbersPair numbersPair) {
		relocateList(numbersPair.x, numbersPair.y);
	}

	private void calculateFirstObjectCoordinatesPivot() {

		int rows, columns;

		if (this.objectsPerRow == -1) {

			rows = 1;
			columns = this.listSizable.getSize();

		} else {

			rows = (int) (Math.ceil((double) this.listSizable.getSize() / this.objectsPerRow));
			columns = (int) Math.min(this.listSizable.getSize(), this.objectsPerRow);

		}

		double width = this.width;
		double height = this.height;

		double totalX = width + (columns - 1) * (width + this.gapX);
		double totalY = height + (rows - 1) * (height + this.gapY);

		switch (this.directionEnumHorizontal) {

		case RIGHT:
			this.firstObjectX = this.x - totalX / 2;
			break;

		case LEFT:
			this.firstObjectX = this.x + totalX / 2;
			break;

		default:
			logErrorShutDown(this.directionEnumHorizontal);
			break;

		}

		switch (this.directionEnumVertical) {

		case DOWN:
			this.firstObjectY = this.y - totalY / 2;
			break;

		case UP:
			this.firstObjectY = this.y + totalY / 2;
			break;

		default:
			logErrorShutDown(this.directionEnumVertical);
			break;

		}

	}

	private void logErrorShutDown(DirectionEnum directionEnum) {

		Logger.INSTANCE.log("ArrayListImageView direction error:");
		Logger.INSTANCE.log(directionEnum);
		ShutDown.INSTANCE.execute();

	}

	public RearrangeTypeEnum getRearrangeTypeEnum() {
		return this.rearrangeTypeEnum;
	}

}
