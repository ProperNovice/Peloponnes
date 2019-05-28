package utils;

public class ShutDown {

	public static void execute() {

		Logger.INSTANCE.newLine();
		Logger.INSTANCE.logNewLine("System.exit(0)");
		System.exit(0);

	}

	public static void execute(String log) {

		Logger.INSTANCE.logNewLine(log);
		execute();

	}

}
