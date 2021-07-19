package try1Amit;

public class Sword extends Weapon {
	/*Sword weapon*/
	
	//Ctor
	public Sword(int x, int y) {
		super(x, y, SWORD);
		this.changeName("Sword");
		// TODO Auto-generated constructor stub
	}

	//Cctor
	public Sword(Weapon cwpn) {
		super(cwpn);
		// TODO Auto-generated constructor stub
	}
	
	//Override toString method
	@Override
    public String toString() { 
        return "   ---[-   "; 
    }

}
