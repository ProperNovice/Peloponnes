package interfaces;

import enums.EResource;
import enums.ETileAbility;
import enums.ETileType;
import utils.ArrayList;

public interface ITileCivilization {

	public ETileType getETileType();

	public ArrayList<EResource> getOneTimeIncome();

	public ArrayList<EResource> getIncomePerRound();

	public ArrayList<ETileAbility> getTileAbility();

}
