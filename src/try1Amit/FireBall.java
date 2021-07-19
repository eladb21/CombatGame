package try1Amit;

public class FireBall extends Weapon {
	/*fireball weapon*/

	//ctor
	public FireBall(int x, int y) {
		super(x, y, FIREBALL);
		this.changeName("fireBall");
		// TODO Auto-generated constructor stub
	}

	//Cctor
	public FireBall(Weapon cwpn) {
		super(cwpn);
		// TODO Auto-generated constructor stub
	}
	
	@Override
    public String toString() { 
        return "    (@>~   "; 
    }

}
