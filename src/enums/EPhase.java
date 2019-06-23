package enums;

public enum EPhase {

	A("A"), B("B"), C("C"), D("D");

	private String string = null;

	private EPhase(String string) {
		this.string = string;
	}

	public String string() {
		return this.string;
	}

}
