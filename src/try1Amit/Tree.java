package try1Amit;

public class Tree extends Entity {
	/*Tree class*/

	//Ctor
	public Tree(int x, int y) {
		super(x, y);
		this.changeName("Tree");
		// TODO Auto-generated constructor stub
	}
	
	//Cctor
	public Tree(Tree ct) {
		super(ct.getX(), ct.getY());
		this.changeName("Tree");
	}

	//Override toString method 
	@Override
    public String toString() { 
        return "    ^Y^    "; 
    }
	
}
