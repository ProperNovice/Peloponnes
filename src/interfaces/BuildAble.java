package interfaces;

import model.BuildImageView;

public interface BuildAble {

	public void setBuilt();

	public void setUnbuilt();

	public boolean isBuilt();

	public BuildImageView getBuildImageView();

}
