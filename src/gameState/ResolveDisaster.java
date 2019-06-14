package gameState;

import enums.EResource;
import enums.EText;
import utils.Logger;

public class ResolveDisaster extends AGameState {

	@Override
	public void handleGameStateChange() {

		EText eText = null;

		switch (super.controllerSingleton.modifiers.eDisasterDrawn) {

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
			resolveEarthquake();
			break;

		case RESOLVE_PLAGUE:
			resolvePlague();
			break;

		case RESOLVE_TEMPEST:
			resolveTempest();
			break;

		default:
			break;

		}

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

	}

	private void resolveDecline() {

		String text = EResource.LUXURY_GOODS.getString();

		int currentResource = super.controllerSingleton.resources.getCurrentAmount(EResource.LUXURY_GOODS);
		int resourceLost = Math.min(10, currentResource);

		super.controllerSingleton.resources.removeCurrentAmount(EResource.LUXURY_GOODS, resourceLost);

		Logger.INSTANCE.log("current " + text + "-> " + currentResource);
		Logger.INSTANCE.log(text + " lost -> " + resourceLost);
		Logger.INSTANCE.log(text + " remained -> " + (currentResource - resourceLost));

	}

	private void resolveEarthquake() {

	}

	private void resolveTempest() {

	}

}
