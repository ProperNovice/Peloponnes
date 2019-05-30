package controller;

public enum ControllerSingleton {

	INSTANCE;

	public Flow flow = null;
	public GameStateManager gameState = null;
	public Modifiers modifiers = null;
	public SaveLoad saveLoad = null;
	public Text text = null;
	public TilePiles tilePiles = null;
	public DisasterChits disasterChits = null;

	public void createInstances() {

		this.gameState = new GameStateManager();
		this.text = new Text();
		this.flow = new Flow();
		this.modifiers = new Modifiers();
		this.saveLoad = new SaveLoad();
		this.tilePiles = new TilePiles();
		this.disasterChits = new DisasterChits();

	}

}
