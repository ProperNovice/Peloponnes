package interfaces;

import enums.EResource;
import enums.ETileAbility;
import enums.ETileType;
import utils.ArrayList;

public interface ITileBuilding {

	public ETileType getETileType();

	public int getPrestigePoints();

	public int getBuyCost();

	public ArrayList<EResource> getOneTimeIncome();

	public ArrayList<EResource> getCostructionCost();

	public ArrayList<EResource> getIncomePerRound();

	public ArrayList<ETileAbility> getTileAbility();

}
