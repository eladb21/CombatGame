package try1Amit;
import Utillities.*;

public abstract class Entity {
	/*Abstract entity class*/
	
	//define wepons
	public static final int SWORD = 0;
	public static final int FIREBALL = 1;
	public static final int MAGICRING = 2;
	Point loc;
	public String Name;
	
	//ctor
	public Entity(int x, int y) {
		this.loc = new Point(x,y);
	}
	
	//ctor
	public Entity(Point p) {
		this.loc = new Point(p.getX(),p.getY());
	}
	
	//change dir (X, Y)
	public void changeX(int x) {
		this.loc.setX(x);
	}
	
	public void changeY(int y) {
		this.loc.setY(y);
	}
	
	//get details (X, Y)
	public int getX() {
		return this.loc.getX();
	}
	
	public int getY() {
		return this.loc.getY();
	}
	
	//change entity name
	public void changeName(String name) {
		this.Name = name;
	}
	
	//set position by prev change functions
	public void setPosition(Point p) {
		this.changeX(p.getX());
		this.changeY(p.getY());
	}
	
}
