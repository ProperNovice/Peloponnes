package controller;

import model.TileRowConquest;
import model.TileRowNormal;

public class TileRows {

	private TileRowNormal tileRowNormal = new TileRowNormal();
	private TileRowConquest tileRowConquest = new TileRowConquest();

	public TileRows() {

		this.tileRowNormal.getArrayList().setCapacity(5);

	}

	public TileRowNormal getTileRowNormal() {
		return this.tileRowNormal;
	}

	public TileRowConquest getTileRowConquest() {
		return this.tileRowConquest;
	}

}
