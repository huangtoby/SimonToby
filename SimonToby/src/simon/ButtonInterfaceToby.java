package simon;

import java.awt.Color;

import guiPractice.components.Action;
import guiPractice.components.Clickable;

public interface ButtonInterfaceToby extends Clickable {
	
	void setColor(Color color);
	void setAction(Action action);
	void highlight();
	void dim();
	void setX(int i);
	void setY(int i);	
}
