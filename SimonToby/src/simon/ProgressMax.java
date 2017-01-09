package simon;

import java.awt.*;

import guiPractice.components.Component;

public class ProgressMax extends Component implements ProgressInterfaceToby {

	private static final int WIDTH = 120;
	private static final int HEIGHT = 50;

	private boolean gameOver;
	private String round;
	private String sequence;

	public ProgressMax() {
		super(60,60,WIDTH,HEIGHT);
	}
	
	@Override
	public void gameOver() {
		// TODO Auto-generated method stub
		gameOver = true;
		update();
	}

	@Override
	public void setRound(int roundNumber) {
		// TODO Auto-generated method stub
		round = "Round #" + roundNumber;
		update();
	}

	@Override
	public void setSequenceLength(int size) {
		// TODO Auto-generated method stub
		sequence = "Sequence Length: " + size;
	}

	@Override
	public void update(Graphics2D arg0) {
		arg0.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		FontMetrics fm = arg0.getFontMetrics();
		if (gameOver) {
			arg0.setColor(new Color(255,55,90));
			arg0.fillRect(0, 0, WIDTH, HEIGHT);
			arg0.setColor(Color.white);
			String go = "GAME OVER!";
			arg0.drawString(go, (WIDTH - fm.stringWidth(go))/2, 20);
			arg0.drawString(sequence, (WIDTH - fm.stringWidth(sequence))/2, 40);

		}
		else {
			arg0.setColor(new Color(220,255,230));
			arg0.fillRect(0, 0, WIDTH, HEIGHT);
			arg0.setColor(Color.black);
			arg0.drawRect(0, 0, WIDTH-1, HEIGHT-1);
			if((sequence != null) && (round != null)){

				arg0.drawString(round, (WIDTH - fm.stringWidth(round))/2, 20);
				arg0.drawString(sequence, (WIDTH - fm.stringWidth(sequence))/2, 40);
			}
		}
	}

}
