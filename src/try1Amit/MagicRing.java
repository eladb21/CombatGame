package try1Amit;

public class MagicRing extends Weapon {
	/*MagicRing weapon*/
	
	//Ctor
	public MagicRing(int x, int y) {
		super(x, y, MAGICRING);
		this.changeName("magicRing");
		// TODO Auto-generated constructor stub
	}
	
	//Cctor
	public MagicRing(Weapon cwpn) {
		super(cwpn);
		// TODO Auto-generated constructor stub
	}
	
	
	//override toString method
	@Override
    public String toString() { 
        return "    (O)    "; 
    }

}
