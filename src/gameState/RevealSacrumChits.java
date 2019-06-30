package gameState;

import controller.CredentialSingleton;
import enums.EText;
import model.ASacrumChit;
import utils.Animation;
import utils.Animation.AnimationSynch;
import utils.ArrayList;
import utils.Executor;
import utils.ImageView;
import utils.Lock;

public class RevealSacrumChits extends AGameState {

	private ArrayList<ASacrumChit> list = new ArrayList<ASacrumChit>();

	@Override
	public void handleGameStateChange() {

		this.list.clear();
		super.controllerSingleton.text.showText(EText.DRAW_SACRUM_CHITS);
	}

	@Override
	protected void executeTextOption(EText eText) {

		animateChitsDown();
		Executor.INSTANCE.sleep(1000);
		animateChitsToThirdListPosition();
		addChitsToSacrumList();
		checkAndSetForSacrificeInhabitants();

		super.controllerSingleton.flow.proceed();

	}

	private void animateChitsDown() {

		while (list.size() < 3)
			this.list.addLast(super.controllerSingleton.sacrumChits.getArrayList().removeFirst());

		for (ASacrumChit aSacrumChit : this.list) {

			double gapY = CredentialSingleton.INSTANCE.DimensionsChit.y
					+ CredentialSingleton.INSTANCE.DimensionsGapBetweenComponents.y;

			ImageView imageView = aSacrumChit.getImageView();

			Animation.INSTANCE.animate(imageView, imageView.getLayoutX(), imageView.getLayoutY() + gapY,
					AnimationSynch.SYNCHRONOUS);

			aSacrumChit.flip();

		}

		super.controllerSingleton.sacrumChits.animateAsynchronous();

		Lock.INSTANCE.lock();

	}

	private void animateChitsToThirdListPosition() {

		for (ASacrumChit aSacrumChit : this.list) {

			aSacrumChit.flip();

			double x = CredentialSingleton.INSTANCE.CoordinatesSacrumChits.x
					- 2 * (CredentialSingleton.INSTANCE.DimensionsChit.x
							+ CredentialSingleton.INSTANCE.DimensionsGapBetweenComponents.x);

			double y = CredentialSingleton.INSTANCE.CoordinatesSacrumChits.y;

			ImageView imageView = aSacrumChit.getImageView();

			Animation.INSTANCE.animate(imageView, x, y, AnimationSynch.SYNCHRONOUS);

		}

		Lock.INSTANCE.lock();

	}

	private void addChitsToSacrumList() {

		this.list.shuffle();
		super.controllerSingleton.sacrumChits.getArrayList().addAll(this.list);
		super.controllerSingleton.sacrumChits.animateAsynchronous();

	}

	private void checkAndSetForSacrificeInhabitants() {

		for (ASacrumChit aSacrumChit : this.list)
			if (aSacrumChit.isSkull())
				super.controllerSingleton.modifiers.sacrificeInhabitants = true;

	}

}
