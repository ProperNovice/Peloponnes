package utils;

import javafx.application.Platform;

public enum Lock {

	INSTANCE;

	private Lockable lock = null;

	private Lock() {

	}

	public void lock() {

		this.lock = new LockNested();
		executeLock();

	}

	public void lock(Runnable runnable) {

		this.lock = new LockRunnable(runnable);
		executeLock();

	}

	private void executeLock() {

		Logger.INSTANCE.log("lock");
		this.lock.lock();

	}

	public void unlock() {
		this.lock.unlock();
	}

	private class LockNested implements Lockable {

		private Object lockObject = new Object();

		@Override
		public void lock() {
			Platform.enterNestedEventLoop(this.lockObject);
		}

		@Override
		public void unlock() {

			Platform.exitNestedEventLoop(this.lockObject, null);
			Logger.INSTANCE.logNewLine("unlock");

		}

	}

	private class LockRunnable implements Lockable {

		private Runnable runnable = null;

		public LockRunnable(Runnable runnable) {
			this.runnable = runnable;
		}

		@Override
		public void lock() {

		}

		@Override
		public void unlock() {

			Logger.INSTANCE.logNewLine("unlock");
			this.runnable.run();

		}

	}

	private interface Lockable {

		public void lock();

		public void unlock();

	}

}
