package enums;

public enum ETileType {

	CIVILIZATION("civilizations"), BUILDING("tiles"), LAND("tiles");

	private String string = null;

	private ETileType(String string) {
		this.string = string;
	}

	public String string() {
		return this.string;
	}

}
