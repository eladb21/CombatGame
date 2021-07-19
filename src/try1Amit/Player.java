package try1Amit;
import Utillities.*;

public class Player extends Entity {
	/*Player class*/
	
	//weapons queue
	Queue weaponsQueue = new Queue(6);
	
	//Ctor
	public Player(int x, int y, String name) {
		super(x, y);
		this.changeName(name);
		// TODO Auto-generated constructor stub
	}
	
	
	//Ctor
	public Player(Point loc, String name) {
		super(loc.getX(), loc.getY());
		this.changeName(name);
	}
	
	//Cctor
	public Player(Player cgmr) {
		super(cgmr.getX(),cgmr.getY());
		this.changeName(cgmr.Name);
	}
	
	//enqueue weapon into queue
	public boolean addWeapon(Weapon w) {
		return this.weaponsQueue.Enqueue(w);
	}
	
	//dequeue weapon from queue
	public Weapon getWeapon() {
		return this.weaponsQueue.Dequeue();
	}
	
	//Override toString weapon
	@Override
    public String toString() { 
        return "    " + this.Name + "    "; 
    }

}
