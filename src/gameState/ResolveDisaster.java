package gameState;

import enums.EDisaster;
import enums.EResource;
import enums.EText;
import enums.ETileAbility;
import interfaces.AbilityAble;
import interfaces.DisasterAble;
import interfaces.ITile;
import interfaces.ITileBuilding;
import interfaces.ITileLand;
import model.Tile;
import utils.ArrayList;
import utils.HashMap;
import utils.Logger;

public class ResolveDisaster extends AGameState {

	private EResource eResourceOne, eResourceTwo;
	private ArrayList<ITile> tileList = new ArrayList<ITile>();
	private EDisaster eDisaster;
	private boolean resolvePhase = false;
	private HashMap<EDisaster, ETileAbility> protections = new HashMap<EDisaster, ETileAbility>();

	public ResolveDisaster() {

		this.protections.put(EDisaster.DECLINE, ETileAbility.PROTECTION_FROM_DECLINE);
		this.protections.put(EDisaster.DRAUGHT, ETileAbility.PROTECTION_FROM_DROUGHT);
		this.protections.put(EDisaster.EARTHQUAKE, ETileAbility.PROTECTION_FROM_EARTHQUAKE);
		this.protections.put(EDisaster.PLAGUE, ETileAbility.PROTECTION_FROM_PLAGUE);
		this.protections.put(EDisaster.TEMPEST, ETileAbility.PROTECTION_FROM_TEMPEST);

	}

	@Override
	public void handleGameStateChange() {

		this.resolvePhase = false;
		this.eDisaster = super.controllerSingleton.modifiers.eDisasterDrawn;

		if (hasProtection())
			handleHasProtection();
		else
			handleHasNotProtection();

	}

	@Override
	protected void executeTextOption(EText eText) {

		switch (eText) {

		case RESOLVE_DECLINE:
			resolveDecline();
			break;

		case RESOLVE_DRAUGHT:
			resolveDraught();
			break;

		case RESOLVE_EARTHQUAKE:
			resolveEarthquakeTempest();
			break;

		case RESOLVE_PLAGUE:
			resolvePlague();
			break;

		case RESOLVE_TEMPEST:
			resolveEarthquakeTempest();
			break;

		case PROTECT_ALL_TILES:
			protectAllTiles();
			break;

		case CONTINUE:
			handleProceed();
			break;

		case CONTINUE_PROTECTION:
			super.controllerSingleton.flow.proceed();
			break;

		default:
			break;

		}

	}

	@Override
	protected void handleTilePressedBoard(ITile iTile) {

		if (!this.resolvePhase)
			return;

		if (!this.tileList.contains(iTile))
			return;

		if (!canProtectSingleTile())
			return;

		super.controllerSingleton.text.concealText();
		protectOneTile(iTile);
		setCredentials();

	}

	private void resolvePlague() {
		loseOneThirdOfResources(EResource.POPULATION_GAIN);
	}

	private void resolveDraught() {
		loseOneThirdOfResources(EResource.FOOD);
	}

	private void loseOneThirdOfResources(EResource eResource) {

		String text = eResource.getString();

		int currentResource = super.controllerSingleton.resources.getCurrentAmount(eResource);
		double resourceLostDouble = (double) currentResource / 3;
		resourceLostDouble = Math.ceil(resourceLostDouble);
		int resourceLostInt = (int) resourceLostDouble;

		super.controllerSingleton.resources.removeCurrentAmount(eResource, resourceLostInt);

		Logger.INSTANCE.log("current " + text + "-> " + currentResource);
		Logger.INSTANCE.log(text + " lost -> " + resourceLostInt);
		Logger.INSTANCE.log(text + " remained -> " + (currentResource - resourceLostInt));
		
		super.controllerSingleton.flow.proceed();

	}

	private void resolveDecline() {

		String text = EResource.LUXURY_GOODS.getString();

		int currentResource = super.controllerSingleton.resources.getCurrentAmount(EResource.LUXURY_GOODS);
		int resourceLost = Math.min(10, currentResource);

		super.controllerSingleton.resources.removeCurrentAmount(EResource.LUXURY_GOODS, resourceLost);

		Logger.INSTANCE.log("current " + text + "-> " + currentResource);
		Logger.INSTANCE.log(text + " lost -> " + resourceLost);
		Logger.INSTANCE.log(text + " remained -> " + (currentResource - resourceLost));
		
		super.controllerSingleton.flow.proceed();

	}

