package controller;

import interfaces.ISaveLoadAble;
import utils.ArrayList;

public class SaveLoad {

	private ArrayList<ISaveLoadAble> list = new ArrayList<>();

	public SaveLoad() {

	}

	public void add(ISaveLoadAble iSaveLoadAble) {
		this.list.addLast(iSaveLoadAble);
	}

	public void saveState() {

		for (ISaveLoadAble saveLoadAble : this.list)
			saveLoadAble.saveState();

	}

	public void loadState() {

		for (ISaveLoadAble saveLoadAble : this.list)
			saveLoadAble.loadState();

	}

}
