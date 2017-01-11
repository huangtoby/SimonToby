package simon;

public class Move implements MoveInterfaceToby {

	private ButtonInterfaceToby b; 
	
	public Move(ButtonInterfaceToby b) {
		this.b = b;
	}
	
	public ButtonInterfaceToby getButton() {
		return b;
	}

}