	private void resolveEarthquakeTempest() {

		addTilesToListSetDisasterImageview();
		setCredentials();
		this.resolvePhase = true;

	}

	private void setCredentials() {

		setResources();

		if (canProtectSingleTile())
			super.controllerSingleton.text.showText(EText.CHOOSE_TILE_TO_PROTECT);

		if (canProtectAllTiles())
			super.controllerSingleton.text.showText(EText.PROTECT_ALL_TILES);

		super.controllerSingleton.text.showText(EText.CONTINUE);

	}

	private void setResources() {

		if (this.eDisaster == EDisaster.EARTHQUAKE) {

			this.eResourceOne = EResource.STONE;
			this.eResourceTwo = EResource.WOOD;

		} else if (this.eDisaster == EDisaster.TEMPEST) {

			this.eResourceOne = EResource.FOOD;
			this.eResourceTwo = EResource.COIN;

		}

	}

	private void addTilesToListSetDisasterImageview() {

		for (ITile iTile : super.controllerSingleton.board.getArrayList()) {

			if (this.eDisaster == EDisaster.EARTHQUAKE && iTile instanceof ITileBuilding)
				this.tileList.addLast(iTile);

			else if (this.eDisaster == EDisaster.TEMPEST && iTile instanceof ITileLand)
				this.tileList.addLast(iTile);

		}

		for (ITile iTile : this.tileList) {

			DisasterAble disasterAble = (DisasterAble) iTile;
			disasterAble.setDisaster();

		}

	}

	private boolean canProtectAllTiles() {

		int eResourceOneCurrent = super.controllerSingleton.resources.getCurrentAmount(this.eResourceOne);
		int eResourceTwoCurrent = super.controllerSingleton.resources.getCurrentAmount(this.eResourceTwo);
		int luxuryGoodsCurrent = super.controllerSingleton.resources.getCurrentAmount(EResource.LUXURY_GOODS);

		int luxuryGoodsCost = 0;
		luxuryGoodsCost += 2 * Math.max(0, this.tileList.size() - eResourceOneCurrent);
		luxuryGoodsCost += 2 * Math.max(0, this.tileList.size() - eResourceTwoCurrent);

		return luxuryGoodsCost <= luxuryGoodsCurrent;

	}

	private void protectAllTiles() {

		handleAllResources(this.eResourceOne);
		handleAllResources(this.eResourceTwo);

		for (ITile iTile : this.tileList) {

			DisasterAble disasterAble = (DisasterAble) iTile;
			disasterAble.getDisasterImageView().getImageView().setVisible(false);

		}

		this.tileList.clear();
		handleProceed();

	}

	private void handleAllResources(EResource eResource) {

		int currentResources = super.controllerSingleton.resources.getCurrentAmount(eResource);
		int resourcesUsed = Math.min(this.tileList.size(), currentResources);
		super.controllerSingleton.resources.removeCurrentAmount(eResource, resourcesUsed);
		int resourcesLeft = currentResources - resourcesUsed;

		Logger.INSTANCE.log(eResource);
		Logger.INSTANCE.log("current -> " + currentResources);
		Logger.INSTANCE.log("used -> " + resourcesUsed);
		Logger.INSTANCE.log("left -> " + resourcesLeft);
		Logger.INSTANCE.newLine();

		currentResources = super.controllerSingleton.resources.getCurrentAmount(EResource.LUXURY_GOODS);
		resourcesUsed = 2 * (this.tileList.size() - resourcesUsed);
		super.controllerSingleton.resources.removeCurrentAmount(EResource.LUXURY_GOODS, resourcesUsed);
		resourcesLeft = currentResources - resourcesUsed;

		Logger.INSTANCE.log(EResource.LUXURY_GOODS);
		Logger.INSTANCE.log("current -> " + currentResources);
		Logger.INSTANCE.log("used -> 2*" + resourcesUsed / 2 + " -> " + resourcesUsed);
		Logger.INSTANCE.log("left -> " + (currentResources - resourcesUsed));
		Logger.INSTANCE.newLine();

	}

	private boolean canProtectSingleTile() {

		int eResourceOneCurrent = super.controllerSingleton.resources.getCurrentAmount(this.eResourceOne);
		int eResourceTwoCurrent = super.controllerSingleton.resources.getCurrentAmount(this.eResourceTwo);
		int luxuryGoodsCurrent = super.controllerSingleton.resources.getCurrentAmount(EResource.LUXURY_GOODS);

		int luxuryGoodsCost = 0;

		if (eResourceOneCurrent == 0)
			luxuryGoodsCost += 2;
		if (eResourceTwoCurrent == 0)
			luxuryGoodsCost += 2;

		return luxuryGoodsCost <= luxuryGoodsCurrent;

	}

