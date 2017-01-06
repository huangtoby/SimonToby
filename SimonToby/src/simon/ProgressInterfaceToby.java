package simon;

import guiPractice.components.Visible;

public interface ProgressInterfaceToby extends Visible {
	
	void gameOver();
	
	void setRound(int roundNumber);

	void setSequenceLength(int size);
}
