package gameState;

import enums.EResource;
import enums.EText;
import interfaces.BuildAble;
import interfaces.ITile;
import interfaces.ITileBuilding;
import interfaces.ITileLand;
import model.Tile;
import utils.Logger;

public class BuildNowLaterOrDiscard extends ABuildTile {

	@Override
	public void handleGameStateChange() {

		if (super.controllerSingleton.modifiers.tileToBuy instanceof ITileLand) {

			buildNowProceed();
			return;

		}

		if (super.tileBuildingCanBeBuiltNow(super.controllerSingleton.modifiers.tileToBuy)) {

			Logger.INSTANCE.logNewLine("can be built now");
			super.controllerSingleton.text.showText(EText.BUILD_NOW);

		}

		if (tileBoughtCanBeBuildLater()) {

			Logger.INSTANCE.logNewLine("can be built later");
			super.controllerSingleton.text.showText(EText.BUILD_LATER);

		}

		super.controllerSingleton.text.showText(EText.DISCARD);

	}

	@Override
	protected void executeTextOption(EText eText) {

		switch (eText) {

		case BUILD_NOW:
			buildNowProceed();
			break;

		case BUILD_LATER:
			handleBuildLater();
			break;

		case DISCARD:
			handleDiscard();
			break;

		default:
			break;

		}

	}

	private void buildNowProceed() {

		if (super.controllerSingleton.modifiers.tileToBuy instanceof ITileBuilding)
			super.executeBuildResources();

		addTileToBoardAnimateSynchronousLock();
		super.controllerSingleton.flow.proceed();

	}

	private boolean tileBoughtCanBeBuildLater() {

		boolean canBeBuiltLater;

		if (super.coinsCurrent >= 1)
			canBeBuiltLater = true;
		else if (super.luxuryGoodsCurrent >= 2)
			canBeBuiltLater = true;
		else
			canBeBuiltLater = false;

		if (canBeBuiltLater)
			Logger.INSTANCE.logNewLine("can be build later");
		else
			Logger.INSTANCE.logNewLine("can't be build later");

		return canBeBuiltLater;

	}

	private void handleBuildLater() {

		handleBuildLaterCost();
		addTileToBoardAnimateSynchronousLock();

		ITile iTile = super.controllerSingleton.modifiers.tileToBuy;

		BuildAble buildAble = (BuildAble) iTile;
		buildAble.setUnbuilt();

		super.controllerSingleton.flow.proceed();

	}

	private void handleBuildLaterCost() {

		if (super.coinsCurrent > 0)
			super.controllerSingleton.resources.removeCurrentAmount(EResource.COIN, 1);
		else
			super.controllerSingleton.resources.removeCurrentAmount(EResource.LUXURY_GOODS, 2);

	}

	private void handleDiscard() {

		ITile iTile = super.controllerSingleton.modifiers.tileToBuy;
		Tile tile = (Tile) iTile;
		tile.getImageView().setVisible(false);
		super.controllerSingleton.modifiers.tileToBuy = null;
		super.controllerSingleton.flow.proceed();

	}

	private void addTileToBoardAnimateSynchronousLock() {

		setBuildIconsVisibility(false);

		ITile iTile = super.controllerSingleton.modifiers.tileToBuy;

		if (iTile instanceof ITileLand)
			super.controllerSingleton.board.getArrayList().addLast(iTile);
		else if (iTile instanceof ITileBuilding)
			super.controllerSingleton.board.getArrayList().addFirst(iTile);

		super.controllerSingleton.board.relocateList();
		super.controllerSingleton.board.animateSynchronousLock();

		setBuildIconsVisibility(true);

	}

	private void setBuildIconsVisibility(boolean value) {

		for (ITile iTile : super.controllerSingleton.board.getArrayList()) {

			if (!(iTile instanceof BuildAble))
				continue;

			BuildAble buildAble = (BuildAble) iTile;

			if (buildAble.isBuilt())
				continue;

			if (value)
				buildAble.getBuildImageView().relocateToFrontSetVisibleTrue();

			buildAble.getBuildImageView().getImageView().setVisible(value);

		}

	}

}
