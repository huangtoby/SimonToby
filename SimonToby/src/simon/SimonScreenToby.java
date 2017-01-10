package simon;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import guiPractice.components.Action;
import guiPractice.components.Button;
import guiPractice.components.ClickableScreen;
import guiPractice.components.TextLabel;
import guiPractice.components.Visible;

public class SimonScreenToby extends ClickableScreen implements Runnable {

	private TextLabel label;
	private ButtonInterfaceToby[] buttons;
	private ProgressInterfaceToby progress;
	private ArrayList<MoveInterfaceToby> sequence;

	int roundNumber;
	boolean acceptingInput;
	int sequenceIndex;
	int lastSelectedButton;

	public SimonScreenToby(int width, int height) {
		super(width, height);
		Thread app = new Thread(this);
		app.start();
	}

	public void run() {
		label.setText("");
		nextRound();
	}

	private void nextRound() {
		acceptingInput = false;
		roundNumber ++;
		progress.setRound(roundNumber);
		sequence.add(randomMove());
		progress.setSequenceLength(sequence.size());
		changeText("Simon's turn.");
		label.setText("");
		playSequence();
		changeText("Your turn.");
		label.setText("");
		acceptingInput = true;
		sequenceIndex = 0;
	}

	private void playSequence() {
		ButtonInterfaceToby b = null;
		for(MoveInterfaceToby m: sequence){
			if(b!=null)b.dim();
			b = m.getButton();
			b.highlight();
			try {
				Thread.sleep((long)(2000*(2.0/(roundNumber+2))));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		b.dim();
	}

	public void initAllObjects(List<Visible> viewObjects) {
		addButtons();
		progress = getProgress();
		label = new TextLabel(130,230,300,40,"Let's play Simon!");
		sequence = new ArrayList<MoveInterfaceToby>();
		//add 2 moves to start
		lastSelectedButton = -1;
		sequence.add(randomMove());
		sequence.add(randomMove());
		roundNumber = 0;
		viewObjects.add(progress);
		viewObjects.add(label);	
	}

	private MoveInterfaceToby randomMove() {
		//code that randomly selects a ButtonInterface
		int b = (int)(Math.random()*buttons.length);
		while(b == lastSelectedButton){
			b = (int)(Math.random()*buttons.length);
		}
		lastSelectedButton = b;

		return new Move(buttons[b]);
	}

	private ProgressInterfaceToby getProgress() {
		return new ProgressMax(); 
	}

	private void addButtons() {
		Color[] colors = {Color.red, Color.blue, Color.orange, Color.yellow, Color.green};
		int numberOfButtons = 5;
		buttons = new ButtonInterfaceToby[numberOfButtons];
		for(int i = 0; i < numberOfButtons; i++ ){
			buttons[i] = getAButton();
			buttons[i].setColor(colors[i]);
			buttons[i].setX(160 + (int)(100*Math.cos(i*2*Math.PI/(numberOfButtons))));
			buttons[i].setY(200 - (int)(100*Math.sin(i*2*Math.PI/(numberOfButtons))));
			final ButtonInterfaceToby b = buttons[i];
			b.dim();
			buttons[i].setAction(new Action(){
				public void act(){

					Thread blink = new Thread(new Runnable(){
						public void run(){
							b.highlight();
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							b.dim();
						}
					});
					blink.start();

					if(acceptingInput && b == sequence.get(sequenceIndex).getButton()){
						sequenceIndex++;
					}else if(acceptingInput){
						gameOver();
						return;
					}
					if(sequenceIndex == sequence.size()){
						Thread nextRound = new Thread(SimonScreenToby.this);
						nextRound.start();
					}
				}
			});
			viewObjects.add(buttons[i]);
		}
	}	

	private ButtonInterfaceToby getAButton() {
		return new ButtonMax();
	}

	private void gameOver() {
		progress.gameOver();
	}

	private void changeText(String string) {
		try{
			label.setText(string);
			Thread.sleep(1000);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
