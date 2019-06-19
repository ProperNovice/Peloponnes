package controller;

public enum ControllerSingleton {

	INSTANCE;

	public GameStateManager gameState = null;
	public Modifiers modifiers = null;
	public SaveLoad saveLoad = null;
	public Text text = null;
	public TilePiles tilePiles = null;
	public DisasterChits disasterChits = null;
	public Resources resources = null;
	public Civilizations civilizations = null;
	public Board board = null;
	public TileRows tileRows = null;
	public DisasterTiles disasterTiles = null;
	public Flow flow = null;

	public void createInstances() {

		this.gameState = new GameStateManager();
		this.text = new Text();
		this.modifiers = new Modifiers();
		this.saveLoad = new SaveLoad();
		this.tilePiles = new TilePiles();
		this.disasterChits = new DisasterChits();
		this.resources = new Resources();
		this.civilizations = new Civilizations();
		this.board = new Board();
		this.tileRows = new TileRows();
		this.disasterTiles = new DisasterTiles();
		this.flow = new Flow();

	}

}