	private void protectOneTile(ITile iTile) {

		handleOneTileResources(this.eResourceOne);
		handleOneTileResources(this.eResourceTwo);

		DisasterAble disasterAble = (DisasterAble) iTile;
		disasterAble.getDisasterImageView().getImageView().setVisible(false);

		this.tileList.remove(iTile);

	}

	private void handleOneTileResources(EResource eResource) {

		int currentResources = super.controllerSingleton.resources.getCurrentAmount(eResource);

		int resourcesUsed = 0;

		if (currentResources > 0)
			resourcesUsed = 1;

		super.controllerSingleton.resources.removeCurrentAmount(eResource, resourcesUsed);
		int resourcesLeft = currentResources - resourcesUsed;

		Logger.INSTANCE.log(eResource);
		Logger.INSTANCE.log("current -> " + currentResources);
		Logger.INSTANCE.log("used -> " + resourcesUsed);
		Logger.INSTANCE.log("left -> " + resourcesLeft);
		Logger.INSTANCE.newLine();

		currentResources = super.controllerSingleton.resources.getCurrentAmount(EResource.LUXURY_GOODS);

		if (resourcesUsed == 0)
			resourcesUsed = 2;
		else
			resourcesUsed = 0;

		super.controllerSingleton.resources.removeCurrentAmount(EResource.LUXURY_GOODS, resourcesUsed);
		resourcesLeft = currentResources - resourcesUsed;

		Logger.INSTANCE.log(EResource.LUXURY_GOODS);
		Logger.INSTANCE.log("current -> " + currentResources);
		Logger.INSTANCE.log("used -> 2*" + resourcesUsed / 2 + " -> " + resourcesUsed);
		Logger.INSTANCE.log("left -> " + (currentResources - resourcesUsed));
		Logger.INSTANCE.newLine();

	}

	private void handleProceed() {

		if (!this.tileList.isEmpty()) {

			for (ITile iTile : this.tileList) {

				Tile tile = (Tile) iTile;
				tile.getImageView().setVisible(false);

				super.controllerSingleton.board.getArrayList().remove(iTile);

				DisasterAble disasterAble = (DisasterAble) iTile;
				disasterAble.getDisasterImageView().getImageView().setVisible(false);

			}

			super.controllerSingleton.board.relocateList();
			super.controllerSingleton.board.animateAsynchronous();

		}

		super.controllerSingleton.flow.proceed();

	}

	private boolean hasProtection() {

		ETileAbility protectionAbility = this.protections.get(this.eDisaster);

		for (ITile iTile : super.controllerSingleton.board.getArrayList()) {

			AbilityAble abilityAble = (AbilityAble) iTile;

			if (abilityAble.getTileAbility().contains(protectionAbility))
				return true;

		}

		return false;

	}

	private void handleHasNotProtection() {

		EText eText = null;

		switch (this.eDisaster) {

		case DECLINE:
			eText = EText.RESOLVE_DECLINE;
			break;

		case DRAUGHT:
			eText = EText.RESOLVE_DRAUGHT;
			break;

		case EARTHQUAKE:
			eText = EText.RESOLVE_EARTHQUAKE;
			break;

		case PLAGUE:
			eText = EText.RESOLVE_PLAGUE;
			break;

		case TEMPEST:
			eText = EText.RESOLVE_TEMPEST;
			break;

		default:
			break;

		}

		super.controllerSingleton.text.showText(eText);

	}

	private void handleHasProtection() {

		EText eText = null;

		switch (this.eDisaster) {

		case DECLINE:
			eText = EText.PROTECTION_FROM_DECLINE;
			break;

		case DRAUGHT:
			eText = EText.PROTECTION_FROM_DRAUGHT;
			break;

		case EARTHQUAKE:
			eText = EText.PROTECTION_FROM_EARTHQUAKE;
			break;

		case PLAGUE:
			eText = EText.PROTECTION_FROM_PLAGUE;
			break;

		case TEMPEST:
			eText = EText.PROTECTION_FROM_TEMPEST;
			break;

		default:
			break;

		}

		super.controllerSingleton.text.showText(eText);
		super.controllerSingleton.text.showText(EText.CONTINUE_PROTECTION);

	}

}
