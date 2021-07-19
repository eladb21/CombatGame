package try1Amit;

public abstract class Weapon extends Entity {
	//Abstract weapon class 
	private int Wkind;

	//Cotr
	public Weapon(int x, int y, int kind) {
		super(x, y);
		this.Wkind = kind;
		// TODO Auto-generated constructor stub
	}
	
	//Cctor
	public Weapon(Weapon cwpn) {
		super(cwpn.getX(), cwpn.getY());
		this.Wkind = cwpn.draw();
		this.changeName(cwpn.myName());
	}
	
	//Return the weapon kind
	public int draw() {
		return this.Wkind;
	}
	
	//Return weapon name
	public String myName() {
		return this.Name;
	}
	
	
	//check weapons strange and calculate round winner
	public int Stronger(Weapon otr) {
		if(this.Wkind == FIREBALL) {
			if(otr.Wkind == SWORD) {
				return 1;
			}
			else if(otr.Wkind == MAGICRING) {
				return -1;
			}
			else {
				return 0;
			}
		}
		else if(this.Wkind == SWORD) {
			if(otr.Wkind == MAGICRING) {
				return 1;
			}
			else if(otr.Wkind == FIREBALL) {
				return -1;
			}
			else {
				return 0;
			}
		}
		else if(this.Wkind == MAGICRING) {
			if(otr.Wkind == FIREBALL) {
				return 1;
			}
			else if(otr.Wkind == SWORD) {
				return -1;
			}
		}
		return 0;
	}

}
