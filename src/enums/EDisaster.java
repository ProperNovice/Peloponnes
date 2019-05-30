package enums;

public enum EDisaster {

	EARTHQUAKE("earthquake"), DRAUGHT("draught"), DECLINE("decline"), TEMPEST("tempest"), PLAGUE("plague"),
	BLANK("blank");

	private String string = null;

	private EDisaster(String string) {
		this.string = string;
	}

	public String getString() {
		return this.string;
	}

}
