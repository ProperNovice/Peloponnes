package utils;

import controller.CredentialSingleton;
import utils.EventHandler.EventHandlerAble;

public class NumberImageView implements ImageViewAble {

	public static HashMap<Integer, ArrayList<Image>> objectPoolImages = new HashMap<Integer, ArrayList<Image>>();
	private EventHandlerAble eventHandlerAble = null;
	private NumberImageViewColorEnum numberImageViewColorEnum = NumberImageViewColorEnum.BLACK;
	private int numberShowing = -1;

	public NumberImageView() {

		if (!objectPoolImages.containsKey(0))
			createObjectPool();

	}

	public NumberImageView(int number) {

		if (!objectPoolImages.containsKey(0))
			createObjectPool();

		setNumber(number);

	}

	public NumberImageView(EventHandlerAble eventHandlerAble) {

		if (!objectPoolImages.containsKey(0))
			createObjectPool();

		this.eventHandlerAble = eventHandlerAble;

	}

	public NumberImageView(int number, EventHandlerAble eventHandlerAble) {

		if (!objectPoolImages.containsKey(0))
			createObjectPool();

		setNumber(number);
		this.eventHandlerAble = eventHandlerAble;

	}

	public void setNumber(int value) {

		if (value > 20)
			ShutDown.execute("@" + this.getClass());

		setImageView(value);

	}

	public void setInfinity() {
		setImageView(21);
	}

	public void setPhi() {
		setImageView(22);
	}

	public void setX() {
		setImageView(23);
	}

	private void createObjectPool() {

		for (int counter = 0; counter <= 23; counter++)
			objectPoolImages.put(counter, new ArrayList<Image>());

	}

	private void setImageView(int listIndex) {

		if (listIndex == this.numberShowing)
			return;

		String pathNumbers = "misc/numbers/" + this.numberImageViewColorEnum.string() + "/", pathPng = ".png";

		Image image = null;

		if (!objectPoolImages.get(listIndex).isEmpty())
			image = objectPoolImages.get(listIndex).removeFirst();
		else
			image = new Image(pathNumbers + listIndex + pathPng);

		if (mapImageViews.get(this) != null) {

			objectPoolImages.get(this.numberShowing).addLast(mapImageViews.get(this).getImage());
			mapImageViews.get(this).setImage(image);

		} else {

			if (this.eventHandlerAble == null)
				mapImageViews.put(this, new ImageView(image, this));
			else
				mapImageViews.put(this, new ImageView(image, this, this.eventHandlerAble));

			mapImageViews.get(this).setWidth(CredentialSingleton.INSTANCE.DimensionsNumberImageView.x);

		}

		this.numberShowing = listIndex;

	}

	private enum NumberImageViewColorEnum {

		GRAY("gray"), BLACK("black");

		private String string = null;

		private NumberImageViewColorEnum(String string) {
			this.string = string;
		}

		public String string() {
			return this.string;
		}

	}

}
