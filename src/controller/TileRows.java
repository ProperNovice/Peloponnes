package controller;

import model.ATileRow;
import model.TileRowConquest;
import model.TileRowNormal;

public class TileRows {

	private ATileRow tileRowNormal = new TileRowNormal();
	private ATileRow tileRowConquest = new TileRowConquest();

	public ATileRow getTileRowNormal() {
		return this.tileRowNormal;
	}

	public ATileRow getTileRowConquest() {
		return this.tileRowConquest;
	}

}
