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
	
	public void initAllObjects(List<Visible> viewObjects) {
		addButtons(viewObjects);
		progress = getProgress();
		label = new TextLabel(130,230,300,40,"Play Simon");
		sequence = new ArrayList<MoveInterfaceToby>();
		
		lastSelectedButton = -1;
		sequence.add(randomMove());
		sequence.add(randomMove());
		roundNumber = 0;

		viewObjects.add(progress);
		viewObjects.add(label);
	}

	public void run() {
		changeText("");
		nextRound();
	}

	private void nextRound() {
		acceptingInput = false;
		roundNumber ++;
		progress.setRound(roundNumber);
		sequence.add(randomMove());
		progress.setSequenceLength(sequence.size());
		changeText("Simon.");
		label.setText("");
		playSequence();
		changeText("You.");
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

	private MoveInterfaceToby randomMove() {
		//code that randomly selects a ButtonInterface
		int a = (int)(Math.random()*buttons.length);
		while(a == lastSelectedButton){
			a = (int)(Math.random()*buttons.length);
		}
		lastSelectedButton = a;

		return new Move(buttons[a]);
	}

	private void addButtons(List<Visible> viewObjects) {
		Color[] colors = {Color.red, Color.blue, Color.orange, Color.green, Color.yellow, Color.cyan};
		String[] names = {"red", "blue", "orange", "green", "yellow", "cyan"};
		int numberOfButtons = 6;
		buttons = new ButtonInterfaceToby[numberOfButtons];
		for(int i = 0; i <numberOfButtons; i++ ){
			buttons[i] = getAButton();
			buttons[i].setName(names[i]);
			buttons[i].setColor(colors[i]);
			buttons[i].setX(90 + (int)(75*(i)));
			buttons[i].setY(175);
			final ButtonInterfaceToby b = buttons[i];
			System.out.println(b+" has x = "+b.getX()+", y ="+b.getY());
			b.dim();
			buttons[i].setAction(new Action() {
				
				public void act() {
					System.out.println("I was clicked");
					Thread buttonPress = new Thread(new Runnable() {
						public void run() {
							b.highlight();
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							b.dim();
						}
					});
					
					buttonPress.start();
					if(acceptingInput && sequence.get(sequenceIndex).getButton() == b){
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

	private void changeText(String string) {
		try{
			label.setText(string);
			Thread.sleep(1000);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void gameOver() {
		progress.gameOver();
	}
	
	private ProgressInterfaceToby getProgress() {
		return new ProgressMax(); 
	}
	
	private ButtonInterfaceToby getAButton() {
		return new ButtonMax();
	}

}
