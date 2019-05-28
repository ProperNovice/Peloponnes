package enums;

public enum EPhase {

	A("A"), B("B"), C("C");

	private String string = null;

	private EPhase(String string) {
		this.string = string;
	}

	public String string() {
		return this.string;
	}

}
