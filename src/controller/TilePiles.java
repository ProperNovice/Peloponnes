package controller;

import enums.EPhase;
import enums.EResource;
import enums.ETileType;
import tiles.TileBuilder;
import tiles.TilePile;
import utils.HashMap;

public class TilePiles {

	private HashMap<EPhase, TilePile> list = new HashMap<EPhase, TilePile>();

	public TilePiles() {

		createTiles();
		relocateTiles();

	}

	private void createTiles() {

		EPhase ePhase = null;
		int tileNumber = 0;

		for (EPhase ePhaseTemp : EPhase.values())
			this.list.put(ePhaseTemp, new TilePile());

		// A

		ePhase = EPhase.A;

		// 01

		tileNumber++;
		this.list.get(ePhase).getArrayList().addLast(new TileBuilder().ePhase(ePhase).tileNumber(tileNumber)
				.eTileType(ETileType.BUILDING).prestigePoints(3).buyCost(3).incomePerRound(EResource.STONE, 3).build());

	}

	private void relocateTiles() {

		double x = CredentialSingleton.INSTANCE.CoordinatesTilePiles.x;
		double y = CredentialSingleton.INSTANCE.CoordinatesTilePiles.y;
		double gap = CredentialSingleton.INSTANCE.DimensionsTile.y
				+ CredentialSingleton.INSTANCE.DimensionsGapBetweenTiles.y;

		for (EPhase ePhaseTemp : EPhase.values()) {

			this.list.get(ePhaseTemp).relocateList(x, y);
			this.list.get(ePhaseTemp).relocateImageViews();
			y += gap;

		}

	}

}
