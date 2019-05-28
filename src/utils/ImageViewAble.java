package utils;

public interface ImageViewAble {

	public static HashMap<ImageViewAble, ImageView> mapImageViews = new HashMap<>();

	public default ImageView getImageView() {
		return ImageViewAble.mapImageViews.get(this);
	}

}
