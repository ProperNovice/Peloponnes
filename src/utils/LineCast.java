package utils;

public enum LineCast {

	INSTANCE;

	private double startingX, startingY, endingX, endingY, stepX, stepY;
	private ArrayList<ImageViewAble> listLineCast = new ArrayList<ImageViewAble>();
	private ArrayList<ImageViewAble> listStarting = new ArrayList<ImageViewAble>();

	public ArrayList<ImageViewAble> lineCastCoordinates(double startingX, double startingY, double endingX,
			double endingY) {

		this.listLineCast.clear();
		this.listStarting.clear();

		this.startingX = startingX;
		this.startingY = startingY;
		this.endingX = endingX;
		this.endingY = endingY;

		setStepX();
		setStepY();

		setListStartingImageViewAble();
		setListLinecast();

		return this.listLineCast;

	}

	public ArrayList<ImageViewAble> lineCastCoordinates(NumbersPair starting, NumbersPair ending) {
		return lineCastCoordinates(starting.x, starting.y, ending.x, ending.y);
	}

	public ArrayList<ImageViewAble> lineCastDimensions(double startingX, double startingY, double width,
			double height) {
		return lineCastCoordinates(startingX, startingY, startingX + width, startingY + height);
	}

	public ArrayList<ImageViewAble> lineCastDimensions(NumbersPair starting, NumbersPair dimensions) {
		return lineCastCoordinates(starting.x, starting.y, starting.x + dimensions.x, starting.y + dimensions.y);
	}

	public ArrayList<ImageViewAble> lineCastCoordDimens(double startingX, double startingY, NumbersPair dimensions) {
		return lineCastCoordinates(startingX, startingY, startingX + dimensions.x, startingY + dimensions.y);
	}

	private void setStepX() {

		if (this.endingX > this.startingX)
			this.stepX = 1;
		else if (this.endingX < this.startingX)
			this.stepX = -1;

	}

	private void setStepY() {

		double dX = Math.abs(this.startingX - this.endingX);
		double dY = Math.abs(this.startingY - this.endingY);

		this.stepY = dY / dX;

		if (this.endingY < this.startingY)
			this.stepY = -this.stepY;

	}

	private void setListStartingImageViewAble() {
		this.listStarting.addAll(getImageViewAbleContaining(this.startingX, this.endingY));
	}

	private void setListLinecast() {

		double currentX = this.startingX;
		double currentY = this.startingY;

		do {

			ArrayList<ImageViewAble> list = getImageViewAbleContaining(currentX, currentY);

			currentX += this.stepX;
			currentY += this.stepY;

			if (list.isEmpty())
				continue;

			for (ImageViewAble imageViewAble : list) {

				if (this.listStarting.contains(imageViewAble))
					continue;

				if (this.listLineCast.contains(imageViewAble))
					continue;

				this.listLineCast.addLast(imageViewAble);

			}

		} while (currentX != this.endingX);

	}

	private ArrayList<ImageViewAble> getImageViewAbleContaining(double x, double y) {

		ArrayList<ImageViewAble> list = new ArrayList<ImageViewAble>();

		for (ImageViewAble imageViewAble : ImageViewsMap.INSTANCE.getImageViewsMap()) {

			ImageView imageView = ImageViewsMap.INSTANCE.getImageViewsMap().get(imageViewAble);

			if (!imageView.contains(x, y))
				continue;

			list.addLast(imageViewAble);

		}

		return list;

	}

}
