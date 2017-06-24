package com.week9.antRace;


public class AntRace implements AntFields {

	public static void main(String[] args) throws InterruptedException {

		AntField field = new AntField(FIELD4);

		Ant ant = new Ant(field, 2, 4, 1);

		Thread myThread = new Thread(ant);
		myThread.start();
		Runtime.getRuntime().addShutdownHook(new EndingThread(field));
	}

	/**
	 * Makes sure Antfield is printed after all updates are made
	 */
	private static class EndingThread extends Thread{
		AntField field;
		public EndingThread(AntField f) {
			field = f;
		}
		@Override
		public void run() {
			System.out.println(field.toString());
		}
	}
}
