package gameState;

import controller.CredentialSingleton;
import enums.EText;
import enums.ETileAbility;
import interfaces.AbilityAble;
import interfaces.ITile;
import model.ATileRow;
import model.Tile;
import model.TilePile;
import utils.Animation;

public class RevealTile extends AGameState {

	private ITile iTile = null;

	@Override
	public void handleGameStateChange() {

		int tilesDrawn = 0;
		tilesDrawn += super.controllerSingleton.tileRows.getTileRowNormal().getArrayList().size();
		tilesDrawn += super.controllerSingleton.tileRows.getTileRowConquest().getArrayList().size();

		this.iTile = null;

		if (tilesDrawn == 0)
			super.controllerSingleton.text.showText(EText.REVEAL_TILES);
		else
			drawTile();

	}

	@Override
	protected void executeTextOption(EText textEnum) {
		drawTile();
	}

	private void drawTile() {

		removeTileFromPileAnimateAsynchronous();
		setTileDimensions();
//		checkForSupplyRound();
		addTileToRowAnimateAsynchronous();
		proceed();

	}

	private void removeTileFromPileAnimateAsynchronous() {

		TilePile tilePile = super.controllerSingleton.tilePiles.getFirstNonEmptyTilePile();

		this.iTile = tilePile.getArrayList().removeRandom();
		tilePile.animateAsynchronous();

	}

	private void setTileDimensions() {

		Tile tile = (Tile) this.iTile;
		tile.getImageView().setWidth(CredentialSingleton.INSTANCE.DimensionsTileGame.x);

	}

	private void checkForSupplyRound() {

		AbilityAble abilityAble = (AbilityAble) this.iTile;
		super.controllerSingleton.modifiers.supplyRound = abilityAble.getTileAbility()
				.contains(ETileAbility.SUPPLY_ROUND);

	}

	private void addTileToRowAnimateAsynchronous() {

		ATileRow aTileRow = null;

		if (super.controllerSingleton.tileRows.getTileRowNormal()
				.getSize() != super.controllerSingleton.modifiers.tileRowNormalCapacity)
			aTileRow = super.controllerSingleton.tileRows.getTileRowNormal();
		else
			aTileRow = super.controllerSingleton.tileRows.getTileRowConquest();

		aTileRow.getArrayList().addLast(this.iTile);

	}

	private void proceed() {

		int tilesRevealed = 0;
		tilesRevealed += super.controllerSingleton.tileRows.getTileRowNormal().getSize();
		tilesRevealed += super.controllerSingleton.tileRows.getTileRowConquest().getSize();

		super.controllerSingleton.tileRows.getTileRowNormal().animateAsynchronous();
		super.controllerSingleton.tileRows.getTileRowConquest().animateAsynchronous();

		if (tilesRevealed == 5 || super.controllerSingleton.modifiers.supplyRound)
			Animation.INSTANCE.moveAsynchronousToSynchronousLock();

		super.controllerSingleton.flow.proceed();

	}

}
