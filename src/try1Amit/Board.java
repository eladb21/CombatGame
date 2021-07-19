package try1Amit;
import java.util.Random;

import Utillities.*;

public class Board {
	/*Board class - main game owner entity/*/
	
	private Entity gameBoard[][];
	public Board() {
		this.gameBoard = new Entity[10][10];
		for(int x = 0; x < 10 ; x++) {
			for(int y = 0; y < 10 ; y++) {
				this.gameBoard[x][y] = null;
			}
		}
	}
	
	
	//print the board for user by toSring
	public void printBoard() {
		for(int x = 0; x < 10 ; x++) {
			for(int y = 0; y < 10 ; y++) {
				if(this.gameBoard[x][y] == null) {
					System.out.print("   [   ]   ");
				}
				
				if(this.gameBoard[x][y] instanceof Sword) {
					System.out.print(((Sword)this.gameBoard[x][y]).toString());
				}
				
				if(this.gameBoard[x][y] instanceof Tree) {
					System.out.print(((Tree)this.gameBoard[x][y]).toString());
				}
				
				if(this.gameBoard[x][y] instanceof Player) {
					System.out.print(((Player)this.gameBoard[x][y]).toString());
				}
				
				if(this.gameBoard[x][y] instanceof MagicRing) {
					System.out.print(((MagicRing)this.gameBoard[x][y]).toString());
				}
				
				if(this.gameBoard[x][y] instanceof FireBall) {
					System.out.print(((FireBall)this.gameBoard[x][y]).toString());
				}
			}
			System.out.println();
			System.out.println();
		}
	}
	
	//add entity to area
	public void addEntity(Entity e, Point p) {
		this.gameBoard[p.getX()][p.getY()] = e;
	}
	
	//delete entity from area
	public void deleteEntity(Point loc) {
		this.gameBoard[loc.getX()][loc.getY()] = null;
	}
	
	//get entity from area
	public Entity getEntity(Point loc) {
		return this.gameBoard[loc.getX()][loc.getY()];
	}
	
	//check for empty area
	public boolean isEmpty(Point loc) {
		//System.out.println("\n" + loc.getX());
		//System.out.println("\n" + loc.getY());
		return this.gameBoard[loc.getX()][loc.getY()] == null;
	}
	
	//test if specific point are on board
	public boolean isInBoard(Point loc) {
		return loc.getX() >= 0 && loc.getX() < 10 && loc.getY() >= 0 && loc.getY() < 10;
	}
	
	//set new player on board
	public Player setPlayer(Point loc,String name) {
		if(isEmpty(loc) && isInBoard(loc)) {
			this.gameBoard[loc.getX()][loc.getY()] = new Player(loc, name);
			return (Player)this.gameBoard[loc.getX()][loc.getY()];
		}
		return null;
	}
	
	//change position of player
	public Player setPosition(Point p, Player plr) {
		if(isInBoard(p) && isEmpty(p)) {
			this.deleteEntity(plr.loc);
			plr.changeX(p.getX());
			plr.changeY(p.getY());
			this.addEntity(plr, p);
			return plr;
		}
		return null;
	}
	
	//find legal place
	public Point findPlace() {
		Random rn = new Random();
		int x;
		int y;
		Point loc = new Point();
		do {
			x = rn.nextInt(10) % 10;
			y = rn.nextInt(10) % 10;
			loc.setX(x);
			loc.setY(y);
		} 
		while (!isEmpty(loc));
		return loc;
	}
	
	//init board by teacher rules
	public Player[] initBoard(int players, Player[] arr) {
		Tree t;
		Weapon w;
		Point loc;
		Player plr;
		for(int i = 0; i < 3; i++) {
			loc = this.findPlace();
			t = new Tree(loc.getX(),loc.getY());
			this.addEntity(t, loc);
			
			loc = this.findPlace();
			if(i == Entity.FIREBALL) {
				w = new FireBall(loc.getX(),loc.getY());
				this.addEntity(w, loc);
				loc = this.findPlace();
				w = new FireBall(loc.getX(),loc.getY());
				this.addEntity(w, loc);
			}
			
			else if(i == Entity.MAGICRING) {
				w = new MagicRing(loc.getX(),loc.getY());
				this.addEntity(w, loc);
				loc = this.findPlace();
				w = new MagicRing(loc.getX(),loc.getY());
				this.addEntity(w, loc);
			}
			
			else {
				w = new Sword(loc.getX(),loc.getY());
				this.addEntity(w, loc);
				loc = this.findPlace();
				w = new Sword(loc.getX(),loc.getY());
				this.addEntity(w, loc);
			}
		}
		
		for(int i = 0; i < players; i++) {
			loc = this.findPlace();
			plr = new Player(loc.getX(),loc.getY(),"P " + (i + 1));
			arr[i] = plr;
			this.addEntity(plr, loc);
		}
		
		return arr;
		
	}
	
}
