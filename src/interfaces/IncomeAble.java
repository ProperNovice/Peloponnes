package interfaces;

import enums.EResource;
import utils.ArrayList;

public interface IncomeAble {

	public ArrayList<EResource> getOneTimeIncome();

	public ArrayList<EResource> getIncomePerRound();

}
