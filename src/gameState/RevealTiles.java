package gameState;

import controller.CredentialSingleton;
import enums.EText;
import interfaces.ITile;
import model.ATileRow;
import model.Tile;
import model.TilePile;
import utils.Lock;

public class RevealTiles extends AGameState {

	private ITile iTile = null;

	@Override
	public void handleGameStateChange() {

		super.controllerSingleton.text.showText(EText.REVEAL_TILES);

	}

	@Override
	protected void executeTextOption(EText textEnum) {

		drawTile();

	}

	private void drawTile() {

		removeTileFromPileAnimateAsynchronous();
		setTileDimensions();
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

	private void addTileToRowAnimateAsynchronous() {

		ATileRow aTileRow = null;

		if (super.controllerSingleton.tileRows.getTileRowNormal()
				.getSize() != super.controllerSingleton.modifiers.tileRowNormalCapacity)
			aTileRow = super.controllerSingleton.tileRows.getTileRowNormal();
		else
			aTileRow = super.controllerSingleton.tileRows.getTileRowConquest();

		aTileRow.getArrayList().addLast(this.iTile);
		aTileRow.animateSynchronous();

	}

	private void proceed() {

		int tilesRevealed = 0;
		tilesRevealed += super.controllerSingleton.tileRows.getTileRowNormal().getSize();
		tilesRevealed += super.controllerSingleton.tileRows.getTileRowConquest().getSize();

		if (tilesRevealed == 5) {

			Lock.INSTANCE.lock();
			super.controllerSingleton.flow.proceed();

		} else
			drawTile();

	}

}
