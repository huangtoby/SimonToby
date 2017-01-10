package simon;

import guiPractice.GUIApplication;

public class SimonGameToby extends GUIApplication {

	public SimonGameToby(int width, int height) {
		super(width, height);
	}
	
	protected void initScreen() {
		SimonScreenToby simons = new SimonScreenToby(getWidth(), getHeight());
		setScreen(simons);

	}

	public static void main(String[] args) {
		SimonGameToby game = new SimonGameToby(800,500);
		Thread app = new Thread(game);
		app.start();
	}

}
