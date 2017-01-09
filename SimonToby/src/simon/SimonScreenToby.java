package simon;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import guiPractice.components.Action;
import guiPractice.components.ClickableScreen;
import guiPractice.components.TextLabel;
import guiPractice.components.Visible;

public class SimonScreenToby extends ClickableScreen implements Runnable {
	
	private TextLabel label;
	private ButtonInterfaceToby[] button;
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
		int b = (int)(Math.random()*button.length);
		lastSelectedButton = b;
		while(b == lastSelectedButton){
			b = (int)(Math.random()*button.length);
		}
		return getMove(b);
	}

	/**
	Placeholder until partner finishes implementation of ProgressInterface
	*/
	private ProgressInterfaceToby getProgress() {
		// TODO Auto-generated method stub
		return null; 
	}

	private void addButtons() {
		Color[] colors = {Color.red, Color.blue, Color.orange, Color.yellow, Color.green};
		int numberOfButtons = 5;
		button = new ButtonInterfaceToby[numberOfButtons];
		for(int i = 0; i < numberOfButtons; i++ ){
			button[i] = getAButton();
			button[i].setColor(colors[i]);
			final ButtonInterfaceToby b = getAButton();
			
			button[i].setAction(new Action(){
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
			viewObjects.add(button[i]);
		}
	}
	
	private void gameOver() {
		// TODO Auto-generated method stub
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
