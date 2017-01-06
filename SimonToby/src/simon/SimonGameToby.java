package simon;

import guiPractice.GUIApplication;

public class SimonGameToby extends GUIApplication {

	public SimonGameToby() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initScreen() {
		SimonScreenToby simons = new SimonScreenToby(getWidth(), getHeight());
		setScreen(simons);

	}

	public static void main(String[] args) {
		SimonGameToby game = new SimonGameToby();
		Thread app = new Thread(game);
		app.start();

	}

}
