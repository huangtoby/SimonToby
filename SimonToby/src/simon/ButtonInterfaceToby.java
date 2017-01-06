package simon;

import java.awt.Color;

import guiPractice.components.Action;
import guiPractice.components.Clickable;

public interface ButtonInterfaceToby extends Clickable {
	
	void setColor(Color color);
	void setAction(Action a);
	void highlight();
	void dim();
}
