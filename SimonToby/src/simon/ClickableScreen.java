package simon;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import guiPractice.Screen;
import guiPractice.components.Clickable;
import guiPractice.components.Visible;

public abstract class ClickableScreen extends Screen implements MouseListener{
	
	private ArrayList<Clickable> clickables;

	public ClickableScreen(int width, int height) {
		super(width, height);
	}

	public abstract void initAllObjects(List<Visible> viewObjects);
	
	public void initObjects(ArrayList<Visible> viewObjects) {
		clickables = new ArrayList<Clickable>();
		initAllObjects(viewObjects);
		for(Visible v: viewObjects){
			if(v instanceof Clickable){
				clickables.add((Clickable)v);
			}
		}
	}

	public void mouseClicked(MouseEvent m) {
		System.out.println("Click occurred");
		for(int i = 0; i <clickables.size(); i++){
			Clickable c = clickables.get(i);
			if(c.isHovered(m.getX(), m.getY())){
				c.act();
				break;
			}
		}
	}
	
	public void addObject(Visible v){
		super.addObject(v);
		if(v instanceof Clickable){
			clickables.add((Clickable)v);
		}
	}
		 
	public void remove(Visible v){
		super.remove(v);
		clickables.remove(v);
	} 

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public MouseListener getMouseListener(){
		System.out.println("The mouse listener was got.");
		return this;
	}

}
