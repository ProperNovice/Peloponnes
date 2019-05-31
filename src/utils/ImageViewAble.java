package utils;

public interface ImageViewAble {

	public default ImageView getImageView() {
		return ImageViewsMap.INSTANCE.getImageViewsMap().get(this);
	}

}
