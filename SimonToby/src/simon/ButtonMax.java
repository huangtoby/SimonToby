package simon;

import java.awt.*;

import guiPractice.components.Action;
import guiPractice.components.Component;

public class ButtonMax extends Component implements ButtonInterfaceToby {
	
	private static final int WIDTH = 50;
	private static final int HEIGHT = 50;
	private Action action;
	private Color c;
	private Color displayColor;
	private boolean highlight;
	
	public ButtonMax() {
		super(0, 0, WIDTH, HEIGHT);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void act() {
		// TODO Auto-generated method stub
		action.act();
	}

	@Override
	public boolean isHovered(int x, int y) {
		// TODO Auto-generated method stub
		double dX = x - (getX() + (WIDTH / 2));
		double dY = y - (getY() + (HEIGHT / 2));
		double distance = Math.sqrt(Math.pow(dX, 2) + Math.pow(dY, 2) );
		return (distance < (WIDTH / 2));
	}

	@Override
	public void setColor(Color color) {
		// TODO Auto-generated method stub
		this.c = color;
		displayColor = c;
		update();
	}

	@Override
	public void setAction(Action a) {
		// TODO Auto-generated method stub
		this.action = action;
	}

	@Override
	public void highlight() {
		// TODO Auto-generated method stub
		if (c != null) {
			displayColor = c;
		}
		highlight = true;
		
		update();
	}

	@Override
	public void dim() {
		// TODO Auto-generated method stub
		displayColor = Color.gray;
		highlight = false;
		
		update();
	}

	@Override
	public void update(Graphics2D g
			) {
		// TODO Auto-generated method stub
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if(displayColor != null) g.setColor(displayColor);
		else g.setColor(Color.gray);
		g.fillOval(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.black);
		g.drawOval(0, 0, WIDTH-1, HEIGHT-1);
		if(highlight){
			g.setColor(Color.white);
			Polygon p = new Polygon();
			
			int s = (int)(5/8.0 * WIDTH);
			int t = (int)(1.0/5*HEIGHT)+4;
			p.addPoint(s-4, t-4);
			p.addPoint(s+7, t-2);
			p.addPoint(s+10, t);
			p.addPoint(s+14, t+10);
			p.addPoint(s+12, t+14);
			p.addPoint(s+8, t+3);
			g.fill(p);
		}
	}

}
