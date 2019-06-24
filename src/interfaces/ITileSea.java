package interfaces;

import enums.EResource;
import enums.ETileType;
import utils.ArrayList;

public interface ITileSea {

	public ETileType getETileType();

	public ArrayList<EResource> getCostructionCost();

}
