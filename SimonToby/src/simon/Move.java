package simon;

public class Move implements MoveInterfaceToby {

	private ButtonInterfaceToby b; 
	
	public Move(ButtonInterfaceToby b) {
		this.b = b;
	}
	
	@Override
	public ButtonInterfaceToby getButton() {
		// TODO Auto-generated method stub
		return b;
	}

}
